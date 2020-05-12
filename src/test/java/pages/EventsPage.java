package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class EventsPage {

    private WebDriver driver;

    public static final String EVENTS_PAGE_URL ="https://events.epam.com/events";

    @FindBy(xpath = "//span[contains(text(),'Upcoming Events')]")
    public WebElement UPCOMING_EVENTS_NAV_LINK;

    @FindBy(xpath = "//span[contains(text(),'Past Events')]")
    public WebElement PAST_EVENTS_NAV_LINK;

    @FindBy(xpath = "//a[@class='evnt-tab-link nav-link active' and .//span[contains(text(),'Upcoming Events')]]")
    public WebElement UPCOMING_EVENTS_ACTIVE;

    @FindBy(xpath = "//a[@class='evnt-tab-link nav-link active' and .//span[contains(text(),'Past Events')]]")
    public WebElement PAST_EVENTS_ACTIVE;

    @FindBy(xpath = "//a[.//span[contains(text(),'Upcoming Events')]]/span[contains(@class,'evnt-tab-counter')]")
    public WebElement UPCOMING_EVENT_COUNTER;

    @FindBy(xpath = "//a[.//span[contains(text(),'Past Events')]]/span[contains(@class,'evnt-tab-counter')]")
    public WebElement PAST_EVENT_COUNTER;

    @FindBy(css="div.evnt-event-card")
    public List<WebElement> ALL_EVENTS_LIST;

    @FindBy(xpath="//div[@class='evnt-cards-container' and .//h3[text()='This week']]//div[contains(@class,'evnt-events-column')]")
    public List<WebElement> THIS_WEEK_EVENTS_LIST;

    public By LOADER = By.cssSelector("div.evnt-global-loader");

    public EventFilterPanelELement eventFilterPanelElement;

    public List<EventCardElement> eventCardElements = new ArrayList<>();

    public EventsPage(WebDriver driver) {
        this.driver = driver;
        eventFilterPanelElement = new EventFilterPanelELement(driver);
        PageFactory.initElements(driver,this);
    }

    public int getDisplayedEventCount(){
        return ALL_EVENTS_LIST.size();
    }

    public void initEventCardElementsList(List<WebElement> eventList){
        for(WebElement element : eventList){
            eventCardElements.add(new EventCardElement(driver,element));
        }
    }

}
