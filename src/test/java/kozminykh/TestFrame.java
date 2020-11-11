package kozminykh;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TestFrame {

    protected WebDriver driver;
    private final ChromeOptions options = new ChromeOptions();

    @BeforeEach
    void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "E:\\ATS2020\\chromedriver.exe");
        options.addArguments("--user-data-dir=C:\\Users\\Kozminykh\\AppData\\Local\\Google\\Chrome\\User Data\\");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.get("https://google.com/");
    }

    @AfterEach
    void afterTest() {
        driver.quit();
    }
}
