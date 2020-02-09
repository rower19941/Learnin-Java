import java.io.BufferedReader;
import java.util.Date;

public class ReadMsg extends Thread {
    private ServerConnection serverConnection;
    private BufferedReader in;

    ReadMsg(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
        this.in = serverConnection.getIn();
    }

    public void run() {
        try {
            while (serverConnection.isAlive()) {
                String text = in.readLine();
                if (text != null) {
                    System.out.println(Server.getServerTime().format(new Date()) + serverConnection.getConnectionName() + ": " + text);
                    if (text.equals("stop")) {
                        serverConnection.setOnline(false);
                        break;
                    }
                    WriteMsg writeMsg = new WriteMsg(serverConnection.getConnectionName(), text);
                    writeMsg.start();
                }
            }
//            in.close();
//            out.close();
        } catch (Exception e) {
            System.out.println(Server.getServerTime().format(new Date()) + "Проблемы с чтением сообщения");
        }
    }

}
