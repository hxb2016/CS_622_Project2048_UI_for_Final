package loginui;

import tool.LabelForGame;

import javax.swing.*;
import java.awt.*;

/**
 * Purpose of this class is to create label for user information title in LoginUI
 * <p>
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class LoginUILabel extends LabelForGame {
    public LoginUILabel(JDialog jDialog, String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        setPreferredSize(new Dimension(jDialog.getWidth(), 30));
    }
}
