package com.automationframework.core.element;

import com.automationframework.api.interfaces.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 */

public class ElementNotHaveAttribute implements ExpectedCondition<Boolean> {

    private final Element element;
    private String attributeName;

    public ElementNotHaveAttribute(Element element, String attributeName) {
        this.element = element;
        this.attributeName = attributeName;
    }

    @Override
    public Boolean apply( WebDriver driver) {

        return element.getAttribute(attributeName) == null;
    }

    @Override
    public String toString() {

        return String.format("Ele '%s' has attribute '%s'", element.toString(), attributeName);
    }
}