package com.qa.base;

import com.qa.driver.DriverFactory;
import com.qa.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browser   = ConfigReader.get("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));

        DriverFactory.initDriver(browser, headless);
        driver = DriverFactory.getDriver();

        long implicit = Long.parseLong(ConfigReader.get("implicitWait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicit));

        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
