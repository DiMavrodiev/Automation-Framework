package com.automationframework.core.element;

import com.automationframework.api.interfaces.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 */
public class ElementDisplayed implements ExpectedCondition<Boolean> {

    private final Element element;

    public ElementDisplayed(Element element) {

        this.element = element;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver driver) {

        return element.isDisplayed();
    }

    @Override
    public String toString() {

        return String.format("Element '%s' is not displayed!", element.toString());
    }
}