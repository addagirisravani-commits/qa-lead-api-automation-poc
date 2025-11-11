package com.qa.tests;

import com.qa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest extends BaseTest {
    @Test
    public void openExampleAndCheckTitle() {
        driver.get("https://example.com/");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("example"));
    }
}
