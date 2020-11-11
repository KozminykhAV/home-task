package kozminykh.task1.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSearch1 {

    protected WebDriver driver;
    private final WebElement searchField;
    protected String text;

    public GoogleSearch1(WebDriver driver, String text) {
        this.text = text;
        this.driver = driver;
        searchField = driver.findElement(By.name("q"));
        startSearch(text);
    }

    private void startSearch(String text) {
        searchField.click();
        searchField.sendKeys(text);
        searchField.sendKeys(Keys.ENTER);
    }
}
