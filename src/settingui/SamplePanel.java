package settingui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * purpose of this class is to create a SamplePanel For Setting
 *
 * Author: Xiaobing Hou
 * Date: 02/27/2022
 * Course: CS-622
 */
public class SamplePanel extends JPanel {
    public JRadioButton left;
    public JRadioButton center;
    public JRadioButton right;
    public List<JRadioButton> jRadioButtonList = new ArrayList<>();

    public SamplePanel(String title) {
        this.setLayout(new BorderLayout(0, 2));

        JLabel photoSamplePanel_top = new JLabel(title, SwingConstants.CENTER);
        photoSamplePanel_top.setFont(new Font("Times New Roman", Font.BOLD, 16));

        JPanel photoSamplePanel_bottom = new JPanel();
        photoSamplePanel_bottom.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        left = new RadioButtonForSetting("left");
        center = new RadioButtonForSetting("center");
        right = new RadioButtonForSetting("right");
        photoSamplePanel_bottom.add(left);
        photoSamplePanel_bottom.add(center);
        photoSamplePanel_bottom.add(right);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(left);
        buttonGroup.add(center);
        buttonGroup.add(right);

        jRadioButtonList.add(left);
        jRadioButtonList.add(center);
        jRadioButtonList.add(right);

        this.add(photoSamplePanel_top, BorderLayout.NORTH);
        this.add(photoSamplePanel_bottom, BorderLayout.CENTER);
    }

}
