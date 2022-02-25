package game2048_test;

import loginui.LoginUI;
import threadforgame.GetDataThread;
import threadforgame.GetLoginUIThread;
import mainui.MainUI;
import profileui.ProfileUI;
import threadforgame.GetProfileUIThread;
import users.User;

import java.awt.*;
import java.io.File;
import java.util.Map;

/**
 * The purpose of this class is to run the game
 * Notes: In order to save time for test, I change the win number from 2048 to 16 (WinNum = 16).
 * Because it will very long time if the number is too large. which means that any number is larger than 16,
 * it will judge that you win.
 * <p>
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class App {
    public static Color[] backgroundColors = new Color[]{Color.white, new Color(132, 148, 147), new Color(238, 194, 181),
            new Color(151, 138, 147), new Color(235, 219, 204),
            new Color(244, 218, 255), new Color(165, 197, 254),
            new Color(192, 222, 232), new Color(81, 192, 209),
            new Color(154, 250, 239), new Color(203, 190, 216)};
    public static String photosLocation = "src" + File.separator + "image" + File.separator + "photos" + File.separator;
    public static String iconsLocation = "src" + File.separator + "image" + File.separator;
    public static User currentUser = null;
    public final static int defaultGameSize = 4;// Purpose of this parameter is to decide the blockArray's size
    public final static int minGameSize = 3;
    public final static int maxGameSize = 5;
    public final static int WinNum = 16;// You will win the game, if there is any number larger than WinNum
    public static Map<String, User> usersData = null;
    public static String userDataPath = "src" + File.separator + "userdata" + File.separator + "Data.dat";
    public static MainUI mainUI = null;
    public static LoginUI loginUI = null;
    public static ProfileUI profileUI = null;
    public static boolean ifEnd = false;// Judge that the game end or not
    public static boolean ifDeleteAccount = false;
    public static boolean ifPauseTimer = false;
    public static boolean ifStandardMode = true;

    private final static GetDataThread getDataThread = new GetDataThread();

    public static void main(String[] args) {

        try {
            getDataThread.start();
            synchronized (getDataThread) {
                getDataThread.wait();
                mainUI = MainUI.getMainUI();//init main UI
                GetProfileUIThread profileUIThread = new GetProfileUIThread(mainUI);
                GetLoginUIThread loginUIThread = new GetLoginUIThread(mainUI);
                profileUIThread.start();
                loginUIThread.start();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
