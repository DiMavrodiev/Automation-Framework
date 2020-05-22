package com.automationframework.api.browser;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.logging.Level;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 8/9/18
 *
 * Base abstraction representing capabilities for the driver initialization
 * all options should implement a support for custom capabilities that could be passed
 * from the projects to provide custom configurations for particular browser
 */

public abstract class BrowserOptions {

    private static ThreadLocal<UnexpectedAlertBehaviour> alertCapability = ThreadLocal.withInitial(() -> UnexpectedAlertBehaviour.ACCEPT);
    private static ThreadLocal<UnexpectedAlertBehaviour> currentAlertCapability = ThreadLocal.withInitial(() -> UnexpectedAlertBehaviour.ACCEPT);

    public BrowserOptions() {

    }

    public abstract MutableCapabilities get();

    void setAlertBehaviorCapabilities(MutableCapabilities options) {
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, alertCapability.get());
        currentAlertCapability.set(alertCapability.get());
    }

    void setLoggingPrefs(MutableCapabilities options) {
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    }
}