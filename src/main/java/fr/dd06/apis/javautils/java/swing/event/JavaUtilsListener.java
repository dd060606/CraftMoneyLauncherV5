
package fr.dd06.apis.javautils.java.swing.event;

/**
 * The SwingerEventListener
 *
 * <p>
 *    A listener to listen for any SwingerEvent.
 * </p>

 */
public interface JavaUtilsListener {

    /**
     * Executed when an event is called
     *
     * @param event
     *            The called event
     */
    void onEvent(JavaUtilsEvent event);

}
