package AdminPage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Calendar;
import java.util.Date;

public class ApplicationForm extends JFrame {
    private JScrollPane scrollPane;

    ApplicationForm() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Application Form");
        setSize(1400, 800);
        setLayout(null);

        components();

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                scrollPane.setPreferredSize(new Dimension(evt.getComponent().getWidth() - 200, evt.getComponent().getHeight() - 200));
            }
        });

        setVisible(true);
    }

    void components(){
        this.getContentPane().removeAll();
        mainPanel:
        {
            JPanel mainPanel = new JPanel();
            setContentPane(mainPanel);
            mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
            mainPanel.setBounds(0, 0, getWidth(), getHeight());
            mainPanel.setBackground(Color.BLACK);

            ScrollPanelForApplicationFormDisplay:
            {
                scrollPane = new JScrollPane();
                scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setPreferredSize(new Dimension(1000, 600));

                scrollPane.addMouseWheelListener(new MouseWheelListener() {
                    @Override
                    public void mouseWheelMoved(MouseWheelEvent e) {
                        int notches = e.getWheelRotation();
                        int scrollAmount = 20;
                        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();

                        verticalScrollBar.setValue(verticalScrollBar.getValue() + notches * scrollAmount);
                    }
                });

                subPanel:
                {
                    JPanel subPanel = new JPanel();
                    scrollPane.setViewportView(subPanel);

                    subPanel.setLayout(new GridLayout(7,1));

                    Title:{
                        JPanel titlePanel = new JPanel();
                        titlePanel.setLayout(new GridLayout(2,1));

                        JLabel label = new JLabel("Student Application Form");
                        label.setFont(new Font("Tahoma", Font.BOLD, 32));
                        label.setForeground(Color.BLACK);
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        label.setPreferredSize(new Dimension(1000, 50));
                        //label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                        JLabel studentINFO = new JLabel("   Student Information: \n All Spaces are mandatory.");
                        studentINFO.setFont(new Font("Tahoma", Font.PLAIN, 25));
                        studentINFO.setVerticalAlignment(SwingConstants.CENTER);




                        titlePanel.add(label);

                        titlePanel.add(studentINFO);

                        subPanel.add(titlePanel);
                    }


                    NameSpace:{
                        JPanel GreaterSpace = new JPanel();


                        GreaterSpace.add(new JLabel("   "));
                        GreaterSpace.setLayout(new FlowLayout(FlowLayout.LEFT));

                        JPanel NameSpace = new JPanel();
                        JLabel FirstName = new JLabel("First Name");
                        FirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
                        JLabel LastName = new JLabel("Last Name");
                        LastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
                        JTextField FirstNameField = new JTextField();
                        JTextField LastNameField = new JTextField();

                        FirstNameField.setPreferredSize(new Dimension(100, 10));

                        NameSpace.setPreferredSize(new Dimension(1000, 110));

                        NameSpace.setLayout(new GridLayout(3,2,20,5));
                        NameSpace.add(FirstName);
                        NameSpace.add(LastName);
                        NameSpace.add(FirstNameField);
                        NameSpace.add(LastNameField);
                        //ameSpace.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                        DOBSpace:{
                            JPanel DOBSpace = new JPanel();

                            JLabel DOB = new JLabel("Date Of Birth");
                            DOB.setFont(new Font("Tahoma", Font.PLAIN, 20));
                            SpinnerDateModel model = new SpinnerDateModel();
                            JSpinner dateSpinner = new JSpinner(model);
                            JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
                            dateSpinner.setEditor(editor);

                            // Add a ChangeListener to capture the selected date
                            dateSpinner.addChangeListener(new ChangeListener() {
                                @Override
                                public void stateChanged(ChangeEvent e) {
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime((Date) dateSpinner.getValue());
                                    //System.out.println("Selected Date: " + cal.getTime());
                                }
                            });
                            dateSpinner.setPreferredSize(new Dimension(100, 29));

                            DOBSpace.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
                            DOBSpace.add(DOB);
                            DOBSpace.add(new JLabel("    "));
                            DOBSpace.add(dateSpinner);

                            //NameSpace.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                            NameSpace.add(DOBSpace);
                        }

                        //GreaterSpace.setBorder(BorderFactory.createLineBorder(Color.black));

                        GreaterSpace.setPreferredSize(new Dimension(1000, 100));

                        GreaterSpace.add(NameSpace);
                        subPanel.add(GreaterSpace);
                    }

                    GenderSpace:{
                        JPanel GenderSpace = new JPanel();

                        JLabel Gender = new JLabel("   Gender:");
                        Gender.setPreferredSize(new Dimension(100, 25));
                        Gender.setFont(new Font("Tahoma", Font.PLAIN, 20));
                        //Gender.setBorder(BorderFactory.createLineBorder(Color.black));

                        //GenderSpace.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        GenderSpace.setLayout(new FlowLayout(FlowLayout.LEFT));

                        Formatter:{
                            JPanel Formatter = new JPanel();
                            Formatter.setPreferredSize(new Dimension(1000, 100));
                            Formatter.setLayout(new GridLayout(3,1,20,5));

                            JRadioButton MaleButton = new JRadioButton("Male");
                            MaleButton.setFont(new Font("Arial", Font.PLAIN, 20));

                            JRadioButton FemaleButton = new JRadioButton("Female");
                            FemaleButton.setFont(new Font("Arial", Font.PLAIN, 20));

                            JRadioButton OtherButton = new JRadioButton("Other");
                            OtherButton.setFont(new Font("Arial", Font.PLAIN, 20));

                            ButtonGroup genderGroup = new ButtonGroup();
                            genderGroup.add(MaleButton);
                            genderGroup.add(FemaleButton);
                            genderGroup.add(OtherButton);


                            Formatter.add(MaleButton);
                            Formatter.add(FemaleButton);
                            Formatter.add(OtherButton);

                            GenderSpace.add(Gender);
                            GenderSpace.add(Formatter);
                        }

                        subPanel.add(GenderSpace);
                    }

                    Marks_and_Trans:{
                        JPanel GreaterSpace = new JPanel();
                        GreaterSpace.setLayout(new GridLayout(2,1,0,0));

                        JPanel marksPanel = new JPanel();
                        marksPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                        //marksPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                        marksPanel.setPreferredSize(new Dimension(1000, 25));

                        JLabel marks = new JLabel("   Marks:");
                        marks.setFont(new Font("Tahoma", Font.PLAIN, 20));

                        marksPanel.add(marks);

                        JTextField marksField = new JTextField();
                        marksField.setPreferredSize(new Dimension(100, 25));
                        marksField.setFont(new Font("Tahoma", Font.PLAIN, 20));

                        marksPanel.add(marksField);

                        //GreaterSpace.setBorder(BorderFactory.createLineBorder(Color.black));
                        GreaterSpace.add(marksPanel);

                        JLabel contactInfo = new JLabel("   Contact Information: Fill in All Spaces.");
                        contactInfo.setFont(new Font("Tahoma", Font.PLAIN, 25));

                        GreaterSpace.add(contactInfo);

                        subPanel.add(GreaterSpace);

                    }
                    
                    MotherFatherBapManers:{
                        Father:{
                            JPanel InfoPanelBoutTereBap = new JPanel();
                            InfoPanelBoutTereBap.setLayout(new GridLayout(2,1,0,0));
                            
                            JPanel BapKaNam = new JPanel();
                            BapKaNam.setLayout(new FlowLayout(FlowLayout.LEFT));
                            //BapKaNam.setBorder(BorderFactory.createLineBorder(Color.black));
                            
                            JLabel Bap = new JLabel("    Tera Bap Kaun?");
                            Bap.setFont(new Font("Tahoma", Font.PLAIN, 20));
                            JTextField BapField = new JTextField();
                            BapField.setPreferredSize(new Dimension(250, 25));

                            BapKaNam.add(Bap);
                            BapKaNam.add(BapField);

                            JPanel BapKaPhoneBata = new JPanel();
                            BapKaPhoneBata.setLayout(new FlowLayout(FlowLayout.LEFT));

                            JLabel BapKaPhone = new JLabel("    Bap ka number bol:");
                            BapKaPhone.setFont(new Font("Tahoma", Font.PLAIN, 20));
                            JTextField BapKaPhoneField = new JTextField();
                            BapKaPhoneField.setPreferredSize(new Dimension(250, 25));

                            BapKaPhoneBata.add(BapKaPhone);
                            BapKaPhoneBata.add(BapKaPhoneField);

                            InfoPanelBoutTereBap.add(BapKaNam);
                            InfoPanelBoutTereBap.add(BapKaPhoneBata);

                            subPanel.add(InfoPanelBoutTereBap);
                        }

                        Mother:{
                            JPanel InfoPanelBoutTereMa = new JPanel();
                            InfoPanelBoutTereMa.setLayout(new GridLayout(2,1,0,0));

                            JPanel MaKaNam = new JPanel();
                            MaKaNam.setLayout(new FlowLayout(FlowLayout.LEFT));
                            //MaKaNam.setBorder(BorderFactory.createLineBorder(Color.black));

                            JLabel Ma = new JLabel("    Tera Ma Kaun?");
                            Ma.setFont(new Font("Tahoma", Font.PLAIN, 20));
                            JTextField MaField = new JTextField();
                            MaField.setPreferredSize(new Dimension(250, 25));

                            MaKaNam.add(Ma);
                            MaKaNam.add(MaField);

                            JPanel MaKaPhoneBata = new JPanel();
                            MaKaPhoneBata.setLayout(new FlowLayout(FlowLayout.LEFT));

                            JLabel MaKaPhone = new JLabel("    Ma ka number bol:");
                            MaKaPhone.setFont(new Font("Tahoma", Font.PLAIN, 20));
                            JTextField MaKaPhoneField = new JTextField();
                            MaKaPhoneField.setPreferredSize(new Dimension(250, 25));

                            MaKaPhoneBata.add(MaKaPhone);
                            MaKaPhoneBata.add(MaKaPhoneField);

                            InfoPanelBoutTereMa.add(MaKaNam);
                            InfoPanelBoutTereMa.add(MaKaPhoneBata);

                            subPanel.add(InfoPanelBoutTereMa);
                        }
                    }
                    NiceCar:{
                        JPanel niceCarPanel = new JPanel();
                        niceCarPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 0));

                        JButton DiscardButton = new JButton("Discard");
                        JButton SaveButton = new JButton("Save");

                        niceCarPanel.add(DiscardButton);
                        niceCarPanel.add(SaveButton);

                        subPanel.add(niceCarPanel);
                    }
                }

                mainPanel.add(scrollPane);
            }
        }

        revalidate();
        repaint();

    }
    public static void main(String[] args) {
        new ApplicationForm();
        System.out.println("Cool Car \uD83D\uDC31");
    }
}
