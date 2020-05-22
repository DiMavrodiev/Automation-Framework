package com.automationframework.core.element;

import com.automationframework.api.interfaces.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 */

public class ElementHasAttribute implements ExpectedCondition<Boolean> {

    private final Element element;
    private final String attributeName;

    public ElementHasAttribute(Element element, String attributeName) {
        this.element = element;
        this.attributeName = attributeName;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver driver) {

        return element.getAttribute(attributeName) != null;
    }

    @Override
    public String toString() {

        return String.format("Element '%s' does not have attribute '%s'", element.toString(), attributeName);
    }
}