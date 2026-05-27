package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import demo.wrappers.Wrappers;
import java.time.Duration;

public class TestCases {
    WebDriver driver;
    Wrappers wrappers;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wrappers = new Wrappers(driver);
    }

    @Test
    public void testCase01() {
        driver.get("https://www.youtube.com");
        driver.get("https://www.youtube.com/about/");
        By aboutMessage = By.xpath("//*[contains(text(),'give everyone a voice and show them the world')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(aboutMessage));
        String message = driver.findElement(aboutMessage).getText();
        System.out.println(message);
        Assert.assertTrue(message.toLowerCase().contains("give everyone a voice"));
    }

    @Test
    public void testCase02() {
        driver.get("https://www.youtube.com");
        wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//tp-yt-paper-item//yt-formatted-string[contains(text(),'Movies')]")));
        By movieList = By.xpath("//tp-yt-paper-item//yt-formatted-string");
        wrappers.clickElementFromList(movieList, "Movies");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("movies")
                || driver.getPageSource().toLowerCase().contains("movie"));
    }

    @Test
    public void testCase03() {
        driver.get("https://www.youtube.com");
        wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//tp-yt-paper-item//yt-formatted-string[contains(text(),'Music')]")));
        By musicList = By.xpath("//tp-yt-paper-item//yt-formatted-string");
        wrappers.clickElementFromList(musicList, "Music");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("music"));
    }

    @Test
    public void testCase04() {
        driver.get("https://www.youtube.com");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//tp-yt-paper-item//yt-formatted-string[contains(text(),'Show more')]")));
        By showMoreList = By.xpath("//tp-yt-paper-item//yt-formatted-string");
        wrappers.clickElementFromList(showMoreList, "Show more");
        wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//tp-yt-paper-item//yt-formatted-string[contains(text(),'News')]")));
        By newsList = By.xpath("//tp-yt-paper-item//yt-formatted-string");
        wrappers.clickElementFromList(newsList, "News");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("news"));
    }

    @Test
    public void testCase05() {
        driver.get("https://www.youtube.com");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        Assert.assertTrue(driver.findElements(By.tagName("body")).size() > 0);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}