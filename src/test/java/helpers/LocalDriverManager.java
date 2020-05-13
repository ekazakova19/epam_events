package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LocalDriverManager {

    private  WebDriver driver;
    private static final Logger logger = LogManager.getLogger(LocalDriverManager.class);

    public  WebDriver getDriver(String browser) {

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = Drivers.CHROME.createDriver();
                logger.info("Chrome browser set up successfully {}", driver);
                break;
            case "firefox":
                driver = Drivers.FIREFOX.createDriver();
                logger.info("Firefox browser set up successfully");
                break;
            default:
                logger.info("Not supported browser has been passed {} . Set up chrome as default", browser);
                driver = Drivers.CHROME.createDriver();
                logger.info("Chrome browser set up successfully");
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

}
