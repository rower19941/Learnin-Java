import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NickNameEnterWindow extends JFrame {

    JTextArea nickNameInputArea;
    JLabel nickNameLable;
    JButton button;

    public NickNameEnterWindow() {
        super("Введите никнейм");

        nickNameInputArea = new JTextArea(1, 2);
        nickNameLable = new JLabel("Ваш никнейм: ");
        nickNameInputArea.setLineWrap(true);
        button = new JButton("Ок");

        this.setBounds(200, 200, 300, 100);
        this.setVisible(true);
        this.toFront();

        Container nickNameContainer = this.getContentPane();
        nickNameContainer.setLayout(new FlowLayout());

        nickNameContainer.add(nickNameLable);
        nickNameContainer.add(nickNameInputArea);
        button.addActionListener(new ButtonEvent());
        nickNameContainer.add(button);

    }

    class ButtonEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String text = nickNameInputArea.getText();
            nickNameInputArea.setText("");
            Client.WriteMsg(text);
            dispose();
        }
    }
}
