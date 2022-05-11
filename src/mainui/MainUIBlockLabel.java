package mainui;

import tool.KeyBoardListener;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * purpose of this class is to create single label by given round size and text size
 *
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class MainUIBlockLabel extends JLabel {

    private final int roundSize;
    private final Color color2 = new Color(238, 228, 218);
    private final Color color4 = new Color(238, 225, 201);
    private final Color color8 = new Color(243, 178, 122);
    private final Color color16 = new Color(246, 153, 101);
    private final Color color32 = new Color(247, 124, 95);
    private final Color color64 = new Color(247, 95, 59);
    private final Color color128 = new Color(237, 208, 115);
    private final Color color256 = new Color(237, 204, 98);
    private final Color color512 = new Color(237, 201, 80);
    private final Color color1024 = new Color(237, 197, 63);
    private final Color color2048 = new Color(237, 190, 50);

    private final Color numberColor1 = new Color(119, 110, 101);
    private final Color numberColor2 = Color.WHITE;

    public MainUIBlockLabel(int roundSize, int textSize) {
        this.roundSize = roundSize;
        setHorizontalAlignment(CENTER);
        setOpaque(true);
        setForeground(numberColor1);
        setFont(new Font("Times New Roman", Font.BOLD, textSize));

        new KeyBoardListener<>(this);
    }

    public void paint(Graphics g) {
        RoundRectangle2D.Double rect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), roundSize, roundSize);
        g.setClip(rect);
        super.paint(g);
    }

    /**
     * purpose of this method is to set background and text color basis the number of the label
     */
    public void setColor() {
        int blockNum = getText().equals("") ? 0 : Integer.parseInt(getText());
        switch (blockNum) {
            case 2 -> {
                setBackground(color2);
                setForeground(numberColor1);
            }
            case 4 -> {
                setBackground(color4);
                setForeground(numberColor1);
            }
            case 8 -> {
                setBackground(color8);
                setForeground(numberColor1);
            }
            case 16 -> {
                setBackground(color16);
                setForeground(numberColor2);
            }
            case 32 -> {
                setBackground(color32);
                setForeground(numberColor2);
            }
            case 64 -> {
                setBackground(color64);
                setForeground(numberColor2);
            }
            case 128 -> {
                setBackground(color128);
                setForeground(numberColor2);
            }
            case 256 -> {
                setBackground(color256);
                setForeground(numberColor2);
            }
            case 512 -> {
                setBackground(color512);
                setForeground(numberColor2);
            }
            case 1024 -> {
                setBackground(color1024);
                setForeground(numberColor2);
            }
            case 2048 -> {
                setBackground(color2048);
                setForeground(numberColor2);
            }
            default -> {
                setBackground(new Color(205, 193, 180));
            }
        }
    }
}
