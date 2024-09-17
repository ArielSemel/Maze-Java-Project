import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SelectionMenu extends JFrame {
    private final JTextField sizeField;
    private final JComboBox<String> algorithmComboBox;

    public SelectionMenu() {
        setTitle("Selection Menu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Components
        JLabel sizeLabel = new JLabel("Enter Maze Size:");
        sizeField = new JTextField();
        JLabel algorithmLabel = new JLabel("Choose Algorithm:");
        algorithmComboBox = new JComboBox<>(new String[]{"BFS", "DFS", "AStar", "Dijkstra"});
        JButton submitButton = new JButton("Submit");

        // Styling Components
        sizeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        sizeField.setFont(new Font("Arial", Font.PLAIN, 14));
        algorithmLabel.setFont(new Font("Arial", Font.BOLD, 14));
        algorithmComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        styleButton(submitButton); // Style the submit button

        // Layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(sizeLabel, gbc);

        gbc.gridx = 1;
        add(sizeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(algorithmLabel, gbc);

        gbc.gridx = 1;
        add(algorithmComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        // Attach the SubmitButtonListener to the submitButton
        submitButton.addActionListener(new SubmitButtonListener());

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.CYAN);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.BLUE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.CYAN);
            }
        });
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int size = Integer.parseInt(sizeField.getText().trim());
                String algorithmName = (String) algorithmComboBox.getSelectedItem();

                if (size <= 0) {
                    JOptionPane.showMessageDialog(SelectionMenu.this, "Maze size must be a positive integer.");
                    return;
                }

                // Close this menu
                dispose();

                assert algorithmName != null;
                Algorithm algorithm = createAlgorithm(algorithmName);

                // Open maze with the chosen size and algorithm
                new Maze(algorithm, size, 0, 0);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(SelectionMenu.this, "Invalid input. Please enter a valid integer.");
            }
        }
        private Algorithm createAlgorithm(String algorithmName) {
            return switch (algorithmName) {
                case Definitions.ALGORITHM_BFS -> new BFS();
                case Definitions.ALGORITHM_DFS -> new DFS();
                case Definitions.ALGORITHM_DIJKSTRA -> new Dijkstra();
                case Definitions.ALGORITHM_A_STAR-> new AStar();
                default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithmName);
            };
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SelectionMenu::new);
    }
}
