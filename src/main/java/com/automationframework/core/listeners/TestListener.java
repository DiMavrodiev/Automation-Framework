package com.automationframework.core.listeners;

import com.automationframework.core.reporter.Report;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 3/8/18
 */
public class TestListener implements ITestListener {

    public void onTestStart(ITestResult result) {

        Report.debug(result.getName());

    }

    public void onTestSuccess(ITestResult result) {

        Report.info(result.getName());
    }

    public void onTestFailure(ITestResult result) {

        Report.error(result.getName());
        Report.error(result.getTestName());
        Report.error(result.getMethod().getMethodName());
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}