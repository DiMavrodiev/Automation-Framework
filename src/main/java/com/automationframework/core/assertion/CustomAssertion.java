package com.automationframework.core.assertion;

import com.automationframework.api.driver.Driver;
import com.automationframework.api.elementdata.ElementData;
import com.automationframework.core.element.VisibleElement;
import com.automationframework.core.logger.MyLogger;
import org.openqa.selenium.*;
import org.testng.Assert;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 5/22/18
 */
public class CustomAssertion extends VisibleElement {

    private  CustomAssertion(ElementData data) {

        super(data);
    }

    private static CustomAssertion customAssertion(String locator) {

        ElementData data = new ElementData(locator);
        return new CustomAssertion(data);
    }

    private void assertAlertHTML(boolean condition, String message) {

        try {
            Assert.assertTrue(condition, message);
        } catch (Throwable e) {
            MyLogger.log.error(e.getMessage() + e.getCause());
        }
    }

    private static void assertHTML(boolean condition, String message) {

        try {
            Assert.assertTrue(condition, message);
        } catch (Throwable e) {
            MyLogger.log.error(e.getMessage() + e.getCause());
        }
    }

    private static void assertNotNull(Object object, String message) throws NullPointerException {

        try {
            Assert.assertNotNull(object, message);
        } catch (NullPointerException e) {
            MyLogger.log.error(object + " is null");
            e.printStackTrace();
        }
    }

    private static String getBodyText() {

        Driver driver = new Driver();
        WebElement body = driver.getDriver().findElement(By.tagName("body"));
        return body.getText();
    }

    private static boolean isTextPresent(String text) {
        MyLogger.log.info("Verifying if " + "'" + text + "'" + " is present");

        try {
            assertNotNull(text, "Text should not be null");
            return getBodyText().contains(text);
        } catch (NullPointerException e) {
            MyLogger.log.error(text + " is null");
            e.printStackTrace();
        }
        throw new RuntimeException(text);
    }


    public void acceptAlert() {
        MyLogger.log.info("Accepting alert");

        Driver driver = new Driver();
        Alert alert = driver.getDriver().switchTo().alert();
        alert.accept();
        driver.getDriver().switchTo().defaultContent();
    }

    public String getAlertText() {

        Driver driver = new Driver();
        Alert alert = driver.getDriver().switchTo().alert();
        return alert.getText();
    }

    public void assertAlertPresent() {
        MyLogger.log.info("Assert alert present");
        Driver driver = new Driver();

        try {
            driver.getDriver().switchTo().alert();
        } catch (Exception e) {
            assertAlertHTML(false, "Assert alert present");
        }
    }

    public void assertAlertText(String text) {
        MyLogger.log.info("Assert alert text");

        String alertText = getAlertText();
        assertAlertHTML(alertText.contains(text), "Assert alert text");
    }

    public void assertConfirmationText(String text) {
        MyLogger.log.info("Assert conformation text");

        String seenText = getAlertText();
        assertAlertHTML(seenText.contains(text), "Assert confirmation text.");
    }

    public void assertAttribute(String element, String attributeName, String value) {
        MyLogger.log.info("Validating if " + element + "'s" + " attribute " + attributeName + " has " + value);

        try {

            String attributeValue = customAssertion(element).getAttribute(attributeName);
            assertHTML((value != null) && value.equals(attributeValue), element.toString() + " attribute = " + attributeName +
                    ", expectedValue = {" + value + "}" + ", attributeValue = {" +
                    attributeValue + "}");
        } catch (NoSuchElementException e) {
            MyLogger.log.error("Unable to locate" + element);
            e.printStackTrace();
        }
    }

    public void assertAttributeContains(String element, String attributeName, String keyword) {
        MyLogger.log.info("Validating if " + element + "'s" + " attribute " + attributeName + " contains " + keyword);

        try {
            String attributeValue = customAssertion(element).getAttribute(attributeName);
            assertHTML((attributeValue != null && (keyword != null) && attributeValue.contains(keyword)), element.toString() + " attribute=" + attributeName +
                    ", expected to contains keyword {" + keyword + "}" +
                    ", attributeValue = {" + attributeValue + "}");
        } catch (NoSuchElementException e) {
            MyLogger.log.error("Unable to locate" + element);
            e.printStackTrace();
        }

    }

