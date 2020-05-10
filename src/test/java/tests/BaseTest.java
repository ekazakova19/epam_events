package tests;

import helpers.WebDriverLoggingListener;
import helpers.DriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import testProperties.TestConfig;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    TestConfig testConfig;


    public WebDriver setUpLocal(String browser){
        driver = DriverManager.getDriver(browser);
        return driver;

    }
    public WebDriver setUpRemote(String browser){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo",false);

        try {
            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    @BeforeEach
    public void setUp(){
        testConfig = ConfigFactory.create(TestConfig.class);
        System.out.println(testConfig.browser());
        System.out.println(testConfig.runTestStrategy());
        switch (testConfig.runTestStrategy().toUpperCase()){
            case "REMOTE" :
               driver = setUpRemote(testConfig.browser());
                break;
            case "LOCAL" :
                driver = setUpLocal(testConfig.browser());
                break;
            default:
                Assertions.fail(String.format(
                        "Not supported run test strategy has been passed %s",testConfig.runTestStrategy()));
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.register(new WebDriverLoggingListener());
        driver = eventFiringWebDriver;
    }


    @AfterEach
    public void tearDown(){
        DriverManager.closeDriver();
    }
}
