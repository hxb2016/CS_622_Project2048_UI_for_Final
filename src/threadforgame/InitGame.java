package threadforgame;

import game2048_test.App;
import operation.Operate;
import profileui.ImageIconForPhoto;
import settingui.SettingController;
import tool.UpdateTimerPane;
/**
 * Purpose of this class is to create a InitGame thread for init interface
 * <p>
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class InitGame extends Thread{
    public InitGame() {
    }

    @Override
    public void run() {
        App.profileUI.setVisible(false);
        //Update mainUI photo
        ImageIconForPhoto photo = new ImageIconForPhoto(App.photosLocation + "profile1.png");
        App.mainUI.profilePhoto.roundLabel.setIcon(photo);
        // Update mainUI username
        App.mainUI.profilePhoto.setUsername(null);
        // init profile page
        App.ifDeleteAccount = true;
        //init timer panel
        UpdateTimerPane.endTimer();
        App.mainUI.timerPane.setSecond("0 s");
        // Update record panel
        App.mainUI.updateLastBestRecord(true);
        Operate.ifStartOperate = false;
        App.ifEnd = false;
        App.mainUI.pause.setEnabled(false);
        App.mainUI.save.setEnabled(false);
        App.ifPauseTimer=false;;

        SettingController.updateMainInterface();
        SettingController.updateBackground(App.backgroundColors[0]);
        SettingController.updateMainTop(App.mainUI, App.currentUser);
    }
}
