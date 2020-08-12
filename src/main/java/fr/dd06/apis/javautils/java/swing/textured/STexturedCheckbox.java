
package fr.dd06.apis.javautils.java.swing.textured;

import static fr.dd06.apis.javautils.JavaUtils.drawFullsizedImage;

import java.awt.Graphics;
import java.awt.Image;

import fr.dd06.apis.javautils.java.swing.abstractcomponents.AbstractCheckbox;

/**
 * The STexturedCheckbox
 *
 * <p>
 *    A simple textured checkbox, use isChecked to check if it is checked.
 * </p>

 */
public class STexturedCheckbox extends AbstractCheckbox {

    /**
     * The background checkbox image
     */
    private Image backgroundImage;

    /**
     * The image of the little check
     */
    private Image checkImage;

    /**
     * The STexturedCheckbox
     *
     * @param backgroundImage
     *            The background checkbox image
     * @param checkImage
     *            The image of the little check
     */
    public STexturedCheckbox(Image backgroundImage, Image checkImage) {
        super();

        // If the background image is null, throwing an Illegal Argument Exception, else setting it
        if(backgroundImage == null)
            throw new IllegalArgumentException("backgroundImage == null ");
        this.backgroundImage = backgroundImage;

        // If the check image is null, throwing an Illegal Argument Exception, else setting it
        if(checkImage == null)
            throw new IllegalArgumentException("checkImage == null");
        this.checkImage = checkImage;
    }

    @Override
    public void paintComponent(Graphics g) {
        // Drawing the background
        drawFullsizedImage(g, this, backgroundImage);

        // If it is checked
        if(this.isChecked())
            // Drawing the little check image
            drawFullsizedImage(g, this, checkImage);
    }

    /**
     * Set the background checkbox image
     *
     * @param backgroundImage
     *            The new background
     */
    public void setBackgroundImage(Image backgroundImage) {
        // If the given background image is null, throwing an exception, else setting it
        if(backgroundImage == null)
            throw new IllegalArgumentException("backgroundImage == null ");
        this.backgroundImage = backgroundImage;

        repaint();
    }

    /**
     * Return the background checkbox image
     *
     * @return The background
     */
    public Image getBackgroundImage() {
        return this.backgroundImage;
    }

    /**
     * Set the little check image
     *
     * @param checkImage
     *            The new check
     */
    public void setCheckImage(Image checkImage) {
        // If the given check image is null, throwing an exception, else setting it
        if(checkImage == null)
            throw new IllegalArgumentException("checkImage == null ");
        this.checkImage = checkImage;

        repaint();
    }

    /**
     * Return the little check image
     *
     * @return The check
     */
    public Image getCheckImage() {
        return this.checkImage;
    }

}
