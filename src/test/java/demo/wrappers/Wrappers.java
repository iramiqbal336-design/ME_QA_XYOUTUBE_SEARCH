package demo.wrappers;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Wrappers {
    WebDriver driver;

    public Wrappers(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElementFromList(By locator, String text) {
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement element : elements) {
            String value = element.getText().trim();
            if (value.equalsIgnoreCase(text)) {
                element.click();
                return;
            }
        }
        throw new RuntimeException("Element not found: " + text);
    }
}