package com.automationframework.core.element.locate;

import com.automationframework.core.logger.MyLogger;
import com.automationframework.api.driver.Driver;
import com.automationframework.api.interfaces.Element;
import com.automationframework.api.interfaces.Locator;
import com.automationframework.core.properties.ReadElementPropertyValues;
import org.openqa.selenium.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 8/14/18
 *
 * Find lookUpElement
 */

public class ElementLocatable implements Locator {

    private WebElement wrappedElement;
    private String locator;
    private int repeatLocateElementCounter;

    //TODO think about making this value configurable
    private static final int MAX_NUMBER_OF_REPEAT_LOCATE_ELEMENT = 5;

    public ElementLocatable(String locator) {

        this.locator = locator;
    }

    @Override
    public WebElement find(Driver driver) {

        try {
            //for catch stale lookUpElement

            return driver.getDriver().findElement(getBy());
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            againLocate();
            return find(driver);
        } catch (UndeclaredThrowableException e) {
            //This checks for findElementByNoThrow, otherwise we call againLocate
            if (((InvocationTargetException) e.getUndeclaredThrowable()).getTargetException() instanceof NoSuchElementException) {
                try {
                    return driver.getDriver().findElement(getBy());
                } catch (UndeclaredThrowableException noSuchElementException) {
                    throw new NoSuchElementException("Unable to find lookUpElement " + getBy().toString() + ", Exception - " + ((InvocationTargetException) noSuchElementException
                            .getUndeclaredThrowable()).getTargetException().getMessage());
                }
            }
            againLocate();
            return find(driver);
        } catch (WebDriverException e) {
            return null;
        }
    }

    @Override
    public By getBy() {

        String locatorTypeAndValue = ReadElementPropertyValues.getInstance().getProperty(locator);

        String[] locatorTypeAndValueArray = locatorTypeAndValue.split(":");
        String locatorType = locatorTypeAndValueArray[0];
        String locatorValue = locatorTypeAndValueArray[1];

        switch (locatorType.toUpperCase()) {
            case "ID":
                return By.id(locatorValue);
            case "XPATH":
                return By.xpath(locatorValue);
            case "CLASS_NAME":
                return By.className(locatorValue);
            case "PARTIAL_LINKED_TEXT":
                return By.partialLinkText(locatorValue);
            case "TAG_NAME":
                return By.tagName(locatorValue);
            case "NAME":
                return By.name(locatorValue);
            case "LINKED_TEXT":
                return By.linkText(locatorValue);
            case "CSS":
                return By.cssSelector(locatorValue);
            default:
                MyLogger.log.error("Incorrect locator " + locatorType);
                return null;
        }
    }

    private void againLocate() {

        Driver driver = new Driver();
        WebElement againLocateElement = driver.getDriver().findElement(getBy());
        wrappedElement = againLocateElement instanceof Element ? ((Element) againLocateElement)
                .getWrappedWebElement() : againLocateElement;
        increment();
    }

    private void increment() {
        if (repeatLocateElementCounter > MAX_NUMBER_OF_REPEAT_LOCATE_ELEMENT) {
            throw new RuntimeException("Cannot interact properly with lookUpElement with locatable '"
                    + getBy() + "'"
                    + (!wrappedElement.isDisplayed() ? "Element was not displayed!" : ""));
        } else {
            repeatLocateElementCounter++;
        }
    }
}