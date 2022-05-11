package profileui;

import tool.MouseListenerForChangeColor;
import tool.TextButton;

import java.awt.*;

/**
 * Purpose of this class is to create a Button object for profileUI
 * Author: Xiaobing Hou
 * Date: 02/24/2022
 * Course: CS-622
 */
public class TextButtonForProfile extends TextButton {
    public TextButtonForProfile(String text) {
        super(text);
        setFont(new Font("Times New Roman", Font.BOLD, 18));

        new MouseListenerForChangeColor<>(this, Color.BLACK);
    }
}
