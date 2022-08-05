package com.gitlab.rmarzec.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class GooglePage extends BasePage {
    private static final String PAGE_URL = "https://www.google.com/";
    private static final By INVALID_HINT_LINK = By.cssSelector("[href='#']");

    @FindBy(css = "input[title='Szukaj']")
    private WebElement searchInput;

    @FindBy(css = "input[value='Szczęśliwy traf']")
    private List<WebElement> luckyShotButtons;

    @FindBy(xpath = "//button/div[contains(text(),'Zaakceptuj wszystko')]")
    private WebElement acceptCookiesButton;

    public GooglePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void loadPage() {
        webDriver.get(PAGE_URL);
        acceptCookie();
    }

    public W3SchoolsPage searchByLuckyShot(String query) {
        inputQuery(query);
        clickLuckyShot();
        return new W3SchoolsPage(webDriver);
    }

    public void inputQuery(String query) {
        wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        searchInput.sendKeys(query);
    }

    public void clickLuckyShot() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(INVALID_HINT_LINK));
        luckyShotButtons.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(el -> {
                    wait.until(ExpectedConditions.elementToBeClickable(el));
                    el.click();
                });
    }

    private void acceptCookie() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
        acceptCookiesButton.click();
    }
}
