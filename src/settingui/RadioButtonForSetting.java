package settingui;

import javax.swing.*;
import java.awt.*;
/**
 * purpose of this class is to create a RadioButton For Setting
 *
 * Author: Xiaobing Hou
 * Date: 02/27/2022
 * Course: CS-622
 */
public class RadioButtonForSetting extends JRadioButton {
    public RadioButtonForSetting(String text) {
        super(text);
        this.setFocusPainted(false);
        this.setFont(new Font("Times New Roman", Font.ITALIC, 16));
    }

}
