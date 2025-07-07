package Main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class StudentGradeTracker extends JFrame{
	 private JTextField nameField, gradeField;
	    private JButton addButton, summaryButton;
	    private JTable table;
	    private DefaultTableModel model;

	    private ArrayList<String> studentNames = new ArrayList<>();
	    private ArrayList<Double> studentGrades = new ArrayList<>();

	    public StudentGradeTracker() {
	        setTitle("🎓 Student Grade Tracker");
	        setSize(600, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null); // Center the window
	        setLayout(new BorderLayout(10, 10));

	        // Input panel (top)
	        JPanel inputPanel = new JPanel(new FlowLayout());
	        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Student Grade"));

	        nameField = new JTextField(10);
	        gradeField = new JTextField(5);
	        addButton = new JButton("Add");
	        summaryButton = new JButton("Show Summary");

	        inputPanel.add(new JLabel("Name:"));
	        inputPanel.add(nameField);
	        inputPanel.add(new JLabel("Grade:"));
	        inputPanel.add(gradeField);
	        inputPanel.add(addButton);
	        inputPanel.add(summaryButton);

	        add(inputPanel, BorderLayout.NORTH);

	        // Table model and JTable
	        model = new DefaultTableModel(new Object[]{"Name", "Grade"}, 0);
	        table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);

	        add(scrollPane, BorderLayout.CENTER);

	        // Event handlers
	        addButton.addActionListener(e -> addStudent());
	        summaryButton.addActionListener(e -> showSummary());

	        setVisible(true);
	    }

	    private void addStudent() {
	        String name = nameField.getText().trim();
	        String gradeText = gradeField.getText().trim();

	        if (name.isEmpty() || gradeText.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Please enter both name and grade.", "Input Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        try {
	            double grade = Double.parseDouble(gradeText);
	            if (grade < 0 || grade > 100) {
	                JOptionPane.showMessageDialog(this, "Grade must be between 0 and 100.", "Input Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            studentNames.add(name);
	            studentGrades.add(grade);
	            model.addRow(new Object[]{name, grade});

	            nameField.setText("");
	            gradeField.setText("");

	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "Please enter a valid number for the grade.", "Input Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }

	    private void showSummary() {
	        if (studentGrades.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "No student data available.", "Summary", JOptionPane.INFORMATION_MESSAGE);
	            return;
	        }

	        double total = 0, max = Double.MIN_VALUE, min = Double.MAX_VALUE;
	        String topStudent = "", bottomStudent = "";

	        for (int i = 0; i < studentGrades.size(); i++) {
	            double grade = studentGrades.get(i);
	            total += grade;

	            if (grade > max) {
	                max = grade;
	                topStudent = studentNames.get(i);
	            }

	            if (grade < min) {
	                min = grade;
	                bottomStudent = studentNames.get(i);
	            }
	        }

	        double average = total / studentGrades.size();

	        String summary = String.format(
	                "📊 Student Grade Summary:\n" +
	                "Total Students: %d\n" +
	                "Average Grade: %.2f\n" +
	                "Highest Grade: %.2f (%s)\n" +
	                "Lowest Grade: %.2f (%s)",
	                studentGrades.size(), average, max, topStudent, min, bottomStudent
	        );

	        JOptionPane.showMessageDialog(this, summary, "Grade Summary", JOptionPane.INFORMATION_MESSAGE);
	    }
	    public static void main(String[] args) {
			SwingUtilities.invokeLater(StudentGradeTracker::new); 
		}
	

}

/* Features
  GUI created using java swing
  stores student names and grades in ArrayList
  Display the data in a JTable
  Calculate and shows average, highest and lowest score in a summary dialog
  
 
How to Run This Program

Compile and run:
java StudentGradeTracker.java
java StudentGradeTracker
GUI will open allowing you to:

Enter student names and grades

Click "Add" to insert data into the table

Click "Show Summary" to display average, max, and min grades

GUI Features
Styled with titled borders, spacing, and icons.

Uses JTable to display data cleanly.

Uses JOptionPane for summary display.

Handles all input errors with proper validation.


 * */
