package com.automationframework.core.element.waitfor;

import com.automationframework.api.wait.ElementFluentWait;
import com.automationframework.api.driver.Driver;
import com.automationframework.core.element.NullElement;
import com.automationframework.api.interfaces.Element;
import com.automationframework.api.interfaces.ElementWaitFor;
import com.automationframework.api.context.SearchStrategy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 *
 * Immediate Condition waiter for {@link NullElement}
 * Immediately checks for a condition
 * (does not wait for a default timeout as in {@link NullElement})
 */

public class NullElementWaitForImmediately implements ElementWaitFor {

    private final Element element;

    public NullElementWaitForImmediately(Element element) {

        this.element = element;
    }

    public void displayed() {

        throwException();
    }

    public void absent() {

        doNothing();
    }

    public void text(String text) {

        throwException();
    }

    public void attribute(String attributeName, String value) {

        throwException();
    }

    public void attribute(String attributeName) {

        throwException();
    }

    public void notContainsAttributeValue(String attributeName, String value) {

        throwException();
    }

    public void containsAttributeValue(String attributeName, String value) {

        throwException();
    }

    public void stale() {

        throwException();
    }

    public void clickable() {

        throwException();
    }

    public void condition(Function<? super WebDriver, ?> condition) {

        Driver driver = new Driver();
        new ElementFluentWait<>(driver.getDriver(), new SearchStrategy()).waitFor(condition);
    }

    private void throwException() {

        throw new NoSuchElementException("Unable to find lookUpElement with locator.");
    }

    //We can log something here... or just keep it as empty(delete)
    private void doNothing() {
    }
}