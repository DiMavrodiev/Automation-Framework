package com.automationframework.core.element.validator;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.api.wait.ElementFluentWait;
import com.automationframework.api.driver.Driver;
import com.automationframework.core.element.*;
import com.automationframework.core.element.find.VisibleElementLookup;
import com.automationframework.api.interfaces.Element;
import com.automationframework.api.interfaces.ElementShould;
import com.automationframework.api.context.SearchStrategy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/24/18
 *
 * Assertions for Null Element
 */

public class NullShould implements ElementShould {

    private final ElementFluentWait<WebDriver> fluentWait;
    private final ElementData elementData;
    private final SearchStrategy strategy;

    public NullShould(ElementData elementData, ElementFluentWait<WebDriver> fluentWait,
                      SearchStrategy strategy) {
        this.elementData = elementData;
        this.fluentWait = fluentWait;
        this.strategy = strategy;
    }

    public void beDisplayed() {

        fluentWait.waitFor(new ElementDisplayed(getElement()));
    }

    public void beAbsent() {

        doNothing();
    }

    public void haveText(String text) {

        fluentWait.waitFor(new ElementHasText(getElement(), text));
    }

    public void haveNoText() {

        fluentWait.waitFor(new ElementHasNoText(getElement()));
    }

    public void containsText(String text) {

        fluentWait.waitFor(new ElementContainsText(getElement(), text));
    }

    public void haveAnyText() {

        fluentWait.waitFor(new ElementDisplayed(getElement()));
    }

    public void haveAttribute(String attributeName, String value) {

        fluentWait.waitFor(new ElementAttributeValue(getElement(), attributeName, value));
    }

    public void haveAttribute(String attributeName) {

        fluentWait.waitFor(new ElementHasAttribute(getElement(), attributeName));
    }

    public void notHaveAttribute(String attributeName) {

        fluentWait.waitFor(new ElementNotHaveAttribute(getElement(), attributeName));
    }

    public void customCondition(Function<WebDriver, Boolean> condition) {

        fluentWait.waitFor(condition);
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