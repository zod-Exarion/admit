package AdminPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/*
    I wish you luck O brave warrior for you have chosen to explore the

    ALMIGHTY SPAGHETTI CODE
*/

public class Overview extends JFrame {

    private JPanel AdminPageContent;
    private JPanel Filters;

    private JPanel DisplayArea = new JPanel();
    private JButton extend,edit,delete;

    private int currGradeFilter = 0;
    private JScrollPane currFilteredSpace;


    ImageIcon defaultIcon = new ImageIcon("/home/meow/IdeaProjects/pandaaa/src/AdminPage/Extend.png");
    ImageIcon clickedIcon = new ImageIcon("/home/meow/IdeaProjects/pandaaa/src/AdminPage/Retract.png");


    ImageIcon finalClickedIcon = new ImageIcon(clickedIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
    ImageIcon finalDefaultIcon = new ImageIcon(defaultIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));

    Overview() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 750);

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

            Title.setFont(new Font("Arial", Font.BOLD, 30));
            Title.setPreferredSize(new Dimension(1200, 75));
            Title.setForeground(Color.BLACK);

            AdminPageContent.add(Title, BorderLayout.NORTH);
        }
        Filters:
        {
            Filters = new JPanel();

            JLabel FLabel = new JLabel("Filters");
            FLabel.setPreferredSize(new Dimension(100, 20));
            FLabel.setHorizontalAlignment(SwingConstants.CENTER);
            FLabel.setFont(new Font("Arial", Font.BOLD, 20));
            FLabel.setForeground(Color.WHITE);
            FLabel.setName("Filters");

            Filters.add(FLabel);
            Filters.setBackground(new Color(40, 40, 40));
            Filters.setLayout(new GridLayout(12,0, 0, 10));

            AdminPageContent.add(Filters, BorderLayout.WEST);
        }

        StudentArea:
        {
            DisplayArea = new JPanel();
            DisplayArea.setBackground(Color.gray);
            DisplayArea.setLayout(new BorderLayout());
            JLabel filterWarn = new JLabel("Select A Filter.");
            filterWarn.setFont(new Font("Arial", Font.BOLD, 40));
            filterWarn.setHorizontalAlignment(SwingConstants.CENTER);
            DisplayArea.add(filterWarn, BorderLayout.CENTER);

            AdminPageContent.add(DisplayArea, BorderLayout.CENTER);

            for(int i = Student.MIN_GRADE; i <= Student.MAX_GRADE; i++) {
                JScrollPane StudentArea = createStudentArea(i);
                JButton gradeFilter = new JButton("Class " + i);
                gradeFilter.setPreferredSize(new Dimension(150, 50));

                if(StudentArea == null) continue;
                gradeFilter.setBackground(new Color(255, 255, 255));
                gradeFilter.setForeground(Color.BLACK);
                gradeFilter.setFont(new Font("Arial", Font.BOLD, 20));
                gradeFilter.setFocusPainted(false);

                gradeFilter.setName(Integer.toString(i));

                Filters.add(gradeFilter);

                int finalI = i;
                gradeFilter.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        DisplayArea.removeAll();
                        DisplayArea.add(StudentArea, BorderLayout.CENTER);
                        currGradeFilter = finalI;

                        revalidate();
                        repaint();
                    }
                });
            }
        }

        revalidate();
        repaint();
    }

    public JScrollPane createStudentArea(int grade) {
        var students = Student.fetch(grade);
        if (students.isEmpty()) return null;
        Color color = Color.cyan;

        // Initialize the scroll pane and main student area panel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel StudentArea = new JPanel();
        StudentArea.setLayout(new BoxLayout(StudentArea, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(StudentArea);

        for (var student : students) {
            JPanel container = drawContainer(student);
            JPanel ExtensionInfo = drawExtensions(student);


            // Action to toggle extension visibility and icon
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

                    scrollPane.revalidate();
                    scrollPane.repaint();
                }
            });

            StudentArea.add(container);

            StudentArea.add(ExtensionInfo);

            // Edit button logic
            ApplicationForm form = new ApplicationForm(
                    student.getName().split(" ")[0],
                    student.getName().split(" ")[1],
                    student.getDob(),
                    student.getGender(),
                    student.getMarks(),
                    student.getFname(),
                    student.getFno(),
                    student.getMname(),
                    student.getMno()
            );

            edit.addActionListener(e -> {
                setContentPane(form.getContentPanel());
                revalidate();
                repaint();
            });

            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(Component c: StudentArea.getComponents()) {
                        if(c == container) {
                            File myFile = new File("Students/" + c.getName() + ".txt");
                            System.out.println(myFile.getAbsolutePath());
                            System.out.println(myFile.delete());
                            StudentArea.remove(c);
                        }
                    }
                    JPanel filler = new JPanel();
                    filler.setPreferredSize(new Dimension(0, 112));
                    filler.setBackground(new Color(7,7,28));
                    StudentArea.add(filler);

                    revalidate();
                    repaint();
                }
            });

            // Discard action to go back to admin panel
            form.discardButton.addActionListener(e -> {
                setContentPane(AdminPageContent);
                revalidate();
                repaint();
            });


            for(ActionListener l: form.saveButton.getActionListeners())
                form.saveButton.removeActionListener(l);

            form.saveButton.addActionListener(e -> {
                form.save(student);
                setContentPane(AdminPageContent);
                components();

                for(Component c: Filters.getComponents()){
                    if(c.getName().equals(Integer.toString(student.getGrade()))) {
                        if(c instanceof JButton){
                            System.out.println(c.getName());
                            ((JButton) c).doClick();
                        }
                    }
                }

                revalidate();
                repaint();
            });

        }

        JPanel SpaceFiller = new JPanel();
        SpaceFiller.setPreferredSize(new Dimension(0, 720 - students.size() * 112));
        SpaceFiller.setBackground(new Color(7,7,28));
        StudentArea.add(SpaceFiller);

        StudentArea.setBackground(Color.cyan);

        // Validate and repaint after setting up content
        scrollPane.revalidate();
        scrollPane.repaint();

        return scrollPane;
    }

    JPanel drawContainer(Student student) {
        JPanel container = new JPanel();
        drawContainer(student, container);
        return container;
    }

    JPanel drawContainer(Student student, JPanel container) {
        container.setName(student.getID());

        container.setLayout(new BorderLayout());
        container.setPreferredSize(new Dimension(0, 100));

        JPanel InfoSpace = new JPanel();
        InfoSpace.setLayout(new BoxLayout(InfoSpace, BoxLayout.Y_AXIS));


        // Student's basic info
        JLabel studentName = new JLabel(" " + student.getName());
        studentName.setFont(new Font("Arial", Font.PLAIN, 30));
        InfoSpace.add(studentName);

        JLabel studentInfo = new JLabel("  Class: " + student.getGrade() + " | Sec: " + student.getSection() + " | " + student.getRoll());
        studentInfo.setFont(new Font("Arial", Font.PLAIN, 15));
        studentInfo.setForeground(new Color(50, 50, 50));
        InfoSpace.add(studentInfo);

        JLabel ID = new JLabel("  ID: " + student.getID());
        ID.setFont(new Font("Arial", Font.PLAIN, 15));
        ID.setForeground(new Color(50, 50, 50));
        InfoSpace.add(ID);

        JPanel TopPanel = new JPanel();
        TopPanel.setName("Top Panel");
        TopPanel.setLayout(new GridLayout(2, 1));

        // Add Edit and Delete buttons
        edit = new JButton("Edit");
        JPanel editPanel = new JPanel();
        edit.setFocusPainted(false);
        edit.setBackground(Color.WHITE);
        edit.setForeground(Color.black);
        edit.setPreferredSize(new Dimension(75, 25));
        edit.setAlignmentX(Component.CENTER_ALIGNMENT);
        edit.setAlignmentY(Component.CENTER_ALIGNMENT);
        editPanel.add(edit);

        delete = new JButton("Delete");
        JPanel deletePanel = new JPanel();
        delete.setFocusPainted(false);
        delete.setBackground(Color.WHITE);
        delete.setForeground(Color.RED);
        delete.setPreferredSize(new Dimension(75, 25));
        delete.setAlignmentX(Component.CENTER_ALIGNMENT);
        delete.setAlignmentY(Component.CENTER_ALIGNMENT);
        deletePanel.add(delete);

        JPanel modifyPanel = new JPanel(new GridLayout(1, 2));
        modifyPanel.add(editPanel);
        modifyPanel.add(deletePanel);
        TopPanel.add(modifyPanel);

        container.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        container.add(InfoSpace, BorderLayout.WEST);
        container.add(TopPanel, BorderLayout.EAST);

        extend = new JButton(finalDefaultIcon);
        extend.setPreferredSize(new Dimension(50, 50));
        extend.setFocusPainted(false);
        extend.setContentAreaFilled(false);
        extend.setBorderPainted(false);

        JPanel filler = new JPanel();
        filler.add(extend);
        TopPanel.add(filler);

        return container;
    }

    JPanel drawExtensions(Student student) {

        // Extension info panel, initially hidden
        JPanel ExtensionInfo = new JPanel();
        ExtensionInfo.setLayout(new GridLayout(4,2));

        ExtensionInfo.setVisible(false);

        ExtensionInfo.add(new JLabel("Gender: " + student.getGender(), JLabel.LEFT)).setFont(new Font("Arial", Font.PLAIN, 18));
        ExtensionInfo.add(new JLabel("Age: " + student.getAge(), JLabel.LEFT)).setFont(new Font("Arial", Font.PLAIN, 18));
        ExtensionInfo.add(new JLabel("Father: " + student.getFname(), JLabel.LEFT)).setFont(new Font("Arial", Font.PLAIN, 18));
        ExtensionInfo.add(new JLabel("Father's contact: +91 " + student.getFno(), JLabel.LEFT)).setFont(new Font("Arial", Font.PLAIN, 18));
        ExtensionInfo.add(new JLabel("Mother: " + student.getMname(), JLabel.LEFT)).setFont(new Font("Arial", Font.PLAIN, 18));
        ExtensionInfo.add(new JLabel("Mother's contact: +91 " + student.getMno(), JLabel.LEFT)).setFont(new Font("Arial", Font.PLAIN, 18));
        ExtensionInfo.add(new JLabel("Marks obtained: " + student.getMarks(), JLabel.LEFT)).setFont(new Font("Arial", Font.PLAIN, 18));


        // Action to toggle extension visibility and icon
        return ExtensionInfo;
    }


    public static void main(String[] args) {
        new Overview();
    }
}
