package com.qa.base;

import com.qa.driver.DriverFactory;
import com.qa.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// inside class
private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

// use
log.info("Launching browser...");


public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browser  = ConfigReader.get("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));
        DriverFactory.initDriver(browser, headless);
        driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Long.parseLong(ConfigReader.get("implicitWait"))
        ));
        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
