package threadforgame;

import game2048_test.App;
import io.SaveUsersData;
import operation.Operate;
import tool.OptionPane;
import tool.UpdateTimerPane;
import users.RegisteredUser;
import users.User;

import javax.swing.*;
import java.util.HashMap;

/**
 * Purpose of this class is to create a HandleDataAfterGameEnd thread
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class HandleDataAfterGameEnd extends Thread {
    private final User user;
    public HandleDataAfterGameEnd(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        UpdateTimerPane.endTimer();
        if (Operate.isWin(user.currentBlocksArrayData)) {
            user.currentResult = "win";
            OptionPane.setJOptionPaneMessage(App.mainUI, "YOU WIN!!!", "Congratulations", null);
        } else {
            user.currentResult = "fail";
            OptionPane.setJOptionPaneMessage(App.mainUI, "GAME OVER!", "Sorry", null);
        }
        int userOption = OptionPane.setJOptionPaneConfirm(App.mainUI, "Save the result?", "Message");
        if (userOption == JOptionPane.YES_OPTION) {
            if (App.usersData == null) {
                App.usersData = new HashMap<>();
            }
            if (user instanceof RegisteredUser) {
                ((RegisteredUser) user).setData();//set the data to prepare for saving
                App.usersData.put(user.username, user);
                try {
                    SaveUsersData.saveUsersData(App.usersData, App.userDataPath);
                    App.mainUI.updateLastBestRecord();
                    App.mainUI.usersScrollPane.updateUsersTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                App.loginUI.setVisible(true);
            }
        }
    }
}
