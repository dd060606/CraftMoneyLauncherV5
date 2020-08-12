
package fr.dd06.apis.javautils.java.util;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * The JavaFX Loader
 *
 * <p>
 *   Loads JavaFX for Java 7
 * </p>

 */
public final class JavaFXLoader {

    /**
     * Add a jar to the system class loader
     *
     * @param file
     *          The jar to add
     * @return True if it succeed, false if it failed
     */
    public static boolean addToSystemClassLoader(File file) {
        if ((ClassLoader.getSystemClassLoader() instanceof URLClassLoader)) {
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            try {
                Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                method.setAccessible(true);
                method.invoke(classLoader, file.toURI().toURL());

                return true;
            } catch (Throwable t) {
                return false;
            }
        }

        return false;
    }


    /**
     * Load JavaFX
     *
     * @return True if it succeed, false if not
     */
    public static boolean loadJavaFX() {
        if (isJavaFXLoaded())
            return true;

        File javaFxJar = new File(System.getProperty("java.home"), "lib/jfxrt.jar");
        if (javaFxJar.isFile())
            try {
                addToSystemClassLoader(javaFxJar);
                return true;
            } catch (Throwable e) {
                return false;
            }

        return false;
    }

    /**
     * Check if JavaFX is loaded
     *
     * @return True if it is, false if not
     */
    public static boolean isJavaFXLoaded() {
        try {
            JavaFXLoader.class.getClassLoader().loadClass("javafx.embed.swing.JFXPanel");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
