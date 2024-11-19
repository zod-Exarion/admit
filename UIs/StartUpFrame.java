package UIs;

import Tools.FileSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;

public class StartUpFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField,reconfirmField;

    private boolean isLogin = false;

    private final FileSystem file = new FileSystem("passwords.txt");
    private final HashMap<String,String> database = file.fetchDatabase();

    StartUpFrame(){
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);components();
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                components();
            }
        });
        this.setLayout(null);
    }

    public void components(){
        this.getContentPane().removeAll();
        JPanel welcomePanel;
        welcomePanel:
        {
            welcomePanel = new JPanel();
            welcomePanel.setBounds(0, 0, this.getWidth()/3, this.getHeight());
            welcomePanel.setBackground(new Color(20, 20, 20));
            welcomePanel.setLayout(null);

            this.add(welcomePanel);
        }
        rightPanel:
        {
            if(isLogin){
                loginPanel();
            }
            else{
                signupPanel();
            }
        }
        loginOption:
        {
            JButton signupOption = new JButton("Sign up");
            signupOption.setFocusPainted(false);
            signupOption.setBackground(new Color(20,20,20));
            signupOption.setForeground(new Color(255,255,255));
            signupOption.setBounds(0, welcomePanel.getHeight()/2 -15, welcomePanel.getWidth(), 50);

            signupOption.addActionListener(e -> {
                isLogin = false;
                components();
            });

            welcomePanel.add(signupOption);
        }
        SignupOption:
        {
            JButton loginOption = new JButton("Login");
            loginOption.setFocusPainted(false);
            loginOption.setBackground(new Color(20,20,20));
            loginOption.setForeground(new Color(255,255,255));
            loginOption.setBounds(0, welcomePanel.getHeight()/2 - 100, welcomePanel.getWidth(), 50);

            loginOption.addActionListener(e -> {
                isLogin = true;
                components();
            });

            welcomePanel.add(loginOption);
        }
        welcomeLabel:
        {
            JLabel welcomeText = new JLabel("Welcome");
            welcomeText.setText("Welcome");
            welcomeText.setFont(new Font("Arial", Font.BOLD, 20));
            welcomeText.setForeground(new Color(255, 255, 255));
            welcomeText.setBackground(Color.WHITE);
            welcomeText.getWidth();

            welcomeText.setBounds(welcomePanel.getWidth()/2 - 50,0, welcomePanel.getWidth(), 50);

            welcomePanel.add(welcomeText);
            repaint();
        }
    }

    public void loginPanel(){
        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(this.getWidth()/3, 0, 2*this.getWidth()/3+1, this.getHeight());
        loginPanel.setBackground(new Color(0, 0, 0));

        JLabel loginLabel = new JLabel("Login");
        JLabel passwordLabel = new JLabel("Password");
        JLabel emailLabel = new JLabel("Email");
        JButton loginButton = new JButton("Login");

        loginLabel.setBounds(loginPanel.getWidth()/2 - 25, loginPanel.getHeight()/12, loginPanel.getWidth()/2, 50);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginLabel.setForeground(new Color(255, 255, 255));

        emailLabel.setBounds(loginPanel.getWidth()/4, loginPanel.getHeight()/4-50, loginPanel.getWidth()/2, 30);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        emailLabel.setForeground(new Color(255, 255, 255));
        emailField = new JTextField();
        this.emailField.setBounds(loginPanel.getWidth()/4, loginPanel.getHeight()/4, loginPanel.getWidth()/2, 30);

        passwordLabel.setBounds(loginPanel.getWidth()/4, loginPanel.getHeight()/2 - 100, loginPanel.getWidth()/2, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passwordLabel.setForeground(new Color(255, 255, 255));
        passwordField = new JPasswordField();
        this.passwordField.setBounds(loginPanel.getWidth()/4, loginPanel.getHeight()/2 - 50, loginPanel.getWidth()/2, 30);

        loginButton.setBounds(loginPanel.getWidth()/4, loginPanel.getHeight()/2+20, loginPanel.getWidth()/2, 30);
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setBackground(new Color(255,165,20));
        loginButton.setFocusPainted(false);

        loginButton.addActionListener(e -> login());

        loginPanel.add(emailLabel);
        loginPanel.add(passwordLabel);
        loginPanel.add(loginLabel);
        loginPanel.add(loginButton);
        loginPanel.add(emailField);
        loginPanel.add(passwordField);

        this.add(loginPanel);
    }

    public void signupPanel(){
        JPanel signupPanel = new JPanel();
        signupPanel.setBounds(this.getWidth()/3, 0, 2*this.getWidth()/3+1, this.getHeight());
        signupPanel.setBackground(new Color(0, 0, 0));

        JLabel signupLabel = new JLabel("signup");
        JLabel passwordLabel = new JLabel("Password");
        JLabel emailLabel = new JLabel("Email");
        JLabel nameLabel = new JLabel("Name");
        JButton signupButton = new JButton("signup");
        JLabel reconfirmLabel = new JLabel("Re-confirm");
        reconfirmField = new JPasswordField();

        nameLabel.setBounds(signupPanel.getWidth()/4, signupPanel.getHeight()/4-100, signupPanel.getWidth()/2, 30);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(new Color(255, 255, 255));
        JTextField nameField = new JTextField();
        nameField.setBounds(signupPanel.getWidth()/4, signupPanel.getHeight()/4 - 50, signupPanel.getWidth()/2, 30);

        signupLabel.setBounds(signupPanel.getWidth()/2 - 25, signupPanel.getHeight()/100, signupPanel.getWidth()/2, 50);
        signupLabel.setFont(new Font("Arial", Font.BOLD, 20));
        signupLabel.setForeground(new Color(255, 255, 255));

        int emailPos = (signupPanel.getHeight()/4-50 + signupPanel.getHeight()/2 - 75)/2;

        emailLabel.setBounds(signupPanel.getWidth()/4, emailPos - 25, signupPanel.getWidth()/2, 30);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        emailLabel.setForeground(new Color(255, 255, 255));
        emailField = new JTextField();
        this.emailField.setBounds(signupPanel.getWidth()/4, emailPos + 25, signupPanel.getWidth()/2, 30);

        passwordLabel.setBounds(signupPanel.getWidth()/4, signupPanel.getHeight()/2 - 75, signupPanel.getWidth()/2, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passwordLabel.setForeground(new Color(255, 255, 255));
        passwordField = new JPasswordField();
        this.passwordField.setBounds(signupPanel.getWidth()/4, signupPanel.getHeight()/2 - 25, signupPanel.getWidth()/2, 30);

        signupButton.setBounds(signupPanel.getWidth()/4, (int)(signupPanel.getHeight()/1.35), signupPanel.getWidth()/2, 30);
        signupButton.setFont(new Font("Arial", Font.BOLD, 20));
        signupButton.setForeground(new Color(255, 255, 255));
        signupButton.setBackground(new Color(255,165,20));
        signupButton.setFocusPainted(false);

        signupButton.addActionListener(e -> signUp());

        int reconPos = (int)(signupPanel.getHeight()/1.35 + (double) signupPanel.getHeight() /2 - 25)/2;

        reconfirmLabel.setBounds(signupPanel.getWidth()/4, reconPos - 25, signupPanel.getWidth()/2, 30);
        reconfirmLabel.setFont(new Font("Arial", Font.BOLD, 20));
        reconfirmLabel.setForeground(new Color(255, 255, 255));
        //reconfirmField = new JPasswordField();
        reconfirmField.setBounds(signupPanel.getWidth()/4, reconPos + 25, signupPanel.getWidth()/2, 30);

        signupPanel.add(emailLabel);
        signupPanel.add(passwordLabel);
        signupPanel.add(signupLabel);
        signupPanel.add(signupButton);
        signupPanel.add(nameLabel);
        signupPanel.add(reconfirmLabel);
        signupPanel.add(reconfirmField);
        signupPanel.add(nameField);
        signupPanel.add(emailField);
        signupPanel.add(passwordField);

        this.add(signupPanel);
    }

    public void signUp(){
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String password2 = new String(reconfirmField.getPassword());

        if(checkValidSignUp(email) && password.equals(password2)){
            file.write(email+":"+password+"\n",true);
            database.put(email,password);
            JOptionPane.showMessageDialog(null,"Sign Up Successful","INFO",JOptionPane.INFORMATION_MESSAGE);
        }
        else JOptionPane.showMessageDialog(null,"ERROR! Password Mismatch\n OR\nPreexisting User!","ERROR",JOptionPane.ERROR_MESSAGE);
    }

    public void login(){
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if(checkValidLogin(email,password)) JOptionPane.showMessageDialog(null,"Success","INFO",JOptionPane.INFORMATION_MESSAGE);
        else  JOptionPane.showMessageDialog(null,"Invalid Login","ERROR",JOptionPane.ERROR_MESSAGE);
    }

    public boolean checkValidSignUp(String email){
        return(null==(database.get(email)));
    }

    public boolean checkValidLogin(String email,String password){
        if(checkValidSignUp(email)) return false;
        String pass = database.get(email);
        return(pass.equals(password));
    }

    public static void main(String[] args) {
        new StartUpFrame();
    }
}