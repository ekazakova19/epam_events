package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.EventInfoPage;
import testSteps.EventInfoPageSteps;
import testSteps.EventsPageSteps;
import testSteps.MainPageSteps;

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
    @DisplayName("View event card")
    void viewEventCardTest(){
        eventsPageSteps.openUpcomingEvents();
        eventsPageSteps.assertThatEventCardsDisplayed();
        eventsPageSteps.assertThatEveryEventCardHasFields();
    }
    @Test
    @DisplayName("View event details")
    void validateUpcomingEventDatesTest(){
        eventsPageSteps.openUpcomingEvents();
        eventsPageSteps.assertThatEventCardsDisplayed();
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
