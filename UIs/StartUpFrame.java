package UIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class StartUpFrame extends JFrame {
    private JButton loginOption;
    private JPanel welcomePanel;
    private JPanel loginPanel;
    private JPanel signupPanel;
    private JLabel welcomeText;
    private JButton signupOption;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField nameField = new JTextField();

    private boolean entryMethodisLogin = false;

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
        welcomePanel:
        {
            this.welcomePanel = new JPanel();
            this.welcomePanel.setBounds(0, 0, this.getWidth()/3, this.getHeight());
            this.welcomePanel.setBackground(new Color(20, 20, 20));
            this.welcomePanel.setLayout(null);

            this.add(this.welcomePanel);
        }
        rightPanel:
        {
            if(entryMethodisLogin){
                loginPanel();
            }
            else{
                signupPanel();
            }
        }
        loginOption:
        {
            signupOption = new JButton("SIGN UP");
            this.signupOption.setFocusPainted(false);
            this.signupOption.setBackground(new Color(20,20,20));
            this.signupOption.setForeground(new Color(255,255,255));
            this.signupOption.setBounds(0, this.welcomePanel.getHeight()/2 -15, this.welcomePanel.getWidth(), 50);

            this.signupOption.addActionListener(e -> {
                entryMethodisLogin = false;
                components();
            });

            this.welcomePanel.add(this.signupOption);
        }
        SignupOption:
        {
            loginOption = new JButton("LOGIN");
            this.loginOption.setFocusPainted(false);
            this.loginOption.setBackground(new Color(20,20,20));
            this.loginOption.setForeground(new Color(255,255,255));
            this.loginOption.setBounds(0, this.welcomePanel.getHeight()/2 - 100, this.welcomePanel.getWidth(), 50);

            this.loginOption.addActionListener(e -> {
                entryMethodisLogin = true;
                components();
            });

            this.welcomePanel.add(this.loginOption);
        }
        welcomeLabel:
        {
            this.welcomeText = new JLabel("WELCOME");
            this.welcomeText.setText("WELCOME");
            this.welcomeText.setFont(new Font("Arial", Font.BOLD, 20));
            this.welcomeText.setForeground(new Color(255, 255, 255));
            this.welcomeText.setBackground(Color.WHITE);
            this.welcomeText.getWidth();

            //this.welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
            //this.welcomeText.setBorder(BorderFactory.createLineBorder(Color.RED));
            this.welcomeText.setBounds(this.welcomePanel.getWidth()/2 - 50,0, this.welcomePanel.getWidth(), 50);

            this.welcomePanel.add(this.welcomeText);
            repaint();
        }
    }

    public void loginPanel(){
        this.loginPanel = new JPanel();
        this.loginPanel.setBounds(this.getWidth()/3, 0, 2*this.getWidth()/3+1, this.getHeight());
        this.loginPanel.setBackground(new Color(0, 0, 0));

        JLabel loginLabel = new JLabel("LOGIN");
        JLabel passwordLabel = new JLabel("Password");
        JLabel emailLabel = new JLabel("Email");
        JButton loginButton = new JButton("Login");

        loginLabel.setBounds(this.loginPanel.getWidth()/2 - 25, this.loginPanel.getHeight()/12, this.loginPanel.getWidth()/2, 50);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginLabel.setForeground(new Color(255, 255, 255));

        emailLabel.setBounds(this.loginPanel.getWidth()/4, this.loginPanel.getHeight()/4-50, this.loginPanel.getWidth()/2, 30);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        emailLabel.setForeground(new Color(255, 255, 255));
        emailField = new JTextField();
        this.emailField.setBounds(this.loginPanel.getWidth()/4, this.loginPanel.getHeight()/4, this.loginPanel.getWidth()/2, 30);

        passwordLabel.setBounds(this.loginPanel.getWidth()/4, this.loginPanel.getHeight()/2 - 100, this.loginPanel.getWidth()/2, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passwordLabel.setForeground(new Color(255, 255, 255));
        passwordField = new JPasswordField();
        this.passwordField.setBounds(this.loginPanel.getWidth()/4, this.loginPanel.getHeight()/2 - 50, this.loginPanel.getWidth()/2, 30);

        loginButton.setBounds(this.loginPanel.getWidth()/4, this.loginPanel.getHeight()/2+20, this.loginPanel.getWidth()/2, 30);
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setBackground(new Color(255,165,20));
        loginButton.setFocusPainted(false);

        this.loginPanel.add(emailLabel);
        this.loginPanel.add(passwordLabel);
        this.loginPanel.add(loginLabel);
        this.loginPanel.add(loginButton);
        this.loginPanel.add(emailField);
        this.loginPanel.add(passwordField);

        this.add(this.loginPanel);
    }

    public void signupPanel(){
        this.signupPanel = new JPanel();
        this.signupPanel.setBounds(this.getWidth()/3, 0, 2*this.getWidth()/3+1, this.getHeight());
        this.signupPanel.setBackground(new Color(0, 0, 0));

        JLabel signupLabel = new JLabel("SIGN UP");
        JLabel passwordLabel = new JLabel("Password");
        JLabel emailLabel = new JLabel("Email");
        JLabel nameLabel = new JLabel("Name");
        JButton signupButton = new JButton("Sign Up");
        JLabel reconfirmLabel = new JLabel("Re-confirm");
        JTextField reconfirmField = new JTextField();

        nameLabel.setBounds(this.signupPanel.getWidth()/4, this.signupPanel.getHeight()/4-100, this.signupPanel.getWidth()/2, 30);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(new Color(255, 255, 255));
        nameField = new JTextField();
        this.nameField.setBounds(this.signupPanel.getWidth()/4, this.signupPanel.getHeight()/4 - 50, this.signupPanel.getWidth()/2, 30);

        signupLabel.setBounds(this.signupPanel.getWidth()/2 - 25, this.signupPanel.getHeight()/100, this.signupPanel.getWidth()/2, 50);
        signupLabel.setFont(new Font("Arial", Font.BOLD, 20));
        signupLabel.setForeground(new Color(255, 255, 255));

        int emailPos = (this.signupPanel.getHeight()/4-50 + this.signupPanel.getHeight()/2 - 75)/2;

        emailLabel.setBounds(this.signupPanel.getWidth()/4, emailPos - 25, this.signupPanel.getWidth()/2, 30);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        emailLabel.setForeground(new Color(255, 255, 255));
        emailField = new JTextField();
        this.emailField.setBounds(this.signupPanel.getWidth()/4, emailPos + 25, this.signupPanel.getWidth()/2, 30);

        passwordLabel.setBounds(this.signupPanel.getWidth()/4, this.signupPanel.getHeight()/2 - 75, this.signupPanel.getWidth()/2, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passwordLabel.setForeground(new Color(255, 255, 255));
        passwordField = new JPasswordField();
        this.passwordField.setBounds(this.signupPanel.getWidth()/4, this.signupPanel.getHeight()/2 - 25, this.signupPanel.getWidth()/2, 30);

        signupButton.setBounds(this.signupPanel.getWidth()/4, (int)(this.signupPanel.getHeight()/1.35), this.signupPanel.getWidth()/2, 30);
        signupButton.setFont(new Font("Arial", Font.BOLD, 20));
        signupButton.setForeground(new Color(255, 255, 255));
        signupButton.setBackground(new Color(255,165,20));
        signupButton.setFocusPainted(false);

        int reconPos = (int)(this.signupPanel.getHeight()/1.35 + (double) this.signupPanel.getHeight() /2 - 25)/2;

        reconfirmLabel.setBounds(this.signupPanel.getWidth()/4, reconPos - 25, this.signupPanel.getWidth()/2, 30);
        reconfirmLabel.setFont(new Font("Arial", Font.BOLD, 20));
        reconfirmLabel.setForeground(new Color(255, 255, 255));
        reconfirmField.setBounds(this.signupPanel.getWidth()/4, reconPos + 25, this.signupPanel.getWidth()/2, 30);

        this.signupPanel.add(emailLabel);
        this.signupPanel.add(passwordLabel);
        this.signupPanel.add(signupLabel);
        this.signupPanel.add(signupButton);
        this.signupPanel.add(nameLabel);
        this.signupPanel.add(reconfirmLabel);
        this.signupPanel.add(reconfirmField);
        this.signupPanel.add(nameField);
        this.signupPanel.add(emailField);
        this.signupPanel.add(passwordField);

        this.add(this.signupPanel);
    }


    public static void main(String[] args) {
        new StartUpFrame();
    }
}
