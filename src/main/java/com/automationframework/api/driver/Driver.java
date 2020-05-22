package com.automationframework.api.driver;

import com.automationframework.core.browsermobproxy.PerformanceProxyServer;
import com.automationframework.api.settings.SettingsConfig;
import com.automationframework.api.interfaces.Drivers;
import com.automationframework.core.properties.SystemEnvironmentalProperties;
import org.openqa.selenium.WebDriver;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 5/10/18
 */

public class Driver {

    private SettingsConfig config = new SettingsConfig();

    public Driver() {

    }

    public void init() {

        Drivers drivers;
        drivers = new DriverFactory();
        drivers.get(getBrowser());
        getDriver().get(getInitialUrl());
        getDriver().manage().timeouts().pageLoadTimeout(config.getDefaultPageTimeOut(), TimeUnit.SECONDS);
    }

    public void closeDriver() {

       if (getBrowserMobProxy()) {
           try {

               PerformanceProxyServer.getHarFile();
           } catch (IOException e) {
               e.printStackTrace();
           }
        } else {
            getDriver().quit();
        }
        getDriver().quit();
    }

    public String getBrowser() {

        String browser = null;
        if (getDefaultTestRun()) {
            for (String currentBrowser : parseXML("../Automation Framework/Aprimo.xml")) {
                browser = currentBrowser;
            }
        } else {
            browser = SystemEnvironmentalProperties.getInstance().getBrowser();
        }
        return browser;
    }

    public String getInitialUrl() {

        return SystemEnvironmentalProperties.getInstance().getURL();
    }

    public void goTo(String URL) {

        getDriver().get(URL);
    }

    public String getElementsPathPropertyFile() {

        return SystemEnvironmentalProperties.getInstance().getElementsPath();
    }

    public int getExplicitWait() {

        return config.getExplicitWaitTimeout();
    }

    public int getImplicitWait() {

        return config.getImplicitWaitTimeout();
    }

    public int getPageLoadTimeout() {

        return config.getDefaultPageTimeOut();
    }

    public Boolean getHeadless() {

        return config.isHeadlessBrowser();
    }

    public Boolean getJavaScript() {

        return config.isEnableJavascript();
    }

    public WebDriver getDriver() {

        DriverHolder holder = new DriverHolder();
        return holder.getDriver();
    }

    public Boolean getBrowserMobProxy() {

        return config.isBrowserMobProxy();
    }

    public Duration getPollingInterval() {

        return config.getDefaultPollingInterval();
    }

    public Duration getWaitTimeout() {

        return config.getDefaultWaitTimeout();
    }

    public boolean getDefaultTestRun() {

        return config.getLocal();
    }

    public String getCurrentUrl() {

        return getDriver().getCurrentUrl();
    }

    public String getTitle() {

        return getDriver().getTitle();
    }

    public void close() {

        getDriver().close();
    }

    public void back() {

        getDriver().navigate().back();
    }

    public void forward() {

        getDriver().navigate().forward();
    }

    public void refresh() {

        getDriver().navigate().refresh();
    }

    public void navigateTo(String url) {

        getDriver().navigate().to(url);
    }

    public void navigateTo(URL url) {

        getDriver().navigate().to(url);
    }

    public String getUrl() {

        return getDriver().getCurrentUrl();
    }

    public void maximize() {

        getDriver().manage().window().maximize();
    }

    public void fullscreen() {

        getDriver().manage().window().fullscreen();
    }

    private List<String> parseXML(String filename) {

        List<String> browserList = new ArrayList<>();
        String browser;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(filename));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()){
                    String localPart = xmlEvent.asStartElement().getName().getLocalPart();
                    if (localPart.equalsIgnoreCase("parameter")) {
                        Iterator<Attribute> attribute = xmlEvent.asStartElement().getAttributes();
                        while (attribute.hasNext()) {
                            Attribute myAttribute = attribute.next();
                            if (myAttribute.getName().toString().equalsIgnoreCase("value")) {
                                browser = myAttribute.getValue();
                                browserList.add(browser);
                            }
                        }
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return browserList;
    }

}