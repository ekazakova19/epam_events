package testSteps;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EventCardElement;
import pages.EventsPage;

import java.time.LocalDate;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
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
        Assertions.assertTrue(!eventsPage.THIS_WEEK_EVENTS_LIST.isEmpty());
    }

    public void assertThatThisWeekEventsOnThisWeek(){
        //restrict to this week only
        eventsPage.initEventCardElementsList(eventsPage.THIS_WEEK_EVENTS_LIST);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

        for (EventCardElement eventCardElement : eventsPage.eventCardElements){
           LocalDate eventDate = LocalDate.parse(eventCardElement.EVENT_DATE.getText(),formatter);
           Assertions.assertTrue(isDateInCurrentWeek(eventDate),"Event date less than today date or it is out of the current week");
        }
    }

    public boolean isDateInCurrentWeek(LocalDate date){
        LocalDate today = LocalDate.now();
        LocalDate mondayOfCurrentWeek = today.with(previousOrSame(MONDAY));
        LocalDate sundayOfCurrentWeek = today.with(nextOrSame(SUNDAY));
        if(date.isBefore(today)){
            return false;
        }
        else if((date.isEqual(mondayOfCurrentWeek) || date.isAfter(mondayOfCurrentWeek))
                && (date.isEqual(sundayOfCurrentWeek)||date.isBefore(sundayOfCurrentWeek))){
            return true;
        }
        else {
            return false;
        }
    }
}
