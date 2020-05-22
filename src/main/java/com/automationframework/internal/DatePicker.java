package com.automationframework.internal;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.api.driver.Driver;
import com.automationframework.core.element.VisibleElement;
import com.automationframework.core.element.find.VisibleElementLookup;
import com.automationframework.core.element.find.VisibleElementsLookup;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 11/16/18
 */

public class DatePicker extends VisibleElement {

    private static int dateCounter;

    private DatePicker(ElementData data) {

        super(data);
    }

    private static DatePicker datePicker(String locator) {

        ElementData data = new ElementData(locator);
        return new DatePicker(data);
    }

    public static void calendarClickDatesPick(String calendar, String table, String pickDates) {

        Button.click(calendar);
        Driver driver = new Driver();
        VisibleElementsLookup elementsLookup = new VisibleElementsLookup(driver);

        try {
            List<WebElement> dates = elementsLookup.find(table);
            while (dateCounter < 2) {
                for (WebElement date : dates) {
                    try {
                        if (date.isDisplayed() && date.getText().equals(pickDates)) {
                            date.click();
                            dateCounter++;
                        }
                    } catch (StaleElementReferenceException ignored) {

                    }
                }
            }
        } catch (NullPointerException e) {
            throw new RuntimeException("******* ERROR ******* unable to locate elements from " + table);
        }
    }

    public static void calendarClickDatePickWithIndex(String calendar, String table, int startDate, int endDate) {

        Button.click(calendar);
        Driver driver = new Driver();
        VisibleElementsLookup elementsLookup = new VisibleElementsLookup(driver);

        try {
            List<WebElement> dates = elementsLookup.find(table);

            try {
                dates.get(startDate - 1).click();
                dates.get(endDate - 1).click();
            } catch (StaleElementReferenceException ignored) {

            }
        } catch (NullPointerException e) {
            throw new RuntimeException("******* ERROR ******* unable to locate elements from " + table);
        }
    }

    public static void datePick(String pickDate) {

        Driver driver = new Driver();
        VisibleElementLookup elementLookup = new VisibleElementLookup(driver);
        WebElement date = elementLookup.find(pickDate);
        try {
            if (date.isDisplayed()) {
                date.click();
            }
        } catch (StaleElementReferenceException ignored) {

        }
    }
}