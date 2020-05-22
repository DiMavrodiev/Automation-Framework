package com.automationframework.api.driver;

import com.automationframework.api.enums.DriverType;
import com.automationframework.api.interfaces.Drivers;
import com.automationframework.api.browser.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ThreadGuard;

import static com.automationframework.api.enums.DriverType.*;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 1/10/19
 */


public class DriverFactory implements Drivers {

    private WebDriver driver;

    @Override
    public synchronized WebDriver get(String driverType) {

        switch (getType(driverType)) {
            case CHROME : {
                return chrome();
            }
            case FIREFOX : {
                return firefox();
            }
            case EDGE : {
                return edge();
            }
            case SAFARI : {
                return safari();
            }
            case IE : {
                return ie();
            }
            default : {
                return throwException(driverType);
            }
        }
    }

    private synchronized DriverType getType(String browserName) {

        switch (browserName.toLowerCase().trim()) {
            case "chrome" : {
                return CHROME;
            }
            case "firefox" : {
                return FIREFOX;
            }
            case "ie" : {
                return IE;
            }
            case "edge" : {
                return EDGE;
            }
            case "safari" : {
                return SAFARI;
            }
            default:
                throw new RuntimeException("****** ERROR ****** " + this + " driver is NOT available.");
        }

    }

    private synchronized WebDriver chrome() {

        DriverHolder holder = new DriverHolder();
        holder.setDriverType(DriverType.CHROME);
        ChromeBrowserOptions browserOptions = new ChromeBrowserOptions();
        driver = ThreadGuard.protect(new ChromeDriver(browserOptions.get()));
        holder.setDriver(driver);

        return driver;
    }

    private synchronized WebDriver firefox() {

        DriverHolder holder = new DriverHolder();
        holder.setDriverType(DriverType.FIREFOX);
        FirefoxBrowserOptions browserOptions = new FirefoxBrowserOptions();
        driver = ThreadGuard.protect(new FirefoxDriver(browserOptions.get()));
        holder.setDriver(driver);

        return driver;
    }

    private synchronized WebDriver edge() {

        DriverHolder holder = new DriverHolder();
        holder.setDriverType(DriverType.EDGE);
        EdgeBrowserOptions browserOptions = new EdgeBrowserOptions();
        driver = ThreadGuard.protect(new EdgeDriver(browserOptions.get()));
        holder.setDriver(driver);

        return driver;
    }

    private synchronized WebDriver safari() {

        DriverHolder holder = new DriverHolder();
        holder.setDriverType(DriverType.SAFARI);
        SafariBrowserOptions browserOptions = new SafariBrowserOptions();
        driver = ThreadGuard.protect(new SafariDriver(browserOptions.get()));
        holder.setDriver(driver);

        return driver;
    }

    private synchronized WebDriver ie() {

        DriverHolder holder = new DriverHolder();
        holder.setDriverType(DriverType.IE);
        IEBrowserOptions browserOptions = new IEBrowserOptions();
        driver = ThreadGuard.protect(new InternetExplorerDriver(browserOptions.get()));
        holder.setDriver(driver);

        return driver;
    }

    private WebDriver throwException(String  browserName) {

        throw new RuntimeException("****** ERROR ****** " + browserName + " is NOT supported.");
    }
}