package com.automationframework.internal;

import com.automationframework.api.elementdata.ElementData;
import com.automationframework.core.element.VisibleElement;
import org.openqa.selenium.*;

import static com.automationframework.core.actions.JsActions.executeScript;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/25/18
 */

public class TextField extends VisibleElement {

    private TextField(final ElementData data) {

        super(data);
    }

    private static TextField textField(String locator) {

        ElementData data = new ElementData(locator);
        return new TextField(data);
    }

    public static String getText(String locator) {

        try {
            String text;
            if (isInputTextField(locator) || isTextAreaTextField(locator)) {
                text = textField(locator).getWrappedWebElement().getText().isEmpty()
                        ? textField(locator).getWrappedWebElement().getAttribute("value")
                        : textField(locator).getWrappedWebElement().getText();
            } else {
                text = textField(locator).getWrappedWebElement().getText();
            }
            return text;
        } catch (StaleElementReferenceException e) {
            int repeat = 0;
            while (repeat < 3) {
                getText(locator);
                try {
                    textField(locator).waitFor().stale();
                } catch (TimeoutException ignored) {

                }
                try {
                    textField(locator).isDisplayed();
                } catch (WebDriverException ignored) {
                    return getText(locator);
                }
                repeat ++;
            }
            return getText(locator);
        }
    }

    private static boolean isInputTextField(String locator) {

        return textField(locator).getTagName().equals("input")
                && textField(locator).getAttribute("type") != null
                || textField(locator).getAttribute("placeholder") != null;
    }

    private static boolean isTextAreaTextField(String locator) {

        return textField(locator).getTagName().equals("textarea")
                && textField(locator).getAttribute("placeholder") != null
                || textField(locator).getAttribute("type") !=null;
    }

    public static void type(final String locator, final String value) {

        try {
            if (isInputTextField(locator) || isTextAreaTextField(locator)) {
                clear(locator);
                textField(locator).getWrappedWebElement().sendKeys(value);
            }
        } catch (ElementNotInteractableException e) {
            Button.click(locator);
            type(locator, value);
        }
    }

    public static void typeWithJavaScript(final String locator, final String value) {

        try {
            if (isInputTextField(locator) || isTextAreaTextField(locator)) {
                clear(locator);
                executeScript("arguments[0].value='" + value + "';", textField(locator).getWrappedWebElement());
            }
        } catch (ElementNotInteractableException e) {
            Button.clickWithJavaScript(locator);
            typeWithJavaScript(locator, value);
        }
    }

    public static void clear(final String locator) {

        try {
            if (isTextAreaTextField(locator) || isInputTextField(locator)) {
                    textField(locator).clear();
            }
        } catch (ElementNotInteractableException e) {
            Button.click(locator);
            clear(locator);
        }
    }

    public static void clearWithBackspaceAndType(final String locator, final String toType) {

        int numberOfCharactersToDelete = textField(locator).getWrappedWebElement().getAttribute("value").toCharArray().length;
        while (numberOfCharactersToDelete > 0) {
            textField(locator).getWrappedWebElement().sendKeys(Keys.BACK_SPACE);
            numberOfCharactersToDelete--;
        }
        textField(locator).getWrappedWebElement().sendKeys(toType);
    }

    public static void clearWithBackspaceAndTypeWithTab(final String locator, final String toType) {

        clearWithBackspaceAndType(locator,toType);
        // Tab key is sent to make sure the key press/change/blur event gets triggered...
        textField(locator).getWrappedWebElement().sendKeys(Keys.TAB);
    }

    public static void clearWithBackspaceNumberOfCharsAndType(final String locator, final String toType, int charsToDelete) {

        int numberOfCharactersToDelete = textField(locator).getWrappedWebElement().getAttribute("value").toCharArray().length;
        int numberOfCharactersLeft = numberOfCharactersToDelete - charsToDelete;
        while (numberOfCharactersToDelete > numberOfCharactersLeft) {
            textField(locator).getWrappedWebElement().sendKeys(Keys.BACK_SPACE);
            numberOfCharactersToDelete--;
        }
        textField(locator).getWrappedWebElement().sendKeys(toType);
    }

    /**
     * Use the method for checking an alert after incorrect data input in the text field
     * or if it is a requirement to click on the field before typing
     *
     * @param locator
     * @param value
     */

    public void clickAndType(final String locator, final String value) {

        Button.click(locator);
        try {
            if (isInputTextField(locator) || isTextAreaTextField(locator)) {
                clear(locator);
                textField(locator).getWrappedWebElement().sendKeys(value);
            }
        } catch (UnhandledAlertException ignored) {
            //In case we try to input some non text/number symbol like ")" or "+" selenium throws this exception
            //this fails the test (for example E4_1920); The solution is to ignore this exception
            //If this causes some problems in future this try catch should be refactored

            //ignored.printStackTrace();
        }
    }

}