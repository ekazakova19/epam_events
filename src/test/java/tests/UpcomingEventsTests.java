package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testSteps.EventsPageSteps;
import testSteps.MainPageSteps;

public class UpcomingEventsTests extends BaseTest{
    MainPageSteps mainPageSteps;
    EventsPageSteps eventsPageSteps;

    @BeforeEach
    public void initSteps(){
        mainPageSteps = new MainPageSteps(driver);
        eventsPageSteps = new EventsPageSteps(driver);
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
}
