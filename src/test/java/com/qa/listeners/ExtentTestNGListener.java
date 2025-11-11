package com.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.qa.report.ExtentManager;
import com.qa.utils.ScreenshotUtil;
import org.testng.*;

public class ExtentTestNGListener implements ITestListener, ISuiteListener {

    private static final ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();
    private ExtentReports extent;

    @Override
    public void onStart(ISuite suite) {
        extent = ExtentManager.getInstance();
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String name = result.getMethod().getMethodName();
        String cls  = result.getTestClass().getName();
        tlTest.set(extent.createTest(cls + " :: " + name));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest t = tlTest.get();
        if (t != null) t.pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest t = tlTest.get();
        if (t != null) {
            String path = ScreenshotUtil.capture(result.getMethod().getMethodName());
            if (path != null) {
                t.fail(result.getThrowable(),
                        MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            } else {
                t.fail(result.getThrowable());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest t = tlTest.get();
        if (t != null) t.skip(result.getThrowable() != null ? result.getThrowable().getMessage() : "Skipped");
    }
}
