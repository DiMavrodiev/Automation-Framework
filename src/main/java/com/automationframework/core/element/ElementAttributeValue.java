package com.automationframework.core.element;

import com.automationframework.api.interfaces.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 */

public class ElementAttributeValue implements ExpectedCondition<Boolean> {

    private final Element element;
    private final String attributeName;
    private final String value;

    public ElementAttributeValue(Element element, String attributeName, String value) {
        this.element = element;
        this.attributeName = attributeName;
        this.value = value;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver driver) {

        return value.equals(element.getAttribute(attributeName));
    }

    @Override
    public String toString() {
        return String.format("Element '%s' attribute '%s' value is not '%s'", element.toString(), attributeName, value);
    }
}