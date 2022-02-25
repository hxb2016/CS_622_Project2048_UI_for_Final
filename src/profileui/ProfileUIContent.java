package profileui;

import game2048_test.App;
import tool.PasswordField;
import tool.TextButton;
import tool.TextFieldForGame;
import users.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * purpose of this class is to create a component panel for profile interface
 * <p>
 * Author: Xiaobing Hou
 * Date: 02/12/2022
 * Course: CS-622
 */
public class ProfileUIContent extends JPanel {

    public ProfilePhoto profilePhoto;
    public TextFieldForGame username;
    public TextFieldForGame gender;
    public TextFieldForGame age;
    public TextFieldForGame introduction;
    public JPasswordField password;
    public TextButton editButton;
    public TextButton delButton;
    public TextButton quitButton;

    public static ProfileUIContent profileUIContent = null;

    private ProfileUIContent(User user) {
        profilePhoto = new ProfilePhoto(user);
        profilePhoto.setOpaque(false);
        username = ProfileTextField.getProfileTextField(user.username);
        gender = ProfileTextField.getProfileTextField(user.gender);
        age = ProfileTextField.getProfileTextField(user.age + "");
        introduction = ProfileTextField.getProfileTextField(user.introduce);

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        profilePhoto.username.setText(user.username);
        topPanel.add(profilePhoto);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new GridLayout(6, 1));

        JPanel usernamePane = new JPanel();
        usernamePane.setOpaque(false);
        JLabel usernameTitle = new ProfileLabel("Username: ", SwingConstants.RIGHT);
        usernamePane.add(usernameTitle);
        usernamePane.add(username);

        JPanel passwordPane = new JPanel();
        passwordPane.setOpaque(false);
        JLabel passwordTitle = new ProfileLabel("Password: ", SwingConstants.RIGHT);
        password = PasswordField.getPasswordField(String.valueOf(user.password));
        password.setPreferredSize(new Dimension(300, 30));
        password.setEditable(false);
        passwordPane.add(passwordTitle);
        passwordPane.add(password);

        JPanel agePane = new JPanel();
        agePane.setOpaque(false);
        JLabel ageTitle = new ProfileLabel("Age: ", SwingConstants.RIGHT);
        agePane.add(ageTitle);
        agePane.add(age);

        JPanel genderPane = new JPanel();
        genderPane.setOpaque(false);
        JLabel genderTitle = new ProfileLabel("Gender: ", SwingConstants.RIGHT);
        genderPane.add(genderTitle);
        genderPane.add(gender);

        JPanel introductionPane = new JPanel();
        introductionPane.setOpaque(false);
        JLabel introductionTitle = new ProfileLabel("Intr: ", SwingConstants.RIGHT);
        introductionPane.add(introductionTitle);
        introductionPane.add(introduction);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        editButton = new TextButtonForProfile("Edit Profile");
        quitButton = new TextButtonForProfile("Quit Account");
        delButton = new TextButtonForProfile("Del Account");
        buttonPanel.add(editButton);
        buttonPanel.add(quitButton);
        buttonPanel.add(delButton);

        bottomPanel.add(usernamePane);
        bottomPanel.add(passwordPane);
        bottomPanel.add(agePane);
        bottomPanel.add(genderPane);
        bottomPanel.add(introductionPane);
        bottomPanel.add(buttonPanel);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.CENTER);
    }

    /**
     * purpose of this method is to return a ProfileUIContent object
     */
    public static void setProfileUIContent(User user) {
        if (App.ifDeleteAccount) {
            App.profileUI.remove(profileUIContent);
            profileUIContent = new ProfileUIContent(user);
            App.profileUI.add(profileUIContent, BorderLayout.CENTER);
            ProfileUIController.setController(profileUIContent);
            profileUIContent.updateUI();
            App.ifDeleteAccount = false;

        } else {
            if (profileUIContent == null) {
                profileUIContent = new ProfileUIContent(user);
                App.profileUI.add(profileUIContent, BorderLayout.CENTER);
                ProfileUIController.setController(profileUIContent);
            }
        }
        profileUIContent.setBackground(App.backgroundColors[App.currentUser.backgroundColor]);
    }

}
