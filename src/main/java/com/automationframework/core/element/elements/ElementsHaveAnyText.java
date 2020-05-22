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

public class ElementsHaveAnyText implements ExpectedCondition<Boolean> {

    private final List<WebElement> elements;
    private List<WebElement> errorElements;

    public ElementsHaveAnyText(List<WebElement> elements) {
        this.elements = elements;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver webDriver) {
        errorElements = new ArrayList<>();
        boolean isCorrect = true;
        for (WebElement el : elements) {
            if (el.getText() == null || el.getText().trim().equals("")) {
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
                    .append(String.format("Element |%s| does not have any text! Actual text is '%s'.",
                            el, el.getText()));
        }
        return error.toString();
    }
}