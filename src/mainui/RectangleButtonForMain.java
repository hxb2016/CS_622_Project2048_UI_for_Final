package mainui;

import tool.RectangleButton;

import javax.swing.*;
import java.awt.*;

/**
 * the purpose of this class is to create a button for MainUI
 *
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class RectangleButtonForMain extends RectangleButton {
    public RectangleButtonForMain(String text, ImageIcon icon) {
        super(text, icon);
        if (text != null) {
            setFont(new Font("Times New Roman", Font.BOLD, 16));
        }
    }
}
