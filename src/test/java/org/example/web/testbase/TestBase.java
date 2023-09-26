package org.example.web.testbase;

import io.qameta.allure.Allure;
import org.example.driver.driverManagerTL;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.ByteArrayInputStream;

public class TestBase {

    // call to driver, take screenshot
    @BeforeSuite
    protected void setUp(){   // protected so that these methods can be used only in same package
        driverManagerTL.init();
    }
    @AfterSuite
    protected void tearDown(){
        driverManagerTL.tearDown();
    }

    protected void takeScreenShot(String name){   // This is on demand ScreenShot, not on failure
        Allure.addAttachment(name,new ByteArrayInputStream(((TakesScreenshot)driverManagerTL.getDriver()).getScreenshotAs(OutputType.BYTES)));
    }
}
