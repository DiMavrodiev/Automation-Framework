package com.automationframework.internal;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.core.reporter.Report;
import com.automationframework.core.element.VisibleElement;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 11/16/18
 */

public class ClickableText extends VisibleElement {

    private ClickableText(final ElementData data) {

        super(data);
    }

    private static ClickableText clickableText(String locator) {

        ElementData data = new ElementData(locator);
        return new ClickableText(data);
    }

    public static void click(String locator) {

        try {
            if (isTextClickable(locator)) {
                clickableText(locator).click();
            } else {
                Report.error("****** ERROR ****** " + locator + " NOT CLICKABLE **** Expected tagName 'a' but found " + clickableText(locator).getTagName().toUpperCase() + " : Actual 'href' value " +
                clickableText(locator).getAttribute("href").toUpperCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickUrlNavigation(String locator) {

        String href = clickableText(locator).getAttribute("href");
        try {
            if (isTextClickable(locator)) {
                clickableText(locator).click();
                Page.urlIs(href);
            } else {
                Report.error("****** ERROR ****** " + locator + " NOT CLICKABLE **** Expected tagName 'a' but found " + clickableText(locator).getTagName().toUpperCase() + " : Actual 'href' value " +
                        href.toUpperCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static boolean isTextClickable(String locator) {

        return clickableText(locator).getTagName().equals("a")
                && getLink(locator) != null;
    }

    public static String getLink(String locator) {

        return clickableText(locator).getAttribute("href");
    }

    public static void displayed(String locator) {

        int counter = 0;
        while (counter < 3) {
            try {
                clickableText(locator).should().beDisplayed();

            } catch (StaleElementReferenceException e) {
                counter++;
                e.printStackTrace();
            }
        }
    }

    public static void text(String locator, String text) {

        int counter = 0;
        while (counter < 3) {
            try {
                clickableText(locator).should().haveText(text);
            } catch (StaleElementReferenceException e) {
                counter++;
                e.printStackTrace();
            }
        }
    }

    public static void containsText(String locator, String partialText) {

        int counter = 0;
        while (counter < 3) {
            try {
                clickableText(locator).should().containsText(partialText);
            } catch (StaleElementReferenceException e) {
                counter++;
                e.printStackTrace();
            }
        }
    }
}