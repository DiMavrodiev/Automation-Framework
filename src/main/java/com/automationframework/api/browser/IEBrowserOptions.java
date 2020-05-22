package com.automationframework.api.browser;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 8/10/18


 * Caps for IE driver
 */
public class IEBrowserOptions extends BrowserOptions {


    public IEBrowserOptions() {

    }

    public InternetExplorerOptions get() {

        return getIEOptions();
    }

    private InternetExplorerOptions getIEOptions() {

        InternetExplorerOptions options = new InternetExplorerOptions();

        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        options.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
        options.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
        options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        options.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        //Found that setting this capability could increase IE tests speed. Should be checked.
        options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        setAlertBehaviorCapabilities(options);
        setLoggingPrefs(options);
        return options;
    }
}