package com.automationframework.api.driver;

import com.automationframework.api.enums.DriverType;
import org.openqa.selenium.WebDriver;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 12/27/18
 */

public class DriverHolder {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<DriverType> driverType = new ThreadLocal<>();

    public  WebDriver getDriver() {

        return driver.get();
    }

    public void setDriver(WebDriver driver) {

        DriverHolder.driver.set(driver);
    }

    public DriverType getDriverType() {

        return driverType.get();
    }

    public void setDriverType(DriverType driverType) {

        DriverHolder.driverType.set(driverType);
    }
}