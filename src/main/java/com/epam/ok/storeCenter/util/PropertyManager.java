package com.epam.ok.storeCenter.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    private static final Logger logger = Logger.getLogger(PropertyManager.class);

    public static Properties getProperty(String fileName) throws PropertyManagerException {
        Properties properties = new Properties();
        try (InputStream in = PropertyManager.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(in);
        } catch (IOException e) {
            throw new PropertyManagerException("Can't load property file: " + fileName, e);
        }
        logger.info("Loaded property file: " + fileName);
        return properties;
    }
}
