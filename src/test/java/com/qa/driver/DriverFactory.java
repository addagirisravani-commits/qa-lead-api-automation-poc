package com.qa.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static void initDriver(String browser, boolean headless) {
        switch (browser.toLowerCase()) {
            case "edge" -> {
                EdgeOptions opt = new EdgeOptions();
                if (headless) opt.addArguments("--headless=new");
                WebDriverManager.edgedriver().setup();
                tlDriver.set(new EdgeDriver(opt));
            }
            case "firefox" -> {
                FirefoxOptions opt = new FirefoxOptions();
                if (headless) opt.addArguments("-headless");
                WebDriverManager.firefoxdriver().setup();
                tlDriver.set(new FirefoxDriver(opt));
            }
            default -> { // chrome
                ChromeOptions opt = new ChromeOptions();
                if (headless) opt.addArguments("--headless=new");
                WebDriverManager.chromedriver().setup();
                tlDriver.set(new ChromeDriver(opt));
            }
        }
        getDriver().manage().window().maximize();
    }

    public static WebDriver getDriver() { return tlDriver.get(); }

    public static void quitDriver() {
        WebDriver d = tlDriver.get();
        if (d != null) { d.quit(); tlDriver.remove(); }
    }
}
