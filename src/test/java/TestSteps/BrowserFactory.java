package TestSteps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {
    public static WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://hotel-test.equalexperts.io/");
    }

    @After
    public void quit() {
        if (null != driver) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

}
