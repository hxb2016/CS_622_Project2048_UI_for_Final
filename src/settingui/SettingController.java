package settingui;

import game2048_test.App;
import mainui.MainUI;
import mainui.MainUIBlockLabel;
import mainui.MainUIBlocksArrayPane;
import mainui.MainUIBlocksArrayPaneUpdate;
import tool.CreateBlockArrayData;
import tool.OptionPane;
import users.User;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;

/**
 * purpose of this class is to set action listener of buttons in settingUI
 * <p>
 * Author: Xiaobing Hou
 * Date: 02/27/2022
 * Course: CS-622
 */
public class SettingController {
    public static int currentColorNum = App.currentUser.backgroundColor;

    /**
     * Purpose of this method is to set action for change color and game size buttons
     */
    public static void setController(SettingUI settingUI) {
        setActionForButtonGroup(App.currentUser, settingUI);
        //////////////////////////////////////////////////////////
        settingUI.leftSize.addActionListener(e -> {

            int currentSize = Integer.parseInt(settingUI.sizeLabel.getText());
            if (currentSize > App.minGameSize) {
                int nextSize = currentSize - 1;
                settingUI.sizeLabel.setText(nextSize + "");
                App.currentUser.gameSize = nextSize;
                updateMainInterface();
                if (nextSize == App.minGameSize) {
                    OptionPane.setJOptionPaneMessage(App.mainUI, "Not standard mode. Can't save record", "Message", null);
                    App.ifStandardMode = false;
                    settingUI.leftSize.setEnabled(false);
                } else {
                    App.ifStandardMode = true;
                }
                if (nextSize == App.maxGameSize - 1) {
                    settingUI.rightSize.setEnabled(true);
                }
            }

        });

        settingUI.rightSize.addActionListener(e -> {
            int currentSize = Integer.parseInt(settingUI.sizeLabel.getText());
            if (currentSize < App.maxGameSize) {
                int nextSize = currentSize + 1;
                settingUI.sizeLabel.setText(nextSize + "");
                App.currentUser.gameSize = nextSize;
                updateMainInterface();
                if (nextSize == App.maxGameSize) {
                    OptionPane.setJOptionPaneMessage(App.mainUI, "Not standard mode. Can't save record", "Message", null);
                    App.ifStandardMode = false;
                    settingUI.rightSize.setEnabled(false);
                } else {
                    App.ifStandardMode = true;
                }

                if (nextSize == App.minGameSize + 1) {
                    settingUI.leftSize.setEnabled(true);
                }
            }
        });

        settingUI.leftColor.addActionListener(e -> {
            if (currentColorNum > 0) {
                currentColorNum--;
                settingUI.colorLabel.setBackground(App.backgroundColors[currentColorNum]);
                updateBackground(App.backgroundColors[currentColorNum]);
                App.currentUser.backgroundColor = currentColorNum;
                if (currentColorNum == App.backgroundColors.length - 2) {
                    settingUI.rightColor.setEnabled(true);
                }
            }
            if (currentColorNum == 0) {
                settingUI.colorLabel.setBackground(App.backgroundColors[0]);
                updateBackground(App.backgroundColors[currentColorNum]);
                App.currentUser.backgroundColor = currentColorNum;
                settingUI.leftColor.setEnabled(false);
            }


        });


        settingUI.rightColor.addActionListener(e -> {
            if (currentColorNum < App.backgroundColors.length - 1) {
                currentColorNum++;
                settingUI.colorLabel.setBackground(App.backgroundColors[currentColorNum]);
                updateBackground(App.backgroundColors[currentColorNum]);
                App.currentUser.backgroundColor = currentColorNum;
                if (currentColorNum == App.backgroundColors.length - 1) {
                    settingUI.rightColor.setEnabled(false);
                }
                if (currentColorNum == 1) {
                    settingUI.leftColor.setEnabled(true);
                }
            }
        });

    }

