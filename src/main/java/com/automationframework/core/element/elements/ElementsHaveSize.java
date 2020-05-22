package com.automationframework.core.element.elements;

import com.automationframework.api.interfaces.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/8/18
 */

public class ElementsHaveSize implements ExpectedCondition<Boolean> {

    private final List<WebElement> elements;
    private final int expectedSize;

    public ElementsHaveSize(List<WebElement> els, int expectedSize) {
        this.elements = els;
        this.expectedSize = expectedSize;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver webDriver) {

        return elements.size() == expectedSize;
    }

    @Override
    public String toString() {
        return String.format("Elements does not have expected size. Expected '%s'. Actual '%s'", expectedSize, elements.size());
    }
}