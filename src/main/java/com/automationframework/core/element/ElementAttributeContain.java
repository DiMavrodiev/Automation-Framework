package com.automationframework.core.element;

import com.automationframework.api.interfaces.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 */

public class ElementAttributeContain implements ExpectedCondition<Boolean> {

    private final Element element;
    private final String attributeName;
    private final String value;

    public ElementAttributeContain(Element element, String attributeName, String value) {
        this.element = element;
        this.attributeName = attributeName;
        this.value = value;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver driver) {

        String attribute = element.getAttribute(attributeName);
        return attribute != null && attribute.contains(value);
    }

    @Override
    public String toString() {

        return String.format("Element '%s' attribute '%s' does not contain value '%s'!", element.toString(), attributeName, value);
    }
}