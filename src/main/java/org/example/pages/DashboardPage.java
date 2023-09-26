package org.example.pages;

import org.example.base.BasePage;
import org.example.driver.driverManagerTL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage extends BasePage {

    public DashboardPage(){
        super();
    }


    // Locators
    By usernameOnDashboard = By.cssSelector("[data-qa=\"lufexuloga\"]");


    // Actions
    public String loggedInUsername () throws InterruptedException {
        //Thread.sleep(5000);
        presenceOfElement(usernameOnDashboard);
        return driverManagerTL.getDriver().findElement(usernameOnDashboard).getText();
    }

}

