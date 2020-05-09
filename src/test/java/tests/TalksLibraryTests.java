package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testSteps.FilterType;
import testSteps.MainPageSteps;
import testSteps.TalksLibraryPageSteps;

public class TalksLibraryTests extends BaseTest {

    MainPageSteps mainPageSteps;
    TalksLibraryPageSteps talksLibraryPageSteps;

    @BeforeEach
    public void initSteps(){
        mainPageSteps = new MainPageSteps(driver);
        talksLibraryPageSteps = new TalksLibraryPageSteps(driver);
    }

    @Test
    @DisplayName("Check talks filtering by categories ")
    public void viewPastEventsInCanadaTest() {
        mainPageSteps.navigateToTalksLibrary();
        talksLibraryPageSteps.filterSteps.clickOnMoreFilters();
        talksLibraryPageSteps.filterSteps.applyFilters(FilterType.LOCATION,"Belarus");
//        talksLibraryPageSteps.filterSteps.applyFilters(FilterType.LANGUAGE,"ENGLISH");
//        talksLibraryPageSteps.filterSteps.applyFilters(FilterType.CATEGORY,"Design");
        talksLibraryPageSteps.getTalksLinks();
        //assertThatTalksCardShown
       // talksLibraryPageSteps.assertThatFiltersResultsApplied();
    }

}
