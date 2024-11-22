package UI_Integrations;

import Database.Fees;
import Database.Student;
import UI_Elements.ApplicationForm;
import UI_Elements.FeesReport;
import UI_Elements.StudentDashBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentUI{
    private boolean adminAccess = false;
    public JButton BackButton;
    public JButton AdmitButton;

    private JPanel mainPanel;
    public StudentUI(Student student) {
        components(student);
    }
    public StudentUI(Student student, boolean adminAccess) {
        this.adminAccess = adminAccess;
        components(student);
    }

    void components(Student student) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel floatingButtonPanel = new JPanel();
        floatingButtonPanel.setPreferredSize(new Dimension(0, 50));
        floatingButtonPanel.setLayout(new GridLayout(1,3));

        JPanel mainDisplayPanel = new JPanel();
        mainDisplayPanel.setLayout(new BorderLayout());


        JButton DashBoardButton = new JButton("DashBoard");
        JButton FeesReportButton = new JButton("Fees Report");
        JButton EditWindowButton = new JButton("Edit");


        floatingButtonPanel.add(DashBoardButton);
        floatingButtonPanel.add(FeesReportButton);
        floatingButtonPanel.add(EditWindowButton);

        setButtonColor(floatingButtonPanel);

        StudentDashBoard dashBoard = new StudentDashBoard(student, adminAccess);

        JPanel StudentDashboardPanel = dashBoard.getMainPanel();
        JPanel FeesReportPanel = new FeesReport(student).getMainPanel();
        JPanel EditWindowPanel = new ApplicationForm(student).getContentPanel();

        DashBoardButton.addActionListener(e -> {
            setButtonColor(floatingButtonPanel);
            DashBoardButton.setBackground(new Color(32,32,32));

            mainDisplayPanel.removeAll();
            mainDisplayPanel.add(StudentDashboardPanel);

            mainDisplayPanel.revalidate();
            mainDisplayPanel.repaint();
        });

        FeesReportButton.addActionListener(e -> {
            setButtonColor(floatingButtonPanel);
            FeesReportButton.setBackground(new Color(32,32,32));

            mainDisplayPanel.removeAll();
            mainDisplayPanel.add(FeesReportPanel);

            mainDisplayPanel.revalidate();
            mainDisplayPanel.repaint();
        });

        EditWindowButton.addActionListener(e -> {
            setButtonColor(floatingButtonPanel);
            EditWindowButton.setBackground(new Color(32,32,32));

            mainDisplayPanel.removeAll();
            mainDisplayPanel.add(EditWindowPanel);

            mainDisplayPanel.revalidate();
            mainDisplayPanel.repaint();
        });

        BackButton = dashBoard.Back;
        AdmitButton = dashBoard.Admit;


        mainPanel.add(floatingButtonPanel, BorderLayout.NORTH);
        mainPanel.add(mainDisplayPanel);

    }

    void setButtonColor(JPanel floatingButtonPanel) {
        for (Component c : floatingButtonPanel.getComponents()) {
            if (c instanceof JButton) {
                c.setBackground(new Color(64, 64, 64));
                c.setForeground(Color.WHITE);
                c.setFont(new Font("", Font.PLAIN, 20));
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
class MainStudentUI extends JFrame {
    MainStudentUI(Student student) {
        setTitle("Student Information");
        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );
        setSize(1300, 750);

        setContentPane(new StudentUI(student).getMainPanel());
        setVisible(true);
    }
    public static void main(String[] args) {
        Student s = new Student();
        s = s.fetch(10).getFirst();
        new MainStudentUI(s);
    }
}
