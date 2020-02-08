package root.server.client;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private static Socket socket;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static volatile boolean isOnline;

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
        while (true) {
            if (!isOnline) {
                socket.close();
                in.close();
                out.close();
            }
        }
    }

    public static void setIsOnline(boolean isOnline) {
        isOnline = isOnline;
    }

    public static BufferedReader getIn() {
        return in;
    }

    public static BufferedWriter getOut() {
        return out;
    }
}
