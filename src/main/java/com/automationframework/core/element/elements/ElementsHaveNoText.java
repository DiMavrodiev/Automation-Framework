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
public class ElementsHaveNoText implements ExpectedCondition<Boolean> {

    private final List<WebElement> elements;
    private List<WebElement> errorElements;

    public ElementsHaveNoText(List<WebElement> els) {

        this.elements = els;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver webDriver) {
        errorElements = new ArrayList<>();
        boolean isCorrect = true;
        for (WebElement el : elements) {
            if (!el.getText().isEmpty()) {
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
                    .append(String.format("Element '%s' has text '%s'", el.toString(), el.getText()))
                    .append("\n");
        }
        return error.toString();
    }
}