package com.automationframework.core.element;

import com.automationframework.api.interfaces.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 */

public class ElementContainsText implements ExpectedCondition<Boolean> {

    private final Element el;
    private final String text;

    public ElementContainsText(Element el, String text) {
        this.el = el;
        this.text = text;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver driver) {

        return el.getText().contains(text);
    }

    @Override
    public String toString() {
        return String.format("Element '%s' text is not contains '%s'! Actual text is '%s'.",
                el.toString(), text, el.getText());
    }
}