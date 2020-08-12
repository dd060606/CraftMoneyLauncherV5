
package fr.dd06.apis.javautils.java.util.frames.movers;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

/**
 * The Window Mover
 *
 * <p>
 *     This class when added as a mouse listener and mouse motion listener to a JFrame
 *     will move it when the user will click on it.
 * </p>
 *
 * To add it :
 *
 * <code>
 *     WindowMover mover = new WindowMover(frame);
 *     frame.addMouseListener(mover);
 *     frame.addMouseMotionListener(mover);
 * </code>
 *
 * For the listeners, you can replace frame by an object that will 'move the window'

 */
public class WindowMover extends MouseAdapter {

    /**
     * The initial click point
     */
    private Point click;

    /**
     * The window to move
     */
    private JFrame window;

    /**
     * Basic constructor
     *
     * @param window
     *            The window to move
     */
    public WindowMover(JFrame window) {
        this.window = window;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // If the initial click point isn't null (can happen sometimes)
        if (click != null) {
            // Get the dragged point
            Point draggedPoint = MouseInfo.getPointerInfo()
                    .getLocation();

            // And move the window
            window.setLocation(new Point((int) draggedPoint.getX()
                    - (int) click.getX(), (int) draggedPoint
                    .getY() - (int) click.getY()));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        click = e.getPoint();
    }


}
