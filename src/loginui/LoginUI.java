package loginui;

import game2048_test.App;
import tool.PasswordField;
import tool.RectangleButton;
import tool.TextButton;
import tool.TextFieldForGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * purpose of this class is to create a login ui interface
 *
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class LoginUI extends JDialog {
    public TextFieldForGame userNameBox;
    public JPasswordField passwordBox;
    public JLabel ageTitle;
    public TextFieldForGame ageBox;
    public JLabel genderTitle;
    public TextFieldForGame genderBox;
    public RectangleButton signIn;
    public RectangleButton signUp;
    public RectangleButton cancelSignUp;
    public TextButton creatAccount;
    public TextButton startAsGuest;
    public JPanel signInAndUp;
    public JPanel informationArea;
    private static LoginUI loginUI = null;

    private LoginUI(Frame owner) {
        super(owner);
        setResizable(false);
        setTitle("login");
        ImageIcon logo = new ImageIcon("src" + File.separator + "image" + File.separator + "2048.png");
        setIconImage(logo.getImage());
        setLayout(new BorderLayout());
        setSize(300, 400); // 设置大小
        setLocationRelativeTo(null); // 相对屏幕居中
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!App.ifEnd) {// If you have not started the game, the game will quit when you close the login pane.
                    System.exit(0);
                }
            }
        });

        //////////////////////////information area////////////////////////////////
        //this area includes username, password, age, gender, creatAccount and startAsGuest components
        informationArea = new JPanel();

        JLabel userNameTitle = new LoginUILabel(this,"User name:", SwingConstants.CENTER);
        userNameBox = LoginUITextField.getLoginUITextField();

        JLabel passwordTitle = new LoginUILabel(this,"Password:", SwingConstants.CENTER);
        passwordBox = PasswordField.getPasswordField(null);
        passwordBox.setPreferredSize(new Dimension(200, 30));

        creatAccount = new TextButtonForLoginUI("Creat Account");
        startAsGuest = new TextButtonForLoginUI("Start as Guest");

        ageTitle = new LoginUILabel(this,"Age:", SwingConstants.CENTER);
        ageBox = LoginUITextField.getLoginUITextField();

        genderTitle = new LoginUILabel(this,"Gender:", SwingConstants.CENTER);
        genderBox = LoginUITextField.getLoginUITextField();


        informationArea.add(userNameTitle);
        informationArea.add(userNameBox);
        informationArea.add(passwordTitle);
        informationArea.add(passwordBox);
        informationArea.add(creatAccount);
        informationArea.add(startAsGuest);
        //////////////////////////information area end////////////////////////////

        signInAndUp = new JPanel();
        signInAndUp.setLayout(new GridLayout(1, 2));
        signInAndUp.setSize(getWidth(), 50);

        signIn = new RectangleButtonForLogin("Sign in",null);// sign in button
        signInAndUp.add(signIn, BorderLayout.CENTER);

        signUp = new RectangleButtonForLogin("Sign up",null);// sign up button
        cancelSignUp = new RectangleButtonForLogin("Cancel",null);

        add(signInAndUp, BorderLayout.SOUTH);
        add(informationArea, BorderLayout.CENTER);
    }

    /**
     * purpose of this method is to return a loginUI object and make sure that just only one loginUI object exist in the system
     */
    public static LoginUI getLoginUI(Frame owner) {
        if (loginUI == null) {
            loginUI = new LoginUI(owner);
            LoginUIController.setController(loginUI);
            loginUI.setVisible(true);
        }
        return loginUI;

    }

}
