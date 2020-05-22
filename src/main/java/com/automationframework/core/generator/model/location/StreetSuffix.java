package com.automationframework.core.generator.model.location;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

public class StreetSuffix {

    private String longVersion;
    private String shortVersion;

    public StreetSuffix(String longVersion, String shortVersion) {
        this.longVersion = longVersion;
        this.shortVersion = shortVersion;
    }

    public String getLongVersion() {
        return longVersion;
    }

    public String getShortVersion() {
        return shortVersion;
    }
}