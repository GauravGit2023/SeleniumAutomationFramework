package org.example.pages;

import org.example.base.BasePage;
import org.example.driver.driverManagerTL;
import org.example.utils.ProperReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(){
        super();
    }

    // page Locators - object repository
    private By username_element = By.cssSelector("input[id=\"login-username\"]");  //By.id("#login-username");
    private By password_element = By.cssSelector("input[id=\"login-password\"]");
    private By signIn_button_element = By.id("js-login-btn");

    // encapsulation
    private By signUp_link = By.partialLinkText("Start a free trial");

    // image and other link locators - not interested

    // error message
    private By errorMessage = By.id("js-notification-box-msg"); // js-notification-box-msg

    // Actions
    // navigate to url
    // send text in email and password
    // click button
    // New page - dashboard

    public LoginPage loginToVwo(boolean invalid) throws Exception {   // String email, String passwordText
        if(invalid){
            enterInput(username_element, ProperReader.readkey("username"));
        } else {
            enterInput(username_element, ProperReader.readkey("invalid_username"));
        }

        enterInput(password_element,ProperReader.readkey("password"));
        clickElement(signIn_button_element) ;
        return this;
    }

    public DashboardPage afterLogin(){
        return new DashboardPage();
    }

    public String errorMessageText() throws InterruptedException {
        visibiltyOfElement(errorMessage);
        //Thread.sleep(10000);
        return driverManagerTL.getDriver().findElement(errorMessage).getText();
    }
}

