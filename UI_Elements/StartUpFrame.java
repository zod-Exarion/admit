package UI_Elements;

import Database.Student;
import Tools.FileSystem;
import jdk.jshell.spi.SPIResolutionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class StartUpFrame extends JFrame {
    private final JPanel loginPanel = new JPanel();
    private final JPanel signupPanel = new JPanel();
    private final JPanel mainPanel;

    private JButton loginButton;
    private JButton signUpButton;

    private boolean isLogin = false;

    private final FileSystem file = new FileSystem("passwords.txt");
    private final HashMap<String,String> database = file.fetchDatabase();

    StartUpFrame(){
        mainPanel = new JPanel();
        components();

        ComponentAdapter adapter = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                mainPanel.getComponent(0).setPreferredSize(new Dimension((int)(mainPanel.getWidth()/3.5), mainPanel.getHeight()));


                loginButton.setPreferredSize(new Dimension(mainPanel.getComponent(0).getWidth(), 50));
                signUpButton.setPreferredSize(new Dimension(mainPanel.getComponent(0).getWidth(), 50));

                mainPanel.getComponent(0).revalidate();
                mainPanel.getComponent(0).repaint();

                System.out.println("hmmmmm");
            }
        };

        mainPanel.addComponentListener(adapter);
        mainPanel.setSize(mainPanel.getPreferredSize());
    }

    private void components() {
        mainPanel.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(0,200));
        leftPanel.setBackground(new Color(32,32,32));

        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(leftPanel.getWidth(), 50));
        loginButton.setBackground(new Color(32,32,32));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.white));
        loginButton.setFont(new Font("", Font.PLAIN, 20));
        loginButton.setForeground(Color.white);
        signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(leftPanel.getWidth(), 50));
        signUpButton.setBackground(new Color(32,32,32));
        signUpButton.setBorder(BorderFactory.createLineBorder(Color.white));
        signUpButton.setFont(new Font("", Font.PLAIN, 20));
        signUpButton.setForeground(Color.white);

        leftPanel.add(loginButton);
        leftPanel.add(signUpButton);

        SpringLayout layout = new SpringLayout();
        leftPanel.setLayout(layout);

        layout.putConstraint(SpringLayout.WEST, loginButton, 0, SpringLayout.WEST, leftPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, loginButton, -50, SpringLayout.VERTICAL_CENTER, leftPanel);


        layout.putConstraint(SpringLayout.WEST, signUpButton, 0, SpringLayout.WEST, leftPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, signUpButton, 50, SpringLayout.VERTICAL_CENTER, leftPanel);


        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.BLACK);
        rightPanel.setPreferredSize(new Dimension(200,0));


        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(loginPanel, BorderLayout.CENTER);


        loginButton.addActionListener(e -> {
            rightPanel.removeAll();
            rightPanel.add(loginPanel, BorderLayout.CENTER);
            System.out.println("hello");

            rightPanel.revalidate();
            rightPanel.repaint();
        });

        signUpButton.addActionListener(e -> {
            rightPanel.removeAll();
            rightPanel.add(signupPanel, BorderLayout.CENTER);
            System.out.println("hello");

            rightPanel.revalidate();
            rightPanel.repaint();
        });


        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        setSignupPanel();
        setloginPanel();

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void setloginPanel() {
        SpringLayout layout = new SpringLayout();
        loginPanel.setLayout(layout);
        loginPanel.setBackground(Color.black);

        JLabel Login = new JLabel("Login");
        Login.setForeground(Color.white);
        Login.setFont(new Font("", Font.BOLD, 30));
        loginPanel.add(Login);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, Login, -200, SpringLayout.VERTICAL_CENTER, loginPanel); // Adjust below the header
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, Login, 0, SpringLayout.HORIZONTAL_CENTER, loginPanel);

        JLabel Email = new JLabel("Email:");
        Email.setForeground(Color.white);
        Email.setFont(new Font("", Font.BOLD, 25));
        JTextField EmailField = new JTextField();
        EmailField.setPreferredSize(new Dimension(500, 35));

        loginPanel.add(Email);
        loginPanel.add(EmailField);

        layout.putConstraint(SpringLayout.NORTH, Email, 40, SpringLayout.SOUTH, Login);
        layout.putConstraint(SpringLayout.WEST, Email, -200, SpringLayout.WEST, Login);

        layout.putConstraint(SpringLayout.NORTH, EmailField, 10, SpringLayout.SOUTH, Email);
        layout.putConstraint(SpringLayout.WEST, EmailField, 0, SpringLayout.WEST, Email);

        JLabel Password = new JLabel("Password:");
        Password.setForeground(Color.white);
        Password.setFont(new Font("", Font.BOLD, 25));
        JPasswordField PasswordField = new JPasswordField();
        PasswordField.setPreferredSize(new Dimension(500, 35));

        loginPanel.add(Password);
        loginPanel.add(PasswordField);

        layout.putConstraint(SpringLayout.NORTH, Password, 20, SpringLayout.SOUTH, EmailField);
        layout.putConstraint(SpringLayout.WEST, Password, 0, SpringLayout.WEST, EmailField);

        layout.putConstraint(SpringLayout.NORTH, PasswordField, 10, SpringLayout.SOUTH, Password);
        layout.putConstraint(SpringLayout.WEST, PasswordField, 0, SpringLayout.WEST, EmailField);

        JButton LoginButton = new JButton("Login");
        LoginButton.setForeground(Color.white);
        LoginButton.setFont(new Font("", Font.BOLD, 25));
        LoginButton.setBackground(new Color(237, 116, 24));
        LoginButton.setPreferredSize(new Dimension(500, 35));

        loginPanel.add(LoginButton);

        layout.putConstraint(SpringLayout.NORTH, LoginButton, 40, SpringLayout.SOUTH, PasswordField);
        layout.putConstraint(SpringLayout.WEST, LoginButton, 0, SpringLayout.WEST, PasswordField);

        loginPanel.revalidate();
        loginPanel.repaint();
    }

    public void setSignupPanel(){
        SpringLayout layout = new SpringLayout();
        signupPanel.setLayout(layout);

        JLabel Signup = new JLabel("Signup");
        Signup.setForeground(Color.white);
        Signup.setFont(new Font("", Font.BOLD, 25));
        signupPanel.setBackground(Color.black);
        signupPanel.add(Signup);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, Signup, 0, SpringLayout.HORIZONTAL_CENTER, signupPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, Signup, -250, SpringLayout.VERTICAL_CENTER, signupPanel);



        JLabel Name = new JLabel("Name: ");
        Name.setForeground(Color.white);
        Name.setFont(new Font("", Font.BOLD, 25));
        signupPanel.add(Name);

        layout.putConstraint(SpringLayout.NORTH, Name, 40, SpringLayout.SOUTH, Signup);
        layout.putConstraint(SpringLayout.WEST, Name, -200, SpringLayout.WEST, Signup);



        JTextField NameField = new JTextField();
        NameField.setPreferredSize(new Dimension(500, 35));
        signupPanel.add(NameField);

        layout.putConstraint(SpringLayout.NORTH, NameField, 10, SpringLayout.SOUTH, Name);
        layout.putConstraint(SpringLayout.WEST, NameField, 0, SpringLayout.WEST, Name);



        JLabel Email = new JLabel("Email:");
        Email.setForeground(Color.white);
        Email.setFont(new Font("", Font.BOLD, 25));
        signupPanel.add(Email);


        layout.putConstraint(SpringLayout.NORTH, Email, 10, SpringLayout.SOUTH, NameField);
        layout.putConstraint(SpringLayout.WEST, Email, 0, SpringLayout.WEST, NameField);



        JTextField EmailField = new JTextField();
        EmailField.setPreferredSize(new Dimension(500, 35));
        signupPanel.add(EmailField);


        layout.putConstraint(SpringLayout.NORTH, EmailField, 10, SpringLayout.SOUTH, Email);
        layout.putConstraint(SpringLayout.WEST, EmailField, 0, SpringLayout.WEST, Email);



        JLabel Password = new JLabel("Password:");
        Password.setForeground(Color.white);
        Password.setFont(new Font("", Font.BOLD, 25));
        signupPanel.add(Password);


        layout.putConstraint(SpringLayout.NORTH, Password, 10, SpringLayout.SOUTH, EmailField);
        layout.putConstraint(SpringLayout.WEST, Password, 0, SpringLayout.WEST, EmailField);



        JPasswordField PasswordField = new JPasswordField();
        PasswordField.setPreferredSize(new Dimension(500, 35));
        signupPanel.add(PasswordField);


        layout.putConstraint(SpringLayout.NORTH, PasswordField, 10, SpringLayout.SOUTH, Password);
        layout.putConstraint(SpringLayout.WEST, PasswordField, 0, SpringLayout.WEST, Password);



        JLabel repeatPassword = new JLabel("Reconfirm Password:");
        repeatPassword.setForeground(Color.white);
        repeatPassword.setFont(new Font("", Font.BOLD, 25));
        signupPanel.add(repeatPassword);


        layout.putConstraint(SpringLayout.NORTH, repeatPassword, 10, SpringLayout.SOUTH, PasswordField);
        layout.putConstraint(SpringLayout.WEST, repeatPassword, 0, SpringLayout.WEST, PasswordField);



        JPasswordField repeatPasswordField = new JPasswordField();
        repeatPasswordField.setPreferredSize(new Dimension(500, 35));
        signupPanel.add(repeatPasswordField);


        layout.putConstraint(SpringLayout.NORTH, repeatPasswordField, 10, SpringLayout.SOUTH, repeatPassword);
        layout.putConstraint(SpringLayout.WEST, repeatPasswordField, 0, SpringLayout.WEST, repeatPassword);



        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setForeground(Color.black);
        signUpButton.setFont(new Font("", Font.BOLD, 25));
        signUpButton.setBackground(new Color(237, 116, 24));
        signUpButton.setPreferredSize(new Dimension(500, 35));
        signupPanel.add(signUpButton);


        layout.putConstraint(SpringLayout.NORTH, signUpButton, 40, SpringLayout.SOUTH, repeatPasswordField);
        layout.putConstraint(SpringLayout.WEST, signUpButton, 0, SpringLayout.WEST, repeatPasswordField);
    }

    public void signupPanel(){
        signupPanel.removeAll();

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}

class MainStartUpFrame extends JFrame {
    MainStartUpFrame(){
        this.setTitle("Start Up Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 650);

        this.setContentPane((new StartUpFrame()).getMainPanel());
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainStartUpFrame();
    }
}