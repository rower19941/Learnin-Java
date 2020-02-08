package root.server.client;
import java.io.BufferedReader;
import java.io.IOException;

public class ReadMsg extends Thread {
    public void run() {
        try {
            BufferedReader reader = Client.getIn();
            while (true) {
                String text = reader.readLine();
                if (text != null) {
                    if (text.equals("stop")) break;
                    System.out.println(text);
                }
            }
            //reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
