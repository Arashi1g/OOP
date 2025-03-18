package spaceinvaders;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageSelection {
    private Image shooterImage;
    private Image invaderImage;
    private Image bulletImage; // Added bullet image field
    public Image getShooterImage() {
        return shooterImage;
    }

    public Image getInvaderImage() {
        return invaderImage;
    }
    //
    public Image getBulletImage() {
        return bulletImage; // Assuming the bullet image is the same as the shooter image
    }

    
    

    public void setGameImages() {
        shooterImage = loadImage("shooter", "./resources/Shooter.png");
        invaderImage = loadImage("invader", "./resources/Invader.png");
        bulletImage = loadImage("bullet", "./resources/Bullet.png"); //Loads Bullet Image
    }

    private static Image loadImage(String imageType, String defaultResourcePath) {
        String imageUrl = JOptionPane.showInputDialog(null,
                "Enter URL for " + imageType + " image (or leave blank for default):");

        // Need to handle case where url is not an image, ie a png or jpeg.
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                return ImageIO.read(new URL(imageUrl));
            } catch (MalformedURLException e) {
                GameExceptions.showErrorDialog(
                        "Invalid URL for " + imageType + " image: " + e.getMessage() + "\nLoading default image");
            } catch (IOException e) {
                GameExceptions.showErrorDialog(
                        "Failed to load " + imageType + " image: " + e.getMessage() + "\nLoading default image");
            }
        }

        // If no URL is provided or URL fails, load the default resource
        try {
            return ImageIO.read(ImageSelection.class.getResource(defaultResourcePath));
        } catch (IOException e) {
            GameExceptions.showErrorDialog("Failed to load default " + imageType + " image: " + e.getMessage());
        }

        return null;
    }
}
