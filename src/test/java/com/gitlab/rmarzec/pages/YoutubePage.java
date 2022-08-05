package com.gitlab.rmarzec.pages;

import com.gitlab.rmarzec.model.YTTile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class YoutubePage extends BasePage {
    private static final String PAGE_URL = "https://www.youtube.com/";
    private static final String TITLE_ATTR = "title";
    private static final String ARIA_LABEL = "aria-label";
    private static final By TILE_LENGTH_INFO = By.cssSelector(".ytd-thumbnail-overlay-time-status-renderer[id='text']");
    private static final By TILE_TITLE_INFO = By.cssSelector("[id='video-title']");
    private static final By TILE_CHANNEL_INFO = By.cssSelector("[id='avatar-link']");
    private static final By TILE_LIVE_INFO = By.cssSelector(".badge-style-type-live-now-alternate");
    private static final By TILE_SLIM = By.cssSelector("ytd-rich-grid-slim-media");

    @FindBy(xpath = "//tp-yt-paper-button[contains(@aria-label,'Zaakceptuj')]")
    private WebElement acceptCookiesButton;

    @FindBy(css = "div[id='contents'] .style-scope.ytd-rich-item-renderer[id='content']")
    private List<WebElement> videoTiles;

    public YoutubePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void loadPage() {
        webDriver.get(PAGE_URL);
        acceptCookies();
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
        acceptCookiesButton.click();
    }

    public List<YTTile> createYTTiles() {
        wait.until((ExpectedCondition<Boolean>) driver -> !videoTiles.isEmpty());
        return videoTiles.stream().filter(this::isNotSlim).limit(12).map(this::createYTTile).collect(Collectors.toList());
    }

    private boolean isNotSlim(WebElement tile) {
        return tile.findElements(TILE_SLIM).isEmpty();
    }

    private YTTile createYTTile(WebElement element) {
        YTTile tile = new YTTile();
        tile.setTitle(element.findElement(TILE_TITLE_INFO).getText());
        tile.setChannel(element.findElement(TILE_CHANNEL_INFO).getAttribute(TITLE_ATTR));
        String length = element.findElements(TILE_LIVE_INFO).isEmpty() ? getVideoLength(element) : null;
        tile.setLength(length);
        return tile;
    }

    private String getVideoLength(WebElement element) {
        wait.until((ExpectedCondition<Boolean>) driver -> !element.findElements(TILE_LENGTH_INFO).isEmpty());
        return element.findElement(TILE_LENGTH_INFO).getAttribute(ARIA_LABEL);
    }
}
