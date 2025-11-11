package com.qa.utils;

import com.qa.driver.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    public static String capture(String testName) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) return null;

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String time = LocalDateTime.now().format(TS);
            Path outDir = Path.of("target", "screenshots");
            Files.createDirectories(outDir);
            Path dest = outDir.resolve(testName + "_" + time + ".png");
            Files.copy(src.toPath(), dest);
            return dest.toString().replace("\\", "/");
        } catch (Exception e) {
            return null;
        }
    }
}
