import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }

    class ButtonEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Client.WriteMsg();
        }
    }

    public JTextArea getOutputTextArea() {
        return outputTextArea;
    }

    public JTextArea getInputTextArea() {
        return inputTextArea;
    }
}
