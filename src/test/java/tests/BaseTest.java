package tests;

import com.epam.healenium.SelfHealingDriver;
import helpers.WebDriverLoggingListener;
import helpers.LocalDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import testProperties.TestConfig;
import com.typesafe.config.Config;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected TestConfig testConfig;

    public WebDriver setUpLocal(String browser){
        driver = new LocalDriverManager().getDriver(browser);
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
        driver = delegateDriverToHealing(driver);

    }

    public SelfHealingDriver delegateDriverToHealing(WebDriver driver){
        Config config = com.typesafe.config.ConfigFactory.load("healenium.properties");
        return SelfHealingDriver.create(driver, config);
    }
}
