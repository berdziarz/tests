package com.gitlab.rmarzec.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class W3SchoolsPage extends BasePage {
    private static final String PAGE_URL = "https://www.w3schools.com/tags/tag_select.asp";
    private static final String IFRAME_RESULT_ID = "iframeResult";
    private static final String VALUE_ATTR = "value";

    @FindBy(id = "accept-choices")
    private WebElement acceptCookiesButton;

    @FindBy(css = ".w3-btn.w3-margin-bottom")
    private List<WebElement> tryItButtons;

    @FindBy(id = "cars")
    private WebElement cars;

    @FindBy(css = "h1")
    private WebElement header;


    public W3SchoolsPage(WebDriver driver) {
        super(driver);
        if (!webDriver.getCurrentUrl().equals(PAGE_URL)) {
            System.out.println("Current url: " + webDriver.getCurrentUrl());
            loadPage();
        }
        acceptCookies();
    }

    @Override
    public void loadPage() {
        webDriver.get(PAGE_URL);
    }

    private void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
        acceptCookiesButton.click();
    }

    public void clickFirstTryItButton() {
        tryItButtons.get(0).click();
        switchWindow();
    }

    private void switchWindow() {
        webDriver.getWindowHandles().stream()
                .filter(w -> !w.equals(webDriver.getWindowHandle()))
                .findFirst()
                .ifPresent(w -> webDriver.switchTo().window(w));
    }

    public void switchToResultFrame() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(IFRAME_RESULT_ID)));
        webDriver.switchTo().frame(IFRAME_RESULT_ID);
    }


    public String getHeaderText() {
        return header.getText();
    }

    public String chooseCar(String car) {
        Select carSelect = new Select(cars);
        carSelect.selectByVisibleText(car);
        WebElement selectedCar = carSelect.getFirstSelectedOption();
        return selectedCar.getText() + "," + selectedCar.getAttribute(VALUE_ATTR);
    }
}
