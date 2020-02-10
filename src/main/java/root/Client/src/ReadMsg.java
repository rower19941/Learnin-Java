import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

public class ReadMsg extends Thread {
    public void run() {
        BufferedReader reader = Client.getIn();
        try {
            while (Client.getIsOnline()) {
                String text = reader.readLine();
                if (text != null) {
                    Client.getWindow().getOutputTextArea().append(Client.getClientTime().format(new Date()) + text + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            try {
                reader.close();
            } catch (IOException ex) {}
        }
    }
}
