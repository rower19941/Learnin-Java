package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;

public class WriteMsg extends Thread {
    public void run() {
        try {
            BufferedWriter bufferedWriter = Client.getOut();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String text = reader.readLine();
                bufferedWriter.write(text + "\n");
                bufferedWriter.flush();
                if (text.equals("stop")) break;
            }
            bufferedWriter.flush();
            reader.close();
            Client.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
