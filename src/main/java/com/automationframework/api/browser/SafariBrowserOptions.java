package com.automationframework.api.browser;

import com.automationframework.api.settings.SettingsConfig;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariOptions;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 8/10/18
 *
 * Caps for Safari Technology Preview on Mac
 */

public class SafariBrowserOptions extends BrowserOptions {

    public SafariBrowserOptions() {

    }

    public SafariOptions get() {

        return getSafariOptions();
    }

    private SafariOptions getSafariOptions() {

        SafariOptions options = new SafariOptions();
        SettingsConfig config = new SettingsConfig();

        options.setUseTechnologyPreview(true);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, config.isEnableJavascript());
        setAlertBehaviorCapabilities(options);
        setLoggingPrefs(options);
        return options;
    }
}