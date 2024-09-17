import javax.swing.SwingUtilities;

public class Main {
    public Main() {
        new MainMenu();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
