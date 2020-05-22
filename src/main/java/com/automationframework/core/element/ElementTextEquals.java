package com.automationframework.core.element;

import com.automationframework.api.interfaces.Element;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 */

public class ElementTextEquals implements ExpectedCondition<Boolean> {

    private final Element element;
    private final String text;

    public ElementTextEquals(Element element, String text) {
        this.element = element;
        this.text = text;
    }

    @Override
    public Boolean apply(@NotNull WebDriver driver) {

        return element.getText().equals(text);
    }

    @Override
    public String toString() {

        return String.format("Element '%s' text is not '%s'", element.toString(), text);
    }
}