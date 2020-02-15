import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

public class ButtonPanel extends JPanel implements ActionListener {
    private static Button button1 = new Button("");
    private static Button button2 = new Button("");
    private static Button button3 = new Button("");
    private static Button button4 = new Button("");
    private static Button button5 = new Button("");
    private static Button button6 = new Button("");
    private static Button button7 = new Button("");
    private static Button button8 = new Button("");
    private static Button button9 = new Button("");
    private static String nextPlayer = "X";
    private static boolean gameOn = true;
    public static Button[] buttons = { button1, button2, button3, button4, button5, button6, button7, button8, button9 };
    public static String[] buttonValues = { "", "", "", "", "", "", "", "", "" };
    public ButtonPanel() {
        super();
        setLayout(new GridLayout(3, 3));
        addButtons();
    }

    protected void addButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setActionCommand(Integer.toString(i));
            buttons[i].addActionListener(this);
            this.add(buttons[i]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (!gameOn) {
            return;
        }
        JButton currentButton = (JButton) e.getSource();
        String currentPlayer = getNextPlayer();
        int clickedButtonIndex = Integer.parseInt(currentButton.getActionCommand());
        buttons[clickedButtonIndex].setText(currentPlayer);
        getButtonValues();
        boolean winnerExists = getWinner();
        System.out.println(winnerExists);
        if (winnerExists) {
            try {
                DetailBar.endGame();
            } catch (Exception error) {
                System.out.println(error.getMessage());
            }
            gameOn = false;
            System.out.println(getNextPlayer()+" wins!");
        } else {
            gameOn = moveExists();
            if (gameOn) {
                setNextPlayer();
                DetailBar.updatePlayer();
                if (nextPlayer == "O") {
                    int nextMove = analyzeMove();
                    try {
                        click(buttons[nextMove], 50);
                    } catch (Exception error) {
                        System.out.println(error.getMessage());
                    }
                }
            } else {
                try {
                    DetailBar.tieGame();
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }
        }

        System.out.println(currentButton.getText());
        System.out.println(currentButton.getActionCommand());
    }

    private static void setNextPlayer() {
        if (nextPlayer == "X") {
            nextPlayer = "O";
        } else {
            nextPlayer = "X";
        }
    }

    public void click(Button button, int millis) throws AWTException {
        Point p = button.getLocationOnScreen();
        Robot r = new Robot();
        r.mouseMove(p.x + button.getWidth() / 2, p.y + button.getHeight() / 2);
        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static String getNextPlayer() {
        return nextPlayer;
    }

    public static void getButtonValues() {
        for (int i = 0; i < 9; i++) {
            String buttonValue = buttons[i].getText();
            buttonValues[i] = buttonValue;
            System.out.println(buttonValues[i]);
        }

    }

    private static boolean moveExists() {
        boolean moveExists = false;
        for(String value : buttonValues) {
            if (value == "") {
                moveExists = true;
            }
        }
        return moveExists;
    }

    public static boolean getWinner() {
        int[][] check = {
                { 0, 1, 2 },
                { 3, 4, 5 },
                { 6, 7, 8 },
                { 0, 3, 6 },
                { 1, 4, 7 },
                { 2, 5, 8 },
                { 0, 4, 8 },
                { 2, 4, 6 }
        };
        boolean winnerExists = false;
        for (int i = 0; i < 8; i++) {
            if (buttonValues[check[i][0]] != "" && buttonValues[check[i][0]] == buttonValues[check[i][1]] && buttonValues[check[i][0]] == buttonValues[check[i][2]]) {
                System.out.println(buttonValues[check[i][0]]);
                System.out.println("Wins!");
                winnerExists = true;
            }
        }
        return winnerExists;
    }

    public static int analyzeMove() {
        int[][] check = {
                { 0, 1, 2 },
                { 3, 4, 5 },
                { 6, 7, 8 },
                { 0, 3, 6 },
                { 1, 4, 7 },
                { 2, 5, 8 },
                { 0, 4, 8 },
                { 2, 4, 6 }
        };
        int nextMoveIndex = 100;
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (buttonValues[check[i][0]] == "O" && buttonValues[check[i][0]] == buttonValues[check[i][1]] && buttonValues[check[i][2]] != "X") {
                nextMoveIndex = check[i][2];
            } else if (buttonValues[check[i][1]] == "O" && buttonValues[check[i][1]] == buttonValues[check[i][2]] && buttonValues[check[i][0]] != "X") {
                nextMoveIndex = check[i][0];
            }
        }
        if (nextMoveIndex != 100) {
            return nextMoveIndex;
        }
        for (int i = 0; i < 8; i++) {
            if (buttonValues[check[i][0]] == "X" && buttonValues[check[i][0]] == buttonValues[check[i][1]] && buttonValues[check[i][2]] != "O") {
                nextMoveIndex = check[i][2];
            } else if (buttonValues[check[i][1]] == "X" && buttonValues[check[i][1]] == buttonValues[check[i][2]] && buttonValues[check[i][0]] != "O") {
                nextMoveIndex = check[i][0];
            }
        }
        if (nextMoveIndex != 100) {
            return nextMoveIndex;
        }
        for (int i = 0; i < 8; i++) {
            if (buttonValues[check[i][0]] == "O" && buttonValues[check[i][1]] == "") {
                nextMoveIndex = check[i][1];
            } else if (buttonValues[check[i][0]] == "O" && buttonValues[check[i][2]] == "") {
                nextMoveIndex = check[i][2];
            } else if (buttonValues[check[i][1]] == "O" && buttonValues[check[i][2]] == "") {
                nextMoveIndex = check[i][2];
            } else if (buttonValues[check[i][1]] == "O" && buttonValues[check[i][0]] == "") {
                nextMoveIndex = check[i][0];
            } else if (buttonValues[check[i][2]] == "O" && buttonValues[check[i][1]] == "") {
                nextMoveIndex = check[i][1];
            } else if (buttonValues[check[i][2]] == "O" && buttonValues[check[i][0]] == "") {
                nextMoveIndex = check[i][0];
            }
        }
        if (buttonValues[4] == "") {
            nextMoveIndex = 4;
        }
        for (int i = 0; i < 9; i++) {
            if (buttonValues[i] == "") {
                nextMoveIndex = i;
            }
        }
        return nextMoveIndex;
    }
}
