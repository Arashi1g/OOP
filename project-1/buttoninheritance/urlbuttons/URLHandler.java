package buttoninheritance.urlbuttons;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;

public final class URLHandler {
    private URLHandler() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static void openBrowser(String url) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException ex) {
                showErrorDialog("Error opening the browser:\n" + ex.getMessage());
            }
        } else {
            showErrorDialog("Error: Desktop is not supported on this platform.");
        }
    }

    private static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(
            null, 
            message, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }
}
