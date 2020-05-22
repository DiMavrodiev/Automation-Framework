package com.automationframework.core.element;

import com.automationframework.api.interfaces.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 */


public class ElementHasAnyText implements ExpectedCondition<Boolean> {

    private final Element element;

    public ElementHasAnyText(Element element) {

        this.element = element;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver driver) {

        return element.getText() != null && !element.getText().trim().equals("");
    }

    @Override
    public String toString() {

        return String.format("Ele does not have any text! Actual text is '%s'.",
                element.getText());
    }
}