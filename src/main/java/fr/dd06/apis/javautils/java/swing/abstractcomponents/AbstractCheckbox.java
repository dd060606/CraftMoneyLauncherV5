package fr.dd06.apis.javautils.java.swing.abstractcomponents;



import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

/**
 * The AbstractCheckbox
 *
 * <p>
 *    The super-class for the checkbox, contains the checkboxes
 *    mechanisms (checked, not checked)
 * </p>
 *

 */
public abstract class AbstractCheckbox extends JComponent implements MouseListener {

    /**
     * If the box is checked
     */
    private boolean checked;

    public AbstractCheckbox() {
        this.addMouseListener(this);
    }

    /**
     * Set the box checked, or not
     *
     * @param checked
     *            If the box need to be now checked, or not
     */
    public void setChecked(boolean checked) {
        this.checked = checked;

        repaint();
    }

    /**
     * Return if the box is checked, or not
     *
     * @return True if it is, false if not
     */
    public boolean isChecked() {
        return this.checked;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setChecked(!checked);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
