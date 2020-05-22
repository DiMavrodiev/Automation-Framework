package com.automationframework.core.element;

import com.automationframework.api.interfaces.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 */

public class ElementStale implements ExpectedCondition<Boolean> {

    private final Element element;

    public ElementStale(Element element) {

        this.element = element;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver driver) {

        return element.isStale();
    }

    public String toString() {

        return String.format("Element '%s' didn't become stale!", element.toString());
    }
}