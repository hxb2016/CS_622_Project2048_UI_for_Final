package tool;

import javax.swing.*;
import java.awt.*;

public abstract class RectangleButton extends JButton {
    public RectangleButton(String text, ImageIcon icon) throws Exception {
        if (text == null && icon == null) {
            throw new Exception("The text and icon can not be empty at the same time");
        } else {
            if (text != null) {
                setText(text);
                setForeground(Color.WHITE);
            } else {
                setIcon(icon);
            }
            setMargin(new Insets(2, 10, 2, 10));
            setFocusPainted(false);
            setBackground(new Color(18, 150, 219));
        }


        new KeyBoardListener<>(this);
    }
}
