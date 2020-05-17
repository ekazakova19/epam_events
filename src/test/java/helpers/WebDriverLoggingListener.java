package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;


public class WebDriverLoggingListener extends AbstractWebDriverEventListener {

    private static final Logger logger = LogManager.getLogger(WebDriverLoggingListener.class);

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        logger.debug("Navigated to {}", url);
    }
    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        logger.debug("Clicking element {}",element);
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        logger.debug("Clicked element {}", element);
    }
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        logger.debug("Try to locate element using {}", by);
    }
    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        logger.debug("Located element using {}", by);
    }
}
