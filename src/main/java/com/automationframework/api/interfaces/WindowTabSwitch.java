package com.automationframework.api.interfaces;

import org.openqa.selenium.WebDriver;

import java.util.function.Function;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 12/21/18
 */

public interface WindowTabSwitch {

    Function<WebDriver, String> findAndSwitch();
}
