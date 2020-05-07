package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testSteps.EventsPageSteps;

public class PastEventsTests extends BaseTest {

    EventsPageSteps eventsPageSteps;

    @BeforeEach
    public void initSteps(){
        eventsPageSteps = new EventsPageSteps(driver);
    }

    @Test
    @DisplayName("View past events in Canada")
    public void viewPastEventsInCanadaTest(){
        eventsPageSteps.openPastEvents();
        eventsPageSteps.filterByLocation("Canada");
        eventsPageSteps.assertThatPastEventsCounterCorrect();
        eventsPageSteps.assertThatEventsDateIsLessThanToday();
    }

    @Test
    @DisplayName("View past events in Canada")
    public void debug(){

    }

}
