package com.automationframework.api.interfaces;

import org.openqa.selenium.Keys;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 1/18/19
 */

public interface Interactions {

    void doubleClick(String locator);

    void click(String locator);

    void hoverOver(String locator);

    void rightClick(String locator);

    void dragAndDrop(String source, String target);

    void clickAndSelect(String locator, Keys... key);

    void clickHoldMoveRelease(String source, String target);

}
