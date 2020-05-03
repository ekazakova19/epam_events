package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EventsPage {

    WebDriver driver;

    public String eventsPageURL ="https://events.epam.com/events";

    @FindBy(xpath = "//span[contains(text(),'Upcoming Events')]")
    public WebElement UPCOMING_EVENTS_NAV_LINK;

    @FindBy(xpath = "//a[@class='evnt-tab-link nav-link active' and .//span[contains(text(),'Upcoming Events')]]")
    public WebElement UPCOMING_EVENTS_ACTIVE;

    @FindBy(xpath = "//span[contains(text(),'Past Events')")
    public WebElement PAST_EVENTS_NAV_LINK;

    @FindBy(xpath = "//a[.//span[contains(text(),'Upcoming Events')]]/span[contains(@class,'evnt-tab-counter')]")
    public WebElement UPCOMING_EVENT_COUNTER;

    @FindBy(css="div.evnt-events-column")
    List<WebElement> eventCardsList;


    List<EventCardElement> eventCardElements;

    public EventsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    void initListEventCardElement(){

    }

    public int getDisplayedEventCount(){
        return eventCardsList.size();
    }


}
