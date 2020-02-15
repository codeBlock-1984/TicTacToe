import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailBar extends JPanel {
    private static JLabel nextPlayerValue = new JLabel("");
    private static JLabel nextPlayer = new JLabel("Player:");
    private static JPanel gameHistoryPane = new JPanel();
    public DetailBar() {
        super();
        setLayout(new GridLayout(1, 1));
        addDetails();
        updatePlayer();
    }

    public static void updatePlayer() {
        String currentPlayer = ButtonPanel.getNextPlayer();
        nextPlayerValue.setText(currentPlayer);
    }

    public static void endGame() throws MalformedURLException {
        String currentPlayer = ButtonPanel.getNextPlayer();
        nextPlayerValue.setText("");
        nextPlayer.setText(currentPlayer+" wins!");
        if (currentPlayer == "O") {
                URL url = new File("src/yay.gif").toURI().toURL();
                Icon icon = new ImageIcon(url);
                JLabel winnerAnimation = new JLabel(icon);
                gameHistoryPane.add(winnerAnimation);
        } else  {
            URL url = new File("src/squidward.gif").toURI().toURL();
            Icon icon = new ImageIcon(url);
            JLabel winnerAnimation = new JLabel(icon);
            gameHistoryPane.add(winnerAnimation);
        }
    }

    public static void tieGame() throws MalformedURLException {
        nextPlayerValue.setText("");
        nextPlayer.setText("Game Tied! Nice one");
        URL url = new File("src/oops.gif").toURI().toURL();
        Icon icon = new ImageIcon(url);
        JLabel winnerAnimation = new JLabel(icon);
        gameHistoryPane.add(winnerAnimation);
    }

    protected void addDetails() {
        JPanel topSubPanel = new JPanel();
        topSubPanel.setLayout(new GridBagLayout());
        GBLConstraints c = new GBLConstraints(0, 0);
        c.setPadding(20, 40);
        c.setWeight(0, 0.01);
        c.setAnchor(GridBagConstraints.NORTH);
        c.setInsets(new Insets(0, 5, 0, 20));
        topSubPanel.add(nextPlayer, c);
        c.setPosition(1, 0);
        c.setWeight(0, 0.01);
        c.setAnchor(GridBagConstraints.NORTH);
        c.setPadding(10, 40);
        c.setInsets(new Insets(0, 10, 0, 0));
        topSubPanel.add(nextPlayerValue, c);
        c.setPosition(0, 1);
        c.setSize(2, 6);
        c.setFill(GridBagConstraints.BOTH);
        c.setPadding(50, 0);
        c.setWeight(0, 1);
        c.setAnchor(GridBagConstraints.NORTH);
        c.setInsets(new Insets(0, 0, 10, 10));
        topSubPanel.add(gameHistoryPane, c);
        this.add(topSubPanel);
        ButtonPanel.getButtonValues();
        ButtonPanel.getWinner();
        System.out.println(ButtonPanel.buttons);
    }
}
