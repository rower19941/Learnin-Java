package root.server.server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class ReadAndWrite extends Thread {
    public void run() {
        try {
            BufferedReader reader = Server.getIn();
            BufferedWriter bufferedWriter = Server.getOut();
            while (true) {
                String text = reader.readLine();
                if (text.equals("exit")) break;
                System.out.print(text + "\n");
                bufferedWriter.write(text + "\n");
                bufferedWriter.flush();
            }
            reader.close();
            bufferedWriter.close();
            Server.setOnlineStatus(false);
        } catch (Exception e) {}
    }
}
