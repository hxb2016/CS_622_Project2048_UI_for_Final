package profileui;

import tool.TextFieldForGame;

import javax.swing.*;
import java.awt.*;

/**
 * purpose of this class is to create text box component for typing from users
 * <p>
 * Author: Xiaobing Hou
 * Date: 02/25/2022
 * Course: CS-622
 */
public class ProfileTextField extends TextFieldForGame {
    public ProfileTextField(String message) {
        super(message);
        setPreferredSize(new Dimension(300, 30));
    }
    /**
     * purpose of this method is to get a TextField which has style of the current system
     */
    public static ProfileTextField getProfileTextField(String message) {
        ProfileTextField profileTextField;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            profileTextField = new ProfileTextField(message);
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ex) {
            profileTextField = new ProfileTextField(message);
            ex.printStackTrace();
        }
        return profileTextField;
    }
}
