package com.automationframework.internal;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.api.driver.Driver;
import com.automationframework.core.reporter.Report;
import com.automationframework.core.element.ElementList;
import com.automationframework.core.element.VisibleElement;
import com.automationframework.core.element.find.VisibleElementsLookup;
import com.automationframework.core.utils.TestUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/25/18
 */

public class Dropdown extends VisibleElement {

    //TODO find a way to fix a NullPointerException that occurs when the locator doesn't match the property file locator. This error loops forever.

    private Dropdown(final ElementData data) {

        super(data);
    }

    private static Dropdown dropdown(String locator) {

        ElementData data = new ElementData(locator);
        return new Dropdown(data);
    }

    public static void pickByName(String locator, String list, String... select) {

        try {
            dropdown(locator).click();
            Driver driver = new Driver();
            VisibleElementsLookup elementsLookup = new VisibleElementsLookup(driver);
            List<WebElement> options = elementsLookup.find(list);
            ((ElementList) options).should().beDisplayed();
            for (WebElement element : options) {
                for (String elementItem : select) {
                    if (element.getAttribute("value").equalsIgnoreCase(elementItem) || element.getText().equalsIgnoreCase(elementItem)) {
                        element.click();
                        break;
                    }
                    //TODO add a check if the selected element is displayed
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pickByIndex(String locator, String list, int... select) {

        try {
            dropdown(locator).click();
            Driver driver = new Driver();
            VisibleElementsLookup elementsLookup = new VisibleElementsLookup(driver);
            List<WebElement> options = elementsLookup.find(list);
            ((ElementList) options).should().beDisplayed();
            for (int elementItem : select) {
                try {
                    options.get(elementItem - 1).click();
                } catch (IndexOutOfBoundsException e) {
                    throw new RuntimeException("****** ERROR ****** option " + elementItem + " does NOT exist.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void searchAndClick(String locator, String list, String... search) {

        try {
            for (String elementItem : search) {
                dropdown(locator).sendKeys(elementItem);
                dropdown(list).should().containsText(elementItem);
                dropdown(list).click();
            }
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Unable to locate one of the elements, try locating and clicking on the field before sending the text");
        }
    }

    private static boolean isMultiple(final String locator) {

        return dropdown(locator).wrappedSelect().isMultiple()
                || dropdown(locator).getAttribute("class").contains("multiselect")
                || dropdown(locator).getAttribute("multiple") != null;
    }

    public static void selectByIndex(String locator, int select) {

        if (isSelectable(locator)) {
            if (!isSelectedByIndex(locator, select)) {
                dropdown(locator).wrappedSelect().selectByIndex(select - 1);
            } else {
                Report.error("****** ERROR ****** Option " + select + " IS ALREADY selected.");
            }
        } else {
            Report.error("****** ERROR ****** " + locator + " is NOT selectable");
        }
    }

    public static void selectMultipleByIndex(String locator, int... select) {

        if (isMultiple(locator) && isSelectable(locator)) {
            for (int selectItem : select) {
                if (!isSelectedByIndex(locator, selectItem)) {
                    dropdown(locator).wrappedSelect().selectByIndex(selectItem - 1);
                } else {
                    Report.error("****** ERROR ****** Option " + selectItem + " IS ALREADY selected.");
                }
            }
        } else {
            Report.error("****** ERROR ****** " + locator + " is NOT selectable or can NOT select multiple indexes");
        }
    }

    public static void selectMultipleByValue(String locator, String... select) {

        if (isMultiple(locator) && isSelectable(locator)) {
            for (String selectItem : select) {
                if (isNotSelectedByValue(locator, selectItem)) {
                    dropdown(locator).wrappedSelect().selectByValue(selectItem);
                } else if (isNotSelectedByPartialValue(locator, selectItem)) {
                    selectByPartialValue(locator, selectItem);
                } else {
                    Report.error("****** ERROR ****** " + selectItem + " IS ALREADY selected.");
                }
            }
        } else {
            Report.error("****** ERROR ****** " + locator + " is NOT selectable or can NOT select multiple values.");
        }

    }

    public static void selectByValue(String locator, String select) {

        if (isSelectable(locator)) {
            if (!isNotSelectedByValue(locator, select)) {
                dropdown(locator).wrappedSelect().selectByValue(select);
            } else if (isNotSelectedByPartialValue(locator, select)) {
                selectByPartialValue(locator, select);
            } else {
                Report.error("****** ERROR ******" + select + " IS ALREADY selected.");
            }
        } else {
            Report.error("****** ERROR ****** " + locator + " is NOT selectable.");
        }
    }

    public static void selectMultipleByText(String locator, String... select) {

        if (isMultiple(locator) && isSelectable(locator)) {
            for (String elementItem : select) {
                if (isNotSelectedByVisibleTextMatch(locator, elementItem)) {
                    dropdown(locator).wrappedSelect().selectByVisibleText(elementItem);
                } else if (isNotSelectedByPartialTextMatch(locator, elementItem)) {
                    selectByPartialText(locator, elementItem);
                } else {
                    Report.error("****** ERROR ****** " + elementItem + " IS ALREADY selected.");
                }
            }
        } else {
            Report.error("****** ERROR ****** " + locator + " is NOT selectable or can NOT select multiple elements by visible text.");
        }
    }

    public static void selectByText(String locator, String select) {

        if (isSelectable(locator)) {
            if (!isNotSelectedByVisibleTextMatch(locator, select)) {
                dropdown(locator).wrappedSelect().selectByVisibleText(select);
            } else if (!isNotSelectedByPartialTextMatch(locator, select)) {
                selectByPartialText(locator, select);
            } else {
                Report.error("****** ERROR ****** " + select + " IS ALREADY selected.");
            }
        } else {
            Report.error("****** ERROR ****** " + locator + " is NOT selectable.");
        }
    }

    public static void selectAll(String locator) {

        try {
            if (isMultiple(locator) && isSelectable(locator)) {
                List<Boolean> options = isOptionSelectedByIndex(locator);
                for (int i = 0; i < options.size(); i++) {
                    if (!options.get(i)) {
                        dropdown(locator).wrappedSelect().selectByIndex(i);
                    } else {
                        Report.info("Option " + (i + 1) + " IS ALREADY selected.");
                    }
                }
            } else {
                Report.error("****** ERROR ****** " + locator + " is NO selectable.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("****** ERROR ****** Trying to select an option that doesn't exist.");
        }
    }

    public static void deselectByIndex(String locator, int deselect) {

        if (isSelectable(locator)) {
            if (isSelectedByIndex(locator, deselect)) {
                dropdown(locator).wrappedSelect().deselectByIndex(deselect - 1);
            } else {
                Report.error("****** ERROR ****** option " + deselect + " is NOT selected.");
            }
        } else {
            Report.error("****** ERROR ****** " + locator + " is NOT selectable.");
        }
    }

    public static void deselectMultipleByIndex(String locator, int... deselect) {

        if (isMultiple(locator) && isSelectable(locator)) {
            for (int elementItem : deselect) {
                if (isSelectedByIndex(locator, elementItem)) {
                    dropdown(locator).wrappedSelect().deselectByIndex(elementItem - 1);
                } else {
                    Report.error("****** ERROR ****** option " + elementItem + " is NOT selected.");
                }
            }
        } else {
            Report.error("****** ERROR ****** " + locator + " is NOT de-selectable or can NOT deselect multiple indexes.");
        }
    }

    public static void deselectByValue(String locator, String deselect) {

        if (isSelectable(locator)) {
            if (isSelectedByValue(locator, deselect)) {
                dropdown(locator).wrappedSelect().deselectByValue(deselect);
            } else if (isSelectedByPartialValue(locator, deselect)) {
                deselectByPartialValue(locator, deselect);
            } else {
                Report.error("****** ERROR ****** " + deselect + " is NOT selected.");
            }
        } else {
            Report.error("****** ERROR ****** " + locator + " is NOT de-selectable.");
        }
    }

    public static void deselectMultipleByValue(String locator, String... deselect) {

        if (isMultiple(locator) && isSelectable(locator)) {
            for (String elementItem : deselect) {
                if (isSelectedByValue(locator, elementItem)) {
                    dropdown(locator).wrappedSelect().deselectByValue(elementItem);
                } else if (isSelectedByPartialValue(locator, elementItem)) {
                    deselectByPartialValue(locator, elementItem);
                } else {
                    Report.error("****** ERROR ****** " + elementItem + " is NOT selected.");
                }
            }
        } else {
            Report.error("****** ERROR ****** " + locator + " is NOT de-selectable or can NOT deselect multiple values.");
        }
    }

    public static void deselectByText(String locator, String deselect) {

        if (isSelectable(locator)) {
            if (isNotSelectedByVisibleTextMatch(locator, deselect)) {
                dropdown(locator).wrappedSelect().deselectByVisibleText(deselect);
            } else if (isSelectedByPartialTextMatch(locator, deselect)) {
                deselectByPartialText(locator, deselect);
            } else {
                Report.error("****** ERROR ****** " + deselect + " is NOT selected.");
            }
        } else {
            Report.error("****** ERROR ****** " + locator + " is NOT de-selectable.");
        }
    }

    public static void deselectMultipleByText(String locator, String... deselect) {

        if (isMultiple(locator) && isSelectable(locator)) {
            for (String elementItem : deselect) {
                if (isSelectedByVisibleTextMatch(locator, elementItem)) {
                    dropdown(locator).wrappedSelect().deselectByVisibleText(elementItem);
                } else if (isSelectedByPartialTextMatch(locator, elementItem)) {
                    deselectByPartialText(locator, elementItem);
                } else {
                    Report.error("****** ERROR ****** " + elementItem + " is NOT selected.");
                }
            }
        } else {
            Report.error("****** ERROR ****** " + locator + " is NOT de-selectable or can NOT deselect multiple text.");
        }
    }

    public static void deselectAll(String locator) {

        int counter = 0;
        if (isSelectable(locator)) {
            while (counter < 5) {
                try {
                    dropdown(locator).wrappedSelect().deselectAll();
                    break;
                } catch (StaleElementReferenceException e) {
                    counter++;
                    TestUtils.sleep(1000, "Find better solution for this.");
                }
            }
        } else {
            Report.error("****** ERROR ****** " + locator + " is NOT de-selectable.");
        }
    }

    private static boolean listContainsText(String locator, String text) {

        boolean result = false;
        try {
            List<WebElement> options = dropdown(locator).wrappedSelect().getOptions();
            for (WebElement option : options) {
                if (option.getText().equalsIgnoreCase(text) || option.getText().contains(text)) {
                    result = true;
                    break;
                }
            }
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("******* ERROR ******* " + text + " exist in DOM but it is not displayed.");
        }
        return result;
    }

    public static void optionsDisplayed(String locator, String ...text) {

        try {
            Set<String> result = new HashSet<>(Arrays.asList(text));
            List<WebElement> test = dropdown(locator).wrappedSelect().getOptions();
            Set<String> options = new HashSet<>();
            for (WebElement element : test) {
                options.add(element.getText());
            }

            if (result.removeAll(options) && !result.isEmpty() && !options.isEmpty()) {
                Report.error("****** ERROR ****** " + result.toString() + " is NOT available to select.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void optionDisplayed(String locator, String  option) {

        if (isSelectable(locator)) {
            if (!listContainsText(locator,option)) {
                Report.error("****** ERROR ****** " + option + " is NOT available to select.");
            }
            else {
                Report.info("Exists. " + option);
            }
        }
    }

    private static boolean isSelectable(String locator) {

        return dropdown(locator).getTagName().equalsIgnoreCase("select");
    }

    private Select wrappedSelect() {

        return new Select(getWrappedWebElement());
    }

    private static boolean isNotSelectedByVisibleTextMatch(String locator, String selected) {

        try {
            for (WebElement elementItem : dropdown(locator).wrappedSelect().getOptions()) {
                if (elementItem.getText().equalsIgnoreCase(selected) && !elementItem.isSelected()) {
                    return true;
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isSelectedByVisibleTextMatch(String locator, String select) {

        try {
            for (WebElement elementItem : dropdown(locator).wrappedSelect().getOptions()) {
                if (elementItem.getText().equalsIgnoreCase(select) && elementItem.isSelected()) {
                    return true;
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isNotSelectedByPartialTextMatch(String locator, String selected) {

        try {
            for (WebElement elementItem : dropdown(locator).wrappedSelect().getOptions()) {
                if (elementItem.getText().contains(selected) && !elementItem.isSelected()) {
                    return true;
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isSelectedByPartialTextMatch(String locator, String selected) {

        try {
            for (WebElement elementItem : dropdown(locator).wrappedSelect().getOptions()) {
                if (elementItem.getText().contains(selected) && elementItem.isSelected()) {
                    return true;
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isNotSelectedByValue(String locator, String selected) {

        try {
            for (WebElement elementItem : dropdown(locator).wrappedSelect().getOptions()) {
                if (elementItem.getAttribute("value").equalsIgnoreCase(selected) && !elementItem.isSelected()) {
                    return true;
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isSelectedByValue(String locator, String selected) {

        try {
            for (WebElement elementItem : dropdown(locator).wrappedSelect().getOptions()) {
                if (elementItem.getAttribute("value").equalsIgnoreCase(selected) && elementItem.isSelected()) {
                    return true;
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isNotSelectedByPartialValue(String locator, String selected) {

        try {
            for (WebElement elementItem : dropdown(locator).wrappedSelect().getOptions()) {
                if (elementItem.getAttribute("value").contains(selected) && !elementItem.isSelected()) {
                    return true;
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }


    private static boolean isSelectedByPartialValue(String locator, String selected) {

        try {
            for (WebElement elementItem : dropdown(locator).wrappedSelect().getOptions()) {
                if (elementItem.getAttribute("value").contains(selected) && elementItem.isSelected()) {
                    return true;
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isSelectedByIndex(String locator, int index) {

        try {
            List<WebElement> options = dropdown(locator).wrappedSelect().getOptions();
            if (options.get(index - 1).isSelected()) {
                return true;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("****** ERROR ***** Option " + index + " does NOT exist.");
        }
        return false;
    }

    private static List<Boolean> isOptionSelectedByIndex(String locator) {

        try {
            return dropdown(locator).wrappedSelect().getOptions()
                    .stream()
                    .map(WebElement::isSelected)
                    .collect(Collectors.toList());
        } catch (UndeclaredThrowableException ignored) {
            TestUtils.sleep(3000, "Find better solution if it happens too often.");
            return dropdown(locator).wrappedSelect().getOptions()
                    .stream()
                    .map(WebElement::isSelected)
                    .collect(Collectors.toList());
        }
    }

    public static void selectRandom(String locator) {

        try {
            if (isMultiple(locator) && isSelectable(locator)) {
                List<WebElement> options = dropdown(locator).wrappedSelect().getOptions();
                if (options.size() > 1) {
                    Random random = new Random();
                    dropdown(locator).wrappedSelect().selectByIndex(random.nextInt(options.size()));
                }
            }
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
    }

    private static void deselectByPartialText(String locator, String partialText) {
        for (WebElement option : dropdown(locator).wrappedSelect().getOptions()) {
            if (option.getText().contains(partialText)) {
                dropdown(locator).wrappedSelect().deselectByVisibleText(option.getText());
                return;
            }
        }
    }

    private static void selectByPartialText(String locator, String partialText) {
        for (WebElement option : dropdown(locator).wrappedSelect().getOptions()) {
            if (option.getText().contains(partialText)) {
                dropdown(locator).wrappedSelect().selectByVisibleText(option.getText());
                return;
            }
        }
    }

    private static void deselectByPartialValue(String locator, String partialValue) {
        for (WebElement option : dropdown(locator).wrappedSelect().getOptions()) {
            if (option.getAttribute("value").contains(partialValue)) {
                dropdown(locator).wrappedSelect().deselectByValue(option.getAttribute("value"));
                return;
            }
        }
    }

    private static void selectByPartialValue(String locator, String partialValue) {
        for (WebElement option : dropdown(locator).wrappedSelect().getOptions()) {
            if (option.getAttribute("value").contains(partialValue)) {
                dropdown(locator).wrappedSelect().selectByValue(option.getAttribute("value"));
                return;
            }
        }
    }
}