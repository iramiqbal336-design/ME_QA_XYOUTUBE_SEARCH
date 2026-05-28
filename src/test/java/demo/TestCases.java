package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;

import demo.wrappers.Wrappers;

public class TestCases {

    WebDriver driver;
    Wrappers wrappers;
    WebDriverWait wait;

    @BeforeTest
    public void startBrowser() {

        System.setProperty("java.util.logging.config.file", "logging.properties");

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);

        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(
                ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
                "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wrappers = new Wrappers(driver);
    }

    @Test
    public void testCase01() {

        driver.get("https://www.youtube.com");
        driver.get("https://www.youtube.com/about/");

        By aboutMessage = By.xpath(
                "//*[contains(text(),'give everyone a voice and show them the world')]");

        wait.until(ExpectedConditions.visibilityOfElementLocated(aboutMessage));

        String message = driver.findElement(aboutMessage).getText();

        System.out.println(message);

        Assert.assertTrue(
                message.toLowerCase().contains("give everyone a voice"));
    }

    @Test
    public void testCase02() {

        driver.get("https://www.youtube.com");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//tp-yt-paper-item//yt-formatted-string[contains(text(),'Movies')]")));

        By movieList = By.xpath("//tp-yt-paper-item//yt-formatted-string");

        wrappers.clickElementFromList(movieList, "Movies");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        Assert.assertTrue(
                driver.getPageSource().toLowerCase().contains("movies")
                        || driver.getPageSource().toLowerCase().contains("movie"));
    }

    @Test
    public void testCase03() {

        driver.get("https://www.youtube.com");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//tp-yt-paper-item//yt-formatted-string[contains(text(),'Music')]")));

        By musicList = By.xpath("//tp-yt-paper-item//yt-formatted-string");

        wrappers.clickElementFromList(musicList, "Music");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        Assert.assertTrue(
                driver.getPageSource().toLowerCase().contains("music"));
    }

    @Test
    public void testCase04() {

        driver.get("https://www.youtube.com");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//tp-yt-paper-item//yt-formatted-string[contains(text(),'Show more')]")));

        By showMoreList = By.xpath("//tp-yt-paper-item//yt-formatted-string");

        wrappers.clickElementFromList(showMoreList, "Show more");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//tp-yt-paper-item//yt-formatted-string[contains(text(),'News')]")));

        By newsList = By.xpath("//tp-yt-paper-item//yt-formatted-string");

        wrappers.clickElementFromList(newsList, "News");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        Assert.assertTrue(
                driver.getPageSource().toLowerCase().contains("news"));
    }

    @Test
    public void testCase05() {

        driver.get("https://www.youtube.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        Assert.assertTrue(
                driver.findElements(By.tagName("body")).size() > 0);
    }

    @AfterClass
    public void teardown() {

        if (driver != null) {
            driver.quit();
        }
    }
}