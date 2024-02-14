import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Display our weather App gui
                new WeatherAppGui().setVisible(true);
            }
        });
    }
}
