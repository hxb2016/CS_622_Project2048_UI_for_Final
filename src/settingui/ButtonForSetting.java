package settingui;

import javax.swing.*;
/**
 * purpose of this class is to create a Button For Setting
 *
 * Author: Xiaobing Hou
 * Date: 02/27/2022
 * Course: CS-622
 */
public class ButtonForSetting extends JButton {
    public ButtonForSetting(Icon icon) {
        super(icon);
    }

    public static ButtonForSetting getButton(Icon icon){
        ButtonForSetting button;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            button=new ButtonForSetting(icon);
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ex) {
            button=new ButtonForSetting(icon);
            ex.printStackTrace();
        }
        return button;
    }
}
