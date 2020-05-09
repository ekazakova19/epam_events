package testSteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.TalksLibraryPage;

import java.util.ArrayList;
import java.util.List;

public class TalksLibraryPageSteps {
    private WebDriver driver;
    private TalksLibraryPage talksLibraryPage;
    public  FilterSteps filterSteps;
    List<String> talksLinks = new ArrayList<>();

    public TalksLibraryPageSteps(WebDriver driver) {
        this.driver = driver;
        filterSteps = new FilterSteps(driver);
        talksLibraryPage = new TalksLibraryPage(driver);
    }

    public void getTalksLinks(){
        for (WebElement talksCard : talksLibraryPage.talksCardsList) {
            String talkUrl = talksCard.findElement(By.cssSelector("a")).getAttribute("href");
            System.out.println(talkUrl);
            talksLinks.add(talkUrl);
        }
    }
    public void assertThatFiltersResultsApplied(){


    }
}
