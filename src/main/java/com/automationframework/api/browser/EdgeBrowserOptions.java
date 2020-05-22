package com.automationframework.api.browser;

import com.automationframework.api.settings.SettingsConfig;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 8/10/18
 *
 * Caps for Edge browser
 */
public class EdgeBrowserOptions extends BrowserOptions {

    public EdgeBrowserOptions() {

    }

    public EdgeOptions get() {

        return getEdgeOptions();
    }

    private EdgeOptions getEdgeOptions() {

        EdgeOptions options = new EdgeOptions();
        SettingsConfig config = new SettingsConfig();

        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, config.isEnableJavascript());
        options.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        setAlertBehaviorCapabilities(options);
        setLoggingPrefs(options);
        return options;
    }
}
