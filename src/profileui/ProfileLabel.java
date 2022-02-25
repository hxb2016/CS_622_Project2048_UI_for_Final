package profileui;

import tool.LabelForGame;

import java.awt.*;
/**
 * Purpose of this class is to create a label object for profileUI
 * Author: Xiaobing Hou
 * Date: 02/24/2022
 * Course: CS-622
 */
public class ProfileLabel extends LabelForGame {
    public ProfileLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        this.setPreferredSize(new Dimension(90,30));
    }
}
