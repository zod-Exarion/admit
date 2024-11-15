package UIs;

import Database.Student;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
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
    public JTextField addressField;
    // Default constructor
    public ApplicationForm() {
        initializeComponents();
        addComponentsToMainPanel();
    }

    // Constructor with parameters to initialize fields
    public ApplicationForm(String firstName, String lastName, Date dateOfBirth, String gender, int marks, String bap, long bapPhone, String ma, long maPhone, String address) {
        initializeFields(firstName, lastName, dateOfBirth, gender, marks, bap, bapPhone, ma, maPhone, address);
        addComponentsToMainPanel();
    }

    public void initializeFields(String firstName, String lastName, Date dateOfBirth, String gender, int marks, String bap, long bapPhone, String ma, long maPhone, String address) {
        firstNameField = new JTextField(firstName);
        lastNameField = new JTextField(lastName);

        dateOfBirth.setYear(dateOfBirth.getYear() - 1900);
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
        addressField = new JTextField(address);
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
        addressField = new JTextField();
    }

    public void addComponentsToMainPanel() {
        // Create a sub-panel with a vertical layout for structured addition of each form section
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
        subPanel.setBackground(new Color(32,32,32));

        Color bgColor = new Color(255, 255, 255);
        Color fieldForeGround = new Color(137, 150, 236);

        Title:{
            JPanel titlePanel = new JPanel(new GridLayout(2, 1));

            JLabel titleLabel = new JLabel("Student Application Form", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
            titleLabel.setForeground(Color.white);

            JLabel infoLabel = new JLabel("   Student Information: All Spaces are mandatory.", SwingConstants.LEFT);
            infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
            infoLabel.setForeground(new Color(243, 96, 15));

            titlePanel.add(titleLabel);
            titlePanel.add(infoLabel);
            titlePanel.setBackground(new Color(32,32,32));


            subPanel.add(titlePanel);
        }

        NameSpace: {
            JPanel namePanel = new JPanel(new GridLayout(3, 2, 20, 0));

            JLabel firstNameLabel = new JLabel("First Name");
            firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
            firstNameLabel.setForeground(Color.white);
            JLabel lastNameLabel = new JLabel("Last Name");
            lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
            lastNameLabel.setForeground(Color.white);

            firstNameField.setPreferredSize(new Dimension(0, 10));
            firstNameField.setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 3));
            if(firstNameField.getText() != null)
                firstNameField.setText("  "+firstNameField.getText());
            firstNameField.setBackground(new Color(32,32,32));
            firstNameField.setForeground(fieldForeGround);


            lastNameField.setPreferredSize(new Dimension(0, 10));
            lastNameField.setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 3));
            if(lastNameField.getText() != null)
                lastNameField.setText("  "+lastNameField.getText());
            lastNameField.setBackground(new Color(32,32,32));
            lastNameField.setForeground(fieldForeGround);

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
                dobPanel.setBackground(new Color(32,32,32));

                JLabel dobLabel = new JLabel("Date Of Birth");
                dobLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
                dobLabel.setForeground(bgColor);

                dobPanel.add(dobLabel);
                dobPanel.add(Box.createHorizontalStrut(0));  // Add spacing
                dobPanel.add(dateSpinner);

                JComponent editor = dateSpinner.getEditor();
                if (editor instanceof JSpinner.DefaultEditor) {
                    JFormattedTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
                    textField.setBackground(new Color(32, 32, 32));  // Set your desired background color
                    textField.setForeground(fieldForeGround); // Set your desired foreground color
                    textField.setCaretColor(Color.green);
                    textField.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 0)); // Optional: Border for the text field
                }

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
            genderLabel.setForeground(bgColor);

            maleButton.setFont(new Font("Arial", Font.PLAIN, 20));
            maleButton.setActionCommand("M");
            femaleButton.setFont(new Font("Arial", Font.PLAIN, 20));
            femaleButton.setActionCommand("F");
            otherButton.setFont(new Font("Arial", Font.PLAIN, 20));
            otherButton.setActionCommand("T");

            maleButton.setBackground(new Color(32,32,32));
            maleButton.setForeground(fieldForeGround);

            femaleButton.setBackground(new Color(32,32,32));
            femaleButton.setForeground(fieldForeGround);

            otherButton.setBackground(new Color(32,32,32));
            otherButton.setForeground(fieldForeGround);

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
            marksLabel.setForeground(bgColor);

            marksField.setPreferredSize(new Dimension(100, 35));
            marksField.setFont(new Font("Tahoma", Font.PLAIN, 20));
            if(marksField.getText() != null)
                marksField.setText(" " + marksField.getText());

            marksField.setBackground(new Color(32,32,32));
            marksField.setForeground(fieldForeGround);

            marksPanel.add(marksLabel);
            marksPanel.add(marksField);

            JLabel contactInfoLabel = new JLabel("   Contact Information: Fill in All Spaces.", SwingConstants.LEFT);
            contactInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
            contactInfoLabel.setForeground(new Color(243, 96, 15));

            subPanel.add(marksPanel);

            JPanel temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
            temp.add(contactInfoLabel);
            subPanel.add(temp);
        }

        MotherFatherInfo: {
            Father: {
                JPanel fatherPanel = new JPanel(new GridLayout(2, 1));

                JPanel fatherNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel fatherNameLabel = new JLabel("   Father's Name:");
                fatherNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
                fatherNameLabel.setForeground(bgColor);

                fatherNamePanel.add(fatherNameLabel);
                fatherNamePanel.add(bapField);

                JPanel fatherPhonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel fatherPhoneLabel = new JLabel("   Father's Phone:");
                fatherPhoneLabel.setForeground(bgColor);
                fatherPhoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

                fatherPhonePanel.add(fatherPhoneLabel);
                fatherPhonePanel.add(bapKaPhoneField);

                fatherPanel.add(fatherNamePanel);
                fatherPanel.add(fatherPhonePanel);

                for(Component c: fatherPanel.getComponents()){
                    for(Component m: ((JPanel)c).getComponents())
                        if(m instanceof JTextField k){
                            if(k.getText() != null)
                                k.setText(" " + k.getText());

                            k.setPreferredSize(new Dimension(250, 35));
                            k.setBackground(new Color(32,32,32));
                            k.setForeground(fieldForeGround);
                            k.setFont(new Font("Tahoma", Font.PLAIN, 15));
                        }
                }

                subPanel.add(fatherPanel);
            }

            Mother: {
                JPanel motherPanel = new JPanel(new GridLayout(2, 1));

                JPanel motherNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel motherNameLabel = new JLabel("   Mother's Name:");
                motherNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
                motherNameLabel.setForeground(bgColor);
                maField.setPreferredSize(new Dimension(250, 25));
                maField.setFont(new Font("Tahoma", Font.PLAIN, 15));

                motherNamePanel.add(motherNameLabel);
                motherNamePanel.add(maField);

                JPanel motherPhonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel motherPhoneLabel = new JLabel("   Mother's Phone:");
                motherPhoneLabel.setForeground(bgColor);
                motherPhoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
                maKaPhoneField.setPreferredSize(new Dimension(250, 25));
                maKaPhoneField.setFont(new Font("Tahoma", Font.PLAIN, 15));

                motherPhonePanel.add(motherPhoneLabel);
                motherPhonePanel.add(maKaPhoneField);

                motherPanel.add(motherNamePanel);
                motherPanel.add(motherPhonePanel);

                for(Component c: motherPanel.getComponents()){
                    for(Component m: ((JPanel)c).getComponents())
                        if(m instanceof JTextField k){
                            if(k.getText() != null)
                                k.setText(" " + k.getText());

                            k.setPreferredSize(new Dimension(250, 35));
                            k.setBackground(new Color(32,32,32));
                            k.setForeground(fieldForeGround);
                            k.setFont(new Font("Tahoma", Font.PLAIN, 15));
                        }
                }

                subPanel.add(motherPanel);
            }
        }

        Address:{
            JPanel addressPanel = new JPanel();
            addressPanel.setLayout(new FlowLayout(FlowLayout.LEADING));


            JLabel addressLabel = new JLabel("   Address:");
            addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
            addressLabel.setForeground(bgColor);
            addressPanel.add(addressLabel);

            addressField.setFont(new Font("Tahoma", Font.PLAIN, 15));
            addressField.setPreferredSize(new Dimension(600, 40));
            if(addressField.getText() != null)
                addressField.setText("  " + addressField.getText());
            addressField.setBackground(new Color(32,32,32));
            addressField.setForeground(fieldForeGround);


            addressPanel.add(addressField);


            subPanel.add(addressPanel);;
        }

        Buttons: {
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 0));

            discardButton = new JButton("Discard");
            discardButton.setBackground(new Color(255,165,20));
            discardButton.setForeground(Color.black);
            discardButton.setPreferredSize(new Dimension(150, 35));
            discardButton.setFont(new Font("Tahoma", Font.BOLD, 20));

            saveButton = new JButton("Save");
            saveButton.setBackground(new Color(255,165,20));
            saveButton.setForeground(Color.black);
            saveButton.setPreferredSize(new Dimension(150, 35));
            saveButton.setFont(new Font("Tahoma", Font.BOLD, 15));

            buttonPanel.add(discardButton);
            buttonPanel.add(saveButton);

            subPanel.add(buttonPanel);
        }
        scrollPane = new JScrollPane(subPanel);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane);

        for(Component c: subPanel.getComponents()){
            if( c instanceof JPanel) {
                c.setBackground(new Color(32, 32, 32));
                for (Component k : ((JPanel)c).getComponents()) {
                    if (k instanceof JPanel)
                        k.setBackground(new Color(32, 32, 32));
                }
            }
        }
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
            String address = this.addressField.getText();

            Date date = (Date) dateSpinner.getValue();

            // Convert to LocalDate
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            int year = localDate.getYear();
            int month = localDate.getMonthValue() - 1;
            int day = localDate.getDayOfMonth();

            Date dob = new Date(year, month, day);
            System.out.println(dob);

            Student student = new Student(name,dob,gender,marks,fname,fno,mname,mno, address);
            student.save();
            JOptionPane.showMessageDialog(null,"Registration Successful","INFO",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Make sure to fill all fields!!","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }

    void save(Student student){
        File myFile = new File("Students/" + student.getID() + ".txt");
        myFile.delete();

        save();
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

        ApplicationForm form = new ApplicationForm("Adrito", "Gayen", new Date(), "Male", 69, "Someone", 11234L, "Someone ELse", 12324L, "Somewhere in this universe of aww, pain, agon and death i guess.");

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


