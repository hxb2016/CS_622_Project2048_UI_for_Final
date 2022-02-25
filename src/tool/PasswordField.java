package tool;

import javax.swing.*;
import java.awt.*;
/**
 * purpose of this class is to create PasswordField component
 * <p>
 * Author: Xiaobing Hou
 * Date: 02/24/2022
 * Course: CS-622
 */
public class PasswordField extends JPasswordField {
    private PasswordField(String text) {
        super(text);
        this.setForeground(new Color(18, 150, 219));
        this.setFont(new Font("Times New Roman", Font.PLAIN, 18));
    }

    /**
     * purpose of this method is to get a passwordField which has style of the current system
     */
    public static PasswordField getPasswordField(String text) {
        PasswordField password;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            password = new PasswordField(text);
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            password = new PasswordField(text);
            e.printStackTrace();
        }
        return password;
    }
}
