package com.automationframework.core.properties;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 3/23/18
 */
public class ReadElementPropertyValues {

    private final Properties properties = new Properties();

    private ReadElementPropertyValues() {

        InputStream input = this.getClass().getClassLoader().getResourceAsStream(SystemEnvironmentalProperties.getInstance().getElementsPath());
        try{
            properties.load(input);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static class PropertyFile {

        private static ReadElementPropertyValues instance = new ReadElementPropertyValues();
    }

    public static ReadElementPropertyValues getInstance() {

        return PropertyFile.instance;
    }

    public String getProperty (String element) {

        return properties.getProperty(element);
    }
}