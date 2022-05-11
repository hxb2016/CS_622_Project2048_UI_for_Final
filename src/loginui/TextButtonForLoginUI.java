package loginui;

import tool.MouseListenerForChangeColor;
import tool.TextButton;

import java.awt.*;

/**
 * purpose of this class is to create button for LoginUI
 * <p>
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class TextButtonForLoginUI extends TextButton {
    public TextButtonForLoginUI(String text) {
        super(text);
        setFont(new Font("Times New Roman", Font.PLAIN, 12));

        new MouseListenerForChangeColor<>(this, new Color(18, 150, 219));
    }
}
