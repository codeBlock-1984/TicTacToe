import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {
    static Frame myFrame = new Frame("Tic Tac Toe Game", "");
    public static void main(String[] args) {
        myFrame.getContentPane().setLayout(new GridLayout(1, 2));
        myFrame.getContentPane().setBackground(Color.BLUE);
        myFrame.getContentPane().add(new ButtonPanel());
        myFrame.getContentPane().add(new DetailBar());
        myFrame.showFrame();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // myFrame.append()
    }
}