    public void assertAttributeMatches(String element, String attributeName, String regex) {
        MyLogger.log.info("Validating if " + element + "'s" + " attribute " + attributeName + " matches " + regex);

        try {
            String attributeValue = customAssertion(element).getAttribute(attributeName);
            assertHTML((attributeValue != null) && (regex != null) && attributeValue.matches(regex), element + " attribute=" + attributeName +
                    " expected to match regex {" + regex + "}" +
                    ", attributeValue = {" + attributeValue + "}");
        } catch (NoSuchElementException e) {
            MyLogger.log.error("Unable to locate" + element);
            e.printStackTrace();
        }
    }

    public void assertElementEnabled(String element) {
        MyLogger.log.info("assert " + element + " is enabled.");

        try {
            assertHTML(customAssertion(element).isEnabled(), element + " NOT found.");
        } catch (NoSuchElementException e) {
            MyLogger.log.error("Unable to locate " + element);
            e.printStackTrace();
        }
    }

    public void assertElementNotEnabled(String element) {
        MyLogger.log.info("assert " + element + " is NOT enabled.");

        try {
            assertHTML(!customAssertion(element).isEnabled(), element + " not found.");
        } catch (NoSuchElementException e) {
            MyLogger.log.error("Unable to locate " + element);
            e.printStackTrace();
        }
    }

    public void assertElementDisplayed(String element) {
        MyLogger.log.info("assert " + element + " is displayed.");

        try {
            assertHTML(customAssertion(element).isDisplayed(), element + " not found.");
        } catch (NoSuchElementException e) {
            MyLogger.log.error("Unable to locate " + element);
            e.printStackTrace();
        }
    }

    public void assertElementSelected(String element) {
        MyLogger.log.info("assert " + element + " is selected.");

        try {
            assertHTML(customAssertion(element).isSelected(), element + " not found.");
        } catch (NoSuchElementException e) {
            MyLogger.log.error("Unable to locate " + element);
            e.printStackTrace();
        }
    }

    public void assertElementNotSelected(String element) {
        MyLogger.log.info("assert " + element + " is NOT selected.");

        try {
            assertHTML(!customAssertion(element).isSelected(), element + " NOT found.");
        } catch (NoSuchElementException e) {
            MyLogger.log.error("Unable to locate " + element);
            e.printStackTrace();
        }
    }

    public void assertPromptText(String text) {
        MyLogger.log.info("Assert prompt text");

        String seenText = getAlertText();
        assertAlertHTML(seenText.contains(text), "Alert prompt text");
    }

    public static void assertTextNotPresent(String text) {

        try {
            assertHTML(!isTextPresent(text), "Text {" + text + "} found.");
        } catch (RuntimeException e) {
            MyLogger.log.error(text + " exceptions exception");
            e.printStackTrace();
        }
    }

    public void assertTextNotPresentIgnoreCase(String text) {
        MyLogger.log.info("assert text \"" + text + "\" is not present.(ignore case)");

        assertHTML(!getBodyText().toLowerCase().contains(text.toLowerCase()), "Text= {" + text + "} found.");
    }

    public static void assertTextPresent(String text) {

        try {
            assertHTML(isTextPresent(text), "Text {" + text + "} not found.");
        } catch (RuntimeException e) {
            MyLogger.log.error("Runtime exception");
            e.printStackTrace();
        }
    }

    public void assertTextPresentIgnoreCase(String text) {
        MyLogger.log.info("assert text \"" + text + "\" is present.(ignore case)");

        assertHTML(getBodyText().toLowerCase().contains(text.toLowerCase()), "Text {" + text + "} not found.");
    }

    public String cancelConformation() {

        Driver driver = new Driver();
        Alert alert = driver.getDriver().switchTo().alert();
        String seenText = getAlertText();
        alert.dismiss();
        driver.getDriver().switchTo().defaultContent();

        return seenText;
    }

    public Alert getAlert() {

        Driver driver = new Driver();
        return driver.getDriver().switchTo().alert();
    }

    public String getConfirmation() {

        Driver driver = new Driver();
        Alert alert = driver.getDriver().switchTo().alert();
        return alert.getText();
    }


}