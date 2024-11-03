package AdminPage;

import Database.Student;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class ApplicationForm {
    public JPanel mainPanel;
    public JScrollPane scrollPane;

    // public data members for all input fields
    public JTextField firstNameField;
    public JTextField lastNameField;
    public JSpinner dateSpinner;
    public ButtonGroup genderGroup;
    public JRadioButton maleButton;
    public JRadioButton femaleButton;
    public JRadioButton otherButton;
    public JTextField marksField;
    public JTextField bapField;
    public JTextField bapKaPhoneField;
    public JTextField maField;
    public JTextField maKaPhoneField;
    public JButton saveButton;
    public JButton discardButton;

    // Default constructor
    public ApplicationForm() {
        initializeComponents();
        addComponentsToMainPanel();
    }

    // Constructor with parameters to initialize fields
    public ApplicationForm(String firstName, String lastName, Date dateOfBirth, String gender, int marks, String bap, long bapPhone, String ma, long maPhone) {
        initializeFields(firstName, lastName, dateOfBirth, gender, marks, bap, bapPhone, ma, maPhone);
        addComponentsToMainPanel();
    }

    public void initializeFields(String firstName, String lastName, Date dateOfBirth, String gender, int marks, String bap, long bapPhone, String ma, long maPhone) {
        firstNameField = new JTextField(firstName);
        lastNameField = new JTextField(lastName);

        SpinnerDateModel model = new SpinnerDateModel(dateOfBirth, null, null, Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(editor);

        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        otherButton = new JRadioButton("Other");

        // No pre-selection on gender
        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherButton);

        switch (gender.toLowerCase().charAt(0)) {
            case 'm':
                maleButton.setSelected(true);
                break;
            case 'f':
                femaleButton.setSelected(true);
                break;
            default:
                otherButton.setSelected(true);
        }

        marksField = new JTextField();
        marksField.setText(String.valueOf(marks));
        bapField = new JTextField(bap);
        bapKaPhoneField = new JTextField(bapPhone + "");
        maField = new JTextField(ma);
        maKaPhoneField = new JTextField(maPhone + "");
    }

    public void initializeComponents() {
        firstNameField = new JTextField();
        lastNameField = new JTextField();

        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(editor);

        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        otherButton = new JRadioButton("Other");

        // No pre-selection on gender
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherButton);

        marksField = new JTextField();
        bapField = new JTextField();
        bapKaPhoneField = new JTextField();
        maField = new JTextField();
        maKaPhoneField = new JTextField();
    }

    public void addComponentsToMainPanel() {
        // Create a sub-panel with a vertical layout for structured addition of each form section
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));

        Title:{
            JPanel titlePanel = new JPanel(new GridLayout(2, 1));

            JLabel titleLabel = new JLabel("Student Application Form", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
            titleLabel.setForeground(Color.BLACK);

            JLabel infoLabel = new JLabel("   Student Information: All Spaces are mandatory.", SwingConstants.LEFT);
            infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));

            titlePanel.add(titleLabel);
            titlePanel.add(infoLabel);

            subPanel.add(titlePanel);
        }

        NameSpace: {
            JPanel namePanel = new JPanel(new GridLayout(3, 2, 20, 5));

            JLabel firstNameLabel = new JLabel("First Name");
            firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
            JLabel lastNameLabel = new JLabel("Last Name");
            lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

            firstNameField.setPreferredSize(new Dimension(0, 10));
            lastNameField.setPreferredSize(new Dimension(0, 10));

            firstNameField.setFont(new Font("Tahoma", Font.PLAIN, 22));
            lastNameField.setFont(new Font("Tahoma", Font.PLAIN, 22));

            //namePanel.setBorder(BorderFactory.createLineBorder(Color.black));

            namePanel.add(firstNameLabel);
            namePanel.add(lastNameLabel);
            namePanel.add(firstNameField);
            namePanel.add(lastNameField);

            namePanel.setPreferredSize(new Dimension(1000, 150));

            DOBSpace: {
                JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

                JLabel dobLabel = new JLabel("Date Of Birth");
                dobLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

                dobPanel.add(dobLabel);
                dobPanel.add(Box.createHorizontalStrut(10));  // Add spacing
                dobPanel.add(dateSpinner);

                dateSpinner.setPreferredSize(new Dimension(150, 35));
                dateSpinner.setFont(new Font("Tahoma", Font.PLAIN, 20));

                namePanel.add(dobPanel);
            }
            JPanel temp = new JPanel();
            temp.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 10));
            temp.add(namePanel);

            //temp.setBorder(BorderFactory.createLineBorder(Color.black));

            subPanel.add(temp);
        }

        GenderSpace: {
            JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel genderLabel = new JLabel("   Gender:");
            genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

            maleButton.setFont(new Font("Arial", Font.PLAIN, 20));
            femaleButton.setFont(new Font("Arial", Font.PLAIN, 20));
            otherButton.setFont(new Font("Arial", Font.PLAIN, 20));

            genderPanel.add(genderLabel);
            genderPanel.add(maleButton);
            genderPanel.add(femaleButton);
            genderPanel.add(otherButton);

            subPanel.add(genderPanel);
        }

        Marks_and_Trans: {
            JPanel marksPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel marksLabel = new JLabel("   Marks:");
            marksLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

            marksField.setPreferredSize(new Dimension(100, 25));
            marksField.setFont(new Font("Tahoma", Font.PLAIN, 20));

            marksPanel.add(marksLabel);
            marksPanel.add(marksField);

            JLabel contactInfoLabel = new JLabel("   Contact Information: Fill in All Spaces.", SwingConstants.LEFT);
            contactInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));

            subPanel.add(marksPanel);

            JPanel temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
            temp.add(contactInfoLabel);
            subPanel.add(temp);
        }

        MotherFatherInfo: {
            Father: {
                JPanel fatherPanel = new JPanel(new GridLayout(2, 1));

                JPanel fatherNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel fatherNameLabel = new JLabel("    Father's Name:");
                fatherNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
                bapField.setPreferredSize(new Dimension(250, 25));
                bapField.setFont(new Font("Tahoma", Font.PLAIN, 15));

                fatherNamePanel.add(fatherNameLabel);
                fatherNamePanel.add(bapField);

                JPanel fatherPhonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel fatherPhoneLabel = new JLabel("    Father's Phone:");
                fatherPhoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
                bapKaPhoneField.setPreferredSize(new Dimension(250, 25));
                bapKaPhoneField.setFont(new Font("Tahoma", Font.PLAIN, 15));

                fatherPhonePanel.add(fatherPhoneLabel);
                fatherPhonePanel.add(bapKaPhoneField);

                fatherPanel.add(fatherNamePanel);
                fatherPanel.add(fatherPhonePanel);

                subPanel.add(fatherPanel);
            }

            Mother: {
                JPanel motherPanel = new JPanel(new GridLayout(2, 1));

                JPanel motherNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel motherNameLabel = new JLabel("    Mother's Name:");
                motherNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
                maField.setPreferredSize(new Dimension(250, 25));
                maField.setFont(new Font("Tahoma", Font.PLAIN, 15));

                motherNamePanel.add(motherNameLabel);
                motherNamePanel.add(maField);

                JPanel motherPhonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel motherPhoneLabel = new JLabel("    Mother's Phone:");
                motherPhoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
                maKaPhoneField.setPreferredSize(new Dimension(250, 25));
                maKaPhoneField.setFont(new Font("Tahoma", Font.PLAIN, 15));

                motherPhonePanel.add(motherPhoneLabel);
                motherPhonePanel.add(maKaPhoneField);

                motherPanel.add(motherNamePanel);
                motherPanel.add(motherPhonePanel);

                subPanel.add(motherPanel);
            }
        }

        Buttons: {
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 50, 0));

            discardButton = new JButton("Discard");
            saveButton = new JButton("Save");

            buttonPanel.add(discardButton);
            buttonPanel.add(saveButton);

            subPanel.add(buttonPanel);
        }
        scrollPane = new JScrollPane(subPanel);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane);
    }

    void save(){

        try{
            String name = this.firstNameField.getText() + " " + this.lastNameField.getText();
            String gender = this.genderGroup.getSelection().getActionCommand();
            int marks =  Integer.parseInt(this.marksField.getText());
            String fname = this.bapField.getText();
            long fno = Long.parseLong(this.bapKaPhoneField.getText());
            String mname = this.maField.getText();
            long mno = Long.parseLong(this.maKaPhoneField.getText());

            Student student = new Student(name,(Date)this.dateSpinner.getValue(),gender,marks,fname,fno,mname,mno);
            System.out.println(student);
            JOptionPane.showMessageDialog(null,"Registration Successful","INFO",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Make sure to fill all fields!!","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }


    // Method to return main panel as a reusable component
    public JPanel getContentPanel() {
        return mainPanel;
    }
}
class MainAppFrame extends JFrame {
    public MainAppFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Application Frame");
        setSize(1400, 800);

        ApplicationForm form = new ApplicationForm("Adrito", "Gayen", new Date(), "Male", 69, "Someone", 11234L, "Someone ELse", 12324L);

        JPanel formPanel = form.getContentPanel();
        form.saveButton.addActionListener(e -> form.save());

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainAppFrame();
    }
}
