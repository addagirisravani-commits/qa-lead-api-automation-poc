package com.yourcompany.listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    private String getTimestamp() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
    }

    private void captureScreenshot(ITestResult result) {
        Object testClass = result.getInstance();
        try {
            // Expect test classes extend a BaseTest providing getDriver()
            WebDriver driver = (WebDriver) testClass.getClass().getMethod("getDriver").invoke(testClass);
            if (driver == null) return;

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Read reportPath param if present
            String reportPath = "target/screenshots";
            Object repParam = result.getTestContext().getCurrentXmlTest().getParameter("reportPath");
            if (repParam != null && !repParam.toString().isEmpty()) {
                reportPath = repParam.toString();
            }

            File destDir = new File(reportPath);
            if (!destDir.exists()) destDir.mkdirs();

            String fileName = result.getTestContext().getName() + "_" + result.getMethod().getMethodName()
                    + "_" + getTimestamp() + ".png";
            File dest = new File(destDir, fileName);
            FileUtils.copyFile(src, dest);

            // Add path to test result so reporters/listeners can read it
            result.setAttribute("screenshot", dest.getAbsolutePath());
        } catch (Exception e) {
            // Keep listener failure silent but log
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getMethod().getMethodName());
        captureScreenshot(result);
    }

    // Optional overrides for other events
    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
