package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.YTTile;
import com.gitlab.rmarzec.pages.YoutubePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


public class Task4Test {

    private WebDriver webDriver;
    private YoutubePage youtubePage;

    @BeforeClass
    public void setUp() {
        DriverFactory driverFactory = new DriverFactory();
        webDriver = driverFactory.initDriver();
        youtubePage = new YoutubePage(webDriver);
    }

    @Test
    public void Task4Test() {
        int expectedTilesSizes = 12;
        youtubePage.loadPage();
        //Lista kafelkow
        List<YTTile> ytTileList = youtubePage.createYTTiles();
        ytTileList.stream()
                .filter(YTTile::isNotLive)
                .forEach(tile -> System.out.println(tile.getTitle() + " -> " + tile.getLength()));
        Assert.assertEquals(expectedTilesSizes, ytTileList.size());
        Assert.assertTrue(ytTileList.stream().noneMatch(t -> t.getTitle().isEmpty()));
        Assert.assertTrue(ytTileList.stream().noneMatch(t -> t.getChannel().isEmpty()));
        Assert.assertTrue(ytTileList.stream().noneMatch(t -> t.getLength().isEmpty()));
    }

    @AfterClass
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
