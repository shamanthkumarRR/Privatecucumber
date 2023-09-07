package com.platformX.web.core;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ConfigFileReader {

    private static Logger log  = LogManager.getLogger("Property");
    private static PropertiesConfiguration properties;

    /**
     * Get properties file.
     */
    public static PropertiesConfiguration getProperties(String propertyFilePath) {
        try {
            File propFile = new File(propertyFilePath);
            FileInputStream inputStream = new FileInputStream(propFile);
            properties = new PropertiesConfiguration();
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException | ConfigurationException exp) {
            exp.printStackTrace();
            log.error(exp.getMessage());
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
        return properties;
    }

    /**
     * Get String Data from any properties file
     * @param propPath
     * @param key
     * @return
     */
    public static String getProperty(String propPath, String key) {
        return getProperties(propPath).getString(key);
    }

    /**
     * Get String array from any properties file
     * @param propPath
     * @param key
     * @return
     */
    public static String[] getPropertyArray(String propPath, String key) {
        return getProperties(propPath).getStringArray(key);
    }


}
