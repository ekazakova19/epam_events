package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EventsPage {

    private WebDriver driver;

    public static final String EVENTS_PAGE_URL ="https://events.epam.com/events";

    @FindBy(xpath = "//span[contains(text(),'Upcoming Events')]")
    public WebElement UPCOMING_EVENTS_NAV_LINK;

    @FindBy(xpath = "//a[@class='evnt-tab-link nav-link active' and .//span[contains(text(),'Upcoming Events')]]")
    public WebElement UPCOMING_EVENTS_ACTIVE;

    @FindBy(xpath = "//span[contains(text(),'Past Events')")
    public WebElement PAST_EVENTS_NAV_LINK;

    @FindBy(xpath = "//a[.//span[contains(text(),'Upcoming Events')]]/span[contains(@class,'evnt-tab-counter')]")
    public WebElement UPCOMING_EVENT_COUNTER;

    @FindBy(css="div.evnt-events-column")
    public List<WebElement> EVENT_CARD_WEBELEMENT;


    public List<EventCardElement> eventCardElements ;

    public EventsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public int getDisplayedEventCount(){
        return EVENT_CARD_WEBELEMENT.size();
    }

    public void initEventCardElementsList(){
        for(WebElement element : EVENT_CARD_WEBELEMENT){
            eventCardElements.add(new EventCardElement(driver,element));
        }
        System.out.println("eventCardElements initialized "+eventCardElements.size());
    }

}
