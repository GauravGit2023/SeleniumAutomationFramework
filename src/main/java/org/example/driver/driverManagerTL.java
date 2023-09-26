package org.example.driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class driverManagerTL {

    // Thread local
    // Every thread will have their own sandbox to have their own webdriver

    static WebDriver driver;

    private static final ThreadLocal<WebDriver> dr = new ThreadLocal<>();

    public static void setDriver(WebDriver driverRef){
        dr.set(driverRef);
    }

    public static WebDriver getDriver(){
        return dr.get();
    }

    public static void unload(){
        // this is used to remove stuck/ too slow driver
        dr.remove();
    }

    @BeforeMethod
    public static void init(){
        // TODO #1 - Need to make this support firefox, edge, ie, safari
        if(getDriver() == null){
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            setDriver(new ChromeDriver(options));
        }
    }
    @AfterMethod
    public  static void tearDown(){
        if(getDriver() != null){
            getDriver().quit();
        }
    }
}
