package tool;

import javax.swing.*;
import java.awt.*;
/**
 * purpose of this abstract class is to create label have basic style for the game
 *
 * Author: Xiaobing Hou
 * Date: 02/24/2022
 * Course: CS-622
 */
public abstract class LabelForGame extends JLabel {
    public LabelForGame(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        this.setFont(new Font("Times New Roman", Font.BOLD, 18));
    }

}
