import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame {

    private JTextArea outputTextArea = new JTextArea(17, 89);
    private JTextArea inputTextArea = new JTextArea(5, 89);
    private JLabel outLabel = new JLabel("Окно чата");
    private JLabel inLabel = new JLabel("Поле ввода");
    private JButton button = new JButton("Отправить");

    public Window() {
        super("TextChat");
        this.setBounds(100, 100, 1000, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        outputTextArea.setLineWrap(true);
        inputTextArea.setLineWrap(true);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());

        container.add(outLabel);
        container.add(outputTextArea);
        container.add(inLabel);
        container.add(inputTextArea);
        button.addActionListener(new ButtonEvent());
        container.add(button);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Client.WriteMsg("Server close this connection please 4815162342");
                super.windowClosing(e);
            }
        });

    }


    class ButtonEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String text = inputTextArea.getText();
            inputTextArea.setText("");
            Client.WriteMsg(text);
        }
    }

    public void createWindowForNickname() {
        NickNameEnterWindow nickNameEnterWindow = new NickNameEnterWindow();
    }

    public JTextArea getOutputTextArea() {
        return outputTextArea;
    }

    public JTextArea getInputTextArea() {
        return inputTextArea;
    }
}
