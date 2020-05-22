package com.automationframework.api.browser;

import com.automationframework.api.driver.Driver;
import com.automationframework.core.browsermobproxy.PerformanceProxyServer;
import com.automationframework.api.settings.SettingsConfig;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.Arrays;


/**
 * Created By: Dimitar Mavrodiev
 * Date: 8/9/18
 *
 * BrowserOptions for Chrome browser on any platform (WindowTabs, Linux, Mac)
 */

public class ChromeBrowserOptions extends BrowserOptions {

    public ChromeBrowserOptions() {

    }

    public ChromeOptions get() {

        return getChromeOptions();
    }

    private ChromeOptions getChromeOptions() {

        ChromeOptions options = new ChromeOptions();
        SettingsConfig config = new SettingsConfig();
        Driver driver = new Driver();

        options.setHeadless(config.isHeadlessBrowser());
        options.setExperimentalOption("excludeSwitches", Arrays.asList("test-type", "--ignore-certificate-errors"));
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, config.isEnableJavascript());
        options.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        setAlertBehaviorCapabilities(options);
        setLoggingPrefs(options);

        if (driver.getBrowserMobProxy()) {

            PerformanceProxyServer.startBrowserMobProxy(options);
        }

        return options;
    }
}