package com.automationframework.api.interfaces;

import com.automationframework.api.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Function;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/24/18
 *
 * Abstraction representing different kinds of conditions
 * that might be need while finding elements
 *
 * Usage context is to be implemented by conditions defined by different strategies:
 * - Wait for first visible lookUpElement
 * - wait for all visible elements
 * - wait for first present lookUpElement
 * - wait for all present elements
 */

public interface Conditions {

    Function<Driver, List<WebElement>> visibilityOfAllElements(String locator);

    Function<Driver, WebElement> visibility(String locator);

    Function<Driver, List<WebElement>> presenceOfAllElements(String locator);

    Function<Driver, WebElement> presence(String locator);

    Function<Driver, String> title(String title);

    Function<Driver, String> titleContains(String title);

    Function<Driver, String> url(String url);

    Function<Driver, String> urlContains(String url);

    Function<Driver, Boolean> invisibilityOfElement(String locator);

    Function<Driver, Boolean> clickable(String locator);

    Function<Driver, WebElement> select(String locator);

    Function<Driver, WebElement> deselect(String locator);

    Function<Driver, Boolean> pageLoaded(String url);

    Function<Driver, Boolean> isUrlChanged(String url1, String url2);

    Function<Driver, Boolean> isTitleChanged(String title1, String title2);

    Function<Driver, Boolean> findAndSwitchTabByUrl(String ulr);

    Function<Driver, Boolean> findAndSwitchTabByPartialUrl(String partialUrl);

    Function<Driver, Boolean> findAndSwitchTabByTitle(String title);

    Function<Driver, Boolean> isElementVisible(String locator);

}