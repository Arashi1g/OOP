package buttoninheritance.drawingbuttons;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

// Extends ParentDrawingButton to provide specific graph plotting
public class CosineDrawingButton extends ParentDrawingButton {
    public CosineDrawingButton(String text) {
        super(text);
    }

    @Override
    protected void plotGraph(JFrame plotterFrame) {
        plotterFrame.add(new CosineDrawer());
    }

    // Inner class to handle cosine plotting
    private static class CosineDrawer extends JPanel implements ActionListener {
        private final Timer timer;
        private double angle = 0.0;
        private final List<Point> points;

        public CosineDrawer() {
            points = new ArrayList<>();
            timer = new Timer(10, this);
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE);
            for (Point point : points) {
                g.fillOval(point.x, point.y, 4, 4);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == timer) {
                angle += 0.05; // Adjusted step size for better plotting
                int x = (int) (angle * 100);
                int y = (int) ((Math.cos(angle) + 1) * 100);

                points.add(new Point(x, y));
                repaint();

                // Stop animation when one full period (2π) is reached
                if (angle > 2 * Math.PI || x > getWidth()) {
                    timer.stop();
                }
            }
        }
    }
}
