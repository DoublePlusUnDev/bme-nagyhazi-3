package me.doubleplusundev.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A config utility, allows access to the values defined in the config file.
 */
public class Config {
    private static final Properties properties = new Properties();
    
    static {
        try {
            InputStream in = Config.class.getResourceAsStream("/config/config.properties");
            properties.load(in);
        } catch (IOException e) {
            UIUtils.showException(e);
        }
    }

    public String getString(String key, String defaultValue){
        return properties.getProperty(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue){
        return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
    }

    public static boolean getBool(String key, boolean defaultValue) {
        return Boolean.parseBoolean(properties.getProperty(key, String.valueOf(defaultValue)));
    }
}
