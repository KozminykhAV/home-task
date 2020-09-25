package kozminykh;

import kozminykh.task1.page.factory.TaskOnePF;
import kozminykh.task1.page.object.GoogleSearchResult1;
import kozminykh.task2.page.factory.TaskTwoPF;
import kozminykh.task2.page.object.BankWebsite;
import kozminykh.task2.page.object.GoogleSearchResult2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.PageFactory;

public class Tests extends TestFrame {

    @Test // Page-Object // Task1
    void isWikiLinkThere() {
        GoogleSearchResult1 searchResult = new GoogleSearchResult1(driver, "Гладиолус");
        Assertions.assertTrue(searchResult.isLinkExist("Шпажник — Википедия"), "Link doesn't exist");
    }

    @Test // Page-Object // Task2
    void buyingRateIsLess() {
        GoogleSearchResult2 searchResult = new GoogleSearchResult2(driver, "Открытие");
        searchResult.goToWebsite("Банк «Открытие»");
        BankWebsite bankWebsite = new BankWebsite(driver);
        Assertions.assertTrue(bankWebsite.checkExchangeRates("USD"), "The Bank made a mistake");
    }

    @Test // Page-Factory // Task1
    void isWikiLinkTherePF() {
        TaskOnePF taskOnePF = PageFactory.initElements(driver, TaskOnePF.class);
        taskOnePF.startSearch("Гладиолус");
        Assertions.assertTrue(taskOnePF.isLinkExist("Шпажник — Википедия"), "Link doesn't exist");
    }

    @Test // Page-Factory // Task2
    void  buyingRateIsLessPF() {
        TaskTwoPF taskTwoPF = PageFactory.initElements(driver, TaskTwoPF.class);
        taskTwoPF.startSearch("Открытие");
        taskTwoPF.goToWebsite("Банк «Открытие»");
        Assertions.assertTrue(taskTwoPF.checkExchangeRates("USD"),"The Bank made a mistake)");
    }
}
