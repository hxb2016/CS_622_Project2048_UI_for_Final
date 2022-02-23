package profileui;

import javax.swing.*;
import java.awt.*;

/**
 * purpose of this class is to create text box component for typing from users
 * <p>
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class ProfileTextField extends JTextField {
    public ProfileTextField(String message) {
        this.setPreferredSize(new Dimension(300, 30));
        this.setForeground(new Color(18, 150, 219));
        this.setFont(new Font("Times New Roman", Font.BOLD, 18));
        this.setEditable(false);
        this.setText(message);
    }

    public static ProfileTextField getProfileTextField(String message) {
        ProfileTextField profileTextField = null;
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
