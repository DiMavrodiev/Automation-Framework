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

public class ElementsAbsent implements ExpectedCondition<Boolean> {

    private final List<WebElement> elements;
    private List<WebElement> errorElements;

    public ElementsAbsent(List<WebElement> elements) {

        this.elements = elements;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver webDriver) {
        boolean allAbsent = true;
        errorElements = new ArrayList<>();
        for (WebElement el : elements) {
            if (el.isDisplayed()) {
                allAbsent = false;
                errorElements.add(el);
            }
        }
        return allAbsent;
    }

    @Override
    public String toString() {
        StringBuilder error = new StringBuilder();
        for (WebElement el : errorElements) {
            error
                    .append(el.toString())
                    .append("| ");
        }
        return String.format("Elements |%s are not absent!", error.toString());
    }
}