
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.awt.event.WindowAdapter;


public class GUIMainMenu {
    private final JFrame frame;
    public GUIMainMenu(){

        frame = new JFrame("Maze Solver");

        // Set Up the frame
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(2, 1));

        // Add components
        JButton btnStart = new JButton("Start Maze");
        JButton btnExit = new JButton("Exit");

        // Style buttons
        styleButton(btnStart,Color.CYAN, Color.BLACK);
        styleButton(btnExit,Color.RED, Color.WHITE);

        btnStart.addActionListener(e -> SelectionMenu());
        btnExit.addActionListener(e -> exitApplication());

        frame.add(btnStart);
        frame.add(btnExit);

        frame.setVisible(true);

        // Add window listener for exit confirmation
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApplication();
            }
        });
    }

    private void styleButton(JButton button, Color backgroundColor, Color textColor) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(backgroundColor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor);
            }
        });
    }

    private void SelectionMenu() {
        frame.setVisible(false);
        new GUISelectionMenu();
    }

    private void exitApplication() {

        int confirm = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUIMainMenu::new);
    }

}