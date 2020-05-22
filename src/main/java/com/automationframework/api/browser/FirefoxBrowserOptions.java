package com.automationframework.api.browser;

import com.automationframework.api.driver.Driver;
import com.automationframework.core.browsermobproxy.PerformanceProxyServer;
import com.automationframework.api.settings.SettingsConfig;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 8/9/18
 *
 * Caps for Firefox browser
 */
public class FirefoxBrowserOptions extends BrowserOptions {

    public FirefoxBrowserOptions() {

    }

    public FirefoxOptions get() {

        return getFirefoxOptions();
    }

    private FirefoxOptions getFirefoxOptions() {

        FirefoxOptions options = new FirefoxOptions();
        SettingsConfig config = new SettingsConfig();
        Driver driver = new Driver();

        options.setHeadless(config.isHeadlessBrowser());
       // options.setProfile(createFirefoxProfile());
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, config.isEnableJavascript());
        options.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        options.setAcceptInsecureCerts(true);
        setAlertBehaviorCapabilities(options);
        setLoggingPrefs(options);

        if (driver.getBrowserMobProxy()) {

            PerformanceProxyServer.startBrowserMobProxy(options);
        }

        return options;
    }

    private FirefoxProfile createFirefoxProfile() {

        FirefoxProfile profile = new FirefoxProfile();

        profile.setPreference("dom.max_chrome_script_run_time", 999);
        profile.setPreference("dom.max_script_run_time", 999);

        //Add this to avoid JAVA plugin certificate warnings
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(true);
        profile.setPreference("plugin.state.java", 2);
        profile.setPreference("browser.selfsupport.url", "");

        //for sso auth
        profile.setPreference("network.automatic-ntlm-auth.trusted-uris", "http://,https://");

        return profile;
    }
}