package com.automationframework.api.interfaces;

import org.openqa.selenium.WebDriver;

import java.util.function.Function;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 8/14/18
 /**
 * Conditions for lookUpElement
 */

public interface ElementWaitFor {

    void displayed();

    void absent();

    void text(String text);

    void attribute(String attributeName, String value);

    void attribute(String attributeName);

    void notContainsAttributeValue(String attributeName, String value);

    void containsAttributeValue(String attributeName, String value);

    void stale();

    void clickable();

    void condition(Function<? super WebDriver, ?> condition);
}
