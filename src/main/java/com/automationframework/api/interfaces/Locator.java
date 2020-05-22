package com.automationframework.api.interfaces;

import com.automationframework.api.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/24/18
 *
 * Abstraction for repeated location of an lookUpElement.
 * To match different cases of finding an lookUpElement it
 * has
 *
 * {@link #find(Driver)} - method that is responsible for an lookUpElement loop up logic
 * {@link #getBy()} - method that provided the locator
 */

public interface Locator {

    WebElement find(Driver driver);

    By getBy();
}
