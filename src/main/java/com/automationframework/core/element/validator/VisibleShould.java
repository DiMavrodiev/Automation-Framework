package com.automationframework.core.element.validator;

import com.automationframework.api.wait.ElementFluentWait;
import com.automationframework.core.element.*;
import com.automationframework.api.interfaces.Element;
import com.automationframework.api.interfaces.ElementShould;
import com.automationframework.api.context.SearchStrategy;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 *
 * Assertions for Visible Element
 */

public class   VisibleShould implements ElementShould {

    private final ElementFluentWait<WebDriver> fluentWait;
    private final Element element;

    public VisibleShould(Element element, ElementFluentWait<WebDriver> fluentWait) {
        this.element = element;
        this.fluentWait = fluentWait;
    }

    public VisibleShould(Element element, SearchStrategy strategy, ElementFluentWait<WebDriver> fluentWait) {
        this(element, fluentWait);
        this.fluentWait.withTimeout(strategy.getFluentTimeout());
        this.fluentWait.pollingEvery(strategy.getFluentInterval());
    }

    public void beDisplayed()
    {

        waitFor(new ElementDisplayed(element));
    }

    public void beAbsent() {

        waitFor(new ElementAbsent(element));
    }

    public void haveText(String text) {

        waitFor(new ElementHasText(element, text));
    }

    public void haveAnyText() {

        waitFor(new ElementHasAnyText(element));
    }

    public void haveNoText() {

        waitFor(new ElementHasNoText(element));
    }

    public void containsText(String text) {

        waitFor(new ElementContainsText(element, text));
    }

    public void haveAttribute(String attributeName, String value) {

        waitFor(new ElementAttributeValue(element, attributeName, value));
    }

    public void haveAttribute(String attributeName) {

        waitFor(new ElementHasAttribute(element, attributeName));
    }

    public void notHaveAttribute(String attributeName) {

        waitFor(new ElementNotHaveAttribute(element, attributeName));
    }

    public void customCondition(Function<WebDriver, Boolean> condition) {

        waitFor(condition);
    }

    private void waitFor(Function<WebDriver, Boolean> condition) {

        fluentWait.waitFor(condition);
    }
}