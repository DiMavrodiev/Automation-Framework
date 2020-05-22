package com.automationframework.core.utils;

import com.automationframework.core.properties.SystemEnvironmentalProperties;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 8/13/18
 */

public class ExecutionUtils {

    public static boolean isFF() {

        return SystemEnvironmentalProperties.getInstance().getBrowser().equals("firefox");
    }

    public static boolean isChrome() {

        return SystemEnvironmentalProperties.getInstance().getBrowser().equals("chrome");
    }
}