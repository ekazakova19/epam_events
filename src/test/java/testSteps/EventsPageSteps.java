package testSteps;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EventsPage;

public class EventsPageSteps {
    WebDriver driver;
    WebDriverWait wait;
    EventsPage eventsPage;

    public EventsPageSteps(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,5);
        eventsPage = new EventsPage(driver);
    }

    public void clickOnUpcomingEvents(){
        eventsPage.UPCOMING_EVENTS_NAV_LINK.click();
        wait.until(ExpectedConditions.visibilityOf(eventsPage.UPCOMING_EVENTS_ACTIVE));

    }
    public void openUpcomingEvents(){
        driver.get(eventsPage.eventsPageURL);
        clickOnUpcomingEvents();
    }

    public void assertThatEventCardDisplayed(){
        Assertions.assertTrue(eventsPage.getDisplayedEventCount()>0);
    }
    public int getUpcomingEventCounterValue(){
        return Integer.parseInt(eventsPage.UPCOMING_EVENT_COUNTER.getText());
    }
    public void assertThatUpcomingEventsCounterCorrect(){
        Assertions.assertEquals(getUpcomingEventCounterValue(),eventsPage.getDisplayedEventCount());
    }
}
