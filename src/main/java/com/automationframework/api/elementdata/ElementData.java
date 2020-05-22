package com.automationframework.api.elementdata;

import com.automationframework.api.interfaces.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 9/21/18
 *
 * Class-holder for all data that is used for Element creation;
 */

public class ElementData {

    //lookUpElement that we found and will be wrapping with Element;
    private WebElement element;

    //when we search for an lookUpElement inside another lookUpElement;
    private Element context;

    //locator's type and value;
    private String locator;

    //locator for an lookUpElement that we are searching for;
    private By by;

    //index of a particular lookUpElement in a list;
    private Integer index;

    public ElementData(WebElement element, String locator, Element context, Integer index, By by) {
        this.context = context;
        this.element = element;
        this.locator = locator;
        this.index = index;
        this.by = by;
    }

    public ElementData(String locator) {

        this(null, locator, null, null, null);

    }

    public ElementData(Element context, WebElement element, String locator)
    {

        this(element, locator, context, null,null);
    }

    public ElementData(WebElement element, String locator, Element context, int index) {

        this(element, locator, context, index, null);
    }

    public ElementData(WebElement firstSelectedOption, By by) {
    }


    public WebElement getElement() {

        return element;
    }

    public Element getSearchContext() {

        return context;
    }

    public String getLocator() {

        return locator;
    }

    public By getBy() {

        return by;
    }

    public Integer getIndex() {

        return index;
    }
}