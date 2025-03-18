package buttoninheritance;

import buttoninheritance.drawingbuttons.CosineDrawingButton;
import buttoninheritance.drawingbuttons.ParentDrawingButton;
import buttoninheritance.drawingbuttons.SineDrawingButton;
import buttoninheritance.urlbuttons.OCPButton;
import buttoninheritance.urlbuttons.ParentURLButton;
import buttoninheritance.urlbuttons.SRPButton;
import buttoninheritance.urlbuttons.SecretsButton;
import java.awt.*;
import javax.swing.*;

// Main class to demonstrate the UI
// Added Dr lehr Comment 
public class ButtonDemo extends JFrame {
    public ButtonDemo() {
        setTitle("Shape Button Inheritance Demo");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());



        // Create buttons
        ParentURLButton errorButton = new ParentURLButton("Test Error", "xyz");
        SRPButton srpButton = new SRPButton("SRP","https://www.youtube.com/watch?v=MPp4A4F6rQI&t=359s");
        OCPButton ocpButton = new OCPButton("OCP","https://www.youtube.com/watch?v=j9G-1TF9KkQ");
        SecretsButton secretsButton = new SecretsButton("Secrets","https://www.youtube.com/watch?v=vJQhI8gIsJI");
        ParentDrawingButton drawingButton = new CosineDrawingButton("Cosine Plotter");
        ParentDrawingButton sineButton = new SineDrawingButton("Sine Plotter");


        // Add buttons to the frame
        add(errorButton);
        add(srpButton);
        add(ocpButton);
        add(secretsButton);
        add(drawingButton);
        add(sineButton);
       
    }

    public static void main(String[] args) {
        // Create and show the UI
        SwingUtilities.invokeLater(() -> {
            ButtonDemo demo = new ButtonDemo();
            demo.setVisible(true);
        });
    }
}

