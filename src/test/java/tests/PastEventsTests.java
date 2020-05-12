package tests;

import helpers.TestResultExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import testSteps.EventsPageSteps;
import testSteps.FilterType;

@ExtendWith(TestResultExtension.class)
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
        eventsPageSteps.filterSteps.applyFilters(FilterType.LOCATION,"Canada");
        eventsPageSteps.assertThatPastEventsCounterCorrect();
        eventsPageSteps.assertThatEventsDateIsLessThanToday();
    }
}
