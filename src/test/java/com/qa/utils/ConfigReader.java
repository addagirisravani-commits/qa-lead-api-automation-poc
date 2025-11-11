package com.qa.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();
    static {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) props.load(is);
        } catch (Exception e) { throw new RuntimeException("Failed to load config.properties", e); }
    }
    public static String get(String key) {
        // System property override (e.g., -Dbrowser=edge)
        String sys = System.getProperty(key);
        return (sys != null && !sys.isBlank()) ? sys : props.getProperty(key);
    }
}
