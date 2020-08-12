package fr.dd06.apis.javautils.java.swing.abstractcomponents;




import fr.dd06.apis.javautils.java.swing.event.JavaUtilsEvent;
import fr.dd06.apis.javautils.java.swing.event.JavaUtilsListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/*
 * The AbstractButton
 *
 * <p>
 *    The super-class for the buttons, contains the button
 *    mechanisms (hover state, event listeners, etc...)
 * </p>
 *

 */
public abstract class AbstractButton extends JComponent implements MouseListener {

    /**
     * The button text
     */
    private String text;

    /**
     * The color of the text
     */
    private Color textColor;

    /**
     * The event listeners, to execute when the button was clicked
     */
    private ArrayList<JavaUtilsListener> eventListeners = new ArrayList<JavaUtilsListener>();

    /**
     * If the mouse is on the button
     */
    private boolean hover = false;

    public AbstractButton() {
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // If the button is enabled
        if(this.isEnabled())
            // Executing all the action listeners
            for(JavaUtilsListener eventListener : this.eventListeners)
                eventListener.onEvent(new JavaUtilsEvent(this, JavaUtilsEvent.BUTTON_CLICKED_EVENT));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        hover = true;

        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        hover = false;

        repaint();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        repaint();
    }

    /**
     * Set the text of the button
     *
     * @param text
     *            The new button text
     */
    public void setText(String text) {
        // If the given text is null, throwing an Illegal Argument Exception, else setting it
        if(text == null)
            throw new IllegalArgumentException("text == null");
        this.text = text;

        repaint();
    }

    /**
     * Return the text of the button
     *
     * @return The button text
     */
    public String getText() {
        return text;
    }

    /**
     * Set the text color
     *
     * @param textColor
     *            The new string color
     */
    public void setTextColor(Color textColor) {
        // If the given string color is null, throwing an Illegal Argument Exception, else setting it
        if(textColor == null)
            throw new IllegalArgumentException("textColor == null");
        this.textColor = textColor;

        repaint();
    }

    /**
     * Return the text color (default is null)
     *
     * @return The text color
     */
    public Color getTextColor() {
        return textColor;
    }

    /**
     * Add an event listener for this button, to execute when
     * it will be clicked
     *
     * @param eventListener
     *            The event listener to add
     */
    public void addEventListener(JavaUtilsListener eventListener) {
        // If the given event listener is null, throwing an Illegal Argument Exception, else setting it
        if(eventListener == null)
            throw new IllegalArgumentException("eventListener == null");

        this.eventListeners.add(eventListener);
    }

    /**
     * Returns all the event listeners of this button
     *
     * @return An array list of the event listeners of this button
     */
    public ArrayList<JavaUtilsListener> getEventListeners() {
        return this.eventListeners;
    }

    /**
     * Return if the mouse is on the button
     *
     * @return If the button is hover
     */
    public boolean isHover() {
        return this.hover;
    }

}
