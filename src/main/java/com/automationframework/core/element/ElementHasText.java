package com.automationframework.core.element;

import com.automationframework.api.interfaces.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 */

public class ElementHasText implements ExpectedCondition<Boolean> {

    private final Element element;
    private final String text;

    public ElementHasText(Element element, String text) {

        this.text = text;
        this.element = element; }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver driver) {

        return element.getText().equals(text);
    }

    @Override
    public String toString() {

        return String.format("Element '%s' text is not '%s'! Actual text is '%s'.",
                element.toString(), text, element.getText());
    }
}