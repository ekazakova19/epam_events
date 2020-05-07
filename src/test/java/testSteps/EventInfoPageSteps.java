package testSteps;

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
        wait = new WebDriverWait(driver,5);
        actions = new Actions(driver);
    }

    public void assertThatEventInfoPageOpened(){
        wait.until(ExpectedConditions.visibilityOf(eventInfoPage.EVENT_PAGE_CONTAINER));
    }

    public void assertThatMainElementsDisplayed(){

        assertAll("Assert that fields are not empty",
                ()->assertTrue(eventInfoPage.REG_BUTTON.isDisplayed(),"Register event button is not displayed or absent on the page"),
                ()->assertTrue(eventInfoPage.EVENT_AGENDA.isDisplayed(),"Event agenda is not displayed or absent on the page"),
                ()->assertTrue(eventInfoPage.EVENT_CARD_PROMO.isDisplayed(),"Event card promo is not displayed or absent on the page"),
                ()->assertTrue((eventInfoPage.EVENT_DAY.isDisplayed()&&!eventInfoPage.EVENT_DAY.getText().isEmpty()),"Event Day is not displayed or empty on the page")
        );
    }

}
