package com.automationframework.core.conditions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/25/18
 */

public class PageLoaded implements ExpectedCondition<Boolean> {

    @Nullable
    @Override
    public Boolean apply(@Nullable WebDriver driver) {
        assert driver != null;
        return "complete".equals(((JavascriptExecutor) driver).executeScript("return document.readyState"));
    }

    @Override
    public String toString() {

        return "Page is not loaded";
    }
}