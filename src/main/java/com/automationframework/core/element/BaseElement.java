package com.automationframework.core.element;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.api.driver.Driver;
import com.automationframework.core.reporter.Report;
import com.automationframework.core.element.find.VisibleElementLookup;
import com.automationframework.core.element.find.VisibleElementsLookup;
import com.automationframework.api.interfaces.Element;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import static com.automationframework.core.actions.JsActions.executeScript;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 */

public abstract class BaseElement implements Element, Locatable {

    private WebElement wrappedElement;

    private int repeatLocateElementCounter;
    private List<WebElement> elements;

    //TODO think about making this value configurable
    private static final int MAX_NUMBER_OF_REPEAT_LOCATE_ELEMENT = 5;

     public BaseElement(ElementData elementData) {
        this.repeatLocateElementCounter = 0;
        this.wrappedElement = getWrappedElement(elementData);
        this.elements = getWrappedElements(elementData);
    }

    private WebElement getWrappedElement(ElementData elementData) {
        try {
            WebElement element = lookUpElement(elementData.getLocator());
            return element instanceof Element ? ((Element) element).getWrappedWebElement() : element;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<WebElement> getWrappedElements(ElementData elementData) {
         try {
             List<WebElement> elements = elements(elementData.getLocator());
             return elements instanceof WebElement ? ((ElementList) elements) : elements;
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
    }

    @Override
    public WebElement lookUpElement(String locator) {

        Driver driver = new Driver();
        return new VisibleElementLookup(driver).find(locator);
    }

    @Override
    public ElementList elements(String locator) {

        Driver driver = new Driver();
        return new VisibleElementsLookup(driver).find(locator);
    }

    @Override
    public void click() {

         wrappedElement.click();
    }

    public void clickForNeedToScroll() {
        Report.info("ElementNotVisibleException***** during click! Scrolling to lookUpElement and trying again ---Locatable=");
        increment();
        scrollIntoView(wrappedElement);
        scrollToElementLocation(wrappedElement);
        click();
    }

    public void clickForIgnoredScroll(WebDriverException ignoredOrNeedToScroll) {

        Report.info("*****WebDriverException***** during click!");
        increment();
        Driver driver = new Driver();
        //For Android error text is different and does not have any information related to clickable issue
        String ignoredOrNeedToScrollMessage = ignoredOrNeedToScroll.getMessage();
        if (ignoredOrNeedToScrollMessage.contains("is not clickable at point")) {
            Report.info("*****Element is not clickable at point***** during click! Scrolling to lookUpElement and trying again.");

            scrollIntoView(wrappedElement);
            scrollToElementLocation(wrappedElement);
        }
        if (ignoredOrNeedToScrollMessage.contains("Error: lookUpElement is not attached to the page document")) {
            againLocate();
        }
        if (ignoredOrNeedToScrollMessage.contains("unknown error: no lookUpElement reference returned by script")) {
            againLocate();
            executeScript("arguments[0].click();", wrappedElement);
        } else if (ignoredOrNeedToScrollMessage.contains("Other lookUpElement would receive the click")) {
            //TODO NT: workaround for 2.49.1
            //If dropdown option lookUpElement have bigger size then dropdown we findElements error 'Element is not clickable at point... Other lookUpElement would receive the click...'
            new Actions(driver.getDriver())
                    .moveToElement(wrappedElement, 0, 0)
                    .click()
                    .perform();
        } else {
            click();
        }
    }

    @Override
    public void submit() {

        wrappedElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        try {
            wrappedElement.sendKeys(charSequences);
        } catch (StaleElementReferenceException e) {
            againLocate();
            sendKeys(charSequences);
        }
    }

    @Override
    public void clear() {
        try {
            wrappedElement.clear();
        } catch (StaleElementReferenceException e) {
            againLocate();
            clear();
        }
    }

    @Override
    public String getTagName() {
        try {
            return wrappedElement.getTagName();
        } catch (StaleElementReferenceException e) {
            againLocate();
            return getTagName();
        }
    }

    @Override
    public String getAttribute(String s) {
        try {
            return wrappedElement.getAttribute(s);
        } catch (StaleElementReferenceException e) {
            againLocate();
            return getAttribute(s);
        }
    }

    @Override
    public boolean isSelected() {
        try {
            return wrappedElement.isSelected();
        } catch (StaleElementReferenceException e) {
            againLocate();
            return isSelected();
        }
    }

    /**
     * This method *should not* catch StaleElementReferenceException
     * as it is used to define staleness of lookUpElement
     */
    @Override
    public boolean isEnabled() {
        try {
            return wrappedElement.isEnabled();
        } catch (StaleElementReferenceException e) {
            againLocate();
            return isEnabled();
        }
    }

    @Override
    public boolean isStale() {
        try {
            wrappedElement.isEnabled();
            return false;
        } catch (StaleElementReferenceException elementIsStale) {
            return true;
        }
    }

    @Override
    public String getText() {
        try {
            String text;
            if (isInputTextField()) {
                text = wrappedElement.getText().isEmpty() ? wrappedElement.getAttribute("value") : wrappedElement.getText();
            } else {
                text = wrappedElement.getText();
            }
            return text;
        } catch (StaleElementReferenceException e) {
            againLocate();
            return getText();
        }
    }

    private boolean isInputTextField() {
        return wrappedElement.getTagName().equals("input")
                && wrappedElement.getAttribute("type") != null
                && wrappedElement.getAttribute("type").equals("text");
    }

    public WebElement findElement(By by) {

        return getWrappedWebElement();
    }

    public List<WebElement> findElements(By by) {
        this.repeatLocateElementCounter = 0;
        return finds(by);
    }

    @Override
    public boolean isDisplayed() {
        try {
            return wrappedElement.isDisplayed();
        } catch (StaleElementReferenceException e) {
            againLocate();
            return isDisplayed();
        }
    }

    @Override
    public Point getLocation() {
        try {
            return wrappedElement.getLocation();
        } catch (StaleElementReferenceException e) {
            againLocate();
            return getLocation();
        }
    }

    @Override
    public Dimension getSize() {
        try {
            return wrappedElement.getSize();
        } catch (StaleElementReferenceException e) {
            againLocate();
            return getSize();
        }
    }

    @Override
    public Rectangle getRect() {
        try {
            return wrappedElement.getRect();
        } catch (StaleElementReferenceException e) {
            againLocate();
            return getRect();
        }
    }

    @Override
    public String getCssValue(String s) {
        try {
            return wrappedElement.getCssValue(s);
        } catch (StaleElementReferenceException e) {
            againLocate();
            return getCssValue(s);
        }
    }

    @Override
    public Coordinates getCoordinates() {
        return ((org.openqa.selenium.interactions.Locatable) getWrappedWebElement()).getCoordinates();
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) {
        Driver driver = new Driver();
        if (driver.getDriver().getClass() == RemoteWebDriver.class) {
            WebDriver augmentedDriver = new Augmenter().augment(driver.getDriver());
            return ((TakesScreenshot) augmentedDriver).getScreenshotAs(target);
        } else {
            return ((TakesScreenshot) driver.getDriver()).getScreenshotAs(target);
        }
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o != null && wrappedElement != null && wrappedElement.equals(o) ||
                this == o || o != null && wrappedElement != null && (o instanceof Element) && wrappedElement
                .equals(((Element) o).getWrappedWebElement());
    }

    @Override
    public int hashCode() {

        return wrappedElement != null ? wrappedElement.hashCode() : 0;
    }

    @Override
    public WebElement getWrappedWebElement() {

        return wrappedElement;
    }

    public List<WebElement> getWrappedWebElements() {

        return elements;
    }


    private void againLocate() {

        WebElement againLocateElement = getWrappedWebElement();
        wrappedElement = againLocateElement instanceof Element ? ((Element) againLocateElement)
                .getWrappedWebElement() : againLocateElement;
        increment();
    }

    private void increment() {
        if (repeatLocateElementCounter > MAX_NUMBER_OF_REPEAT_LOCATE_ELEMENT) {
            throw new RuntimeException("Cannot interact properly with lookUpElement with locatable '"
                    + (!wrappedElement.isDisplayed() ? "Element was not displayed!" : ""));
        } else {
            repeatLocateElementCounter++;
        }
    }

    private void scrollIntoView(WebElement element) {

        executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void scrollToElementLocation(WebElement element) {
        executeScript("scroll(" + (element.getLocation().getX() + element.getSize()
                .getWidth()) + "," + element.getLocation().getY() + ");");
    }

    private List<WebElement> finds(By by) {
        try {
            //for catch stale lookUpElement
            Driver driver = new Driver();
            isEnabled();
            return driver.getDriver().findElements(by);
        } catch (StaleElementReferenceException e) {
            againLocate();
            return finds(by);
        } catch (UndeclaredThrowableException e) {
            //This checks for findElementByNoThrow, otherwise we call againLocate
            if (((InvocationTargetException) e.getUndeclaredThrowable()).getTargetException() instanceof NoSuchElementException) {
                try {
                    Driver driver = new Driver();
                    return driver.getDriver().findElements(by);
                } catch (UndeclaredThrowableException noSuchElementException) {
                    throw new NoSuchElementException("Unable to find elements " + by.toString() + ", Exception - " + ((InvocationTargetException) noSuchElementException
                            .getUndeclaredThrowable()).getTargetException().getMessage());
                }
            }
            againLocate();
            return finds(by);
        }
    }

    private WebElement getParentElement(WebElement element) {

        return (WebElement) executeScript("return arguments[0].parentNode", element);
    }
}