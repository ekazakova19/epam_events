package tests;

import helpers.WebDriverLoggingListener;
import helpers.DriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp(){
        String browser = System.getProperty("browser");
        if(browser==null){
            driver = DriverManager.getDriver("chrome");
        }
        else {
            driver = DriverManager.getDriver(browser);
        }
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.register(new WebDriverLoggingListener());
        driver = eventFiringWebDriver;

    }

   // @AfterEach
    public void tearDown(){
        DriverManager.closeDriver();
    }
}
