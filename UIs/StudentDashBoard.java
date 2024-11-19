package UIs;

import Database.Student;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class StudentDashBoard extends JFrame {
    StudentDashBoard(Student student) {
        setVisible(true);
        setTitle("Student Dash Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1000, 650);

        components(student);
    }
    void components(Student student) {
        JPanel mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());

        SchoolInfo:{   JPanel SpaceCorrectorPanel = new JPanel();
            SpaceCorrectorPanel.setPreferredSize(new Dimension(0, 75));

            JPanel SchoolInfoPanel = new JPanel();
            SchoolInfoPanel.setBackground(new Color(32,32,32));
            SchoolInfoPanel.setLayout(new BoxLayout(SchoolInfoPanel, BoxLayout.Y_AXIS));

            JLabel schoolInfoLabel = new JLabel("  VERY GOOD SCHOOL  ");
            schoolInfoLabel.setForeground(new Color(134, 0, 255));
            schoolInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            schoolInfoLabel.setFont(new Font("Arial", Font.PLAIN, 40));

            SchoolInfoPanel.add(schoolInfoLabel);

            schoolInfoLabel = new JLabel("                      A UNIT OF VERY GOOD SOCIETY");
            schoolInfoLabel.setForeground(new Color(134, 0, 255));
            schoolInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            schoolInfoLabel.setFont(new Font("Arial", Font.BOLD, 14));

            SchoolInfoPanel.add(schoolInfoLabel);
            SpaceCorrectorPanel.add(SchoolInfoPanel);

            SpaceCorrectorPanel.setBackground(new Color(32,32,32));

            mainPanel.add(SpaceCorrectorPanel, BorderLayout.NORTH);
        }

        JPanel smth = new JPanel();
        smth.setLayout(new BorderLayout());

        BombasticSideEye:{
            JPanel SidePanel = new JPanel();
            SidePanel.setBackground(new Color(32,32,32));

            //SidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
            //SidePanel.setLayout(new BorderLayout());
            SpringLayout layout = new SpringLayout();
            SidePanel.setLayout(layout);
            SidePanel.setPreferredSize(new Dimension(250, 0));

            //Display Area for Image
            JPanel imagePanel = new JPanel();
            imagePanel.setPreferredSize(new Dimension(200, 225));
            imagePanel.setBackground(new Color(32,32,32));
            JLabel imageDisplay = new JLabel(new ImageIcon((new ImageIcon("/home/meow/IdeaProjects/AdmissionManagment/src/BlankUser.jpg").getImage().getScaledInstance(200,225, Image.SCALE_SMOOTH))));
            imagePanel.add(imageDisplay);
            SidePanel.add(imagePanel);
            smth.add(SidePanel, BorderLayout.WEST);

            //Choosing Button
            JButton imageChooser = new JButton("Choose Image");
            imageChooser.setFocusPainted(false);
            imageChooser.addActionListener(e -> {
                FileDialog fileDialog = new FileDialog(this, "Select an Image", FileDialog.LOAD);
                fileDialog.setFilenameFilter((dir, name) -> name.toLowerCase().endsWith(".png") ||
                        name.toLowerCase().endsWith(".jpg") ||
                        name.toLowerCase().endsWith(".jpeg") ||
                        name.toLowerCase().endsWith(".bmp") ||
                        name.toLowerCase().endsWith(".gif"));
                fileDialog.setVisible(true);

                String directory = fileDialog.getDirectory();
                String fileName = fileDialog.getFile();
                if (directory != null && fileName != null) {
                    File selectedFile = new File(directory, fileName);
                    try {
                        BufferedImage bufferedImage = ImageIO.read(selectedFile);
                        ImageIcon image = new ImageIcon(bufferedImage.getScaledInstance(200, 225, Image.SCALE_SMOOTH));
                        imagePanel.removeAll();
                        imagePanel.add(new JLabel(image));
                        mainPanel.revalidate();
                        mainPanel.repaint();

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            imageChooser.setPreferredSize(new Dimension(150, 30));

            layout.putConstraint(SpringLayout.NORTH, imagePanel, 100, SpringLayout.WEST, SidePanel);
            layout.putConstraint(SpringLayout.WEST, imagePanel, 20, SpringLayout.WEST, SidePanel);
            layout.putConstraint(SpringLayout.NORTH, imageChooser, 50, SpringLayout.SOUTH, imagePanel);
            layout.putConstraint(SpringLayout.WEST, imageChooser, 40, SpringLayout.WEST, SidePanel);

            SidePanel.add(imageChooser);
        }

        MainInfoArea:{
            JPanel mainArea = new JPanel();
            mainArea.setBackground(new Color(32,32,32));
            mainArea.setLayout(new BoxLayout(mainArea, BoxLayout.Y_AXIS));

            mainArea.add(new JLabel(" "));
            mainArea.add(new JLabel(" "));
            mainArea.add(new JLabel(" "));


            JLabel Name = new JLabel("Name: " + student.getName());
            Name.setForeground(new Color(234, 131, 17));
            Name.setFont(new Font("Arial", Font.PLAIN, 20));
            mainArea.add(Name);
            Name.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainArea.add(new JLabel(" "));


            JLabel Grade = new JLabel("Grade: " + student.getGrade() + "   Sec: " + student.getSection());
            Grade.setForeground(new Color(234, 131, 17));
            Grade.setFont(new Font("Arial", Font.PLAIN, 20));
            mainArea.add(Grade);
            Grade.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainArea.add(new JLabel(" "));


            JTextArea LegalGuardian = getJTextArea(student);

            mainArea.add(LegalGuardian);

            smth.add(mainArea, BorderLayout.CENTER);
        }

        mainPanel.add(smth);

        revalidate();
        repaint();
    }

    private static JTextArea getJTextArea(Student student) {
        JTextArea LegalGuardian = new JTextArea("Legal Guardians: \n" + student.getFname() + "\n" + student.getMname());

        LegalGuardian.setText(LegalGuardian.getText() + "\n\nAddress: \n" + student.getAddress());

        LegalGuardian.setText(LegalGuardian.getText() + "\n\nTel no. : " + student.getFno() + ", " + student.getMno());

        LegalGuardian.setText(LegalGuardian.getText() + "\n\nSession: " + ((new Date()).getYear() + 1900) + "-" + ((new Date()).getYear() + 1901));


        LegalGuardian.setForeground(new Color(234, 131, 17));
        LegalGuardian.setBackground(new Color(32,32,32));
        LegalGuardian.setFont(new Font("Arial", Font.PLAIN, 20));
        LegalGuardian.setLineWrap(true);
        LegalGuardian.setWrapStyleWord(true);
        return LegalGuardian;
    }


    public static void main(String[] args) {
        Student s = new Student();
        s = s.fetch(10).getFirst();
        new StudentDashBoard(s);
    }
}
