package testSteps;


import helpers.DateManager;
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

    public EventsPageSteps(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,5);
        eventsPage = new EventsPage(driver);
    }

    public void clickOnUpcomingEvents(){
        eventsPage.UPCOMING_EVENTS_NAV_LINK.click();
        wait.until(ExpectedConditions.visibilityOf(eventsPage.UPCOMING_EVENTS_ACTIVE));
    }
    public void clickOnPastvents(){
        eventsPage.PAST_EVENTS_NAV_LINK.click();
        wait.until(ExpectedConditions.visibilityOf(eventsPage.PAST_EVENTS_ACTIVE));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(eventsPage.LOADER));
    }
    public void openUpcomingEvents(){
        driver.get(EventsPage.EVENTS_PAGE_URL);
        clickOnUpcomingEvents();
    }

    public void openPastEvents(){
        driver.get(EventsPage.EVENTS_PAGE_URL);
        clickOnPastvents();
    }

    public void assertThatEventCardsDisplayed(){
        Assertions.assertTrue(eventsPage.getDisplayedEventCount()>0);
    }

    private int getUpcomingEventCounterValue(){
        return Integer.parseInt(eventsPage.UPCOMING_EVENT_COUNTER.getText());
    }
    private int getPastEventCounterValue(){
        return Integer.parseInt(eventsPage.PAST_EVENT_COUNTER.getText());
    }

    public void assertThatUpcomingEventsCounterCorrect(){
        Assertions.assertEquals(getUpcomingEventCounterValue(),eventsPage.getDisplayedEventCount());
    }
    public void assertThatPastEventsCounterCorrect(){
        Assertions.assertEquals(getPastEventCounterValue(),eventsPage.getDisplayedEventCount());
    }

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

    public void assertThatThisWeekEventsDisplayed(){
        Assertions.assertTrue(!eventsPage.THIS_WEEK_EVENTS_LIST.isEmpty(),"This week events are absent on the page");
    }

    public void assertThatThisWeekEventsOnThisWeek(){
        eventsPage.initEventCardElementsList(eventsPage.THIS_WEEK_EVENTS_LIST);
        for (EventCardElement eventCardElement : eventsPage.eventCardElements){
           LocalDate eventDate = LocalDate.parse(DateManager.handleRangeEventDate(eventCardElement.EVENT_DATE.getText())
                   ,DateManager.EVENTDATE_FORMATTER);
           Assertions.assertTrue(DateManager.isDateInCurrentWeek(eventDate),"Event date less than today date or it is out of the current week");
        }
    }

    public void assertThatEventsDateIsLessThanToday(){
        eventsPage.initEventCardElementsList(eventsPage.ALL_EVENTS_LIST);
        for (EventCardElement eventCardElement : eventsPage.eventCardElements){
            LocalDate eventDate = LocalDate.parse(DateManager.handleRangeEventDate(eventCardElement.EVENT_DATE.getText())
                    ,DateManager.EVENTDATE_FORMATTER);
            Assertions.assertTrue(DateManager.isDateBeforeThanToday(eventDate),"Event date more than today date");
        }
    }

    public void filterByLocation(String location){
        eventsPage.eventFilterPanelElement.LOCATION_FIELD.click();
        wait.until(ExpectedConditions.visibilityOf(eventsPage.eventFilterPanelElement.LOCATION_INPUT_FIELD));
        eventsPage.eventFilterPanelElement.LOCATION_INPUT_FIELD.sendKeys(location);
        System.out.println(eventsPage.eventFilterPanelElement.FOUND_CHECKBOX.getText());
        Assertions.assertTrue(eventsPage.eventFilterPanelElement.FOUND_CHECKBOX.getText().equalsIgnoreCase(location));
        eventsPage.eventFilterPanelElement.FOUND_CHECKBOX.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(eventsPage.LOADER));
    }


}
