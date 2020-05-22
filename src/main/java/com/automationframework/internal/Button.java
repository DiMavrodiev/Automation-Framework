package com.automationframework.internal;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.api.driver.Driver;
import com.automationframework.core.reporter.Report;
import com.automationframework.core.element.VisibleElement;
import com.automationframework.core.element.find.VisibleElementLookup;
import com.automationframework.core.utils.TestUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.UnreachableBrowserException;

import static com.automationframework.core.actions.JsActions.executeScript;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/26/18
 */

public class Button extends VisibleElement {

    private Button(ElementData data) {

        super(data);
    }

    private static Button button(String locator) {

        ElementData data = new ElementData(locator);
        return new Button(data);
    }

    public static void click(final String locator) {

        try {
            try {
                button(locator).getWrappedWebElement().click();
            } catch (UnhandledAlertException ignored) {
                Report.error("*****UnhandledAlertException***** during click! Doing nothing just trying to continue the test.");
            } catch (UnreachableBrowserException ignored) {
                //doing this because for FireFox for some reason browser becomes unresponsive after click
                //but actually it is alive so it worth to try to continue test
                //it will fail on the next method after click if some real error happened
                Report.error("*****ERROR*****UnreachableBrowserException***** during click! Doing nothing just trying to continue the test.");
            } catch (ElementNotVisibleException needToScroll) {
                button(locator).clickForNeedToScroll();
            } catch (WebDriverException ignoredOrNeedToScroll) {
                button(locator).clickForIgnoredScroll(ignoredOrNeedToScroll);
            }
        } catch (Exception e) {
            Report.error("*****UNKNOWN ERROR*****Exception***** DURING CLICK LOGIC. SHOULD BE REFACTORED!!!!", e);
        }
    }

    public static void clickWithReload(final String locator) {
        int iterationCount = 0;
        while (iterationCount < 3) {
            button(locator).click();
            try {
                button(locator).waitFor().stale();
                return;
            } catch (TimeoutException ignored) {
            }
            //To avoid WebDriverException: unknown error: Runtime.evaluate threw exception: Error: lookUpElement is not attached to the page document.
            //This happened in chrome after staleness lookUpElement.
            try {
                button(locator).isEnabled();
            } catch (WebDriverException ignored) {
                return;
            }
            TestUtils.sleep(3000, "Sleeping before trying to click again");
            iterationCount++;
        }
        clickWithJavaScript(locator);
        try {
            button(locator).waitFor().stale();
            return;
        } catch (TimeoutException ignored) {
        }
        throw new WebDriverException("Button is not reloaded after click");
    }

    public static void clickWithJavaScript(final String locator) {

        executeScript("arguments[0].click();", button(locator).getWrappedWebElement());
    }

    public static boolean clickable(final String locator) {

        Driver driver = new Driver();
        VisibleElementLookup elementLookUp = new VisibleElementLookup(driver);
        if(!elementLookUp.isElementClickable(locator)) {
            Report.info("NOT");
            return false;
        } else {
            Report.info(locator + " is clickable");
            return true;
        }
    }

    public static void displayed(String locator) {

        int count = 0;
        while (count < 3) {
            try {
                button(locator).should().beDisplayed();
            } catch (StaleElementReferenceException e) {
                count++;
                e.printStackTrace();
            }
        }
    }

    public static void hasText(String locator, String text) {

        int count = 0;
        while (count < 3) {
            try {
                button(locator).should().haveText(text);
            } catch (StaleElementReferenceException e) {
                count++;
                e.printStackTrace();
            }
        }
    }

    public static void absent(String locator) {

        int count = 0;
        while (count < 3) {
            if (button(locator).isDisplayed()) {
                button(locator).waitFor().absent();
            }
            count++;
        }
    }


    public static void containsText(String locator, String partialText) {

        int count = 0;
        while (count < 3) {
            try {
                button(locator).should().containsText(partialText);
            } catch (StaleElementReferenceException e) {
                count++;
                e.printStackTrace();
            }
        }
    }
}