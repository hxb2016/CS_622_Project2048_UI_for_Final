package threadforgame;

import game2048_test.App;
import operation.Operate;
import tool.OptionPane;
import tool.UpdateTimerPane;
import users.User;


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
        App.mainUI.pause.setEnabled(false);
        if(App.ifStandardMode){
            App.mainUI.save.setEnabled(true);
        }
    }
}
