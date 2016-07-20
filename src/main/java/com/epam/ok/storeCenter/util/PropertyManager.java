package com.epam.ok.storeCenter.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    public static Properties getProperty(String fileName) throws PropertyManagerException {
        Properties properties = new Properties();
        try (InputStream in = PropertyManager.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(in);
        } catch (IOException e) {
            throw new PropertyManagerException("", e);
        }
        return properties;
    }
}
