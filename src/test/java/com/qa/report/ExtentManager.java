package com.qa.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            try {
                String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                Path reportsDir = Path.of("target", "reports");
                Files.createDirectories(reportsDir);
                String reportPath = reportsDir.resolve("ExtentReport_" + stamp + ".html").toString();

                ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
                spark.config().setReportName("UI Automation Report");
                spark.config().setDocumentTitle("Automation Results");

                extent = new ExtentReports();
                extent.attachReporter(spark);
                extent.setSystemInfo("Project", "QA Lead POC");
                extent.setSystemInfo("Env", System.getProperty("environment", "local"));
                extent.setSystemInfo("Browser", System.getProperty("browser", "chrome"));
            } catch (Exception ignored) { }
        }
        return extent;
    }
}
