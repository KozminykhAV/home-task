package kozminykh.task1.page.factory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class TaskOnePF {

    private WebDriver driver;

    @FindBy(how = How.ID, id = "realbox")
    WebElement searchField;

    @FindAll(@FindBy(how = How.XPATH, xpath = "//a[@href]"))
    List<WebElement> result;

    public TaskOnePF(WebDriver driver) {
        this.driver = driver;
    }

    public void startSearch(String text) {
        searchField.click();
        searchField.sendKeys(text);
        searchField.sendKeys(Keys.ENTER);
    }

    public boolean isLinkExist(String text) {
        return result.stream().anyMatch(x -> x.getText().contains(text));
    }

}
