package testSteps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EventInfoPage;

import static org.junit.jupiter.api.Assertions.*;

public class EventInfoPageSteps {
    private WebDriver driver;
    private EventInfoPage eventInfoPage;
    private WebDriverWait wait;
    private Actions actions;

    public EventInfoPageSteps(WebDriver driver) {
        this.driver = driver;
        eventInfoPage = new EventInfoPage(driver);
        wait = new WebDriverWait(driver,8);
        actions = new Actions(driver);
    }

    @Step("Assert that event info page opened")
    public void assertThatEventInfoPageOpened(){
        wait.until(ExpectedConditions.visibilityOf(eventInfoPage.EVENT_PAGE_CONTAINER));
    }

    @Step("Assert that event fields are shown on the event info page")
    public void assertThatMainElementsDisplayed(){
        assertAll("Assert that fields are not empty",
                ()->assertTrue(eventInfoPage.REG_BUTTON.isDisplayed(),
                        "Register event button is not displayed or absent on the page"),
                ()->assertTrue(eventInfoPage.EVENT_AGENDA.isDisplayed(),
                        "Event agenda is not displayed or absent on the page"),
                ()->assertTrue(eventInfoPage.EVENT_CARD_PROMO.isDisplayed(),
                        "Event card promo is not displayed or absent on the page"),
                ()->assertTrue((eventInfoPage.EVENT_DAY.isDisplayed()&&!eventInfoPage.EVENT_DAY.getText().isEmpty()),
                        "Event Day is not displayed or empty on the page")
        );
    }

}
