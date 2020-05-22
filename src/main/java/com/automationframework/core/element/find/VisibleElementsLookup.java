package com.automationframework.core.element.find;

import com.automationframework.api.wait.ElementFluentWait;
import com.automationframework.core.conditions.ConditionFactory;
import com.automationframework.api.driver.Driver;
import com.automationframework.core.element.ElementList;
import com.automationframework.api.interfaces.LookupWithLocator;
import com.automationframework.api.context.SearchStrategy;
import org.openqa.selenium.WebElement;

import java.util.List;
/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/8/18
 *
 * Finding visible elements
 */

public class VisibleElementsLookup implements LookupWithLocator<ElementList> {

    private final ElementFluentWait<Driver> fluentWait;
    private final ConditionFactory conditionFactory;


    public VisibleElementsLookup(Driver driver) {
        this.fluentWait = new ElementFluentWait<>(driver, new SearchStrategy());
        this.conditionFactory = new ConditionFactory();
    }

    @Override
    public ElementList find(String locator) {
        List<WebElement> webElements;
        try {
            webElements = fluentWait.waitFor(conditionFactory.get().visibilityOfAllElements(locator));
        } catch (AssertionError ignoredToReturnEmptyList) {
            webElements = null;
        }

        if (webElements == null) {
            //this is needed to return null when the user explicitly set to receive null in case of failure
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return null;
            }
        }
        return new ElementList(webElements, locator);
    }

    @Override
    public ElementList exist(String locator) {

        List<WebElement> webElements;
        try {
            webElements = fluentWait.waitFor(conditionFactory.get().presenceOfAllElements(locator));
        } catch (AssertionError ignoredToReturnEmptyList) {
            webElements = null;
        }

        if (webElements == null) {
            //this is needed to return null when the user explicitly set to receive null in case of failure
            SearchStrategy strategy = new SearchStrategy();
            if (strategy.isNullOnFailure()) {
                return null;
            }
        }
        return new ElementList(webElements, locator);
    }
}