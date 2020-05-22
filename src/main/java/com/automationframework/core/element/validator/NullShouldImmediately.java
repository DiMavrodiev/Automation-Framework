package com.automationframework.core.element.validator;

import com.automationframework.api.wait.ElementFluentWait;
import com.automationframework.api.driver.Driver;
import com.automationframework.api.interfaces.Element;
import com.automationframework.api.interfaces.ElementShould;
import com.automationframework.api.context.SearchStrategy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.function.Function;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 */
public class NullShouldImmediately implements ElementShould {

    private final Element element;

    public NullShouldImmediately(Element element) {

        this.element = element;
    }

    //null lookUpElement can't be displayed
    public void beDisplayed() {

        throw new AssertionError("Element is absent in DOM.");
    }

    //null lookUpElement is absent by default
    public void beAbsent() {

        doNothing();
    }

    public void haveText(String text)
    {
        throwException();
    }

    public void haveAnyText() {

        throwException();
    }

    public void haveNoText() {

        throwException();
    }

    public void containsText(String text) {

        throwException();
    }

    public void haveAttribute(String attributeName, String value) {

        throwException();
    }

    public void haveAttribute(String attributeName) {

        throwException();
    }

    public void notHaveAttribute(String attributeName) {

        throwException();
    }

    public void customCondition(Function<WebDriver, Boolean> condition) {

        Driver driver = new Driver();
        new ElementFluentWait<>(driver.getDriver(), new SearchStrategy(Duration.ofSeconds(0), Duration.ofMillis(0)));
    }

    private void throwException() {

        throw new NoSuchElementException("Unable to find lookUpElement with locator.");
    }

    //We can log something here... or just keep it as empty(delete)
    private void doNothing() {
    }
}