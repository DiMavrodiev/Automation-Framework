package com.automationframework.core.element.waitfor;

import com.automationframework.api.wait.ElementFluentWait;
import com.automationframework.core.element.*;
import com.automationframework.api.interfaces.Element;
import com.automationframework.api.interfaces.ElementWaitFor;
import com.automationframework.api.context.SearchStrategy;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 *
 * Condition waiter for Visible Element
 */

public class VisibleElementWaitFor implements ElementWaitFor {

    private final ElementFluentWait<WebDriver> fluentWait;
    private final Element element;

    public VisibleElementWaitFor(Element element, ElementFluentWait<WebDriver> fluentWait) {

        this.element = element;
        this.fluentWait = fluentWait;
    }

    public VisibleElementWaitFor(Element element, SearchStrategy strategy, ElementFluentWait<WebDriver> fluentWait) {
        this(element, fluentWait);
        this.fluentWait.withTimeout(strategy.getFluentTimeout());
        this.fluentWait.pollingEvery(strategy.getFluentInterval());
    }

    public void displayed() {

        fluentWait.waitFor(new ElementDisplayed(element));
    }

    public void absent() {

        fluentWait.waitFor(new ElementAbsent(element));
    }

    public void text(String text) {

        fluentWait.waitFor(new ElementTextEquals(element, text));
    }

    public void attribute(String attributeName, String value) {

        fluentWait.waitFor(new ElementAttributeValue(element, attributeName, value));
    }

    public void attribute(String attributeName) {

        fluentWait.waitFor(new ElementHasAttribute(element, attributeName));
    }

    public void notContainsAttributeValue(String attributeName, String value) {

        fluentWait.waitFor(new ElementAttributeNotContain(element, attributeName, value));
    }

    public void containsAttributeValue(String attributeName, String value) {

        fluentWait.waitFor(new ElementAttributeContain(element, attributeName, value));
    }

    public void stale() {

        fluentWait.waitFor(new ElementStale(element));
    }

    public void clickable() {

        fluentWait.waitFor(new ElementClickable(element));
    }

    public void condition(Function<? super WebDriver, ?> condition) {

        fluentWait.waitFor(condition);
    }

}