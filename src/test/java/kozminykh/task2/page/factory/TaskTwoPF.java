package kozminykh.task2.page.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class TaskTwoPF {

    private final WebDriver driver;
    private boolean isExist;

    @FindBy(how = How.ID, id = "realbox")
    WebElement searchField;

    @FindAll(@FindBy(how = How.XPATH, xpath = "//a[@href]"))
    List<WebElement> result;

    public TaskTwoPF(WebDriver driver) {
        this.driver = driver;
    }

    public void startSearch(String text) {
        searchField.click();
        searchField.sendKeys(text);
        searchField.sendKeys(Keys.ENTER);
        this.isExist = isLinkExist(text);
    }

    public void goToWebsite(String title) {
        if (isExist) {
            result.stream().filter(x -> x.getText().contains(title))
                    .findFirst().ifPresent(target -> driver.get(target.getAttribute("href")));
        }
    }

    public boolean checkExchangeRates(String unit) {
        chooseTab();

        By buyTd = By.xpath("//*[@tabindex='0']//td//span[contains(text(), 'куп')]/ancestor::td");
        By sellTd = By.xpath("//*[@tabindex='0']//td//span[contains(text(), 'прод')]/ancestor::td");
        By usdRow = By.xpath("//*[@tabindex='0']//td//span[contains(text(), '" + unit + "')]/ancestor::tr");

        int indexOfBuyColumn = indexToN(findIndex(buyTd, "cellIndex"));
        int indexOfSellColumn = indexToN(findIndex(sellTd, "cellIndex"));
        int indexOfUsdRow = indexToN(findIndex(usdRow, "rowIndex"));

        WebElement buyingRate = driver.findElement(
                By.xpath("//*[@tabindex='0']//tr[" + indexOfUsdRow + "]//td[" + indexOfBuyColumn + "]//span"));
        WebElement sellingRate = driver.findElement(
                By.xpath("//*[@tabindex='0']//tr[" + indexOfUsdRow + "]//td[" + indexOfSellColumn + "]//span"));

        double bankBuys = getValue(buyingRate);
        double bankSells = getValue(sellingRate);

        return bankBuys < bankSells;
    }

    private boolean isLinkExist(String s) {
        return result.stream().anyMatch(x -> x.getText().contains(s));
    }

    private void chooseTab() {
        List<WebElement> standardRate = driver.findElements(By.id("tab-defaultRate"));
        while (standardRate.isEmpty()) {
            standardRate = driver.findElements(By.id("tab-defaultRate"));
        }
        standardRate.get(0).click();
    }

    private int findIndex(By by, String attributeName) {
        return Integer.parseInt(driver.findElement(by).getAttribute(attributeName));

    }

    private double getValue(WebElement e) {
        return Double.parseDouble(e.getAttribute("innerHTML").replace(',', '.'));
    }

    private Integer indexToN(Integer i) {
        return ++i;
    }
}
