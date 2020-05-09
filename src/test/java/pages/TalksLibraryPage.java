package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TalksLibraryPage {
    private WebDriver driver;
    EventFilterPanelELement eventFilterPanelELement;

    @FindBy(css = "div.evnt-talk-card")
    public List<WebElement> talksCardsList;

    public TalksLibraryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        eventFilterPanelELement = new EventFilterPanelELement(driver);
    }


}
