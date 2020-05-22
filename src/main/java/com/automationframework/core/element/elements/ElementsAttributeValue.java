package com.automationframework.core.element.elements;

import com.automationframework.api.interfaces.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/8/18
 */

public class ElementsAttributeValue implements ExpectedCondition<Boolean> {

    private final List<WebElement> elements;
    private final String attributeName;
    private final String value;
    private List<WebElement> errorElements;

    public ElementsAttributeValue(List<WebElement> elements, String attributeName, String value) {
        this.elements = elements;
        this.attributeName = attributeName;
        this.value = value;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver webDriver) {
        errorElements = new ArrayList<>();
        boolean isCorrect = true;
        for (WebElement el : elements) {
            if (value.equals(el.getAttribute(attributeName))) {
                isCorrect = false;
                errorElements.add(el);
            }
        }
        return isCorrect;
    }

    @Override
    public String toString() {
        StringBuilder error = new StringBuilder();
        for (WebElement el : errorElements) {
            error.append(String.format("Element |%s| does not have attribute '%s'!",
                    el.toString(), attributeName));
        }
        return error.toString();
    }
}