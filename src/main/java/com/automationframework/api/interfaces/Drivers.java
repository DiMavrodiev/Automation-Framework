package com.automationframework.api.interfaces;

import org.openqa.selenium.WebDriver;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 12/27/18
 * Abstract factory to provide instance of WebDriver
 */

public interface Drivers {

    WebDriver get(String driverType);
}
