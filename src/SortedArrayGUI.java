// src/SortedArrayGUI.java
import javax.swing.*;
import java.awt.*;

public class SortedArrayGUI {
    JFrame frame;
    JPanel panel;
    JTextField inputField;
    JButton addButton;
    JButton searchButton;
    JTextArea displayArea;
    SortedArray sortedArray;

    public SortedArrayGUI() {
        sortedArray = new SortedArray(100);

        frame = new JFrame("Sorted String List");
        panel = new JPanel(new FlowLayout());
        inputField = new JTextField(12);
        addButton = new JButton("Add");
        searchButton = new JButton("Search");
        displayArea = new JTextArea(12, 32);
        displayArea.setEditable(false);

        addButton.addActionListener(e -> {
            try {
                String value = inputField.getText();
                sortedArray.add(value);
                log("ADD \"" + value.trim() + "\"");
                log("List: " + sortedArray);
                inputField.setText("");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(frame, "Array is full.");
            }
        });

        searchButton.addActionListener(e -> {
            String value = inputField.getText().trim();
            if (value.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a string to search.");
                return;
            }

            int result = sortedArray.search(value);
            if (result >= 0) {
                log("SEARCH \"" + value + "\" -> found at index " + result);
            } else {
                int insertionPoint = -result - 1;
                log("SEARCH \"" + value + "\" -> not found; would be at index " + insertionPoint);
            }
            inputField.setText("");
        });

        panel.add(new JLabel("String:"));
        panel.add(inputField);
        panel.add(addButton);
        panel.add(searchButton);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        log("Program started.");
        log("List: " + sortedArray);
    }

    private void log(String message) {
        displayArea.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SortedArrayGUI::new);
    }
}
