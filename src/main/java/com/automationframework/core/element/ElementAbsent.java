package com.automationframework.core.element;

import com.automationframework.api.interfaces.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 */

public class ElementAbsent implements ExpectedCondition<Boolean> {

    private final Element element;

    public ElementAbsent(Element element) {

        this.element = element;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver driver) {
        try {
            return !element.isDisplayed();
        } catch (Throwable ignored) {
            //in case of any exception in Element considering that lookUpElement is absent
            return true;
        }
    }

    @Override
    public String toString()
    {
        return String.format("Element '%s' is not absent!", element.toString());
    }
}