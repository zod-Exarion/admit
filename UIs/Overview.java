package UIs;

import Database.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class Overview {

    private JPanel AdminPageContent;
    private JPanel Filters;
    private final JPanel mainPanel = new JPanel();
    private final JPanel DisplayArea = new JPanel();

    private final ImageIcon finalClickedIcon;
    private final ImageIcon finalDefaultIcon;

    private JPanel StudentArea;
    private JScrollPane scrollPane;
    private JPanel filler;

    private ApplicationForm form;

    Overview() {
        ImageIcon defaultIcon = new ImageIcon("/home/meow/IdeaProjects/AdmissionManagment/src/Extend.png");
        ImageIcon clickedIcon = new ImageIcon("/home/meow/IdeaProjects/AdmissionManagment/src/Retract.png");
        finalClickedIcon = new ImageIcon(clickedIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        finalDefaultIcon = new ImageIcon(defaultIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        components();
    }

    public static Overview getInstance() {
        return new Overview();
    }

    private void components() {
        AdminPageContent = new JPanel(new BorderLayout());
        mainPanel.setLayout(new BorderLayout());
        mainPanel.removeAll();
        mainPanel.add(AdminPageContent);

        AdminPageContent.setBackground(Color.WHITE);

        // Title setup
        JLabel title = new JLabel("Student Management System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setPreferredSize(new Dimension(1200, 75));
        title.setForeground(Color.BLACK);
        AdminPageContent.add(title, BorderLayout.NORTH);

        // Filters panel setup
        Filters = new JPanel(new GridLayout(12, 0, 0, 10));
        Filters.setBackground(new Color(40, 40, 40));
        JLabel FLabel = new JLabel("Filters", SwingConstants.CENTER);
        FLabel.setPreferredSize(new Dimension(100, 20));
        FLabel.setFont(new Font("Arial", Font.BOLD, 20));
        FLabel.setForeground(Color.WHITE);
        FLabel.setName("Filters");
        Filters.add(FLabel);
        AdminPageContent.add(Filters, BorderLayout.WEST);

        // Student display area setup
        DisplayArea.setBackground(Color.gray);
        DisplayArea.setLayout(new BorderLayout());
        JLabel filterWarn = new JLabel("Select A Filter.", SwingConstants.CENTER);
        filterWarn.setFont(new Font("Arial", Font.BOLD, 40));
        DisplayArea.add(filterWarn, BorderLayout.CENTER);
        AdminPageContent.add(DisplayArea, BorderLayout.CENTER);

        // Add grade filter buttons
        for (int i = Student.MIN_GRADE; i <= Student.MAX_GRADE; i++) {
            JScrollPane currFilteredSpace = createStudentArea(i);
            JButton gradeFilter = new JButton("Class " + i);
            gradeFilter.setPreferredSize(new Dimension(150, 50));
            if (currFilteredSpace == null) continue;
            gradeFilter.setBackground(Color.WHITE);
            gradeFilter.setForeground(Color.BLACK);
            gradeFilter.setFont(new Font("Arial", Font.BOLD, 20));
            gradeFilter.setFocusPainted(true);
            gradeFilter.setName(Integer.toString(i));
            Filters.add(gradeFilter);

            int finalI = i;
            gradeFilter.addActionListener(e -> {
                DisplayArea.removeAll();
                DisplayArea.add(createStudentArea(finalI), BorderLayout.CENTER);
                mainPanel.revalidate();
                mainPanel.repaint();
            });
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public JScrollPane createStudentArea(int grade) {
        var students = (new Student()).fetch(grade);
        if (students.isEmpty()) return null;

        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        StudentArea = new JPanel();
        StudentArea.setLayout(new BoxLayout(StudentArea, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(StudentArea);

        for (var student : students) {
            ArrayList<Component> components = drawContainer(student);

            JPanel container = (JPanel)components.getFirst();
            JPanel ExtensionInfo = drawExtensions(student);

            JButton extend = (JButton)components.getLast();

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
                    isDefaultIcon = !isDefaultIcon;

                    scrollPane.revalidate();
                    scrollPane.repaint();
                }
            });

            StudentArea.add(container);
            StudentArea.add(ExtensionInfo);

            ApplicationForm form = getApplicationForm(student, components);

            JButton delete = (JButton)components.get(2);

            delete.addActionListener(e -> {
                for (Component c : StudentArea.getComponents()) {
                    if (c == container) {
                        File myFile = new File("Students/" + c.getName() + ".txt");
                        System.out.println(myFile.getAbsolutePath());
                        System.out.println(myFile.delete());
                        StudentArea.remove(c);
                    }
                }
                filler = new JPanel();
                filler.setPreferredSize(new Dimension(0, 112));
                filler.setBackground(new Color(7, 7, 28));
                StudentArea.add(filler);

                mainPanel.revalidate();
                mainPanel.repaint();
            });

            form.discardButton.addActionListener(e -> {
                mainPanel.removeAll();
                mainPanel.add(AdminPageContent);

                mainPanel.revalidate();
                mainPanel.repaint();
            });

            for (ActionListener l : form.saveButton.getActionListeners())
                form.saveButton.removeActionListener(l);

            form.saveButton.addActionListener(e -> {
                form.save(student);

                mainPanel.removeAll();
                mainPanel.add(AdminPageContent);
                components();

                for (Component c : Filters.getComponents()) {
                    if (c.getName().equals(Integer.toString(student.getGrade()))) {
                        if (c instanceof JButton) {
                            System.out.println(c.getName());
                            ((JButton) c).doClick();
                        }
                    }
                }

                mainPanel.revalidate();
                mainPanel.repaint();
            });
        }

        filler = new JPanel();
        filler.setPreferredSize(new Dimension(0, 720 - students.size() * 112));
        filler.setBackground(new Color(7, 7, 28));
        StudentArea.add(filler);
        StudentArea.setBackground(Color.cyan);

        scrollPane.revalidate();
        scrollPane.repaint();

        return scrollPane;
    }

    private ApplicationForm getApplicationForm(Student student, ArrayList<Component> components) {
        ApplicationForm form = new ApplicationForm(
                student.getName().split(" ")[0],
                student.getName().split(" ")[1],
                student.getDob(),
                student.getGender(),
                student.getMarks(),
                student.getFname(),
                student.getFno(),
                student.getMname(),
                student.getMno(),
                student.getAddress()
        );

        JButton edit = (JButton) components.get(1);

        edit.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(form.getContentPanel());

            mainPanel.revalidate();
            mainPanel.repaint();
        });
        return form;
    }

    ArrayList<Component> drawContainer(Student student) {
        JPanel container = new JPanel();
        drawContainer(student, container);
        return drawContainer(student, container);
    }

    ArrayList<Component> drawContainer(Student student, JPanel container) {
        ArrayList<Component> components = new ArrayList<>();
        
        container.setName(student.getID());
        container.setLayout(new BorderLayout());
        container.setPreferredSize(new Dimension(0, 100));

        JPanel InfoSpace = new JPanel();
        InfoSpace.setLayout(new BoxLayout(InfoSpace, BoxLayout.Y_AXIS));

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

        JPanel TopPanel = new JPanel(new GridLayout(2, 1));

        JButton edit = new JButton("Edit");
        JPanel editPanel = new JPanel();
        edit.setFocusPainted(false);
        edit.setBackground(Color.WHITE);
        edit.setForeground(Color.black);
        edit.setPreferredSize(new Dimension(75, 25));
        editPanel.add(edit);

        JButton delete = new JButton("Delete");
        JPanel deletePanel = new JPanel();
        delete.setFocusPainted(false);
        delete.setBackground(Color.WHITE);
        delete.setForeground(Color.RED);
        delete.setPreferredSize(new Dimension(75, 25));
        deletePanel.add(delete);

        JPanel modifyPanel = new JPanel(new GridLayout(1, 2));
        modifyPanel.add(editPanel);
        modifyPanel.add(deletePanel);
        TopPanel.add(modifyPanel);

        container.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        container.add(InfoSpace, BorderLayout.WEST);
        container.add(TopPanel, BorderLayout.EAST);

        JButton extend = new JButton(finalDefaultIcon);
        extend.setPreferredSize(new Dimension(50, 50));
        extend.setFocusPainted(false);
        extend.setContentAreaFilled(false);
        extend.setBorderPainted(false);

        JPanel filler = new JPanel();
        filler.add(extend);
        TopPanel.add(filler);

        components.add(container);
        components.add(edit);
        components.add(delete);
        components.add(extend);
        
        return components;
    }

    JPanel drawExtensions(Student student) {
        JPanel ExtensionInfo = new JPanel(new GridLayout(4, 2));
        ExtensionInfo.setVisible(false);

        ExtensionInfo.add(new JLabel("Gender: " + student.getGender(), JLabel.LEFT)).setFont(new Font("Arial", Font.PLAIN, 18));
        ExtensionInfo.add(new JLabel("Age: " + student.getAge(), JLabel.LEFT)).setFont(new Font("Arial", Font.PLAIN, 18));
        ExtensionInfo.add(new JLabel("Father: " + student.getFname(), JLabel.LEFT)).setFont(new Font("Arial", Font.PLAIN, 18));
        ExtensionInfo.add(new JLabel("Father's contact: +91 " + student.getFno(), JLabel.LEFT)).setFont(new Font("Arial", Font.PLAIN, 18));
        ExtensionInfo.add(new JLabel("Mother: " + student.getMname(), JLabel.LEFT)).setFont(new Font("Arial", Font.PLAIN, 18));
        ExtensionInfo.add(new JLabel("Mother's contact: +91 " + student.getMno(), JLabel.LEFT)).setFont(new Font("Arial", Font.PLAIN, 18));
        ExtensionInfo.add(new JLabel("Marks obtained: " + student.getMarks(), JLabel.LEFT)).setFont(new Font("Arial", Font.PLAIN, 18));

        return ExtensionInfo;
    }

    Component getContentPanel() {
        return mainPanel;
    }
}

class Main{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Overview view = new Overview();

        frame.add(view.getContentPanel());

        frame.setSize(1400, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
