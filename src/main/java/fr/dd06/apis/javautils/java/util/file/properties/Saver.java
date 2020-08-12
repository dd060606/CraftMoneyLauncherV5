package fr.dd06.apis.javautils.java.util.file.properties;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;

public class Saver {

	    /**
	     * The file where to save the things
	     */
	    private File file;

	    /**
	     * The properties object
	     */
	    private Properties properties;

	    /**
	     * The Saver
	     *
	     * @param file The file where to save the things
	     */
	    public Saver(File file)
	    {
	        this.file = file;
	        this.properties = new Properties();

	        if (file.exists())
	            load();
	    }

	    /**
	     * Set something
	     *
	     * @param key   The key
	     * @param value The value
	     */
	    public void set(String key, String value)
	    {
	        properties.setProperty(key, value);
	        save();
	    }

	    /**
	     * Loads something
	     *
	     * @param key The key of the thing to get
	     *
	     * @return The value if founded, or null
	     */
	    public String get(String key)
	    {
	        return properties.getProperty(key);
	    }

	    /**
	     * Loads somethings with a default value
	     *
	     * @param key The key of the thing to get
	     * @param def The default value
	     *
	     * @return The value if founded, or the def if not
	     */
	    public String get(String key, String def)
	    {
	        String value = properties.getProperty(key);
	        return value == null ? def : value;
	    }

	    /**
	     * Save the properties (automatic when you do {@link #set(String, String)})
	     */
	    public void save()
	    {
	        try
	        {
	            properties.store(new BufferedWriter(new FileWriter(file)), "");
	        }
	        catch (Throwable t)
	        {
	            t.printStackTrace();
	        }
	    }

	    /**
	     * Load the properties (automatic when you do {@link #Saver(File)}
	     */
	    public void load()
	    {
	        try
	        {
	            properties.load(new FileInputStream(file));
	        }
	        catch (Throwable t)
	        {
	           t.printStackTrace();
	        }
	    }
	
}
