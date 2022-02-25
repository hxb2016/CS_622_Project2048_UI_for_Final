package tool;

import javax.swing.*;
import java.awt.*;

public abstract class RectangleButton extends JButton {
    public RectangleButton(String text, ImageIcon icon) {
        if (text != null) {
            setText(text);
            setForeground(Color.WHITE);
        } else {
            setIcon(icon);
        }
        setMargin(new Insets(2, 10, 2, 10));
        setFocusPainted(false);
        setBackground(new Color(18, 150, 219));

        KeyBoardListener<RectangleButton> keyBoardListener = new KeyBoardListener<>(this);
        keyBoardListener.setListener();
    }
}
