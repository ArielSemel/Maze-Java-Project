import javax.swing.SwingUtilities;

public class Main {
    public Main() {
        new GUIMainMenu();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
