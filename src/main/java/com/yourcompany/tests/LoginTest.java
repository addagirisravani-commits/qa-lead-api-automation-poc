package com.yourcompany.tests;

import com.yourcompany.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke"})
    public void loginShouldSucceed() {
        // example: page objects recommended; this is a simple assertion demo
        String title = getDriver().getTitle();
        System.out.println("Title: " + title);
        // This assertion might pass depending on the page — for demo keep it soft:
        Assert.assertNotNull(title);
    }
}
