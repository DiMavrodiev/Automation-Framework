package com.automationframework.core.element.find;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.api.wait.ElementFluentWait;
import com.automationframework.core.conditions.ConditionFactory;
import com.automationframework.api.driver.Driver;
import com.automationframework.core.element.NullElement;
import com.automationframework.api.interfaces.LookupWithLocator;
import com.automationframework.api.context.SearchStrategy;
import org.openqa.selenium.WebElement;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 *
 * Finding a visible lookUpElement
 */

public class VisibleElementLookup implements LookupWithLocator<WebElement> {

    private final ElementFluentWait<Driver> fluentWait;
    private final ConditionFactory conditionFactory;


    public VisibleElementLookup(Driver driver) {
        this.fluentWait = new ElementFluentWait<>(driver, new SearchStrategy());
        this.conditionFactory = new ConditionFactory();
    }


    public WebElement find(String locator) {

        WebElement webElement;
        try {
            webElement = fluentWait.waitFor(conditionFactory.get().visibility(locator));
        } catch (AssertionError ignoredToReturnEmptyList) {
            webElement = null;
        }
        if (webElement == null) {
            //this is needed to return null when the user explicitly set to receive null in case of failure
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return null;
            }
        }
        ElementData data = new ElementData (locator);
        return webElement == null ? new NullElement(data) : webElement;
    }

    public boolean isElementVisible(String locator) {

        boolean visible;
        try {
            visible = fluentWait.waitFor(conditionFactory.get().isElementVisible(locator));
        } catch (AssertionError ignoreElement) {
            visible = false;
        }
        if (!visible) {
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return false;
            }
        }
        return visible;
    }

    public Boolean isElementClickable(String locator) {

        boolean elementClickable;
        elementClickable = fluentWait.waitFor(conditionFactory.get().clickable(locator));
        return elementClickable;
    }

    public WebElement exist(String locator) {

        WebElement webElement;
        try {
            webElement = fluentWait.waitFor(conditionFactory.get().presence(locator));
        } catch (AssertionError ignoredToReturnEmptyList) {
            webElement = null;
        }
        if (webElement == null) {
            //this is needed to return null when the user explicitly set to receive null in case of failure
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return null;
            }
        }
        ElementData data = new ElementData(locator);
        return webElement == null ? new NullElement(data) : webElement;

    }


    public WebElement elementSelect(String locator) {

        WebElement webElement;
        try {
            webElement = fluentWait.waitFor(conditionFactory.get().select(locator));
        } catch (AssertionError ignoreSelected) {
            webElement = null;
        }
        if (webElement == null) {
            //this is needed to return null when the user explicitly set to receive null in case of failure
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return null;
            }
        }
        return webElement;
    }

    public WebElement elementDeselect(String locator) {

        WebElement webElement;
        try {
            webElement = fluentWait.waitFor(conditionFactory.get().deselect(locator));
        } catch (AssertionError ignoreDeselected) {
            webElement = null;
        }
        if (webElement == null) {
            //this is needed to return null when the user explicitly set to receive null in case of failure
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return null;
            }
        }
        return webElement;
    }
}