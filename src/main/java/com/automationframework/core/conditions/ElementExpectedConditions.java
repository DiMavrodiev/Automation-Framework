package com.automationframework.core.conditions;

import com.automationframework.api.driver.Driver;
import com.automationframework.core.utils.ExecutionUtils;
import com.automationframework.core.reporter.Report;
import com.automationframework.core.element.locate.ElementLocatable;
import com.automationframework.core.element.locate.ElementsLocatable;
import com.automationframework.api.interfaces.ElementExpectedCondition;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;


/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/24/18
 *
 * Library of custom expected conditions
 */

public final class ElementExpectedConditions {

    private ElementExpectedConditions() {
    }

    public static ElementExpectedCondition<WebElement> presenceOfElementLocatedBy(final String locator) {
        return new ElementExpectedCondition<>() {
            @Override
            public WebElement apply(@NotNull final Driver driver) {
                ElementsLocatable elementsLocatable = new ElementsLocatable(locator);
                List<WebElement> els = elementsLocatable.findElements();
                return els.isEmpty() ? null : els.get(0);
            }

            @Override
            public String toString() {

                return String.format("presence of lookUpElement located by %s", locator);
            }
        };
    }

    public static ElementExpectedCondition<List<WebElement>> presenceOfAllElementsLocatedBy(final String locator) {
        return new ElementExpectedCondition<>() {
            @Override
            public List<WebElement> apply(@NotNull final Driver driver) {
                ElementsLocatable elementsLocatable = new ElementsLocatable(locator);
                List<WebElement> els = elementsLocatable.findElements();
                return els.isEmpty() ? null : els;
            }

            @Override
            public String toString() {

                return String.format("presence of all elements located by %s", locator);
            }
        };
    }

    public static ElementExpectedCondition<String> urlContains(String urlContains) {
        return new ElementExpectedCondition<>() {
            @Override
            public String apply(@NotNull Driver driver) {

                return getUrlContains(driver, urlContains);
            }

            @Override
            public String toString() {

                return String.format("URL contains %s", urlContains);
            }
        };
    }

    public static ElementExpectedCondition<String> titleContains(String titleContains) {
        return new ElementExpectedCondition<>() {
            @Override
            public String apply(@NotNull Driver driver) {

                return getTitleContains(driver, titleContains);
            }

            @Override
            public String toString() {

                return String.format("title of the web page contains %s", titleContains);
            }
        };
    }

    public static ElementExpectedCondition<String> titleIs(String title) {
        return new ElementExpectedCondition<>() {
            @Override
            public String apply(@NotNull Driver driver) {

                return getTitle(driver, title);
            }

            @Override
            public String toString() {

                return String.format("title of the web page is %s", title);
            }
        };
    }

    public static ElementExpectedCondition<String> urlIs(String url) {
        return new ElementExpectedCondition<>() {
            @Override
            public String apply(@NotNull Driver driver) {

                return getUrl(driver, url);
            }

            @Override
            public String toString() {

                return String.format("url of the web page is %s", url);
            }
        };
    }

    public static ElementExpectedCondition<Boolean> isVisible(String locator) {
        return new ElementExpectedCondition<>() {
            @Override
            public Boolean apply(@NotNull Driver driver) {

                return isElementVisible(driver, locator);
            }

            @Override
            public String toString() {

                return String.format("lookUpElement is visible %s", locator);
            }
        };
    }

    public static ElementExpectedCondition<Boolean> elementClickable(String locator) {
        return new ElementExpectedCondition<>() {
            @Override
            public Boolean apply(@NotNull Driver driver) {

                return elementToBeClickable(driver, locator);
            }

            @Override
            public String toString() {

                return String.format("lookUpElement is NOT clickable %s", locator);
            }
        };
    }

    public static ElementExpectedCondition<WebElement> elementSelect(String locator) {
        return new ElementExpectedCondition<>() {
            @Override
            public WebElement apply(@NotNull Driver driver) {

                return elementToBeSelected(driver, locator);
            }

            @Override
            public String toString() {

             return String.format("lookUpElement NOT selected %s", locator);
            }
        };
    }

