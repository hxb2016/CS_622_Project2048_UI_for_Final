package mainui;

import game2048_test.App;
import profileui.TextButtonForProfile;
import tool.MouseListenerForChangeColor;
import tool.UpdateTimerPane;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Purpose of this class is to create a timer pane
 *
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class GameTimerPane extends JPanel {
    private final JLabel second = new JLabel("0 s", SwingConstants.CENTER);
    private final ImageIcon timerImage = new ImageIcon("src" + File.separator + "image" + File.separator + "timer.png");
    public boolean timerHideOrShow = true;

    public GameTimerPane() {
        JPanel timerPane = new JPanel();
        timerPane.setOpaque(false);
        timerPane.setLayout(new BorderLayout());

        second.setFont(new Font("Times New Roman", Font.BOLD, 30));

        timerPane.add(second, BorderLayout.CENTER);

        add(timerPane);

    }
    /**
     * Purpose of this method is to set the number into timer pane
     */
    public void setSecond(String second) {
        this.second.setText(second);
    }

    /**
     * Purpose of this method is to set the image into timer pane
     */
    public void timerHideOrShow() {
        if (timerHideOrShow) {
            second.setText("");
            second.setIcon(timerImage);
        } else {
            if(App.ifEnd){
                second.setText(App.currentUser.currentTakeTime + " s");
            }else{
                second.setText(UpdateTimerPane.second + " s");
            }
            second.setIcon(null);
        }
        timerHideOrShow = !timerHideOrShow;
    }
}
