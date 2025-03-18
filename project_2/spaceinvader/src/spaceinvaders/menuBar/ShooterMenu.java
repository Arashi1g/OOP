package spaceinvaders.menuBar;

import javax.swing.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import spaceinvaders.SpaceInvadersUI;

public class ShooterMenu {
    private final String[][] predefinedImages = {
        {"Cannon", "src/spaceinvaders/resources/Shooter1.png"},
        {"Squid O", "src/spaceinvaders/resources/Shooter2.png"},
        {"Smiley", "src/spaceinvaders/resources/Shooter3.png"}
    };

    private JMenu menu;
    private SpaceInvadersUI game;

    public ShooterMenu(SpaceInvadersUI game) {
        this.game = game;
        menu = new JMenu("Shooter");

        // Adding predefined images with only filename displayed
        for (String[] image : predefinedImages) {
            JMenuItem menuItem = new JMenuItem(image[0]); // Uses name only
            menuItem.addActionListener(e -> handleSelection(image[1])); // Uses full path in action
            menu.add(menuItem);
        }

        JMenuItem customOption = new JMenuItem("Custom Image");
        customOption.addActionListener(e -> selectCustomImage());
        menu.add(customOption);
    }

    public JMenu getMenu() {
        return menu;
    }

    private void handleSelection(String option) {
        try {
            Image selectedImage = option.startsWith("http") ? loadImageFromURL(option) : loadImageFromFile(option);
            game.setShooterImage(selectedImage);
            game.repaint();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Invalid Image", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void selectCustomImage() {
        String[] options = {"Enter URL", "Upload File"};
        int choice = JOptionPane.showOptionDialog(null, "Choose an option for custom image", 
                                                  "Custom Image Selection", 
                                                  JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                                                  null, options, options[0]);

        if (choice == 0) {
            String imageUrl = JOptionPane.showInputDialog("Enter image URL:");
            if (imageUrl != null && !imageUrl.isEmpty()) {
                handleSelection(imageUrl);
            }
        } else if (choice == 1) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "gif"));
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                handleSelection(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
    }

    private Image loadImageFromURL(String urlString) throws IOException {
        try {
            URL url = new URL(urlString);
            Image image = ImageIO.read(url);
            if (image == null) {
                throw new IOException("URL does not point to a valid image file.");
            }
            return image;
        } catch (MalformedURLException e) {
            throw new IOException("Invalid URL format.");
        }
    }

    private Image loadImageFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File does not exist.");
        }
        Image image = ImageIO.read(file);
        if (image == null) {
            throw new IOException("Selected file is not a supported image format.");
        }
        return image;
    }
}