    public static ElementExpectedCondition<Boolean> isElementVisible(String locator) {
        return new ElementExpectedCondition<>() {
            @Override
            public Boolean apply(@NotNull Driver driver) {
                return isDisplayed(driver, locator);
            }

            @Override
            public String toString() {

                return String.format("element NOT visible %s", locator);
            }
        };
    }


    public static ElementExpectedCondition<WebElement> elementDeselect(String locator) {
        return new ElementExpectedCondition<>() {
            @Override
            public WebElement apply(@NotNull Driver driver) {

                return elementToBeDeselected(driver, locator);
            }

            @Override
            public String toString() {

                return String.format("lookUpElement NOT deselected %s", locator);
            }
        };
    }

    public static ElementExpectedCondition<Boolean> pageLoaded(String url) {
        return new ElementExpectedCondition<>() {
            @Override
            public Boolean apply(@NotNull Driver driver) {

                return pageLoaded(driver, url);
            }

            @Override
            public String toString() {

                return "Page NOT loaded";
            }
        };
    }

    public static ElementExpectedCondition<Boolean> urlChange(String url1, String url2) {
        return new ElementExpectedCondition<>() {
            @Override
            public Boolean apply(@NotNull Driver driver) {

                return isUrlChange(driver, url1, url2);
            }

            @Override
            public String toString() {

                return String.format("URL should contain %s", url2);
            }
        };
    }

    public static ElementExpectedCondition<Boolean> titleChanged(String title1, String title2) {
        return new ElementExpectedCondition<>() {
            @Override
            public Boolean apply(@NotNull Driver driver) {

                return isTitleChange(driver, title1, title2);
            }

            @Override
            public String toString() {

                return String.format("Title should contain %s", title2);
            }
        };

    }

