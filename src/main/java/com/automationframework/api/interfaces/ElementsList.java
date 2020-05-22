package com.automationframework.api.interfaces;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/15/18
 *
 * Abstraction for repeated location of an lookUpElement.
 * To match different cases of finding an lookUpElement it
 * has
 *
 * {@link #findElements()} - method that gets a list of WebElements
 * */

public interface ElementsList {

    List<WebElement> findElements();
}