package com.automationframework.internal;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.api.driver.Driver;
import com.automationframework.core.reporter.Report;
import com.automationframework.core.element.VisibleElement;
import com.automationframework.core.url.UrlAndTitleLookup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.automationframework.core.actions.JsActions.executeScript;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 12/21/18
 */

public class WindowTabs extends VisibleElement {

    private WindowTabs(ElementData data) {

        super(data);
    }

    public static void switchToLast() {

        Driver driver = new Driver();
        Iterator<String> iterator = driver.getDriver().getWindowHandles().iterator();
        String window = null;
        while (iterator.hasNext()) {
            window = iterator.next();
        }
        driver.getDriver().switchTo().window(window);
    }

    public static void switchToTab(int tab) {

        Driver driver = new Driver();
        List<String> tabs = new ArrayList<>(driver.getDriver().getWindowHandles());
        driver.getDriver().switchTo().window(tabs.get(tab - 1));
    }


    private WindowTabs windows(String finder) {

        ElementData data = new ElementData(finder);
        return new WindowTabs(data);
    }

    public static void openNewTab() {

        executeScript("window.open();");
    }

    public static void switchTabByUrl(String URL) {

        Driver driver = new Driver();
        UrlAndTitleLookup lookup = new UrlAndTitleLookup(driver);
        if (!lookup.switchTabByUrl(URL)) {
            Report.error("****** ERROR ****** " + URL + " is NOT found in any tab.");
        }
    }

    public static void switchByPartialUrl(String partialUrl) {

        Driver driver = new Driver();
        UrlAndTitleLookup lookup = new UrlAndTitleLookup(driver);
        if (!lookup.switchByPartialUrl(partialUrl)) {
            Report.error("****** ERROR ****** " + partialUrl + " is NOT found in any tab's url.");
        }
    }

    public static void switchTabByTitle(String title) {

        Driver driver = new Driver();
        UrlAndTitleLookup lookup = new UrlAndTitleLookup(driver);
        if (!lookup.switchTabByTitle(title)) {
            Report.error("****** ERROR ****** " + title + " is NOT found in any tab.");
        }
    }

    public static void closeTabByUrl(String URL) {

        Driver driver = new Driver();
        for (String tab : driver.getDriver().getWindowHandles()) {
            driver.getDriver().switchTo().window(tab);
            if (driver.getCurrentUrl().equalsIgnoreCase(URL)) {
                driver.getDriver().close();
            } else {
                Report.error("****** ERROR ****** " + URL + " is NOT found any tab's URL.");
            }
        }
    }

    public static void closeTabByPartialUrl(String partialUrl) {

        Driver driver = new Driver();
        for (String tab : driver.getDriver().getWindowHandles()) {
            driver.getDriver().switchTo().window(tab);
            if (driver.getCurrentUrl().contains(partialUrl)) {
                driver.close();
            } else {
                Report.error("****** ERROR ****** " + partialUrl + " is NOT found any tab's URL.");
            }
        }
    }

    public static void closeTabByTitle(String title) {

        Driver driver = new Driver();
        for (String tab : driver.getDriver().getWindowHandles()) {
            driver.getDriver().switchTo().window(tab);
            if (driver.getTitle().equalsIgnoreCase(title)) {
                driver.close();
            } else {
                Report.error("****** ERROR ****** " + title + " in NOT found in any tab's title.");
            }
        }
    }
}