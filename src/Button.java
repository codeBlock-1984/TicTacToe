import javax.swing.*;

public class Button extends JButton {
    public Button(String text) {
        super(text);
    }

    public void toggleButton() {
        if (this.getText() == "X") {
            this.setText("O");
        } else {
            this.setText("X");
        }
    }

    public String getButtonValue() {
        return this.getText();
    }
}
