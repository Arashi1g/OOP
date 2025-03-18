package buttoninheritance;


import java.awt.*;
import javax.swing.JButton;

public class ButtonUI extends JButton {

    public ButtonUI(String text) {
        super(text);
        setPreferredSize(new Dimension(150, 100));
    }
}
