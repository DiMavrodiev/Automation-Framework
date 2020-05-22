package com.automationframework.core.element;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.api.interfaces.*;
import com.automationframework.core.element.validator.NullShouldImmediately;
import com.automationframework.core.element.waitfor.NullElementWaitForImmediately;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

import java.util.List;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/5/18
 */

public class NullElement implements Element, Locatable {

    private ElementData elementData;

    public NullElement(ElementData elementData) {
        this.elementData = elementData;
    }

    @Override
    public ElementShould should() {

        return new NullShouldImmediately(this);
    }


    @Override
    public ElementWaitFor waitFor() {

        return new NullElementWaitForImmediately(this);
    }

    /*
      All other methods of Element should throw an exception because it's not possible to
      interact with lookUpElement that does not exist
     */

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) {

        throw noSuchElementException();
    }

    @Override
    public WebElement getWrappedWebElement() {

        throw noSuchElementException();
    }

    @Override
    public void submit() {

        throw noSuchElementException();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {

        throw noSuchElementException();
    }

    @Override
    public void clear() {

        throw noSuchElementException();
    }

    @Override
    public String getTagName() {

        throw noSuchElementException();
    }

    @Override
    public String getAttribute(String s) {

        throw noSuchElementException();
    }

    @Override
    public boolean isSelected() {

        throw noSuchElementException();
    }

    @Override
    public boolean isEnabled() {

        throw noSuchElementException();
    }

    @Override
    public boolean isStale() {

        throw noSuchElementException();
    }

    @Override
    public String getText() {

        throw noSuchElementException();
    }

    @Override
    public List<WebElement> findElements(By by) {

        throw noSuchElementException();
    }

    @Override
    public WebElement findElement(By by) {

        throw noSuchElementException();
    }

    @Override
    public boolean isDisplayed() {

        throw noSuchElementException();
    }

    @Override
    public Point getLocation() {

        throw noSuchElementException();
    }

    @Override
    public Dimension getSize() {

        throw noSuchElementException();
    }

    @Override
    public Rectangle getRect() {

        throw noSuchElementException();
    }

    @Override
    public String getCssValue(String s) {

        throw noSuchElementException();
    }

    @Override
    public WebElement lookUpElement(String locator) {

        throw noSuchElementException();
    }

    @Override
    public ElementList elements(String locator) {

        throw noSuchElementException();
    }

    @Override
    public void click() {

        throw noSuchElementException();
    }

    @Override
    public Coordinates getCoordinates() {

        throw noSuchElementException();
    }

    private NoSuchElementException noSuchElementException() {

        return new NoSuchElementException("Unable to find lookUpElement with locatable '" + elementData.getLocator() + "'");
    }
}