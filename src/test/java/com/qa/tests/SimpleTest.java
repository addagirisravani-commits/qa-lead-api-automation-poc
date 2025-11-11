package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest extends BaseTest {
    @Test
    public void titleCheck() {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.getTitle().toLowerCase().contains("example"));
    }
}
