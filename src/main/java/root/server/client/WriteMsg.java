package root.server.client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class WriteMsg extends Thread {
    public void run() {
        try {
            BufferedWriter bufferedWriter = Client.getOut();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String text = reader.readLine();
                if (text.equals("stop")) break;
                //System.out.println(text);
                bufferedWriter.write(text + "\n");
                bufferedWriter.flush();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            Client.setIsOnline(false);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
