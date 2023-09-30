package org.example.web.tests;

import com.aventstack.extentreports.ExtentTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.assertj.core.api.Assertions;
import org.example.driver.driverManagerTL;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;
import org.example.utils.ProperReader;
import org.example.utils.ScreenShotListener;
import org.example.web.testbase.TestBase;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@Listeners(ScreenShotListener.class)
public class LoginTest extends TestBase { // inheritance

    private static final Logger logger = (Logger) LogManager.getLogger(LoginTest.class);
    // we can either use a log4j.properties file to set log pattern or log4j.xml file

    ExtentTest test;
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that invalid login credentials are not working")
    @Test(groups = {"QA","P0", "negative"},priority = 0)
    public void test_VWOLogin_Negative(Method method) throws Exception {
        // navigate to, login, assert
        // Abstraction
        test = createTest(method.getName());
        driverManagerTL.getDriver().get(ProperReader.readkey("url"));  // we are writing here and not in base because url will change with website
        String error_text =  new LoginPage().loginToVwo(false).errorMessageText();

        System.out.println(method.getName());
        if(error_text.equalsIgnoreCase(ProperReader.readkey("expected_error"))){ // on test fail
            logger.info("Failed");
            test.fail("Failed Testcases");
            test.addScreenCaptureFromBase64String(captureScreenshot(driverManagerTL.getDriver()));
            takeScreenShot(method.getName(),driverManagerTL.getDriver());

        }

        //TESTNG - assertion
        //Assert.assertEquals(error_text, "Your email, password, IP address or location did not match");

        // ASSERTJ - assertion
        Assertions.assertThat(error_text).isNotBlank().isNotNull().contains(ProperReader.readkey("expected_error"));
//        Assert.assertTrue(false);

    }
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that valid login credentials are working")
    @Test(groups = {"P0", "positive"},priority = 1)
    public void test_VWOLogin_Positive() throws Exception {
        // navigate to, login, assert
        // Abstraction
        driverManagerTL.getDriver().get(ProperReader.readkey("url"));  // we are writing here and not in base because url will change with website
        DashboardPage dashboardPage = new LoginPage().loginToVwo(true).afterLogin();
        String expectResult = dashboardPage.loggedInUsername();
        Assertions.assertThat(expectResult).isNotBlank().isNotNull().contains(ProperReader.readkey("expected_username"));
    }

}
