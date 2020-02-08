package root.server.server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket clientSocket;
    private static ServerSocket serverSocket;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static boolean isOnline;

    public static void main(String[] args) {
        try {
            try {
                serverSocket = new ServerSocket(8080);
                System.out.println("Сервер ждет подключния");
                clientSocket = serverSocket.accept();
                System.out.println("Пользователь подключен");
                isOnline = true;
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                ReadAndWrite readAndWrite = new ReadAndWrite();
                System.out.println("Начинаем диалог");
                readAndWrite.start();
                while (isOnline) {}
            } finally {
                in.close();
                out.flush();
                out.close();
                clientSocket.close();
                serverSocket.close();

            }
        } catch (Exception e) {}
    }

    public static BufferedReader getIn() {
        return in;
    }

    public static BufferedWriter getOut() {
        return out;
    }

    public static void setOnlineStatus(boolean isOnline) {
        isOnline = isOnline;
    }
}
