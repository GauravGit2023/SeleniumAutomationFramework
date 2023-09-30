package org.example.web.testbase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.example.driver.driverManagerTL;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class TestBase {

    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("target/Report" +".html");

    ExtentTest test;


    // call to driver, take screenshot
    @BeforeSuite
    protected void setUp(){   // protected so that these methods can be used only in same package
        driverManagerTL.init();
    }

    public ExtentTest createTest(String name) {   // extent report features are paid now
        return extent.createTest(name).assignCategory("Regression Test").assignDevice("");  // "MacOsx"
    }

    @BeforeTest
    public void setConfig() {
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("VWO Testcases");
    }
    @AfterSuite
    protected void tearDown(){
        driverManagerTL.tearDown();
    }

    protected void takeScreenShot(String name, WebDriver driver){   // This is on demand ScreenShot, not on failure
        Allure.addAttachment(name,new ByteArrayInputStream(((TakesScreenshot)driverManagerTL.getDriver()).getScreenshotAs(OutputType.BYTES)));
    }

    public static String captureScreenshot(WebDriver driver) throws IOException {
        File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination_filepath = new File(System.getProperty("user.dir") + "images/screenshot" + System.currentTimeMillis() + ".png");
        FileUtils.copyFile(srcfile, destination_filepath);
        return destination_filepath.toString();
    }
}
