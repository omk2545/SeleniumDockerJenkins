package com.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.utils.ExtentHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.utils.ExtentHelper.reportPath;

public class BaseTest {

    protected WebDriver driver;
    public ExtentReports extentReports;



    @BeforeTest
    public void setupDriver(ITestContext ctx) throws MalformedURLException {
        // BROWSER => chrome / firefox
        // HUB_HOST => localhost / 10.0.1.3 / hostname

        String host = "localhost";
        DesiredCapabilities dc;
        dc = DesiredCapabilities.firefox();
        if (System.getProperty("HUB_HOST") != null) {
            host = System.getProperty("HUB_HOST");
        }
        extentReports = ExtentHelper.getInstance();
        String testName = ctx.getCurrentXmlTest().getName();
        String completeUrl = "http://" + host + ":4444/wd/hub";
        dc.setCapability("name", testName);
        this.driver = new RemoteWebDriver(new URL(completeUrl), dc);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterTest
    public void quitDriver() {
        extentReports.flush();
        this.driver.quit();
        System.out.println(reportPath);
        try {
            File srcFile = new File(reportPath);
            File destFile = new File("reports/current/index.html");
            FileUtils.copyFile(srcFile,destFile);
        }catch (Exception e){

        }


    }

    public void assertStringValues(String expected, String actual, ExtentTest test){
        if (!actual.equalsIgnoreCase(expected)){
            test.fail("Expected and Actual values don't match: "+expected+"!="+actual);
        }
        else {
            test.pass("Expected and Actual values match !!!!" );
        }
        Assert.assertEquals(actual,expected);
    }

    public void assertTrue(boolean value,ExtentTest test){
        if (!value){
            test.fail("Got False Expected True");
        }
        else {
            test.pass("Expected and Actual values match !!!!" );
        }
        Assert.assertTrue(value);
    }
}
