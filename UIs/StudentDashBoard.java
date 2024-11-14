package UIs;

import javax.swing.*;
import java.awt.*;

public class StudentDashBoard extends JFrame {
    StudentDashBoard(){
        setVisible(true);
        setTitle("Student Dash Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1000, 650);

        components();
    }
    void components(){
        JPanel mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());

        SchoolInfo:{   JPanel SpaceCorrectorPanel = new JPanel();
            SpaceCorrectorPanel.setPreferredSize(new Dimension(0, 75));

            JPanel SchoolInfoPanel = new JPanel();
            SchoolInfoPanel.setLayout(new BoxLayout(SchoolInfoPanel, BoxLayout.Y_AXIS));

            JLabel schoolInfoLabel = new JLabel("  VERY GOOD SCHOOL  ");
            schoolInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            schoolInfoLabel.setFont(new Font("Arial", Font.PLAIN, 40));

            SchoolInfoPanel.add(schoolInfoLabel);

            schoolInfoLabel = new JLabel("                      A UNIT OF VERY GOOD SOCIETY");
            schoolInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            schoolInfoLabel.setFont(new Font("Arial", Font.BOLD, 14));

            SchoolInfoPanel.add(schoolInfoLabel);
            SpaceCorrectorPanel.add(SchoolInfoPanel);

            mainPanel.add(SpaceCorrectorPanel, BorderLayout.NORTH);
        }

        JPanel smth = new JPanel();
        smth.setBorder(BorderFactory.createLineBorder(Color.red));
        smth.setLayout(new BorderLayout());

        BombasticSideEye:{
            JPanel SidePanel = new JPanel();

            SidePanel.setBackground(new Color(0, 255, 0, 255));
            SidePanel.setBorder(BorderFactory.createLineBorder(Color.cyan));
            SidePanel.setPreferredSize(new Dimension(200, 0));

            smth.add(SidePanel, BorderLayout.WEST);
        }

        MainInfoArea:{
            JPanel mainArea = new JPanel();


            smth.add(mainArea, BorderLayout.CENTER);
        }

        mainPanel.add(smth);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new StudentDashBoard();
    }
}
