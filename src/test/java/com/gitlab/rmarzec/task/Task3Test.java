package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.pages.GooglePage;
import com.gitlab.rmarzec.pages.W3SchoolsPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Task3Test {

    private WebDriver webDriver;
    private GooglePage googlePage;

    @BeforeClass
    public void setUp() {
        DriverFactory driverFactory = new DriverFactory();
        webDriver = driverFactory.initDriver();
        googlePage = new GooglePage(webDriver);
    }

    @Test
    public void Task3Test() {
        String expectedHeader = "The select element";
        String expectedCar = "Opel,opel";
        String query = "HTML select tag - W3Schools";
        String car = "Opel";
        googlePage.loadPage();
        W3SchoolsPage w3SchoolsPage = googlePage.searchByLuckyShot(query);
        w3SchoolsPage.clickFirstTryItButton();
        w3SchoolsPage.switchToResultFrame();
        String header = w3SchoolsPage.getHeaderText();
        System.out.println("Header: " + header);
        Assert.assertEquals(expectedHeader, header);
        String selectedCar = w3SchoolsPage.chooseCar(car);
        System.out.println(selectedCar);
        Assert.assertEquals(expectedCar, selectedCar);
    }

    @AfterClass
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
