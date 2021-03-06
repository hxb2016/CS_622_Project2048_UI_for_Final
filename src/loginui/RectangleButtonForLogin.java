package loginui;

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
public class RectangleButtonForLogin extends RectangleButton {
    public RectangleButtonForLogin(String text, ImageIcon icon) throws Exception {
        super(text, icon);
        if (text != null) {
            setFont(new Font("Times New Roman", Font.BOLD, 18));
        }
    }
}
