package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.pages.WikipediaPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


public class Task2Test {
    private WebDriver webDriver;
    private WikipediaPage wikipediaPage;

    @BeforeClass
    public void setUp() {
        DriverFactory driverFactory = new DriverFactory();
        webDriver = driverFactory.initDriver();
        wikipediaPage = new WikipediaPage(webDriver);
    }

    @Test
    public void Task2Test() {
        String language = "English";
        int expectedLanguagesSize = 139;
        wikipediaPage.loadPage();
        List<String> languages = wikipediaPage.getAllLanguage(language);
        languages.forEach(System.out::println);
        Assert.assertTrue(languages.stream().noneMatch(String::isEmpty));
        Assert.assertEquals(expectedLanguagesSize, languages.size());
    }

    @AfterClass
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
