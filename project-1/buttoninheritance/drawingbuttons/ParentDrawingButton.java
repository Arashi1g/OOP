package buttoninheritance.drawingbuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Added JButton implements ActionListener - Arashi
// Added Abstract (refactoring)
public abstract class ParentDrawingButton extends JButton implements ActionListener {
   public ParentDrawingButton(String text) {
       super(text);
       addActionListener(this);
   }

   // Abstract method to be implemented in child classes for specific plotting behavior
   protected abstract void plotGraph(JFrame plotterFrame);

   // Launch the plotter window when the button is clicked.
   @Override
   public void actionPerformed(ActionEvent e) {
       JFrame plotterFrame = new JFrame("Plotter Window");
       plotterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       plotGraph(plotterFrame);
       plotterFrame.setSize(630, 250);
       plotterFrame.setLocationRelativeTo(null);
       plotterFrame.setVisible(true);
   }
}
