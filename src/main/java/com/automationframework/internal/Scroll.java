package com.automationframework.internal;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.core.element.VisibleElement;


import static com.automationframework.core.actions.JsActions.executeScript;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 5/7/18
 */

public class Scroll extends VisibleElement {


    private Scroll(ElementData element) {

        super(element);
    }

    private static Scroll scroll(String locator) {

        ElementData data = new ElementData(locator);
        return new Scroll(data);
    }


    public static void scrollToTop() {

        executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    public static void scrollToBottom() {

        executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void scrollToElement(String locator) {

        executeScript("arguments[0].scrollIntoView();", scroll(locator).getWrappedWebElement());
    }

    public static void scrollToCoordinates(String startPoint, String endPoint) {

        executeScript("window.scrollTo(" + startPoint + "," + endPoint + ")");

    }
}