import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ServerConnection extends Thread{
    private String name = null;
    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private volatile boolean isOnline;

    ServerConnection(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            isOnline = true;
            while (name == null) {
                String text = in.readLine();
                if (text != null) {
                    name = text;

                }
            }
        } catch (Exception e) {
            System.out.println(Server.getServerTime().format(new Date()) + "Ошибка в создании нового соединения");
            e.printStackTrace();
        }
    }

    public void run() {

        try {
            for (String text : Server.getHistory()) {
                this.out.write(text + "\n");
                this.out.flush();
            }
            for (ServerConnection sc : Server.getServerConnections()) {
                sc.getOut().write("Пользователь " + this.name + " подключился" + "\n");
                Server.addToHistory("Пользователь " + this.name + " подключился");
                sc.getOut().flush();
            }
        } catch (Exception e ) {
            System.out.println(Server.getServerTime().format(new Date()) + "Проблемы с объявлением нового пользователя");
        }
        ReadMsg readMsg = new ReadMsg(this);
        readMsg.start();
        while (isOnline) {}
        closeConnection();
    }

    private void closeConnection() {
        try {
            System.out.println(Server.getServerTime().format(new Date()) + name + " отключился");
            try {
                for (ServerConnection sc : Server.getServerConnections()) {
                    sc.getOut().write("Пользователь " + this.name + " отключился" + "\n");
                    Server.addToHistory("Пользователь " + this.name + " отключился");
                    sc.getOut().flush();
                }
            } catch (Exception e ) {
                System.out.println(Server.getServerTime().format(new Date()) + "Проблемы с прощанием с пользователем");
            }
            in.close();
            out.close();
            clientSocket.close();
            Server.getServerConnections().remove(this);
        } catch (Exception e) {
            System.out.println(Server.getServerTime().format(new Date()) + "Проблема с закрытием подключения");
        }
    }

    BufferedWriter getOut() {
        return out;
    }

    BufferedReader getIn() {
        return in;
    }

    void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    String getConnectionName() {
        return this.name;
    }
}
