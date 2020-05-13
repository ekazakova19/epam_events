package testSteps;


import helpers.DateManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EventCardElement;
import pages.EventsPage;
import java.time.LocalDate;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventsPageSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private EventsPage eventsPage;
    public FilterSteps filterSteps;

    public EventsPageSteps(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,5);
        eventsPage = new EventsPage(driver);
        filterSteps = new FilterSteps(driver);
    }

    @Step("Click on Upcoming Events tab")
    public void clickOnUpcomingEvents(){
        eventsPage.UPCOMING_EVENTS_NAV_LINK.click();
        wait.until(ExpectedConditions.visibilityOf(eventsPage.UPCOMING_EVENTS_ACTIVE));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(filterSteps.eventFilterPanelELement.LOADER));
    }
    @Step("Click on Past Events tab")
    public void clickOnPastEvents(){
        eventsPage.PAST_EVENTS_NAV_LINK.click();
        wait.until(ExpectedConditions.visibilityOf(eventsPage.PAST_EVENTS_ACTIVE));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(eventsPage.eventFilterPanelElement.LOADER));
    }

    @Step("Open events page and click on Upcoming Events")
    public void openUpcomingEvents(){
        driver.get(EventsPage.EVENTS_PAGE_URL);
        clickOnUpcomingEvents();
    }

    @Step("Open Events page and click on Past Events")
    public void openPastEvents(){
        driver.get(EventsPage.EVENTS_PAGE_URL);
        clickOnPastEvents();
    }

    @Step("Assert that event cards is shown on the page")
    public void assertThatEventCardsDisplayed(){
        Assertions.assertTrue(eventsPage.getDisplayedEventCount()>0,
                "No one event card is shown on the page");
    }


    @Step("Assert that upcoming events counter is equal to displayed events count")
    public void assertThatUpcomingEventsCounterCorrect(){
        Assertions.assertEquals(getUpcomingEventCounterValue(),
                eventsPage.getDisplayedEventCount(),
                "Upcoming event counter is not equal to displayed events count");
    }

    @Step("Assert that past event's counter is equal to displayed past events")
    public void assertThatPastEventsCounterCorrect(){
        Assertions.assertEquals(getPastEventCounterValue(),
                eventsPage.getDisplayedEventCount(),
                "Past event counter is not equal to displayed events count");
    }

    @Step("Assert that every event card has correct fields")
    public void assertThatEveryEventCardHasFields(){
        eventsPage.initEventCardElementsList(eventsPage.ALL_EVENTS_LIST);
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

    @Step("Click on random displayed event card")
    public EventInfoPageSteps clickOnAnyDisplayedEventCard(){
       int numberOfDisplayedCards = eventsPage.getDisplayedEventCount();
       int randomCardNumber = 1;
       if(numberOfDisplayedCards==0){
           Assertions.fail("No one event card found on the page");
       }
       else if(numberOfDisplayedCards>1){
          randomCardNumber=new Random().ints(1,eventsPage.getDisplayedEventCount()).findAny().getAsInt();
       }
       eventsPage.ALL_EVENTS_LIST.get(randomCardNumber-1).click();
       return new EventInfoPageSteps(driver);
    }

    @Step("Assert that this week events are shown")
    public void assertThatThisWeekEventsDisplayed(){
        Assertions.assertTrue(!eventsPage.THIS_WEEK_EVENTS_LIST.isEmpty(),
                "This week events are absent on the page");
    }
    @Step("Assert that this week events are after today and before next week")
    public void assertThatThisWeekEventsOnThisWeek(){
        eventsPage.initEventCardElementsList(eventsPage.THIS_WEEK_EVENTS_LIST);
        for (EventCardElement eventCardElement : eventsPage.eventCardElements){
           LocalDate eventDate = LocalDate.parse(DateManager.handleRangeEventDate(eventCardElement.EVENT_DATE.getText())
                   ,DateManager.EVENTDATE_FORMATTER);
           Assertions.assertTrue(DateManager.isDateInCurrentWeek(eventDate),
                   "Event date less than today date or it is out of the current week");
        }
    }

    @Step("Assert that past events are before today date")
    public void assertThatEventsDateIsLessThanToday(){
        eventsPage.initEventCardElementsList(eventsPage.ALL_EVENTS_LIST);
        for (EventCardElement eventCardElement : eventsPage.eventCardElements){
            LocalDate eventDate = LocalDate.parse(DateManager.handleRangeEventDate(eventCardElement.EVENT_DATE.getText())
                    ,DateManager.EVENTDATE_FORMATTER);
            Assertions.assertTrue(DateManager.isDateBeforeThanToday(eventDate),
                    "Event date more than today date");
        }
    }

    private int getUpcomingEventCounterValue(){
        return Integer.parseInt(eventsPage.UPCOMING_EVENT_COUNTER.getText());
    }

    private int getPastEventCounterValue(){
        return Integer.parseInt(eventsPage.PAST_EVENT_COUNTER.getText());
    }

}
