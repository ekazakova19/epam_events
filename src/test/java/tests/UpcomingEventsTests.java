package tests;

import helpers.TestResultExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import testSteps.EventInfoPageSteps;
import testSteps.EventsPageSteps;
import testSteps.MainPageSteps;

@ExtendWith(TestResultExtension.class)
public class UpcomingEventsTests extends BaseTest{
    MainPageSteps mainPageSteps;
    EventsPageSteps eventsPageSteps;
    EventInfoPageSteps eventInfoPageSteps;

    @BeforeEach
    public void initSteps(){
        mainPageSteps = new MainPageSteps(driver);
        eventsPageSteps = new EventsPageSteps(driver);
        eventInfoPageSteps = new EventInfoPageSteps(driver);
    }

    @Test
    @DisplayName("View epam upcoming events")
    void viewUpcomingEventsTest(){
        mainPageSteps.navigateToEvents();
        eventsPageSteps.clickOnUpcomingEvents();
        eventsPageSteps.assertThatEventCardsDisplayed();
        eventsPageSteps.assertThatUpcomingEventsCounterCorrect();
    }

    @Test
    @DisplayName("View displayed upcoming event cards")
    void viewEventCardTest(){
        eventsPageSteps.openUpcomingEvents();
        eventsPageSteps.assertThatEventCardsDisplayed();
        eventsPageSteps.assertThatEveryEventCardHasFields();
    }
    @Test
    @DisplayName("Check upcoming event's date in this week block")
    void validateUpcomingEventDatesTest(){
        eventsPageSteps.openUpcomingEvents();
        eventsPageSteps.assertThatEventCardsDisplayed();
        eventsPageSteps.assertThatThisWeekEventsDisplayed();
        eventsPageSteps.assertThatThisWeekEventsOnThisWeek();
    }

    @Test
    @DisplayName("View event details")
    void viewEventDetailsTest(){
        eventsPageSteps.openUpcomingEvents();
        eventsPageSteps.assertThatEventCardsDisplayed();
        eventsPageSteps.clickOnAnyDisplayedEventCard()
                .assertThatEventInfoPageOpened();
        eventInfoPageSteps.assertThatMainElementsDisplayed();
    }
}
