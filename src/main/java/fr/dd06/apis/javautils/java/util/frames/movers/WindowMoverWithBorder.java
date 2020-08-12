package fr.dd06.apis.javautils.java.util.frames.movers;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class WindowMoverWithBorder extends MouseAdapter{
	 /**
     * The initial click point
     */
    private Point click;

    /**
     * The window to move
     */
    private JFrame window;
    
    private double bordertopsize = 0;
    private double borderbottomsize = 0;
    private double borderrightsize = 0;
    private double borderleftsize = 0;

    /**
     * Basic constructor
     *
     * @param window
     *            The window to move
     */
    public WindowMoverWithBorder(JFrame window, double bordertopsize, double borderbottomsize, double borderrightsize, double borderleftsize) {
        this.window = window;
        this.bordertopsize = bordertopsize;
        this.borderbottomsize = borderbottomsize;
        this.borderrightsize = borderrightsize;
        this.borderleftsize = borderleftsize;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // If the initial click point isn't null (can happen sometimes)
        if (click != null ) {
            // Get the dragged point
            Point draggedPoint = MouseInfo.getPointerInfo()
                    .getLocation();
            double newborderbottomsize = window.getHeight() - borderbottomsize;
            double newborderrightsize = window.getWidth() - borderrightsize;
            
            if(click.getY() <= bordertopsize) {
                window.setLocation(new Point((int) draggedPoint.getX()
                        - (int) click.getX(), (int) draggedPoint
                        .getY() - (int) click.getY()));
            }
            else if(click.getX() <= borderleftsize) {
            	  window.setLocation(new Point((int) draggedPoint.getX()
                          - (int) click.getX(), (int) draggedPoint
                          .getY() - (int) click.getY()));
            }
            else if(click.getY() >= newborderbottomsize && click.getY() >= window.getHeight() / 2) {
            	window.setLocation(new Point((int) draggedPoint.getX()
                        - (int) click.getX(), (int) draggedPoint
                        .getY() - (int) click.getY()));
            }
            else if(click.getX() >= newborderrightsize && click.getX() >= window.getWidth() / 2) {
            	window.setLocation(new Point((int) draggedPoint.getX()
                        - (int) click.getX(), (int) draggedPoint
                        .getY() - (int) click.getY()));
            }

         

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        click = e.getPoint();
    }
}
