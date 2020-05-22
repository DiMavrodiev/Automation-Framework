package com.automationframework.api.context;

import com.automationframework.api.driver.Driver;

import java.time.Duration;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 9/28/18
 */

public class SearchStrategy {

    //fluentTimeout to be used by search strategy
    private Duration fluentTimeout;
    private Duration fluentInterval;

    //flag showing that null should be returned instead of failing
    private boolean nullOnFailure = false;

    public SearchStrategy() {

        //setting default fluentTimeout value taken from SettingsConfig
        Driver driver = new Driver();
        this.fluentTimeout = driver.getWaitTimeout();
        this.fluentInterval = driver.getPollingInterval();
    }

    public SearchStrategy(Duration timeout, Duration interval) {

        this.fluentTimeout = timeout;
        this.fluentInterval = interval;
    }

    public Duration getFluentTimeout() {

        return fluentTimeout;
    }

    public Duration getFluentInterval() {

        return fluentInterval;
    }

    public boolean isNullOnFailure() {

        return nullOnFailure;
    }

    public SearchStrategy withTimeout(Duration customTimeoutInSeconds) {

        this.fluentTimeout = customTimeoutInSeconds;
        return this;
    }

    public SearchStrategy pollingEvery(Duration interval) {

        this.fluentInterval = interval;
        return this;
    }

    public SearchStrategy nullOnFailure() {

        this.nullOnFailure = true;
        return this;
    }
}