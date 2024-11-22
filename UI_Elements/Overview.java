package UI_Elements;

import Database.Student;
import UI_Integrations.StudentUI;

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

    public JButton backButton;

    private ApplicationForm form;

    Overview() {
        ImageIcon defaultIcon = new ImageIcon("Extend.png");
        ImageIcon clickedIcon = new ImageIcon("Retract.png");
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
        SpringLayout layout = new SpringLayout();
        Filters = new JPanel(new GridLayout(12, 1, 0 ,0));

        Filters.setBackground(new Color(40, 40, 40));
        JLabel FLabel = new JLabel("Filters", SwingConstants.CENTER);
        FLabel.setPreferredSize(new Dimension(100, 20));
        FLabel.setFont(new Font("Arial", Font.BOLD, 20));
        FLabel.setForeground(Color.WHITE);
        FLabel.setName("Filters");


        layout.putConstraint(SpringLayout.NORTH, FLabel, 10, SpringLayout.NORTH, Filters);


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
        int finalI = 4;
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

            finalI++;
            int finalI1 = i;
            gradeFilter.addActionListener(e -> {
                DisplayArea.removeAll();
                DisplayArea.add(createStudentArea(finalI1), BorderLayout.CENTER);
                mainPanel.revalidate();
                mainPanel.repaint();
            });
        }


        for(int i = finalI; i < Student.MAX_GRADE + 2; i++){
            Filters.add(new JLabel(""));
        }


        //Get back to Admin DashBoard
        backButton = new JButton("Back");
        Filters.add(backButton);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public JScrollPane createStudentArea(int grade) {
        var students = (new Student()).fetch(grade); // Fetch students of a particular grade
        if (students.isEmpty()) return null;

        // Scroll pane setup
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Main panel to hold student entries
        StudentArea = new JPanel();
        StudentArea.setLayout(new BoxLayout(StudentArea, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking
        StudentArea.setBackground(Color.CYAN);

        // Add each student to the scrollable area
        for (var student : students) {
            ArrayList<Component> components = drawContainer(student);

            JPanel container = (JPanel) components.getFirst(); // Main container for a student
            JPanel extensionInfo = drawExtensions(student); // Hidden panel with additional info
            extensionInfo.setVisible(false); // Initially hidden

            JButton extendButton = (JButton) components.get(4); // Button to toggle visibility of the extension
            extendButton.addActionListener(new ActionListener() {
                private boolean isDefaultIcon = true;

                @Override
                public void actionPerformed(ActionEvent e) {
                    // Toggle visibility and icon
                    if (isDefaultIcon) {
                        extendButton.setIcon(finalClickedIcon);
                        extensionInfo.setVisible(true);
                    } else {
                        extendButton.setIcon(finalDefaultIcon);
                        extensionInfo.setVisible(false);
                    }
                    isDefaultIcon = !isDefaultIcon;

                    // Refresh layout
                    scrollPane.revalidate();
                    scrollPane.repaint();
                }
            });

            // Add main container and hidden extension info
            StudentArea.add(container);
            StudentArea.add(extensionInfo);

            // Add delete functionality
            JButton deleteButton = (JButton) components.get(3);
            deleteButton.addActionListener(e -> {
                File studentFile = new File("Students/" + student.getID() + ".txt");
                if (studentFile.exists() && studentFile.delete()) {
                    StudentArea.remove(container);
                    StudentArea.remove(extensionInfo);
                    StudentArea.revalidate();
                    StudentArea.repaint();
                }
            });

            // Handle edit functionality
            ApplicationForm form = getApplicationForm(student, components);
            JButton editButton = (JButton) components.get(2);
            editButton.addActionListener(e -> {
                mainPanel.removeAll();
                mainPanel.add(form.getContentPanel());
                mainPanel.revalidate();
                mainPanel.repaint();
            });

            StudentUI sUI = new StudentUI(student, true);

            JButton viewButton = (JButton) components.get(1);
            viewButton.addActionListener(e -> {
                mainPanel.removeAll();
                mainPanel.add(sUI.getMainPanel());
                mainPanel.revalidate();
                mainPanel.repaint();
            });

            sUI.BackButton.addActionListener(e -> {
                mainPanel.removeAll();
                mainPanel.add(AdminPageContent);
                mainPanel.revalidate();
                mainPanel.repaint();
            });

            sUI.AdmitButton.addActionListener(e -> {

            });

            // Add save and discard functionality to form
            form.saveButton.addActionListener(e -> {
                form.save(student);
                mainPanel.removeAll();
                mainPanel.add(AdminPageContent);
                components(); // Rebuild the UI
                JButton filterButton = findFilterButton(student.getGrade());
                if (filterButton != null) filterButton.doClick(); // Automatically refresh display
            });

            form.discardButton.addActionListener(e -> {
                mainPanel.removeAll();
                mainPanel.add(AdminPageContent);
                mainPanel.revalidate();
                mainPanel.repaint();
            });
        }

        // Add glue to push content upward if there's extra space
        StudentArea.add(Box.createVerticalGlue());

        // Set the viewport to display the StudentArea
        scrollPane.setViewportView(StudentArea);


        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(5); // Default is 1-5; higher means faster scrolling
        verticalScrollBar.setBlockIncrement(100);


        scrollPane.revalidate();
        scrollPane.repaint();

        return scrollPane;
    }

    private JButton findFilterButton(int grade) {
        for (Component c : Filters.getComponents()) {
            if (c instanceof JButton && c.getName().equals(Integer.toString(grade))) {
                return (JButton) c;
            }
        }
        return null;
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


        JButton view = new JButton("View");
        JPanel viewPanel = new JPanel();
        view.setBackground(Color.WHITE);
        view.setForeground(Color.black);
        view.setPreferredSize(new Dimension(75, 25));
        viewPanel.add(view);

        JButton edit = new JButton("Edit");
        JPanel editPanel = new JPanel();
        edit.setBackground(Color.WHITE);
        edit.setForeground(Color.black);
        edit.setPreferredSize(new Dimension(75, 25));
        editPanel.add(edit);

        JButton delete = new JButton("Delete");
        JPanel deletePanel = new JPanel();
        delete.setBackground(Color.WHITE);
        delete.setForeground(Color.RED);
        delete.setPreferredSize(new Dimension(75, 25));
        deletePanel.add(delete);

        JPanel modifyPanel = new JPanel(new GridLayout(1, 3));
        modifyPanel.add(viewPanel);
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
        components.add(view);
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
