package settingui;

import game2048_test.App;
import io.SaveUsersData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * purpose of this class is to create a SettingUI interface
 * <p>
 * Author: Xiaobing Hou
 * Date: 02/27/2022
 * Course: CS-622
 */
public class SettingUI extends JDialog {
    public JButton leftSize;
    public JButton rightSize;
    public JButton leftColor;
    public JButton rightColor;
    public JLabel sizeLabel;
    public JLabel colorLabel;
    public SamplePanel photoAndTimer;
    public SamplePanel bestRecord;
    public SamplePanel lastRecord;
    public List<List<JRadioButton>> jRadioButtonList;
    private static SettingUI settingUI = null;


    private SettingUI(JFrame owner) {
        super(owner);
        this.setSize(600, 350);
        this.setLocationRelativeTo(null);
        this.setTitle("Setting");
        this.setLayout(new GridLayout(3, 1));
        this.setModal(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    SaveUsersData.saveUsersData(App.usersData, App.userDataPath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JPanel photoAndRecordPanel = new JPanel();
        photoAndRecordPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
        photoAndRecordPanel.setLayout(new GridLayout(1, 3, 5, 0));

        photoAndTimer = new SamplePanel("Photo panel location");
        bestRecord = new SamplePanel("Best panel location");
        lastRecord = new SamplePanel("Last panel location");

        jRadioButtonList = new ArrayList<>();
        jRadioButtonList.add(photoAndTimer.jRadioButtonList);
        jRadioButtonList.add(bestRecord.jRadioButtonList);
        jRadioButtonList.add(lastRecord.jRadioButtonList);

        photoAndRecordPanel.add(photoAndTimer);
        photoAndRecordPanel.add(bestRecord);
        photoAndRecordPanel.add(lastRecord);
        this.add(photoAndRecordPanel);

        JPanel gameSize = new JPanel();
        gameSize.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));

        JLabel gameSize_title = new JLabel("Change game size", SwingConstants.CENTER);
        gameSize_title.setFont(new Font("Times New Roman", Font.BOLD, 20));
        gameSize_title.setPreferredSize(new Dimension(this.getWidth(), 30));

        ImageIcon left = new ImageIcon(App.iconsLocation + "left.png");
        ImageIcon right = new ImageIcon(App.iconsLocation + "right.png");
        leftSize = ButtonForSetting.getButton(left);
        rightSize = ButtonForSetting.getButton(right);

        sizeLabel = new JLabel("4", SwingConstants.CENTER);
        sizeLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));

        gameSize.add(gameSize_title);
        gameSize.add(leftSize);
        gameSize.add(sizeLabel);
        gameSize.add(rightSize);
        this.add(gameSize);

        JPanel uiColor = new JPanel();
        uiColor.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        JLabel uiColor_title = new JLabel("Change background color", SwingConstants.CENTER);
        uiColor_title.setFont(new Font("Times New Roman", Font.BOLD, 20));
        uiColor_title.setPreferredSize(new Dimension(this.getWidth(), 30));

        leftColor = ButtonForSetting.getButton(left);
        rightColor = ButtonForSetting.getButton(right);

        colorLabel = new JLabel();
        colorLabel.setOpaque(true);
        colorLabel.setPreferredSize(new Dimension(45, 45));
        colorLabel.setBackground(Color.WHITE);
        colorLabel.setBorder(new LineBorder(Color.BLACK, 1));

        uiColor.add(uiColor_title);
        uiColor.add(leftColor);
        uiColor.add(colorLabel);
        uiColor.add(rightColor);
        this.add(uiColor);
    }

    /**
     * Purpose of this method is to get setting interface and make sure that you just get only one object
     */
    public static SettingUI getSettingUI(JFrame owner) {
        if (settingUI == null) {
            settingUI = new SettingUI(owner);
            SettingController.setController(settingUI);
        }
        return settingUI;

    }
}
