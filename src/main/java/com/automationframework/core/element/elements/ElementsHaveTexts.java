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

public class ElementsHaveTexts implements ExpectedCondition<Boolean> {

    private final List<WebElement> elements;
    private final List<String> texts;
    private List<WebElement> errorElements;

    public ElementsHaveTexts(List<WebElement> elements, List<String> texts) {
        this.elements = elements;
        this.texts = texts;
    }

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver webDriver) {
        List<String> actualTexts = new ArrayList<>();
        errorElements = new ArrayList<>();
        boolean haveAllTexts = true;
        for (WebElement el : elements) {
            actualTexts.add(el.getText());
            if (!texts.contains(el.getText())) {
                haveAllTexts = false;
                errorElements.add(el);
            }
        }
        return haveAllTexts && actualTexts.size() == texts.size();
    }

    @Override
    public String toString() {
        StringBuilder error = new StringBuilder();
        for (WebElement el : errorElements) {
            error.append(el.toString()).append(" with text '").append(el.getText()).append("'|");
        }
        return String.format("Elements |%s text is not present in the expected texts! Expected texts are %s", error.toString(), texts);
    }
}