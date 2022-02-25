package tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * purpose of this abstract class is to create button have basic style for the game
 *
 * Author: Xiaobing Hou
 * Date: 02/24/2022
 * Course: CS-622
 */
public abstract class TextButton extends JButton {
    public TextButton(String text) {
        this.setText(text);
        this.setFocusPainted(false);

        // This area is used to Create 'create account' and 'start as guest' button
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setForeground(Color.GRAY);

    }
}
