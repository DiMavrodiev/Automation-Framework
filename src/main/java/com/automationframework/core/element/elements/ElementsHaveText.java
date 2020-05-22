package com.automationframework.core.element.elements;

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

public class ElementsHaveText implements ExpectedCondition<Boolean> {

    private final List<WebElement> els;
    private final String text;
    private List<WebElement> errorEls;

    public ElementsHaveText(List<WebElement> els, String text) {
        this.els = els;
        this.text = text;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver webDriver) {
        errorEls = new ArrayList<>();
        boolean isCorrect = true;
        for (WebElement el : els) {
            if (!text.equals(el.getText())) {
                isCorrect = false;
                errorEls.add(el);
            }
        }
        return isCorrect;
    }

    @Override
    public String toString() {
        StringBuilder error = new StringBuilder();
        for (WebElement el : errorEls) {
            error
                    .append(String.format("%s text is incorrect! Expected text is '%s'.", el.toString(), text))
                    .append(String.format("Actual text is '%s'", el.getText()))
                    .append("\n");
        }
        return error.toString();
    }
}