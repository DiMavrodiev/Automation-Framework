package com.automationframework.internal;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.api.driver.Driver;
import com.automationframework.core.httpstatus.HttpStatus;
import com.automationframework.core.reporter.Report;
import com.automationframework.core.element.VisibleElement;
import com.automationframework.api.exceptions.UrlException;
import com.automationframework.core.url.UrlAndTitleLookup;
import org.openqa.selenium.WebDriverException;

import static com.automationframework.core.actions.JsActions.executeScript;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/25/18
 */

public class Page extends VisibleElement {

    private static int refreshCounts;

    private Page(final ElementData data) {

        super(data);
    }

    private static Page page(String locator) {

        ElementData data = new ElementData(locator);
        return new Page(data);
    }

    public static void loaded(String url) {

        Driver driver = new Driver();
        UrlAndTitleLookup urlLookUp = new UrlAndTitleLookup(driver);
        HttpStatus httpStatus = new HttpStatus(url);
        if (!urlLookUp.isPageLoaded(url) || httpStatus.GET() != 200) {
            while (refreshCounts < 3) {
             Report.error(httpStatus.GET() + " - " + httpStatus.getStatusMessage() + " - " + url);
             driver.refresh();
             System.out.println(refreshCounts);
             refreshCounts++;
             loaded(url);
            } throw new WebDriverException(httpStatus.GET() + " - " + "Unable to load " + url);
        }
    }

    public static boolean loadedWithJavaScript() {

        return executeScript("return document.readyState").equals("complete");

    }

    public static void refresh() {

        executeScript("history.go(0);");
    }

    public static void imagesPresent() {

        HttpStatus httpStatus = new HttpStatus();
        httpStatus.validateImagesPresent();
    }

    public static void iamgePresent(String url) {

        HttpStatus httpStatus = new HttpStatus(url);
        if (httpStatus.GET() != 200) {
            while (refreshCounts < 2) {
                Report.error(httpStatus.GET() + " - " + httpStatus.getStatusMessage() + " - " + url);
                refreshCounts++;
                iamgePresent(url);
            }
        }
    }

    public static void brokenLinks(String tagName, String attribute) {

        HttpStatus httpStatus = new HttpStatus();
        httpStatus.checkForBrokenLinks(tagName, attribute);
    }

    public static void urlIs(String url) {

        Driver driver = new Driver();
        UrlAndTitleLookup urlLookUp = new UrlAndTitleLookup(driver);
        if (urlLookUp.getUrl(url) == null) {
           while (refreshCounts < 2) {
               driver.refresh();
               refreshCounts++;
               urlIs(url);
           } throw new UrlException("******* ERROR ******* " + url + " is not as expected");
        }
    }

    public static void urlContains(String partialUrl) {

        Driver driver = new Driver();
        UrlAndTitleLookup urlLookUp = new UrlAndTitleLookup(driver);
        if (urlLookUp.urlContains(partialUrl) == null) {
            while (refreshCounts < 2) {
                driver.refresh();
                refreshCounts++;
                urlContains(partialUrl);
            } throw new UrlException("******* ERROR ******* URL doesn't contains " + partialUrl);
        }
    }

    public static void titleIs(String title) {

        Driver driver = new Driver();
        UrlAndTitleLookup urlLookUp = new UrlAndTitleLookup(driver);
        if (urlLookUp.getTitle(title) == null) {
            while (refreshCounts < 2) {
                driver.refresh();
                refreshCounts++;
                titleIs(title);
            } throw new UrlException("******* ERROR ******* " + title + " is not as expected");
        }
    }

    public static void titleContains(String partialTitle) {

        Driver driver = new Driver();
        UrlAndTitleLookup urlLookUp = new UrlAndTitleLookup(driver);
        if (urlLookUp.titleContains(partialTitle) == null) {
            while (refreshCounts < 2) {
                driver.refresh();
                refreshCounts++;
                titleContains(partialTitle);
            } throw new UrlException("******* ERROR ******* title doesn't contains " + partialTitle);
        }
    }

    public static void waitForUrlToChange(String partialUrlOne, String partialUrlTwo) {

        Driver driver = new Driver();
        UrlAndTitleLookup urlLookUp = new UrlAndTitleLookup(driver);
        if (!urlLookUp.isUrlChanged(partialUrlOne, partialUrlTwo)) {
            while (refreshCounts < 3) {
                driver.refresh();
                System.out.println(refreshCounts);
                refreshCounts++;
                waitForUrlToChange(partialUrlOne, partialUrlTwo);
            } throw new UrlException("******* ERROR ******* URL contains " + partialUrlOne + " :EXPECTED " + partialUrlTwo);
        }
    }

    public static void waitTitleToChange(String partialTitleOne, String partialTitleTwo) {

        Driver driver = new Driver();
        UrlAndTitleLookup urlLookUp = new UrlAndTitleLookup(driver);
        if (!urlLookUp.isTitleChanged(partialTitleOne, partialTitleTwo)) {
            while (refreshCounts < 3) {
                driver.refresh();
                refreshCounts++;
                waitTitleToChange(partialTitleOne, partialTitleTwo);
            } throw new UrlException("******* ERROR ******* URL contains " + partialTitleOne + " :EXPECTED " + partialTitleTwo);
        }
    }
}