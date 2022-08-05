package com.gitlab.rmarzec.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected static WebDriver webDriver;
    protected static WebDriverWait wait;

    public BasePage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(webDriver, this);
    }

    protected abstract void loadPage();
}
