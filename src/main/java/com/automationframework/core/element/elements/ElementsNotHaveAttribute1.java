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

public class ElementsNotHaveAttribute1 implements ExpectedCondition<Boolean> {

    private final List<WebElement> elements;
    private final String attributeName;
    private List<WebElement> errorElements;

    public ElementsNotHaveAttribute1(List<WebElement> elements, String attributeName) {
        this.elements = elements;
        this.attributeName = attributeName;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver webDriver) {
        errorElements = new ArrayList<>();
        boolean isCorrect = true;
        for (WebElement el : elements) {
            if (el.getAttribute(attributeName) != null) {
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
            error
                    .append(el.toString())
                    .append("|");
        }
        return String.format("Elements |%s have attribute '%s'!", error.toString(), attributeName);
    }
}