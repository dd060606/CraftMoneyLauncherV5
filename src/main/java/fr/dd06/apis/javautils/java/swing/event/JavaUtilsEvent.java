
package fr.dd06.apis.javautils.java.swing.event;

/**
 * The SwingerEvent
 *
 * <p>
 *    An event executed by some components, like the buttons when
 *    they are clicked, etc... It can be a super-class for other
 *    event too.
 * </p>
 *

 */
public class JavaUtilsEvent {

    /**
     * The event when a button is clicked
     */
    public static final int BUTTON_CLICKED_EVENT = 0;

    /**
     * The source of the event
     */
    private Object source;

    /**
     * The type of the event (SwingerEvent.BUTTON_CLICKED_EVENT, etc...)
     */
    private int type;

    /**
     * A Swinger Event
     *
     * @param source
     *            The source of the event
     * @param type
     *            The type of the event (SwingerEvent.BUTTON_CLICKED_EVENT, etc...)
     */
    public JavaUtilsEvent(Object source, int type) {
        this.source = source;
    }

    /**
     * Return the source of the event
     *
     * @return The event source component
     */
    public Object getSource() {
        return this.source;
    }

    /**
     * Return the type of the event (SwingerEvent.BUTTON_CLICKED_EVENT, etc...)
     *
     * @return The type of the event
     */
    public int getType() {
        return this.type;
    }

}
