package com.yourcompany.tests;

import com.yourcompany.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginFailureTest extends BaseTest {

    @Test
    public void intentionalFailure() {
        // Intentionally assert a wrong title to produce a failure and capture screenshot
        String actualTitle = getDriver().getTitle();
        System.out.println("Actual title (for failure demo): " + actualTitle);

        // Intentionally wrong expected title
        String expectedTitle = "This Title Will Not Match";
        Assert.assertEquals(actualTitle, expectedTitle, "Intentional mismatch to demo screenshot capture");
    }
}
