package com.gitlab.rmarzec.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.stream.Collectors;


public class WikipediaPage extends BasePage {
    private static final String PAGE_URL = "https://pl.wikipedia.org/wiki/Wiki";
    private static final String HREF_ATTR = "href";
    private static final By SPAN = By.cssSelector("span");

    @FindBy(css = ".interlanguage-link-target")
    private List<WebElement> languages;

    public WikipediaPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void loadPage() {
        webDriver.get(PAGE_URL);
    }


    public List<String> getAllLanguage(String language) {
        return languages.stream().map(e -> getLanguage(e, language)).collect(Collectors.toList());
    }

    private String getLanguage(WebElement element, String language) {
        WebElement spanElement = element.findElement(SPAN);
        wait.until((ExpectedCondition<Boolean>) driver -> !spanElement.getText().isEmpty());
        String text = spanElement.getText();
        return text.equals(language) ? text + " - " + element.getAttribute(HREF_ATTR) : text;
    }


}
