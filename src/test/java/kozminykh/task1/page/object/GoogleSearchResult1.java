package kozminykh.task1.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleSearchResult1 extends GoogleSearch1 {

    private final List<WebElement> result;

    public GoogleSearchResult1(WebDriver driver, String text) {
        super(driver, text);
        result = driver.findElements(By.xpath("//a[@href]"));
    }

    public boolean isLinkExist(String s) {
        return result.stream().anyMatch(x -> x.getText().contains(s));
    }

    public void goToWebsite(String title) {
        if (isLinkExist(text)) {
            result.stream().filter(x -> x.getText().contains(title))
                    .findFirst().ifPresent(target -> driver.get(target.getAttribute("href")));
        }
    }
}
