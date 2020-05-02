package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EventsPage {

    WebDriver driver;

    @FindBy(css = "")
    WebElement UPCOMING_EVENTS_NAV_LINK;

    List<EventCardElement> eventCardElements;

}
