package com.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setupDriver(ITestContext ctx) throws MalformedURLException {
        // BROWSER => chrome / firefox
        // HUB_HOST => localhost / 10.0.1.3 / hostname

        String host = "localhost";
        DesiredCapabilities dc;

        if(System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
            dc = DesiredCapabilities.firefox();
        }else{
//            dc = DesiredCapabilities.chrome();
            dc = DesiredCapabilities.firefox();

        }

        if(System.getProperty("HUB_HOST") != null){
            host = System.getProperty("HUB_HOST");
        }

        String testName = ctx.getCurrentXmlTest().getName();

        String completeUrl = "http://" + host + ":4444/wd/hub";
        dc.setCapability("name", testName);
        this.driver = new RemoteWebDriver(new URL(completeUrl), dc);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox");
//        WebDriver browser = new ChromeDriver(options);
//        browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//
//        WebDriverManager.chromedriver().setup();
//        this.driver = new ChromeDriver();

    }

//    @Test
//    public void launchChrome(){
//
//        driver.get("https://www.google.com");
//
//    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }



}
