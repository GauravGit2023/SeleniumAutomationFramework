package org.example.base;

import io.netty.util.Timeout;
import org.example.driver.driverManagerTL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {
    protected BasePage(){}

    protected void enterInput(By by, String key){
        driverManagerTL.getDriver().findElement(by).sendKeys(key);
    }

    protected void enterInput(WebElement e, String key){         // polymorphism - overloading
        e.sendKeys(key);
    }

    protected void clickElement(By by){
        driverManagerTL.getDriver().findElement(by).click();
    }

    protected WebElement getElement(By key){
        return driverManagerTL.getDriver().findElement(key);
    }

    protected void implicitWait(){
        driverManagerTL.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    protected WebElement presenceOfElement(final By elementLocation){
        return new WebDriverWait(driverManagerTL.getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(elementLocation));
    }

    // visibilityOfElementLocated
    protected WebElement visibiltyOfElement(final By elementLocation){
        return new WebDriverWait(driverManagerTL.getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(elementLocation));
    }
    protected WebElement elementToBeClickable(final By elementIdentifier){
        WebElement element = new WebDriverWait(driverManagerTL.getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(elementIdentifier));
        return element;
    }
}
