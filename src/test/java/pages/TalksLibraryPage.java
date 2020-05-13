package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TalksLibraryPage {

    @FindBy(css = "div.evnt-talk-card")
    public List<WebElement> talksCardsList;

    public By EVENT_TALK_NAME = By.cssSelector("div.evnt-talk-name");

    private WebDriver driver;

    public TalksLibraryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
