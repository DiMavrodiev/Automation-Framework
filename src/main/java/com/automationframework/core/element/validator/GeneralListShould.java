package com.automationframework.core.element.validator;

import com.automationframework.api.wait.ElementFluentWait;
import com.automationframework.core.element.ElementList;
import com.automationframework.core.element.elements.*;
import com.automationframework.api.interfaces.ListShould;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.function.Function;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 10/8/18
 *
 * Temp solution as a POC
 * <p>
 * most likely there will be VisibleListShould, DomListShould and EmptyListShould
 */

public class GeneralListShould implements ListShould {

    private final ElementFluentWait<WebDriver> fluentWait;
    private final ElementList elements;

    public GeneralListShould(ElementList elements, ElementFluentWait<WebDriver> fluentWait) {
        this.elements = elements;
        this.fluentWait = fluentWait;
    }

    @Override
    public void beDisplayed() {

        waitFor(new ElementsDisplayed(elements));
    }

    @Override
    public void beAbsent() {

        waitFor(new ElementsAbsent(elements));
    }

    @Override
    public void haveSize(int expectedSize) {

        waitFor(new ElementsHaveSize(elements, expectedSize));
    }

    @Override
    public void haveText(String text) {

        waitFor(new ElementsHaveText(elements, text));
    }

    @Override
    public void haveAnyText() {

        waitFor(new ElementsHaveAnyText(elements));
    }

    @Override
    public void haveTexts(List<String> texts) {

        waitFor(new ElementsHaveTexts(elements, texts));
    }

    @Override
    public void haveNoText() {

        waitFor(new ElementsHaveNoText(elements));
    }

    @Override
    public void containsText(String text) {

        waitFor(new ElementsContainText(elements, text));
    }

    @Override
    public void haveAttribute(String attributeName, String value) {

        waitFor(new ElementsAttributeValue(elements, attributeName, value));
    }

    @Override
    public void haveAttribute(String attributeName) {

        waitFor(new ElementsHaveAttribute(elements, attributeName));
    }

    @Override
    public void notHaveAttribute(String attributeName) {

        waitFor(new ElementsNotHaveAttribute1(elements, attributeName));
    }

    @Override
    public void customCondition(Function<WebDriver, Boolean> condition) {

        waitFor(condition);
    }

    private void waitFor(Function<WebDriver, Boolean> condition) {

        fluentWait.waitFor(condition);
    }
}