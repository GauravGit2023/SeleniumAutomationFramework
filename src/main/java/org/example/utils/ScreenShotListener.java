package org.example.utils;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.example.driver.driverManagerTL;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScreenShotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result){
//        Allure.addAttachment(result.getTestName(),new ByteArrayInputStream(((TakesScreenshot) driverManagerTL.getDriver()).getScreenshotAs(OutputType.BYTES)));

        Date currentDate = new Date();
        String screenShotFileName = currentDate.toString().replace(" ","-").replace(":","-");
        File screenShotFile = ((TakesScreenshot)driverManagerTL.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShotFile, new File(".//screenshot//"+ screenShotFileName +".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
