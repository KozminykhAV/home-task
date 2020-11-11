package kozminykh.task2.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BankWebsite {

    protected WebDriver driver;

    public BankWebsite(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkExchangeRates(String unit) {
        List<WebElement> standardRate = driver.findElements(By.id("rc-tabs-1-tab-defaultRate"));
        while (standardRate.isEmpty()) {
            standardRate = driver.findElements(By.id("rc-tabs-1-tab-defaultRate"));
        }
        standardRate.get(0).click();

        By buyTd = By.xpath("//*[@tabindex='0']//td//span[contains(text(), 'куп')]/ancestor::td");
        By sellTd = By.xpath("//*[@tabindex='0']//td//span[contains(text(), 'прод')]/ancestor::td");
        By usdRow = By.xpath("//*[@tabindex='0']//td//span[contains(text(), '" + unit + "')]/ancestor::tr");

        int indexOfBuyColumn = indexToN(Integer.parseInt(driver.findElement(buyTd).getAttribute("cellIndex")));
        int indexOfSellColumn = indexToN(Integer.parseInt(driver.findElement(sellTd).getAttribute("cellIndex")));
        int indexOfUsdRow = indexToN(Integer.parseInt(driver.findElement(usdRow).getAttribute("rowIndex")));

        WebElement buyingRate = driver.findElement(
                By.xpath("//*[@tabindex='0']//tr[" + indexOfUsdRow + "]//td[" + indexOfBuyColumn + "]//span"));
        WebElement sellingRate = driver.findElement(
                By.xpath("//*[@tabindex='0']//tr[" + indexOfUsdRow + "]//td[" + indexOfSellColumn + "]//span"));

        double bankBuys = Double.parseDouble(buyingRate.getAttribute("innerHTML").replace(',', '.'));
        double bankSells = Double.parseDouble(sellingRate.getAttribute("innerHTML").replace(',', '.'));

        return bankBuys < bankSells;
    }

    private Integer indexToN(Integer i) {
        return ++i;
    }

}
