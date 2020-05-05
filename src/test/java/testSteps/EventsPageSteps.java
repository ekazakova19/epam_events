package testSteps;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EventCardElement;
import pages.EventsPage;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventsPageSteps {
    WebDriver driver;
    WebDriverWait wait;
    EventsPage eventsPage;
    EventCardElement eventCardElement;

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
        driver.get(EventsPage.EVENTS_PAGE_URL);
        clickOnUpcomingEvents();
    }

    public void assertThatEventCardsDisplayed(){
        Assertions.assertTrue(eventsPage.getDisplayedEventCount()>0);
    }

    public int getUpcomingEventCounterValue(){
        return Integer.parseInt(eventsPage.UPCOMING_EVENT_COUNTER.getText());
    }

    public void assertThatUpcomingEventsCounterCorrect(){
        Assertions.assertEquals(getUpcomingEventCounterValue(),eventsPage.getDisplayedEventCount());
    }

    public void assertThatEveryEventCardHasFields(){
        eventsPage.initEventCardElementsList();
        for (EventCardElement eventCardElement: eventsPage.eventCardElements) {
            assertAll("Assert that fields are not empty",
                    ()->assertTrue(!eventCardElement.LOCATION.getText().isEmpty()),
                    ()->assertTrue(!eventCardElement.LAUNGUAGE.getText().isEmpty()),
                    ()->assertTrue(!eventCardElement.EVENT_NAME.getText().isEmpty()),
                    ()->assertTrue(!eventCardElement.EVENT_DATE.getText().isEmpty()),
                    ()->assertTrue(!eventCardElement.EVENT_STATUS.getText().isEmpty()),
                    ()->assertTrue(eventCardElement.EVENT_SPEAKERS_LIST.size()!=0)
            );
        }
    }

    public EventInfoPageSteps clickOnAnyDisplayedEventCard(){
       int numberOfDisplayedCards = eventsPage.getDisplayedEventCount();
       int randomCardNumber = 1;
       if(numberOfDisplayedCards==0){
           Assertions.fail("No one event card found on the page");
       }
       else if(numberOfDisplayedCards>1){
          randomCardNumber=new Random().ints(1,eventsPage.getDisplayedEventCount()).findAny().getAsInt();
       }
       eventsPage.EVENT_CARD_WEBELEMENT.get(randomCardNumber-1).click();
       return new EventInfoPageSteps(driver);
    }
}
