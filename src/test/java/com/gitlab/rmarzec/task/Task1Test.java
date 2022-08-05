package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Task1Test {
    private WebDriver webDriver;


    @BeforeClass
    public void setUp() {
        DriverFactory driverFactory = new DriverFactory();
        webDriver = driverFactory.initDriver();
    }

    @Test
    public void Task1Test() {
        webDriver.get("https://www.onet.pl/");
    }

    @AfterClass
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
