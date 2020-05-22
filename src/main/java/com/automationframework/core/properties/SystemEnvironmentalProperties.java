package com.automationframework.core.properties;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 3/13/18
 */

public class SystemEnvironmentalProperties {

    private static volatile SystemEnvironmentalProperties instance;

    private static final String BROWSER_ENV_VAR = "BROWSER";
    private static final String URL_ENV_VAR = "PROJECT_URL";
    private static final String ELEMENTS_PATH_ENV_VAR = "ELEMENTS_PATH";

    private String browser;
    private String URL;
    private String elementsPath;


    private SystemEnvironmentalProperties() {

        try {
            this.browser = System.getenv(BROWSER_ENV_VAR);
            this.URL = System.getenv(URL_ENV_VAR);
            this.elementsPath = System.getenv(ELEMENTS_PATH_ENV_VAR);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static SystemEnvironmentalProperties getInstance(){

        if (instance == null) {
            synchronized (SystemEnvironmentalProperties.class) {
                if (instance == null) {
                    instance = new SystemEnvironmentalProperties();
                }
            }
        }
        return instance;
    }

    public String  getBrowser() {

        return browser;
    }

    public String getURL() {

        return URL;
    }

    public String getElementsPath() {

        return elementsPath;
    }
}