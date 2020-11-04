package com.newtours.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.newtours.pages.*;
import com.tests.BaseTest;
import com.utils.ExtentHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.print.DocFlavor;

public class BookFlightTest extends BaseTest {

    private String noOfPassengers;
    private String expectedPrice;
    ExtentTest extentTest;
    ExtentReports extentReports;
    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setupParameters(String noOfPassengers, String expectedPrice) {
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
        extentReports = ExtentHelper.getInstance();
    }

    @Test
    public void registrationPage() {
        String name = "Registration Test" + this.expectedPrice+":"+this.noOfPassengers;
        System.out.println("name is :"+name);
        extentTest = extentReports.createTest(name);
//        extentTest = extentReports.createTest("Name is  ");
        RegistrationPage registrationPage = new RegistrationPage(driver, extentTest);
        registrationPage.goTo();
        registrationPage.enterUserDetails("selenium", "docker");
        registrationPage.enterUserCredentials("selenium", "docker");
        registrationPage.submit();
    }

    @Test(dependsOnMethods = "registrationPage")
    public void registrationConfirmationPage() {
        String name = "Registration Test" + this.expectedPrice+":"+this.noOfPassengers;
        System.out.println("name is :"+name);
        extentTest = extentReports.createTest(name);
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.goToFlightDetailsPage();
    }

    @Test(dependsOnMethods = "registrationConfirmationPage")
    public void flightDetailsPage() {
        String name = "Registration Confirmation Page" + this.expectedPrice+":"+this.noOfPassengers;
        System.out.println("name is :"+name);
        extentTest = extentReports.createTest(name);
        FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
        flightDetailsPage.selectPassengers(noOfPassengers);
        flightDetailsPage.goToFindFlightsPage();
    }

    @Test(dependsOnMethods = "flightDetailsPage")
    public void findFlightPage() {
        String name = "Find Flight" + this.expectedPrice+":"+this.noOfPassengers;
        System.out.println("name is :"+name);
        extentTest = extentReports.createTest(name);
        FindFlightPage findFlightPage = new FindFlightPage(driver,extentTest);
        findFlightPage.submitFindFlightPage();
        findFlightPage.goToFlightConfirmationPage();
    }

    @Test(dependsOnMethods = "findFlightPage")
    public void flightConfirmationPage() {
        String name = "Flight Confirmation " + this.expectedPrice+":"+this.noOfPassengers;
        System.out.println("name is :"+name);
        extentTest = extentReports.createTest(name);
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        String actualPrice = flightConfirmationPage.getPrice();
        assertStringValues(actualPrice,expectedPrice,extentTest);
    }

}
