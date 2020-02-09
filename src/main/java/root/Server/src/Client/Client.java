package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Client {
    private static Socket socket;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static volatile boolean isOnline;
    private static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss ");


    public static void main(String[] args) throws IOException {
        socket = new Socket(InetAddress.getLocalHost(), 8080);
        System.out.println("Подключение прошло успешно");
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ReadMsg readMsg = new ReadMsg();
        WriteMsg writeMsg = new WriteMsg();
        readMsg.start();
        writeMsg.start();
        isOnline = true;
        System.out.println("Укажите ваше имя");
        while (isOnline) {};
    }

    protected static void closeConnection() {
        try {
            in.close();
            out.close();
            socket.close();
            isOnline = false;
        } catch (Exception e) {
            System.out.println("Проблемы с закрытием подключения");
        }
    }

    public static void setIsOnline(boolean isOnline) {
        isOnline = isOnline;
    }

    public static boolean getIsOnline() {
        return isOnline;
    }

    public static BufferedReader getIn() {
        return in;
    }

    public static BufferedWriter getOut() {
        return out;
    }

    public static DateFormat getClientTime() {
        return dateFormat;
    }
}