    private static Boolean isTitleChange(Driver driver, String title1, String title2) {

        final String currentTitle = driver.getTitle();
        try {
            if (currentTitle.equals(title2)) {
                return true;
            } else if (currentTitle.equals(title1)) {
                WebDriverWait wait = new WebDriverWait(driver.getDriver(), driver.getPageLoadTimeout());
                wait.until(ExpectedConditions.titleContains(title2));
                return true;
            } else {
                return false;
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Boolean isDisplayed(Driver driver, String locator) {

        final boolean visible;
        final WebElement element;
        try {
            ElementLocatable elementsLocatable = new ElementLocatable(locator);
            element = elementsLocatable.find(driver);
            visible = element.isDisplayed();
            if (visible) {
                return true;
            } else {
                WebDriverWait wait = new WebDriverWait(driver.getDriver(), driver.getPageLoadTimeout());
                wait.until(ExpectedConditions.visibilityOf(element));
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;

        }
    }

    private static Boolean isUrlChange(Driver driver, String url1, String url2) {

        final String currentUrl = driver.getCurrentUrl();
        try {
            if (currentUrl.contains(url2)) {
               return true;
            } else if (currentUrl.contains(url1)) {
                WebDriverWait wait = new WebDriverWait(driver.getDriver(), driver.getPageLoadTimeout());
                wait.until(ExpectedConditions.urlContains(url2));
                return true;
            } else {
                return false;
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Boolean pageLoaded(Driver driver, String url) {

        final String currentUrl = driver.getCurrentUrl();
        try {
            if (currentUrl.equals(url)) {
                if (((JavascriptExecutor) driver.getDriver()).executeScript("return document.readyState").equals("complete")) {
                    return true;
                } else {
                    WebDriverWait wait = new WebDriverWait(driver.getDriver(), driver.getPageLoadTimeout());
                    wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState"));
                    return true;
                }
            } else {
                WebDriverWait wait = new WebDriverWait(driver.getDriver(), driver.getPageLoadTimeout());
                wait.until(ExpectedConditions.urlToBe(url));
            }
            return false;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    private static WebElement elementToBeSelected(Driver driver, String locator) {

        final boolean selected;
        final WebElement element;
        try {
            ElementLocatable elementLocatable = new ElementLocatable(locator);
            element = elementLocatable.find(driver);
            selected = element.isSelected();
           if (element.isDisplayed() && !selected) {
               return element;
           } else {
               WebDriverWait wait = new WebDriverWait(driver.getDriver(), driver.getPageLoadTimeout());
               wait.until(ExpectedConditions.elementSelectionStateToBe(element, false));
               return element;
           }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static WebElement elementToBeDeselected(Driver driver, String locator) {

        final boolean selected;
        final WebElement element;
        try {
            ElementLocatable elementsLocatable = new ElementLocatable(locator);
            element = elementsLocatable.find(driver);
            selected = element.isSelected();
            if (element.isDisplayed() && selected) {
                return element;
            } else {
                WebDriverWait wait = new WebDriverWait(driver.getDriver(), driver.getPageLoadTimeout());
                wait.until(ExpectedConditions.elementSelectionStateToBe(element, true));
                return element;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getTitle(Driver driver, String title) {

        try {
            final String titleIs = driver.getTitle();
            if (titleIs.equals(title)) {
                return titleIs;
            } else {
                WebDriverWait wait = new WebDriverWait(driver.getDriver(), driver.getPageLoadTimeout());
                wait.until(ExpectedConditions.titleIs(title));
                return title;
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String  getUrlContains(Driver driver, String urlContains) {

        final String url = driver.getCurrentUrl();
        try {
            if (url.contains(urlContains)) {
                return urlContains;
            } else {
                WebDriverWait wait = new WebDriverWait(driver.getDriver(), driver.getPageLoadTimeout());
                wait.until(ExpectedConditions.urlContains(urlContains));
                return urlContains;
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getTitleContains(Driver driver, String titleContains) {

        final String title = driver.getTitle();
        try {
            if (title.contains(titleContains)) {
                return titleContains;
            } else {
                WebDriverWait wait = new WebDriverWait(driver.getDriver(), driver.getPageLoadTimeout());
                wait.until(ExpectedConditions.titleContains(titleContains));
                return titleContains;
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getUrl(Driver driver, String url) {

        final String currentUrl = driver.getCurrentUrl();
        try {
            if (currentUrl.equals(url)) {
                return url;
            } else {
                WebDriverWait wait = new WebDriverWait(driver.getDriver(), driver.getPageLoadTimeout());
                wait.until(ExpectedConditions.urlToBe(url));
                return url;
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Boolean isElementVisible(Driver driver, String locator) {

        ElementsLocatable elementsLocatable = new ElementsLocatable(locator);
        List<WebElement> elements;
        elements = elementsLocatable.findElements();
        try {
            if (elements.isEmpty()) {
                return true;
            } else {
                WebDriverWait wait = new WebDriverWait(driver.getDriver(), driver.getPageLoadTimeout());
                wait.until(ExpectedConditions.invisibilityOfElementLocated(elementsLocatable.getBy()));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Boolean elementToBeClickable(Driver driver, String locator) {

        ElementLocatable elementLocatable = new ElementLocatable(locator);
        final WebElement clickable = elementLocatable.find(driver);
        try {
            if (clickable != null && clickable.isDisplayed() && clickable.isEnabled()) {
                return true;
            } else if (clickable != null) {
                WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver.getDriver(), driver.getPageLoadTimeout())
                        .ignoring(StaleElementReferenceException.class);
                wait.until(ExpectedConditions.elementToBeClickable(clickable));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Expected condition to look for elements that will return as soon as elements are found
     *
     * @param locator
     * @return
     */

    public static ElementExpectedCondition<List<WebElement>> visibilityOfAllElementsLocatedBy(final String locator) {
        return new ElementExpectedCondition<>() {
            @Override
            public List<WebElement> apply(@NotNull final Driver driver) {
                return getFirstVisibleWebElements(locator);
            }

            @Override
            public String toString() {

                return String.format("visibility of lookUpElement located by %s", locator);
            }
        };
    }

    private static List<WebElement> getFirstVisibleWebElements(String locator) {
        List<WebElement> elements;
        ElementsLocatable elementLocatable = new ElementsLocatable(locator);
        elements = elementLocatable.findElements();

        List<WebElement> visibleElements = elements
                .stream()
                .filter(ElementExpectedConditions::isAvailable)
                .collect(Collectors.toList());

        return isNotEmpty(visibleElements) ? visibleElements : null;
    }

    /**
     * Defines whether it's possible to work with lookUpElement
     * i.e. is displayed or located under scroll.
     *
     * @param element - our lookUpElement
     * @return - true if lookUpElement is available for the user.
     */

    private static boolean isAvailable(WebElement element) {
        return element.isDisplayed() || isElementHiddenUnderScroll(element)
                && !element.getCssValue("visibility").equals("hidden");
    }

    public static ElementExpectedCondition<WebElement> visibilityOfElementLocatedBy(String locator) {
        return new ElementExpectedCondition<>() {
            @Override
            public WebElement apply(@NotNull final Driver driver) {

                return getVisibleWebElement(driver, locator);
            }

            @Override
            public String toString() {

                return String.format("visibility of lookUpElement located by %s", locator);
            }
        };
    }

    private static WebElement getVisibleWebElement(Driver driver, String locator) {
        try {
            ElementLocatable elementLocatable = new ElementLocatable(locator);
            final WebElement foundElement = elementLocatable.find(driver);
            if (foundElement != null && isAvailable(foundElement)) {
                return foundElement;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Trick with zero coordinates for not-displayed lookUpElement works only in FF
     */
    private static boolean isElementHiddenUnderScroll(WebElement element) {
        return ExecutionUtils.isFF() && element.getLocation().getX() > 0 && element.getLocation()
                .getY() > 0;
    }

    /**
     * We don't know the actual window title without switching to one.
     * This method was always used to make sure that the window appeared. After it we switched to appeared window.
     * Switching between windows is rather time consuming operation
     * <p>
     * To avoid the double switching to the window we are switching to window in this method
     * <p>
     * The same approach is applies to all PageCondition for windows
     *
     * @param title - title of window
     * @return - handle of expected window
     */

    public static ElementExpectedCondition<Boolean> switchingToTabByTitle(final String title) {
        return new ElementExpectedCondition<>() {
            @Override
            public Boolean apply(@NotNull final Driver driver) {

                return titleMatch(driver, title);
            }

            @Override
            public String toString() {
                return String.format("appearing of window by title %s and switch to it", title);
            }
        };
    }

    private static boolean titleMatch(Driver driver, String title) {

        for (String tab : driver.getDriver().getWindowHandles()) {
            driver.getDriver().switchTo().window(tab);
            if (driver.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

    public static ElementExpectedCondition<Boolean> switchingToWindowByUrl(final String url) {
        return new ElementExpectedCondition<>() {
            @Override
            public Boolean apply(@NotNull final Driver driver) {

                return urlMatch(driver, url);
            }

            @Override
            public String toString() {

                return String.format("appearing of window by url %s and switch to it", url);
            }
        };
    }


    private static boolean urlMatch(Driver driver, String url) {

        for (String tab : driver.getDriver().getWindowHandles()) {
            driver.getDriver().switchTo().window(tab);
            if (driver.getCurrentUrl().equalsIgnoreCase(url)) {
              return true;
            }
        }
        return false;
    }

    public static ElementExpectedCondition<Boolean> switchingToWindowTabByPartialUrl(final String partialUrl) {
        return new ElementExpectedCondition<>() {
            @Override
            public Boolean apply(@NotNull final Driver driver) {

                return partialUrlMatch(driver, partialUrl);
            }

            @Override
            public String toString() {
                return String.format("appearing of window by partial url %s and switch to it", partialUrl);
            }
        };
    }

    private static boolean partialUrlMatch(Driver driver, String partialUrl) {

        for (String tab : driver.getDriver().getWindowHandles()) {
            driver.getDriver().switchTo().window(tab);
            if (driver.getCurrentUrl().contains(partialUrl)) {
                return true;
            }
        }
        return false;
    }


    private static boolean isMatch(String initial, String expected) {
        if (expected.isEmpty()) {
            Report.error("*****EMPTY WINDOW HANDLE*****Attempt to switch to window with '' handle detected! Ignoring it.");
            return false;
        }
        return initial.equals(expected);
    }
}