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

public class InvaderMenu {
    private final String[][] predefinedImages = {
            {"Monster", "src/spaceinvaders/resources/Invader1.png"},
            {"Player 101", "src/spaceinvaders/resources/Invader2.png"},
            {"UFO", "src/spaceinvaders/resources/Invader3.png"}
    };

    private JMenu menu;
    private SpaceInvadersUI game;

    public InvaderMenu(SpaceInvadersUI game) {
        this.game = game;
        menu = new JMenu("Invader");

        for (String[] image : predefinedImages) {
            JMenuItem menuItem = new JMenuItem(image[0]);
            menuItem.addActionListener(e -> handleSelection(image[1]));
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
            game.setInvaderImage(selectedImage);
            game.repaint();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Invalid Image", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void selectCustomImage() {
        String[] options = {"Enter URL", "Upload File"};
        int choice = JOptionPane.showOptionDialog(null, "Choose an option for custom image", "Custom Image Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

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
