package org.example.utils;

import io.qameta.allure.Allure;
import org.example.driver.driverManagerTL;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class ScreenShotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result){
        Allure.addAttachment(result.getTestName(),new ByteArrayInputStream(((TakesScreenshot) driverManagerTL.getDriver()).getScreenshotAs(OutputType.BYTES)));

    }
}
