import javax.swing.*;

public class AppLauncher {
    // main() -> ashhab
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // display our weather app GUI
                new WeatherAppGui().setVisible(true);
            }
        });
    }
}
