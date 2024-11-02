package AdminPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Database.Student;

/*
    I wish you luck O brave warrior for you have chosen to explore the

    ALMIGHTY SPAGHETTI CODE
*/

public class Overveiw extends JFrame {

    private JPanel AdminPageContent;

    Overveiw() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);

        components();
    }

    void components() {
        AdminPageContent = new JPanel();
        setContentPane(AdminPageContent);
        AdminPageContent.setBackground(Color.WHITE);
        AdminPageContent.setLayout(new BorderLayout());

        Title:
        {
            JLabel Title = new JLabel("Student Management System");
            Title.setHorizontalAlignment(SwingConstants.CENTER);
            //Title.setVerticalAlignment(SwingConstants.NORTH);
            Title.setFont(new Font("Arial", Font.BOLD, 30));
            Title.setPreferredSize(new Dimension(1200, 75));
            Title.setForeground(Color.BLACK);

            AdminPageContent.add(Title, BorderLayout.NORTH);
        }
        Filters:{
            JPanel Filters = new JPanel();
            Filters.add(new JLabel("Student Management System"));
            //Filters.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            Filters.setBackground(new Color(40,40,40));

            //Add the filters here.

            AdminPageContent.add(Filters, BorderLayout.WEST);
        }

        StudentArea:
        {
            var temp = new Student();
            var students = temp.fetch();



            JScrollPane scrolLPane = new JScrollPane();
            AdminPageContent.add(scrolLPane, BorderLayout.CENTER);
            //scrolLPane.setBorder(BorderFactory.createLineBorder(Color.red));

            scrolLPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrolLPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            JPanel DisplayArea = new JPanel();
            DisplayArea.setLayout(new BoxLayout(DisplayArea, BoxLayout.Y_AXIS));
            scrolLPane.setViewportView(DisplayArea);



            for(var student: students){
                JPanel container = new JPanel();
                container.setLayout(new BorderLayout(1,2));

                JPanel InfoSpace = new JPanel();

                InfoSpace.setLayout(new BoxLayout(InfoSpace, BoxLayout.Y_AXIS));
                //InfoSpace.setPreferredSize(new Dimension(0,150));

                //JButton extend = new JButton("Extend");
                JButton edit = new JButton("Edit");
                JButton delete = new JButton("Delete");

                JLabel studentName = new JLabel(" " + student.getName());
                studentName.setFont(new Font("Arial", Font.PLAIN, 30));
                InfoSpace.add(studentName, BorderLayout.WEST);

                JLabel studentInfo = new JLabel("  Class: " + student.getGrade() + " | Sec: " + student.getSection() + " | " + student.getRoll());
                studentInfo.setFont(new Font("Arial", Font.PLAIN, 15));
                studentInfo.setForeground(new Color(50,50,50));
                InfoSpace.add(studentInfo, BorderLayout.WEST);

                JLabel ID = new JLabel("  ID: " + student.getID());
                ID.setFont(new Font("Arial", Font.PLAIN, 15));
                ID.setForeground(new Color(50,50,50));
                InfoSpace.add(ID, BorderLayout.WEST);

                //InfoSpace.setBorder(BorderFactory.createLineBorder(Color.blue));

                JPanel TopPanel = new JPanel();
                TopPanel.setLayout(new GridLayout(2,1));

                JPanel modify = new JPanel();
                modify.setLayout(new GridLayout(1,2));

                JPanel editPanel = new JPanel();
                edit.setFocusPainted(false);
                edit.setBackground(Color.WHITE);
                edit.setForeground(Color.black);
                edit.setPreferredSize(new Dimension(75, 25));
                edit.setAlignmentX(Component.CENTER_ALIGNMENT);
                edit.setAlignmentY(Component.CENTER_ALIGNMENT);
                editPanel.add(edit);

                JPanel deletePanel = new JPanel();
                delete.setFocusPainted(false);
                delete.setBackground(Color.WHITE);
                delete.setForeground(Color.RED);
                delete.setPreferredSize(new Dimension(75, 25));
                delete.setAlignmentX(Component.CENTER_ALIGNMENT);
                delete.setAlignmentY(Component.CENTER_ALIGNMENT);
                deletePanel.add(delete);


                modify.add(editPanel, BorderLayout.CENTER);
                modify.add(deletePanel, BorderLayout.CENTER);


                TopPanel.add(modify);

                JPanel filler = new JPanel();

                ImageIcon defaultIcon = new ImageIcon("/home/meow/IdeaProjects/pandaaa/src/AdminPage/Extend.png");
                ImageIcon clickedIcon = new ImageIcon("/home/meow/IdeaProjects/pandaaa/src/AdminPage/Retract.png");

                defaultIcon = new ImageIcon(defaultIcon.getImage()
                            .getScaledInstance(25, 25, Image.SCALE_SMOOTH));

                clickedIcon = new ImageIcon(clickedIcon.getImage()
                            .getScaledInstance(25, 25, Image.SCALE_SMOOTH));

                JButton extend = new JButton(defaultIcon);
                extend.setPreferredSize(new Dimension(50, 50));
                extend.setHorizontalAlignment(SwingConstants.CENTER);
                extend.setVerticalAlignment(SwingConstants.CENTER);

                extend.setFocusPainted(false);  // Remove focus outline
                extend.setContentAreaFilled(false); // Make the button transparent
                extend.setBorderPainted(false);

                // Add action listener to change the icon when clicked
                ImageIcon finalClickedIcon = clickedIcon;
                ImageIcon finalDefaultIcon = defaultIcon;

                filler.add(extend);
                TopPanel.add(filler);//extend.setBorder(BorderFactory.createLineBorder(Color.black));



                container.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                container.add(InfoSpace, BorderLayout.WEST);

                container.add(TopPanel, BorderLayout.EAST);
                DisplayArea.add(container);

                JPanel ExtensionInfo = new JPanel();

                ExtensionInfo.setPreferredSize(new Dimension(0, 150));
                ExtensionInfo.setLayout(new BoxLayout(ExtensionInfo, BoxLayout.Y_AXIS));

                ExtensionInfo.setVisible(false);

                extend.addActionListener(new ActionListener() {
                    private boolean isDefaultIcon = true;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (isDefaultIcon) {
                            extend.setIcon(finalClickedIcon);
                            ExtensionInfo.setVisible(true);
                        } else {
                            extend.setIcon(finalDefaultIcon);
                            ExtensionInfo.setVisible(false);
                        }
                        isDefaultIcon = !isDefaultIcon; // Toggle icon

                        revalidate();
                        repaint();
                    }
                });
                ExtensionInfo.setLayout(new GridLayout(5,-1));

                {
                    ExtensionInfo.add(new JLabel("Gender: " + student.getGender(), JLabel.LEFT));
                    ExtensionInfo.add(new JLabel("Age: " + student.getAge(), JLabel.LEFT));
                    ExtensionInfo.add(new JLabel("Father: " + student.getFname(), JLabel.LEFT));
                    ExtensionInfo.add(new JLabel("Father's contact: +91 " + student.getFno(), JLabel.LEFT));
                    ExtensionInfo.add(new JLabel("Mother: " + student.getMname(), JLabel.LEFT));
                    ExtensionInfo.add(new JLabel("Mother's contact: +91 " + student.getMno(), JLabel.LEFT));
                    ExtensionInfo.add(new JLabel("Marks obtained: " + student.getMarks(), JLabel.LEFT));


                }

                DisplayArea.add(ExtensionInfo);

                String firstName = student.getName().split(" ")[0];
                String lastName = student.getName().split(" ")[1];

                ApplicationForm form = new ApplicationForm(firstName, lastName, student.getDob(), student.getGender(), student.getMarks(), student.getFname(), student.getFno(), student.getMname(), student.getMno());

                edit.addActionListener(e -> {
                    setContentPane(form.getContentPanel());
                    revalidate();
                    repaint();
                });

                form.discardButton.addActionListener(e -> {
                    setContentPane(AdminPageContent);
                    revalidate();
                    repaint();
                });
            }

            JPanel SpaceHolder = new JPanel();
            SpaceHolder.setPreferredSize(new Dimension(0, getHeight() - students.size()*150+100));

            DisplayArea.add(SpaceHolder);

        }


        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new Overveiw();
    }
}
