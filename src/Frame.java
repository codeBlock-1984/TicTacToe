import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame(String t, String l) {
        super(t);
        JLabel emptyLabel = new JLabel(l);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(400, 400);
        setResizable(false);
    }

    public void showFrame() {
        this.setVisible(true);
    }

    public void hideFrame() {
        this.setVisible(false);
    }
}
