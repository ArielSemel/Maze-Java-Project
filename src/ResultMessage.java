import javax.swing.*;

public class ResultMessage {
    private boolean result;
    private Maze maze;

    public ResultMessage(boolean result, Maze maze){
        this.result = result;
        this.maze = maze;
    }

    public void display(){

        String message = String.format(
                "<html><body style='font-size:30px;'>"
                        + "<p style='color:blue;'>Results</p>"
                        + "<p><b>Algorithm:</b> <span style='color:green;'>%s</span></p>"
                        + "<p><b>Solution:</b> <span style='color:%s;'>%s</span></p>"
                        + "<p><b>Number of Nodes Explored:</b> <span style='color:orange;'>%d</span></p>"
                        + "</body></html>",
                maze.getAlgorithm().getName(),
                result ? "green" : "red",
                result ? "Found" : "Not Found",
                Maze.getCounterNodes()
        );

        JOptionPane.showMessageDialog(maze, message, "Results", JOptionPane.INFORMATION_MESSAGE);

        int returnMenu = JOptionPane.showConfirmDialog(
                maze,
                "Do you want to return to the menu?",
                "Return to Menu",
                JOptionPane.YES_NO_OPTION
        );

        if (returnMenu == JOptionPane.YES_OPTION) {
            maze.dispose(); // Close the maze window
            new Main(); // Recreate the main menu
        } else {
            System.exit(0); // Exit the application
        }

    }

}
