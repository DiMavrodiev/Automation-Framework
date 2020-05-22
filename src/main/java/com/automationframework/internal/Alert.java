package com.automationframework.internal;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.api.driver.Driver;
import com.automationframework.core.element.VisibleElement;
import com.automationframework.api.exceptions.AlertExeption;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 1/28/19
 */

public class Alert extends VisibleElement {

    private Alert(ElementData data) {

        super(data);
    }

    private static Alert alert(String locator) {

        ElementData data = new ElementData(locator);
        return new Alert(data);
    }

    public static void switchToAlert() {

        Driver driver = new Driver();
        try {
            WebDriverWait wait = new WebDriverWait(driver.getDriver(), driver.getPageLoadTimeout());
            wait.until(ExpectedConditions.alertIsPresent());
            driver.getDriver().switchTo().alert();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
            throw new AlertExeption("****** ERROR ****** Alert is no present.");
        }
    }
}