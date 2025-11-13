package com.yourcompany.base;

import com.yourcompany.core.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    protected String baseUrl;
    protected String reportPath;

    public WebDriver getDriver() {
        return tlDriver.get();
    }

    @Parameters({"browser", "baseUrl", "reportPath"})
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("chrome") String browser,
                      @Optional("https://example.com") String baseUrl,
                      @Optional("target/screenshots") String reportPath) {
        this.baseUrl = baseUrl;
        this.reportPath = reportPath;

        WebDriver driver = DriverFactory.createDriver(browser);
        tlDriver.set(driver);

        driver.manage().window().maximize();
        // Navigate optionally:
        driver.get(this.baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            tlDriver.remove();
        }
    }
}
