package com.automationframework.core.conditions;

import com.automationframework.api.driver.Driver;
import com.automationframework.api.interfaces.Conditions;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Function;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/24/18
 *
 * Conditions will perform search for lookUpElement(s) with fluent wait applied
 * and will end as soon as first lookUpElement(s) is found
 */

public class FirstFound implements Conditions {

    @Override
    public Function<Driver, List<WebElement>> visibilityOfAllElements(String locator) {

        return ElementExpectedConditions.visibilityOfAllElementsLocatedBy(locator);
    }

    @Override
    public Function<Driver, WebElement> visibility(String locator) {

        return ElementExpectedConditions.visibilityOfElementLocatedBy(locator);
    }

    @Override
    public Function<Driver, List<WebElement>> presenceOfAllElements(String locator) {

        return ElementExpectedConditions.presenceOfAllElementsLocatedBy(locator);
    }


    @Override
    public Function<Driver, WebElement> presence(String locator) {

        return ElementExpectedConditions.presenceOfElementLocatedBy(locator);
    }

    @Override
    public Function<Driver, String> title(String title) {

        return ElementExpectedConditions.titleIs(title);
    }

    @Override
    public Function<Driver, String> titleContains(String title) {

        return ElementExpectedConditions.titleContains(title);
    }

    @Override
    public Function<Driver, String> url(String url) {

        return ElementExpectedConditions.urlIs(url);
    }

    @Override
    public Function<Driver, String> urlContains(String urlContains) {

        return ElementExpectedConditions.urlContains(urlContains);
    }

    @Override
    public Function<Driver, Boolean> invisibilityOfElement(String locator) {

        return ElementExpectedConditions.isVisible(locator);
    }

    @Override
    public Function<Driver, Boolean> clickable(String locator) {

        return ElementExpectedConditions.elementClickable(locator);
    }

    @Override
    public Function<Driver, WebElement> select(String locator) {

        return ElementExpectedConditions.elementSelect(locator);
    }

    @Override
    public Function<Driver, WebElement> deselect(String locator) {

        return ElementExpectedConditions.elementDeselect(locator);
    }

    @Override
    public Function<Driver, Boolean> pageLoaded(String url) {

        return ElementExpectedConditions.pageLoaded(url);
    }

    @Override
    public Function<Driver, Boolean> isUrlChanged(String url1, String url2) {

        return ElementExpectedConditions.urlChange(url1, url2);
    }

    @Override
    public Function<Driver, Boolean> isTitleChanged(String title1, String title2) {

        return ElementExpectedConditions.titleChanged(title1, title2);
    }

    @Override
    public Function<Driver, Boolean> findAndSwitchTabByUrl(String URL) {

        return ElementExpectedConditions.switchingToWindowByUrl(URL);
    }

    @Override
    public Function<Driver, Boolean> findAndSwitchTabByPartialUrl(String partialUrl){

        return ElementExpectedConditions.switchingToWindowTabByPartialUrl(partialUrl);
    }

    @Override
    public Function<Driver, Boolean> findAndSwitchTabByTitle(String title) {

        return ElementExpectedConditions.switchingToTabByTitle(title);
    }

    @Override
    public Function<Driver, Boolean> isElementVisible(String locator) {

        return ElementExpectedConditions.isElementVisible(locator);
    }
}