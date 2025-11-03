package me.doubleplusundev;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();
    
    static {
        try {
            InputStream in = Config.class.getResourceAsStream("/config/config.properties");
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
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
