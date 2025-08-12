import java.awt.*;
import javax.swing.*;

public class Todoapp extends JFrame {

    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton;
    private JButton deleteButton;

    public Todoapp() {
        setTitle("To-Do List");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        initUI();
    }

    private void initUI() {
        // Layout
        setLayout(new BorderLayout());

        // Top panel (Input + Add Button)
        JPanel topPanel = new JPanel(new BorderLayout());
        taskField = new JTextField();
        addButton = new JButton("Add Task");

        topPanel.add(taskField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Center panel (Task list)
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel (Delete Button)
        JPanel bottomPanel = new JPanel();
        deleteButton = new JButton("Delete Selected Task");
        bottomPanel.add(deleteButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event listeners
        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> deleteTask());

        // Enter key also adds task
        taskField.addActionListener(e -> addTask());
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement(task);
            taskField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Task cannot be empty.");
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to delete.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Todoapp().setVisible(true);
        });
    }
}
