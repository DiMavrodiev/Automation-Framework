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

public class ElementsContainText implements ExpectedCondition<Boolean> {

    private final List<WebElement> elements;
    private final String text;
    private List<WebElement> errorElements;

    public ElementsContainText(List<WebElement> els, String text) {
        this.elements = els;
        this.text = text;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver webDriver) {
        errorElements = new ArrayList<>();
        boolean isCorrect = true;
        for (WebElement el : elements) {
            if (!el.getText().contains(text)) {
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
                    .append(String.format("Element '%s' not contains text '%s'", el.toString(), text))
                    .append(String.format("Actual text is '%s'", el.getText()))
                    .append("\n");
        }
        return error.toString();
    }
}