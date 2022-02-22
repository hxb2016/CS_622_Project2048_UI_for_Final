package mainui;

import game2048_test.App;
import io.SaveUsersData;
import operation.Operate;
import profileui.ProfileUIContent;
import settingui.SettingController;
import settingui.SettingUI;
import tool.CreateBlockArrayData;
import tool.OptionPane;
import tool.UpdateTimerPane;
import users.RegisteredUser;
import users.UnRegisteredUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * purpose of this class is to set action listener of buttons in MainUI
 * <p>
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class MainUIController {
    /**
     * purpose of the method is to set action listener of buttons in MainUI
     */

    public static void setUIController(MainUI mainUI) {
        // set action listener for up button in MainUI
        mainUI.up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!App.ifEnd) {
                        Operate.operation(38, App.currentUser);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // set action listener for left button in MainUI
        mainUI.left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!App.ifEnd) {
                        Operate.operation(37, App.currentUser);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // set action listener for down button in MainUI
        mainUI.down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!App.ifEnd) {
                        Operate.operation(40, App.currentUser);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // set action listener for right button in MainUI
        mainUI.right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!App.ifEnd) {
                        Operate.operation(39, App.currentUser);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // set action listener for New Game button in MainUI
        mainUI.newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainUI.save.isEnabled()) {
                    int userOption = OptionPane.setJOptionPaneConfirm(App.mainUI, "Haven't saved result. New game now?", "Message");
                    if (userOption == JOptionPane.YES_OPTION) {
                        newGame(mainUI);
                    }
                } else {
                    newGame(mainUI);
                }


            }

        });

        // set action listener for pause button in MainUI
        mainUI.pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.ifPauseTimer = !App.ifPauseTimer;

                if (App.ifPauseTimer) {
                    UpdateTimerPane.pauseTimer();
                    mainUI.pause.setText("Continue");
                } else {
                    UpdateTimerPane.startTimer();
                    mainUI.pause.setText("Pause");
                }

            }

        });

        // set action listener for save button in MainUI
        mainUI.save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (App.usersData == null) {
                    App.usersData = new HashMap<>();
                }
                if (App.currentUser instanceof RegisteredUser) {
                    ((RegisteredUser) App.currentUser).setData();//set the data to prepare for saving
                    App.usersData.put(App.currentUser.username, App.currentUser);
                    try {
                        SaveUsersData.saveUsersData(App.usersData, App.userDataPath);
                        App.mainUI.updateLastBestRecord(false);
                        App.mainUI.usersScrollPane.updateUsersTable();
                        // Update champion panel
                        App.mainUI.ChampionPanel.setUserToPanel(App.mainUI.usersScrollPane.usersTable.champion);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    mainUI.save.setEnabled(false);
                } else {
                    App.loginUI.setVisible(true);
                }
            }

        });

        // set action listener for setting button in MainUI
        mainUI.setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (App.currentUser instanceof RegisteredUser) {
                    SettingUI settingUI = SettingUI.getSettingUI(App.mainUI);
                    settingUI.colorLabel.setBackground(App.backgroundColors[App.currentUser.backgroundColor]);
                    SettingController.currentColorNum = App.currentUser.backgroundColor;
                    SettingController.selectWhich(App.currentUser, settingUI.jRadioButtonList);
                    settingUI.sizeLabel.setText(App.currentUser.gameSize + "");
                    settingUI.setVisible(true);

                    UpdateTimerPane.endTimer();
                    if (App.mainUI.timerPane.timerHideOrShow) {
                        mainUI.timerPane.setSecond("0 s");
                    }
                    Operate.ifStartOperate = false;
                    App.ifEnd = false;
                    App.ifPauseTimer = false;
                    mainUI.pause.setText("Pause");
                    mainUI.pause.setEnabled(false);
                    mainUI.save.setEnabled(false);
                } else {
                    App.loginUI.setVisible(true);
                }
            }

        });


        // set action listener for profilePhoto label in MainUI
        mainUI.profilePhoto.roundLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (App.currentUser instanceof UnRegisteredUser) {
                    App.loginUI.setVisible(true);
                } else {
                    App.profileUI.setVisible(!App.profileUI.isVisible());
                    ProfileUIContent.setProfileUIContent(App.currentUser);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mainUI.profilePhoto.message.setText("Profile");
                mainUI.profilePhoto.roundLabel.add(mainUI.profilePhoto.message, BorderLayout.SOUTH);
                mainUI.profilePhoto.roundLabel.updateUI();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mainUI.profilePhoto.roundLabel.remove(mainUI.profilePhoto.message);
                mainUI.profilePhoto.roundLabel.updateUI();
            }

        });


        // set action listener for timerPane label in MainUI
        mainUI.timerPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainUI.timerPane.timerHideOrShow();
            }
        });
    }

    /**
     * Purpose of the method is to deal with the new game
     */
    private static void newGame(MainUI mainUI) {
        try {
            CreateBlockArrayData.creatBlockArrayData(App.currentUser);//init current user's block array data
            MainUIBlocksArrayPaneUpdate.updateUI(mainUI.blocksArray, App.currentUser.currentBlocksArrayData, mainUI.blocksArrayPane);//update UI
            UpdateTimerPane.endTimer();
            if (App.mainUI.timerPane.timerHideOrShow) {
                mainUI.timerPane.setSecond("0 s");
            }
            Operate.ifStartOperate = false;
            App.ifEnd = false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        App.ifPauseTimer = false;
        mainUI.pause.setText("Pause");
        mainUI.save.setEnabled(false);
    }
}
