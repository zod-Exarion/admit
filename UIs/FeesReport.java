package UIs;

import Database.Student;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import Database.*;

public class FeesReport extends JFrame {
    Student student;
    public JPanel mainPanel;

    FeesReport(Student student) {
        setTitle("Fees Report");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(1000,650));

        this.student = student;

        components();

        setVisible(true);
        if(this.student == null) {
            throw new NullPointerException("Student is null");
        }
    }

    void components(){
        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        JPanel FeeDisp = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // GridBagLayout weights for controlling column widths
        double[] columnWeights = {0.01, 0.7, 0.2}; // Left: 25%, Middle: 50%, Right: 25%
        for (int col = 0; col < 3; col++) {
            gbc.gridx = col;
            gbc.gridy = 0;
            gbc.weightx = columnWeights[col];
            gbc.weighty = 0.1; // Adjust this if rows should scale vertically
            gbc.fill = GridBagConstraints.BOTH;
            JLabel placeholder = new JLabel(col == 0? "SL. No.": col == 1? "Topic": "Fees", SwingConstants.CENTER);
            placeholder.setFont(new Font("Arial", Font.BOLD, 20));
            placeholder.setOpaque(true);
            placeholder.setBackground(new Color(200 + col * 20, 200, 255));
            placeholder.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            FeeDisp.add(placeholder, gbc);
        }

        HashMap<String, Double> map = (new Fees(this.student)).getFees();
        String[] arr = {"Admission Fees:","Tuition Fees:","Practical Fees:","Late Fine:","Monthly Fees:","Student Scholarship:","Total Fees:"};


        // Populate 10 rows with dummy buttons
        for (int row = 1; row <= 7; row++) {
            for (int col = 0; col < 3; col++) {
                gbc.gridx = col;
                gbc.gridy = row;
                gbc.weightx = columnWeights[col];
                gbc.weighty = 0.1;
                gbc.fill = GridBagConstraints.BOTH;
                JTextField field = new JTextField();
                field.setBackground(new Color(200 + col * 20, 200, 255));
                field.setEditable(false);
                field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                field.setForeground(Color.BLACK);
                field.setHorizontalAlignment(JTextField.CENTER);
                field.setFont(new Font("Arial", Font.PLAIN, 18));

                if(col == 0)
                    field.setText(row + "");
                if(col == 1)
                    field.setText(arr[row - 1]);
                if(col == 2)
                    field.setText(map.get(arr[row - 1]) + "");
                if(row==1 && col==2 && this.student.getActive())
                    field.setText("PAID");
                if(row == 6 && col == 2)
                    field.setText(field.getText() + " %");
                if(col==2 && this.student.getPaid())
                    field.setText("PAID");

                FeeDisp.add(field, gbc);
            }
        }
        mainPanel.add(FeeDisp, BorderLayout.CENTER);

        JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        newPanel.setPreferredSize(new Dimension(0,65));
        mainPanel.add(newPanel, BorderLayout.SOUTH);

        JButton PayAdmission = new JButton("Pay Admission");
        PayAdmission.setPreferredSize(new Dimension(300,55));
        PayAdmission.setBackground(new Color(220,150,255));
        PayAdmission.setFont(new Font("Arial", Font.BOLD, 26));

        PayAdmission.addActionListener(e -> {
            this.student.toggleActive(true);
            mainPanel.removeAll();
            FeesReport f = new FeesReport(this.student);
            this.setVisible(false);
        });

        JButton PayAll = new JButton("Pay All");
        PayAll.setPreferredSize(new Dimension(150,55));
        PayAll.setBackground(new Color(240,150,255));
        PayAll.setFont(new Font("Arial", Font.BOLD, 26));

        PayAll.addActionListener(e -> {
            this.student.togglePaid(true);
            System.out.println(this.student.getPaid());
            mainPanel.removeAll();
            FeesReport f = new FeesReport(this.student);
            this.setVisible(false);
        });

        newPanel.add(PayAdmission);
        newPanel.add(PayAll);

        mainPanel.revalidate();
        mainPanel.repaint();

    }

    public static void main(String[] args) {
        new FeesReport(new Student("Student", new Date(2006 + (int) (8*Math.random()), Calendar.NOVEMBER, 28), "F", 70 + (int) (30*Math.random()), "a", 1L, "b", 2L, "Somewhere in this universe of aww, pain, agony and death i guess."));
    }
}
