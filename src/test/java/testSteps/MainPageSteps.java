package testSteps;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class MainPageSteps {

    WebDriver driver;
    MainPage mainPage;

    public MainPageSteps(WebDriver driver) {
        this.driver = driver;
        mainPage = new MainPage(driver);
    }

    public void navigateToEvents(){
        driver.get(MainPage.MAIN_PAGE_URL);
        mainPage.headerNavBarElement.EVENTS_LINK.click();
        Assertions.assertTrue(driver.getCurrentUrl().contains("events"),"Events page not opened");
    }

}
