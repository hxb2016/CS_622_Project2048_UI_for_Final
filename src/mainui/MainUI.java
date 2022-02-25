package mainui;

import game2048_test.App;
import profileui.ProfilePhoto;
import tool.RectangleButton;
import users.RegisteredUser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * purpose of this class is to create the MainUI
 * <p>
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class MainUI extends JFrame {
    public MainUIBlockLabel[][] blocksArray = new MainUIBlockLabel[App.defaultGameSize][App.defaultGameSize];
    public JPanel blocksArrayPane_outPanel;
    public JPanel recordPane;
    public JPanel buttonPane;
    public JPanel blocksArrayPane;
    public JPanel timerAndPhotoPane;
    public JPanel bestRecordOutPane;
    public JPanel lastRecordOutPane;
    public JLabel lastTitleLabel = new JLabel("Last Record: Taken ...s", SwingConstants.CENTER);
    public MainUIBlockLabel[][] lastBlockArray = new MainUIBlockLabel[App.defaultGameSize][App.defaultGameSize];
    public JPanel lastRecord = new MainUIBlocksArrayPane(lastBlockArray, 10, 2);

    public JLabel bestTitleLabel = new JLabel("best Record: Taken ...s", SwingConstants.CENTER);
    public MainUIBlockLabel[][] bestBlockArray = new MainUIBlockLabel[App.defaultGameSize][App.defaultGameSize];
    public JPanel bestRecord = new MainUIBlocksArrayPane(bestBlockArray, 10, 2);

    public ProfilePhoto profilePhoto = new ProfilePhoto(App.currentUser);
    public GameTimerPane timerPane = new GameTimerPane();
    public UsersScrollPane usersScrollPane = UsersScrollPane.getUsersScrollPane(App.usersData);
    public ChampionPanel ChampionPanel = new ChampionPanel();

    public RectangleButton up;
    public RectangleButton left;
    public RectangleButton down;
    public RectangleButton right;
    public RectangleButton newGame;
    public RectangleButton pause;
    public RectangleButton setting;
    public RectangleButton save;

    private static MainUI mainUI;

    private MainUI() {
        ImageIcon logo = new ImageIcon(App.iconsLocation + "2048.png");//get logo image
        setTitle("CS_622_Game_2048");
        setIconImage(logo.getImage());//set logo image
        setSize(720, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);

        recordPane = new JPanel();
        recordPane.setBorder(new EmptyBorder(0, 0, 10, 0));
        recordPane.setLayout(new GridLayout(1, 3, -20, 0));
        recordPane.setPreferredSize(new Dimension(getWidth(), 220));

////////////////////////////////////////////////////////////////////////////////////
        lastRecordOutPane = new JPanel();
        lastRecordOutPane.setLayout(new BorderLayout());
        lastRecordOutPane.setBorder(new EmptyBorder(0, 20, 0, 20));
        lastRecordOutPane.setOpaque(false);
        lastRecordOutPane.add(lastTitleLabel, BorderLayout.NORTH);
        lastRecordOutPane.add(lastRecord, BorderLayout.CENTER);

        bestRecordOutPane = new JPanel();
        bestRecordOutPane.setLayout(new BorderLayout());
        bestRecordOutPane.setBorder(new EmptyBorder(0, 20, 0, 20));
        bestRecordOutPane.setOpaque(false);
        bestRecordOutPane.add(bestTitleLabel, BorderLayout.NORTH);
        bestRecordOutPane.add(bestRecord, BorderLayout.CENTER);

        timerAndPhotoPane = new JPanel();
        timerAndPhotoPane.setOpaque(false);
        timerAndPhotoPane.setLayout(new BorderLayout());
        profilePhoto.setOpaque(false);
        timerPane.setOpaque(false);
        timerAndPhotoPane.add(profilePhoto, BorderLayout.CENTER);
        timerAndPhotoPane.add(timerPane, BorderLayout.SOUTH);
//////////////////////////////////////////////////////////////////////////////////////////////
        recordPane.add(timerAndPhotoPane);
        recordPane.add(bestRecordOutPane);
        recordPane.add(lastRecordOutPane);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        buttonPane = new JPanel();
        buttonPane.setPreferredSize(new Dimension(220, getHeight()));
        buttonPane.setLayout(new BorderLayout());

        newGame = new RectangleButtonForMain("New Game", null);
        pause = new RectangleButtonForMain("Pause", null);
        setting = new RectangleButtonForMain("Setting", null);
        save = new RectangleButtonForMain("Save", null);
        pause.setEnabled(false);
        save.setEnabled(false);
        JPanel newGamePauseMode = new JPanel();
        newGamePauseMode.setOpaque(false);
        newGamePauseMode.setLayout(new GridLayout(2, 2, 1, 1));
        newGamePauseMode.add(newGame);
        newGamePauseMode.add(pause);
        newGamePauseMode.add(setting);
        newGamePauseMode.add(save);

        JPanel operationButtonArea = new JPanel();
        operationButtonArea.setOpaque(false);
        operationButtonArea.setBorder(new EmptyBorder(0, 0, 10, 0));
        operationButtonArea.setLayout(new GridLayout(2, 1));
        ImageIcon upArrow = new ImageIcon(App.iconsLocation + "upArrow.png");
        ImageIcon leftArrow = new ImageIcon(App.iconsLocation + "leftArrow.png");
        ImageIcon downArrow = new ImageIcon(App.iconsLocation + "downArrow.png");
        ImageIcon rightArrow = new ImageIcon(App.iconsLocation + "rightArrow.png");
        up = new RectangleButtonForMain(null, upArrow);
        JPanel upPane = new JPanel();
        upPane.setOpaque(false);
        upPane.add(up);
        left = new RectangleButtonForMain(null, leftArrow);
        down = new RectangleButtonForMain(null, downArrow);
        right = new RectangleButtonForMain(null, rightArrow);
        JPanel leftDownRightPane = new JPanel();
        leftDownRightPane.setOpaque(false);
        leftDownRightPane.add(left);
        leftDownRightPane.add(down);
        leftDownRightPane.add(right);
        operationButtonArea.add(upPane);
        operationButtonArea.add(leftDownRightPane);

        JPanel userTableAndChampionPanel = new JPanel();
        userTableAndChampionPanel.setLayout(new BorderLayout());

        ChampionPanel.setUserToPanel(usersScrollPane.usersTable.champion);

        userTableAndChampionPanel.add(ChampionPanel, BorderLayout.NORTH);
        userTableAndChampionPanel.add(usersScrollPane, BorderLayout.CENTER);

        buttonPane.add(newGamePauseMode, BorderLayout.NORTH);
        buttonPane.add(userTableAndChampionPanel, BorderLayout.CENTER);
        buttonPane.add(operationButtonArea, BorderLayout.SOUTH);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        blocksArrayPane_outPanel = new JPanel();
        blocksArrayPane_outPanel.setLayout(new BorderLayout());
        blocksArrayPane = new MainUIBlocksArrayPane(blocksArray, 25, 5);
        blocksArrayPane_outPanel.add(blocksArrayPane, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        add(blocksArrayPane_outPanel, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.EAST);
        add(recordPane, BorderLayout.NORTH);
        setVisible(true);//window is visible
    }

    /**
     * Purpose of this method is to update the lastBlockArray and bestBlockArray
     */
    public void updateLastBestRecord(boolean ifInit) {
        if (!ifInit) {
            MainUIBlocksArrayPaneUpdate.updateUI(lastBlockArray, ((RegisteredUser) App.currentUser).lastBlocksArrayData, lastRecord);
            lastTitleLabel.setText("Last Record: Taken " + ((RegisteredUser) App.currentUser).lastTakeTime + " s");

            MainUIBlocksArrayPaneUpdate.updateUI(bestBlockArray, ((RegisteredUser) App.currentUser).bestBlocksArrayData, bestRecord);
            bestTitleLabel.setText("Best Record: Taken " + ((RegisteredUser) App.currentUser).bestTakeTime + " s");
        } else {
            lastTitleLabel.setText("Last Record: Taken ... s");
            bestTitleLabel.setText("Last Record: Taken ... s");

            lastRecordOutPane.remove(lastRecord);
            lastBlockArray = new MainUIBlockLabel[App.defaultGameSize][App.defaultGameSize];
            lastRecord = new MainUIBlocksArrayPane(lastBlockArray, 10, 2);
            lastRecordOutPane.add(lastRecord, BorderLayout.CENTER);
            lastRecordOutPane.updateUI();

            bestRecordOutPane.remove(bestRecord);
            bestBlockArray = new MainUIBlockLabel[App.defaultGameSize][App.defaultGameSize];
            bestRecord = new MainUIBlocksArrayPane(bestBlockArray, 10, 2);
            bestRecordOutPane.add(bestRecord, BorderLayout.CENTER);
            bestRecordOutPane.updateUI();
        }
    }

    /**
     * Purpose of this method is to return a single mainUI
     */
    public static MainUI getMainUI() {
        if (mainUI == null) {
            mainUI = new MainUI();
            MainUIController.setUIController(mainUI);
        }
        return mainUI;
    }


}
