package com.automationframework.internal;

import com.automationframework.api.driver.Driver;
import com.automationframework.api.elementdata.ElementData;
import com.automationframework.core.element.VisibleElement;
import com.automationframework.core.element.find.VisibleElementLookup;
import com.automationframework.core.reporter.Report;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 2/12/19
 */
public class Element extends VisibleElement {

    private Element(ElementData data) {

        super(data);
    }

    private static Element element(String locator) {

        ElementData data = new ElementData(locator);
        return new Element(data);
    }

    public static void displayed(String locator) {

        Driver driver = new Driver();
        VisibleElementLookup elementLookup = new VisibleElementLookup(driver);
        if (!elementLookup.isElementVisible(locator)) {
            Report.error("****** ERROR ****** " + locator + " is NOT visible");
        }
    }
}