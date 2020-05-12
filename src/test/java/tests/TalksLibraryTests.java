package tests;

import helpers.TestResultExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import testSteps.FilterType;
import testSteps.MainPageSteps;
import testSteps.TalksLibraryPageSteps;

@ExtendWith(TestResultExtension.class)
public class TalksLibraryTests extends BaseTest {

    MainPageSteps mainPageSteps;
    TalksLibraryPageSteps talksLibraryPageSteps;

    @BeforeEach
    public void initSteps(){
        mainPageSteps = new MainPageSteps(driver);
        talksLibraryPageSteps = new TalksLibraryPageSteps(driver);
    }

    @Test
    @DisplayName("Check talks filtering by several criteria ")
    public void checkTalksFilterTest() {
        mainPageSteps.navigateToTalksLibrary();
        talksLibraryPageSteps.filterSteps.clickOnMoreFilters();
        talksLibraryPageSteps.filterSteps.applyFilters(FilterType.LOCATION,"Belarus");
        talksLibraryPageSteps.filterSteps.applyFilters(FilterType.LANGUAGE,"ENGLISH");
        talksLibraryPageSteps.filterSteps.applyFilters(FilterType.CATEGORY,"Design");
        talksLibraryPageSteps.assertThatTalksCardShown();
        talksLibraryPageSteps.assertFilterResultForAll();
    }

    @Test
    @DisplayName("Check talks search")
    public void checkTalksSearch(){
        mainPageSteps.navigateToTalksLibrary();
        talksLibraryPageSteps.filterSteps.searchTalk("Azure");
        talksLibraryPageSteps.assertThatTalksCardShown();
        talksLibraryPageSteps.assertSearchResults("Azure");
    }
}
