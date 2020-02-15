import java.awt.*;

public class GBLConstraints extends GridBagConstraints {
    public GBLConstraints(int gX, int gY) {
        super();
        this.gridx = gX;
        this.gridy = gY;
    }

    public void setPosition(int gX, int gY) {
        this.gridx = gX;
        this.gridy = gY;
    }

    public void setSize(int gW, int gH) {
        this.gridwidth = gW;
        this.gridheight = gH;
    }

    public void setWeight(double wX, double wY) {
        this.weightx = wX;
        this.weighty = wY;
    }

    public void setFill(int fill) {
        this.fill = fill;
    }

    public void setAnchor(int anchor) {
        this.anchor = anchor;
    }

    public void setInsets(Insets insets) {
        this.insets = insets;
    }

    public void setPadding(int iPadX, int iPadY) {
        this.ipadx = iPadX;
        this.ipady = iPadY;
    }
}
