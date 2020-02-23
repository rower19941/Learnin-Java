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
    private static Window window;


    public static void main(String[] args) throws IOException {
        window = new Window();
        window.setVisible(true);
        window.createWindowForNickname();
        socket = new Socket(InetAddress.getLocalHost(), 8080);
        System.out.println("Подключение прошло успешно");
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ReadMsg readMsg = new ReadMsg();
        readMsg.start();
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


    public static DateFormat getClientTime() {
        return dateFormat;
    }

    public static Window getWindow() {
        return window;
    }

    public static void WriteMsg(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (Exception e) {
            System.out.println("Проблемы с отправкой сообщения подключения");;
        }
    }
}
