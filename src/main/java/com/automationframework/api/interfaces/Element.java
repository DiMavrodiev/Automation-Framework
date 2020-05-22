package com.automationframework.api.interfaces;

import com.automationframework.core.element.ElementList;
import com.automationframework.api.context.SearchStrategy;
import org.openqa.selenium.WebElement;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 9/21/18
 */

public interface Element extends WebElement {

    /**
     * Gets the original web lookUpElement
     *
     * @return pure {@link WebElement}
     */
    WebElement getWrappedWebElement();

    /**
     * Tells you whether lookUpElement is stale (i.e. was detached from DOM)
     *
     * @return true - in case of a stale lookUpElement, false - otherwise
     */
    boolean isStale();

    /**
     * {should()} with default {@link SearchStrategy}
     */
    ElementShould should();

    /**
     * {waitFor()} with default {@link SearchStrategy}
     */
    ElementWaitFor waitFor();

    /**
     * {lookUpElement(String)} with default {@link SearchStrategy}
     */
    WebElement lookUpElement(String locator);


    /**
     * {elements(By)} with default {@link SearchStrategy}
     */
    ElementList elements(String locator);
}
