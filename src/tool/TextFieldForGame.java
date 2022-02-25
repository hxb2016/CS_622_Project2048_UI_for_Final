package tool;

import javax.swing.*;
import java.awt.*;

/**
 * purpose of this class is to create text box component for typing from users
 * <p>
 * Author: Xiaobing Hou
 * Date: 02/25/2022
 * Course: CS-622
 */
public abstract class TextFieldForGame extends JTextField {

    public TextFieldForGame(String message) {
        setForeground(new Color(18, 150, 219));
        setFont(new Font("Times New Roman", Font.BOLD, 18));
        setEditable(false);
        setText(message);
    }
    public TextFieldForGame() {
        setForeground(new Color(18, 150, 219));
        setFont(new Font("Times New Roman", Font.BOLD, 18));
    }
}
