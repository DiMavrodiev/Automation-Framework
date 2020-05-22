package com.automationframework.api.interfaces;

import org.openqa.selenium.WebDriver;

import java.util.function.Function;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/24/18
 */

public interface ElementShould {


    void beDisplayed();

    void beAbsent();

    void haveText(String text);

    void haveAnyText();

    void haveNoText();

    void containsText(String text);

    void haveAttribute(String attributeName, String value);

    void haveAttribute(String attributeName);

    void notHaveAttribute(String attributeName);

    void customCondition(Function<WebDriver, Boolean> condition);
}