package com.automationframework.core.element;

import com.automationframework.api.interfaces.Element;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 */

public class ElementHasNoText implements ExpectedCondition<Boolean> {

    private final Element element;

    public ElementHasNoText(Element element) {

        this.element = element;
    }

    @Nullable
    @Override
    public Boolean apply(@NotNull WebDriver driver) {

        return element.getText().isEmpty();
    }

    @Override
    public String toString() {

        return String.format("Element '%s' has text '%s'", element.toString(), element.getText());
    }
}