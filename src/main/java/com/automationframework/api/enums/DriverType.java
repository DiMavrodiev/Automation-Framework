package com.automationframework.api.enums;

import com.automationframework.api.exceptions.WebDriverManegerException;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 1/11/19
 */

public enum DriverType {

    FIREFOX,
    CHROME,
    IE,
    EDGE,
    SAFARI;

    @Override
    public String toString() {
        switch (this) {
            case CHROME:
                return "chrome";
            case FIREFOX:
                return "firefox";
            case IE:
                return "ie";
            case EDGE:
                return "edge";
            case SAFARI:
                return "safari";
            default:
                throw new WebDriverManegerException("****** ERROR ****** " + this.name() + " is NOT supported.");
        }
    }
}