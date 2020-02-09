import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class Server {
    private static final int PORT = 8080;
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private volatile static LinkedList<ServerConnection> serverConnections = new LinkedList<>();
    private static volatile ArrayList<String> history = new ArrayList<>(50);
    private static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss ");

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println(dateFormat.format(new Date()) + "Сервер запущен");
            while (true) {
                clientSocket = serverSocket.accept();
                ServerConnection serverConnection = new ServerConnection(clientSocket);
                System.out.println(dateFormat.format(new Date()) + serverConnection.getConnectionName() + " подключился");
                serverConnections.add(serverConnection);
                serverConnection.start();
            }
        } catch (Exception e) {
            System.out.println(dateFormat.format(new Date()) + "Ошибка в запуске сервера или ожидани подключения");
            e.printStackTrace();
        }
    }

    public static LinkedList<ServerConnection> getServerConnections() {
        return serverConnections;
    }

    public static void addToHistory(String text) {
        if (history.size() == 50) {
            history.remove(0);
        }
        history.add(text);
    }

    public static ArrayList<String> getHistory() {
        return history;
    }

    public static DateFormat getServerTime() {
        return dateFormat;
    }
}
