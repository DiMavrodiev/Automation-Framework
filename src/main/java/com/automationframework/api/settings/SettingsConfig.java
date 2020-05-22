package com.automationframework.api.settings;

import java.time.Duration;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 5/22/18
 */

public class SettingsConfig {

    private static final boolean DEFAULT_HEADLESS_BROWSER = false;
    private static final boolean DEFAULT_LOCAL = true;
    private static final boolean DEFAULT_JAVASCRIPT = true;
    private static final int  DEFAULT_IMPLICIT_WAIT_TIMEOUT = 10;
    private static final int DEFAULT_EXPLICIT_WAIT_TIMEOUT = 15;
    private static final int DEFAULT_PAGE_LOAD_TIMEOUT = 10;
    private static final Duration DEFAULT_WAIT_TIMEOUT = Duration.ofSeconds(10);
    private static final Duration DEFAULT_POLLING_INTERVAL = Duration.ofMillis(500);
    private static final boolean DEFAULT_BROWSER_MOB_PROXY = false;

    public int getImplicitWaitTimeout() {

        return DEFAULT_IMPLICIT_WAIT_TIMEOUT;
    }

    public boolean getLocal() {

        return DEFAULT_LOCAL;
    }

    public int getExplicitWaitTimeout() {

        if (DEFAULT_EXPLICIT_WAIT_TIMEOUT < getImplicitWaitTimeout()) {
            return getImplicitWaitTimeout();
        } else {
            return DEFAULT_EXPLICIT_WAIT_TIMEOUT;
        }
    }

    public int getDefaultPageTimeOut() {

        return DEFAULT_PAGE_LOAD_TIMEOUT;
    }

    public  boolean isHeadlessBrowser() {

        return DEFAULT_HEADLESS_BROWSER;
    }

    public boolean isEnableJavascript() {

        return DEFAULT_JAVASCRIPT;
    }

    public  boolean isBrowserMobProxy() {

        return DEFAULT_BROWSER_MOB_PROXY;
    }

    public Duration getDefaultPollingInterval() {

        return DEFAULT_POLLING_INTERVAL;
    }

    public Duration getDefaultWaitTimeout() {

        return DEFAULT_WAIT_TIMEOUT;
    }
}