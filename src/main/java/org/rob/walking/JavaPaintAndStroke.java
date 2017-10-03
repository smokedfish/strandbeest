package org.rob.walking;

/**
 * Java 2D Graphics Example Tutorial On Paint, Gradient and Stroke
 * @author Kushal Paudyal
 * www.sanjaal.com/java
 * Last Modified On 10th August 2009
 */
 
import java.awt.*;
import java.awt.geom.*;
 
import javax.swing.JFrame;
 
public class JavaPaintAndStroke extends JFrame {
 
    private static final long serialVersionUID = 7944800805864455013L;
 
    public static void main(String[] args) {
        JavaPaintAndStroke paintFrame = new JavaPaintAndStroke();
        paintFrame.setTitle("Sanjaal.com/java - GUI Tutorial");
        paintFrame.setSize(350, 200);
        paintFrame.setVisible(true);
        paintFrame.centerTheGUIFrame(paintFrame);
 
    }
 
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        double x = 20, y = 60, w = 80, h = 80;
        /** Defining a rectangle and a gradient **/
        Rectangle2D r = new Rectangle2D.Double(x, y, w, h);
        GradientPaint gp = new GradientPaint(75, 75, Color.red, 95, 95,
                Color.green, true);
 
        /**
         * Filling the rectangle with a gradient paint
         */
        g2.setPaint(gp);
        g2.fill(r);
 
        /**
         * Shifting the frame to the right by 100
         * Then using Stroke With a Solid Color
         *
         * setFrame method below sets the location and size of
         * the outer bounds of this Rectangle2D to the
         * specified rectangular values.
         *
         * x, y locations
         * w, h width and height of new Rectangle frame
         */
        r.setFrame(x + 100, y, w, h);
        g2.setPaint(Color.red);
        g2.setStroke(new BasicStroke(8));
        g2.draw(r);
 
        /**
         * Shifting the original rectangle frame to the right by 200
         * Then using a Stroke With a Gradient Color
         */
        r.setFrame(x + 200, y, w, h);
        g2.setPaint(gp);
        g2.draw(r);
    }
 
    /**
     * This method is used to center the GUI
     * @param frame - Frame that needs to be centered.
     */
    public void centerTheGUIFrame(JFrame frame) {
        int widthWindow = frame.getWidth();
        int heightWindow = frame.getHeight();
 
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int X = (screen.width / 2) - (widthWindow / 2); // Center horizontally.
        int Y = (screen.height / 2) - (heightWindow / 2); // Center vertically.
 
        frame.setBounds(X, Y, widthWindow, heightWindow);
 
    }
}