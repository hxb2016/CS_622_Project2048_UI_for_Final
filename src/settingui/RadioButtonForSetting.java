package settingui;

import tool.MouseListenerForChangeColor;

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
        switch(text){
            case "left" -> setName("photo");
            case "center" -> setName("best");
            case "right" -> setName("last");
        }
        this.setName(text);
        this.setFont(new Font("Times New Roman", Font.ITALIC, 16));

        MouseListenerForChangeColor<RadioButtonForSetting> mouseListener = new MouseListenerForChangeColor<>(this);
        mouseListener.setListener(Color.BLACK, new Color(18, 150, 219));
    }

}
