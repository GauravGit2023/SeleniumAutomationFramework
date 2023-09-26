package org.example.web.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
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
@Listeners(ScreenShotListener.class)
public class LoginTest extends TestBase { // inheritance

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that invalid login credentials are not working")
    @Test(groups = {"P0", "negative"},priority = 0)
    public void test_VWOLogin_Negative() throws Exception {
        // navigate to, login, assert
        // Abstraction
        driverManagerTL.getDriver().get(ProperReader.readkey("url"));  // we are writing here and not in base because url will change with website
        String error_text =  new LoginPage().loginToVwo(false).errorMessageText();

        //TESTNG - assertion
        //Assert.assertEquals(error_text, "Your email, password, IP address or location did not match");

        // ASSERTJ - assertion
        Assertions.assertThat(error_text).isNotBlank().isNotNull().contains(ProperReader.readkey("expected_error"));

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
