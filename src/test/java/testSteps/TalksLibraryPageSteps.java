package testSteps;

import helpers.LocatorsHelper;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.TalkPage;
import pages.TalksLibraryPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TalksLibraryPageSteps {
    private WebDriver driver;
    private TalksLibraryPage talksLibraryPage;
    private TalkPage talkPage;
    public FilterSteps filterSteps;
    private List<String> talksLinks = new ArrayList<>();

    public TalksLibraryPageSteps(WebDriver driver) {
        this.driver = driver;
        filterSteps = new FilterSteps(driver);
        talksLibraryPage = new TalksLibraryPage(driver);
        talkPage = new TalkPage(driver);
    }

    private void initTalksLinks() {
        for (WebElement talksCard : talksLibraryPage.talksCardsList) {
            String talkUrl = talksCard.findElement(By.cssSelector("a")).getAttribute("href");
            System.out.println(talkUrl);
            talksLinks.add(talkUrl);
        }
    }

    @Step("Assert that talk's cars is shown on the page")
    public void assertThatTalksCardShown() {
        Assertions.assertFalse(talksLibraryPage.talksCardsList.isEmpty(), "No one talks card is shown on the page");
    }

    public void assertFilterResultForAll(){
        initTalksLinks();
        for (String url : talksLinks) {
            driver.get(url);
            for (Map.Entry<FilterType, String> entry : filterSteps.appliedFilters.entrySet()) {
                switch (entry.getKey().getTypeAsString().toUpperCase()) {
                    case "LOCATION":
                        assertTalksLocation(entry.getValue());
                        break;
                    case "LANGUAGE":
                        assertTalksLanguage(entry.getValue());
                        break;
                    case "CATEGORY":
                        assertTalksCategory(entry.getValue());
                        break;
                    default:
                        Assertions.fail(String.format("Assert result for %s filter is not implemented",entry.getKey().getTypeAsString()));
                }
        }
        }
    }

    @Step("Assert that search results are correct")
    public void assertSearchResults(String searchValue){
        for (WebElement talksCard : talksLibraryPage.talksCardsList) {
            String eventName = talksCard.findElement(talksLibraryPage.EVENT_TALK_NAME).getText();
            Assertions.assertTrue(eventName.contains(searchValue),
                    String.format("Found talk not match with search criteria. Actual - %s, Expected -%s",eventName,searchValue));

        }
    }

    @Step("Assert that location filter applied")
    private void assertTalksLocation(String expectedValue) {
        String actualText = talkPage.LOCATION.getText();
        Assertions.assertTrue(actualText.contains(expectedValue),
                String.format("Location value does not match filter criteria. " +
                        "Expected location - %s , Actual Location - %s, Talk - %s ", expectedValue,actualText,driver.getCurrentUrl()));
    }
    @Step("Assert that language filter applied")
    private void assertTalksLanguage(String expectedValue){
        String actualLanguage = talkPage.LANGUAGE.getText();
        Assertions.assertTrue(actualLanguage.equalsIgnoreCase(expectedValue),
                String.format("Language value does not match filter criteria. Expected value - %s, Actual value - %s, Talk %s"
                        ,expectedValue,actualLanguage,driver.getCurrentUrl()));
    }
    @Step("Assert that category filter applied")
    private void assertTalksCategory(String expectedValue){
        By generatedLocator = LocatorsHelper.generateXpathLocatorForItem(talkPage.TOPIC_PATTERN,expectedValue);
        Assertions.assertTrue( driver.findElement(generatedLocator).isDisplayed()
                ,String.format("Topic value does not match filter criteria. Expected value - %s, Talk - %s",expectedValue,driver.getCurrentUrl()));
    }

}
