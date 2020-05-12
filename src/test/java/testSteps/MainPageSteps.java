package testSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class MainPageSteps {

    private WebDriver driver;
    private MainPage mainPage;

    public MainPageSteps(WebDriver driver) {
        this.driver = driver;
        mainPage = new MainPage(driver);
    }

    @Step("Open main page and click on Events tab")
    public void navigateToEvents(){
        driver.get(MainPage.MAIN_PAGE_URL);
        mainPage.headerNavBarElement.EVENTS_LINK.click();
        Assertions.assertTrue(driver.getCurrentUrl().contains("events"),"Events page not opened");
    }
    @Step("Open main page and click to Talks tab")
    public void navigateToTalksLibrary(){
        driver.get(MainPage.MAIN_PAGE_URL);
        mainPage.headerNavBarElement.TALKS_LIBRARY_LINK.click();
        Assertions.assertTrue(driver.getCurrentUrl().contains("talks"),"Talks library page not opened");
    }

}
