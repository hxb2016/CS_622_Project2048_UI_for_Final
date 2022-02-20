package mainui;

import javax.swing.*;
import java.awt.*;

/**
 * Purpose of this class is to create a timer pane
 *
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class GameTimerPane extends JPanel {
    private final JLabel second = new JLabel("0 s", SwingConstants.CENTER);

    public GameTimerPane() {
        JPanel timerPane = new JPanel();
        timerPane.setLayout(new BorderLayout());

        this.second.setFont(new Font("Times New Roman", Font.BOLD, 30));

        timerPane.add(this.second, BorderLayout.CENTER);

        this.add(timerPane);
    }
    /**
     * Purpose of this method is to set the number into timer pane
     */
    public void setSecond(String second) {
        this.second.setText(second);
    }
}
