import java.util.Date;

public class WriteMsg extends Thread {
    private String name;
    private String text;

    WriteMsg(String name, String text) {

        this.name = name;
        this.text = text;
    }

    public void run() {
        try {
            for (ServerConnection sc : Server.getServerConnections()) {
                sc.getOut().write(name + ": " + text + "\n");
                sc.getOut().flush();
            }
            Server.addToHistory(name + ": " + text);
        } catch (Exception e) {
            System.out.println(Server.getServerTime().format(new Date()) + "Проблемы с отправкой сообщения");
        }
    }
}
