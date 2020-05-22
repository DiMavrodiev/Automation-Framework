package com.automationframework.internal;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.api.driver.Driver;
import com.automationframework.core.reporter.Report;
import com.automationframework.core.element.VisibleElement;
import com.automationframework.core.element.find.VisibleElementLookup;
import com.automationframework.api.exceptions.ElementSelectedException;
import org.openqa.selenium.StaleElementReferenceException;


/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/25/18
 */

public class RadioButton extends VisibleElement {

    private RadioButton(final ElementData data) {

        super(data);
    }

    private static RadioButton radioButton(String locator) {

        ElementData data = new ElementData(locator);
        return new RadioButton(data);

    }

    public static void clickWithJavaScript(String locator) {

        Button.clickWithJavaScript(locator);
    }


    public static void click(String locator) {

        Button.click(locator);
    }

    public static void clickWithReload(String locator) {

        Button.clickWithReload(locator);
    }

    public static boolean isClickable(String locator) {

       return Button.clickable(locator);
    }

    public static void select(String locator) {

        Driver driver = new Driver();
        VisibleElementLookup elementLookup = new VisibleElementLookup(driver);
        try {
            if (elementLookup.elementSelect(locator) != null) {
                if (elementLookup.find(locator).getTagName().equals("input") && elementLookup.find(locator).getAttribute("type").contains("radio")) {
                    elementLookup.elementSelect(locator).click();
                } else {
                    Report.error("****** ERROR ****** " + locator + " doesn't have 'input' tag or 'radio' attribute name");
                }
            } else {
                Report.error("****** ERROR ****** " + locator + " can't be selected");
            }
        } catch (Exception e) {
            throw new ElementSelectedException(locator + " has already been selected");
        }
    }

    public static void selectWith(String locator, String tagName, String attributeName) {

        Driver driver = new Driver();
        VisibleElementLookup elementLookup = new VisibleElementLookup(driver);
        try {
            if (elementLookup.elementSelect(locator) != null) {
                if (elementLookup.find(locator).getTagName().equals(tagName) && elementLookup.find(locator).getAttribute("type").contains(attributeName)) {
                    elementLookup.elementSelect(locator).click();
                } else {
                    Report.error("****** ERROR ****** " + locator + " doesn't have " + tagName.toUpperCase() + " tag or " + attributeName.toUpperCase() + " attribute name");
                }
            } else {
                Report.error("****** ERROR ****** " + locator + " can't be located");
            }
        } catch (Exception e) {
            throw new ElementSelectedException(locator + " has already been selected");
        }
    }

    public static void displayed(String locator) {

        int counter = 0;
        while (counter < 3) {
            try {
                radioButton(locator).should().beDisplayed();
            } catch (StaleElementReferenceException e) {
                counter++;
                e.printStackTrace();
            }
        }
    }

    public static void hasText(String locator, String text) {

        int counter = 0;
        while (counter < 3) {
            try {
                radioButton(locator).should().haveText(text);
            } catch (StaleElementReferenceException e) {
                counter++;
                e.printStackTrace();
            }
        }
    }

    public static void absent(String locator) {

        int counter = 0;
        while (counter < 3) {
            try {
                radioButton(locator).should().beAbsent();
            } catch (StaleElementReferenceException e) {
                counter++;
                e.printStackTrace();
            }
        }
    }
}