    /**
     * Purpose of this method is to update main game area
     */
    public static void updateMainInterface() {
        App.mainUI.blocksArrayPane_outPanel.remove(App.mainUI.blocksArrayPane);
        CreateBlockArrayData.creatBlockArrayData(App.currentUser);
        App.mainUI.blocksArray = new MainUIBlockLabel[App.currentUser.gameSize][App.currentUser.gameSize];
        App.mainUI.blocksArrayPane = new MainUIBlocksArrayPane(App.mainUI.blocksArray, 25, 5);
        MainUIBlocksArrayPaneUpdate.updateUI(App.mainUI.blocksArray, App.currentUser.currentBlocksArrayData, App.mainUI.blocksArrayPane);
        App.mainUI.blocksArrayPane_outPanel.add(App.mainUI.blocksArrayPane, BorderLayout.CENTER);
        App.mainUI.blocksArrayPane_outPanel.updateUI();
    }

    /**
     * Purpose of this method is to update interface background color
     */
    public static void updateBackground(Color color) {
        App.mainUI.recordPane.setBackground(color);
        App.mainUI.buttonPane.setBackground(color);
    }

    /**
     * Purpose of this method is to set action for Buttongroup in settingUI
     */
    public static void setActionForButtonGroup(User user, SettingUI settingUI) {

        for (int i = 0; i < settingUI.jRadioButtonList.size(); i++) {
            for (JRadioButton jRadioButton : settingUI.jRadioButtonList.get(i)) {
                int finalI = i;
                jRadioButton.addActionListener(e -> {
                    String current = user.photoRecordLayout[finalI];
                    String result = clickWhich(settingUI.jRadioButtonList.get(finalI));
                    for (int j = 0; j < user.photoRecordLayout.length; j++) {
                        if (user.photoRecordLayout[j].equals(result)) {
                            user.photoRecordLayout[j] = current;
                            user.photoRecordLayout[finalI] = result;
                        }
                    }
                    selectWhich(user, settingUI.jRadioButtonList);
                    updateMainTop(App.mainUI, user);
                });
            }
        }
    }

    /**
     * Purpose of this method is to get the JRadioButton's text which selected
     */
    public static String clickWhich(List<JRadioButton> jRadioButtonList) {
        String result = null;
        for (JRadioButton jRadioButton : jRadioButtonList) {
            if (jRadioButton.isSelected()) {
                result = jRadioButton.getText();
            }

        }
        return result;
    }

    /**
     * Purpose of this method is to show the JRadioButton selected in setting interface
     */
    public static void selectWhich(User user, List<List<JRadioButton>> jRadioButtonList) {
        for (int i = 0; i < jRadioButtonList.size(); i++) {
            for (JRadioButton jRadioButton : jRadioButtonList.get(i)) {
                jRadioButton.setSelected(jRadioButton.getText().equals(user.photoRecordLayout[i]));
            }
        }
    }

    /**
     * Purpose of this method is to update top part of mainUI
     */
    public static void updateMainTop(MainUI mainUI, User user) {

        mainUI.recordPane.remove(mainUI.timerAndPhotoPane);
        mainUI.recordPane.remove(mainUI.bestRecordOutPane);
        mainUI.recordPane.remove(mainUI.lastRecordOutPane);
        List<JPanel> jPanelList = new ArrayList<>() {{
            add(mainUI.timerAndPhotoPane);
            add(mainUI.bestRecordOutPane);
            add(mainUI.lastRecordOutPane);
        }};


        List<JPanel> newJPanelList = new ArrayList<>(jPanelList);
        for (int i = 0; i < user.photoRecordLayout.length; i++) {
            switch (user.photoRecordLayout[i]) {
                case "left" -> newJPanelList.set(0, jPanelList.get(i));
                case "center" -> newJPanelList.set(1, jPanelList.get(i));
                case "right" -> newJPanelList.set(2, jPanelList.get(i));
            }
        }

        // lambda for loop
        newJPanelList.forEach(p -> mainUI.recordPane.add(p));

        mainUI.recordPane.updateUI();
    }

}
