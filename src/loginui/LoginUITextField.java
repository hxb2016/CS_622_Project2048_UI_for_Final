package loginui;

import tool.TextFieldForGame;

import javax.swing.*;
import java.awt.*;

/**
 * purpose of this class is to create text box component for typing from users
 * <p>
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class LoginUITextField extends TextFieldForGame {
    public LoginUITextField() {
        super();
        setPreferredSize(new Dimension(200, 30));
    }


    /**
     * purpose of this method is to get a TextField which has style of the current system
     */
    public static LoginUITextField getLoginUITextField() {
        LoginUITextField loginUITextField;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            loginUITextField = new LoginUITextField();
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ex) {
            loginUITextField = new LoginUITextField();
            ex.printStackTrace();
        }
        return loginUITextField;
    }
}
