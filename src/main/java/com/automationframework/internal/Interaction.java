package com.automationframework.internal;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.api.driver.Driver;
import com.automationframework.core.element.VisibleElement;
import com.automationframework.api.exceptions.WrappedActionException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 1/18/19
 */

public class Interaction extends VisibleElement {

    private Interaction(ElementData data) {

        super(data);
    }

    private static Interaction interaction(String locator) {

        ElementData data = new ElementData(locator);
        return new Interaction(data);
    }

    public static void doubleClick(String locator) {

        try {
            interaction(locator).waitFor().displayed();
            wrappedActions().doubleClick(interaction(locator)).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WrappedActionException("****** ERROR ****** this action can NOT be performed");
        }

    }

    public static void hoverOverElement(String locator) {

        try {
            wrappedActions().moveToElement(interaction(locator)).perform();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WrappedActionException("****** ERROR ****** this action can NOT be performed");
        }
    }

    public static void rightClick(String locator) {

        try {
            wrappedActions().contextClick(interaction(locator)).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WrappedActionException("****** ERROR ****** this action can NOT be performed");
        }
    }

    public static void rightClickAndSelect(String locator, Keys... key) {

        try {
            wrappedActions().contextClick(interaction(locator)).sendKeys(key).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WrappedActionException("****** ERROR ****** this action can NOT be performed");
        }
    }

    public static void clickAndSelect(String locator, Keys... key) {

        try {
            interaction(locator).waitFor().displayed();
            wrappedActions().click(interaction(locator)).sendKeys(key).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WrappedActionException("****** ERROR ****** this action can NOT be performed");
        }

    }

    public static void hoverOverAndClick(String locator) {

        try {
            interaction(locator).waitFor().displayed();
            wrappedActions().moveToElement(interaction(locator)).click().build().perform();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WrappedActionException("****** ERROR ****** this action can NOT be performed");
        }
    }

    public static void dragAndDrop(String source, String target) {

        try {
             interaction(source).waitFor().displayed();
             wrappedActions().dragAndDrop(interaction(source), interaction(target)).perform();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WrappedActionException("****** ERROR ****** this action can NOT be performed");
        }
    }

    public static void clickAndSlide(String locator, int x, int y) {

        try {
            interaction(locator).waitFor().displayed();
            wrappedActions().clickAndHold(interaction(locator)).moveByOffset(x,y).release(interaction(locator)).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WrappedActionException("****** ERROR ****** this action can NOT be performed");
        }
    }

    private static Actions wrappedActions() {

        Driver driver = new Driver();
        return new Actions(driver.getDriver());
    }
}