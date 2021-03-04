package message;

import javax.swing.*;
import java.awt.*;

public class Message extends JDialog {

    public Message( JFrame parent, String title, String message ) {
        super(parent, title, true);

        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);

        // add to panel
        panel.add(label);
        getContentPane().add(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void render(JFrame parent, String title, String message) {
        new Message(parent, title, message);
    }
}