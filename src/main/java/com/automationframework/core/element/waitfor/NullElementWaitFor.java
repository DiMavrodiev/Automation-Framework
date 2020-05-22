package com.automationframework.core.element.waitfor;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.api.wait.ElementFluentWait;
import com.automationframework.api.driver.Driver;
import com.automationframework.core.element.*;
import com.automationframework.core.element.find.VisibleElementLookup;
import com.automationframework.api.interfaces.Element;
import com.automationframework.api.interfaces.ElementWaitFor;
import com.automationframework.api.context.SearchStrategy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 *
 * Condition waiter for {@link NullElement}
 */
public class NullElementWaitFor implements ElementWaitFor {

    private final ElementFluentWait<WebDriver> wait;
    private final ElementData elementData;
    private final SearchStrategy strategy;

    public NullElementWaitFor(ElementData elementData, ElementFluentWait<WebDriver> wait,
                              SearchStrategy strategy) {
        this.elementData = elementData;
        this.wait = wait;
        this.strategy = strategy;
    }

    public void displayed() {

        wait.waitFor(new ElementDisplayed(getElement()));
    }

    public void absent() {

        doNothing();
    }

    public void text(String text) {

        wait.waitFor(new ElementHasText(getElement(), text));
    }

    public void attribute(String attributeName, String value) {

        wait.waitFor(new ElementAttributeValue(getElement(), attributeName, value));
    }

    public void attribute(String attributeName) {

        wait.waitFor(new ElementHasAttribute(getElement(), attributeName));
    }

    public void notContainsAttributeValue(String attributeName, String value) {

        doNothing();
    }

    public void containsAttributeValue(String attributeName, String value) {

        wait.waitFor(new ElementAttributeContain(getElement(), attributeName, value));
    }

    public void stale() {

        wait.waitFor(new ElementStale(getElement()));
    }

    public void clickable() {

        wait.waitFor(new ElementClickable(getElement()));
    }

    public void condition(Function<? super WebDriver, ?> condition) {

        wait.waitFor(condition);
    }

    private void throwException() {

        throw new NoSuchElementException("Unable to find lookUpElement with locator '" + elementData.getBy() + "'");
    }

    private Element getElement() {

        Driver driver = new Driver();
         WebElement lastAttemptToGetElement = new VisibleElementLookup(driver).find(elementData.getLocator());
        if (lastAttemptToGetElement instanceof NullElement) {
            //if lookUpElement is not found again - throw exception
            throwException();
            return null;
        }

        return (Element) lastAttemptToGetElement;
    }

    //We can log something here... or just keep it as empty(delete)
    private void doNothing() {
    }
}