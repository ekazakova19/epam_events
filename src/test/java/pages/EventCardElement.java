package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class EventCardElement {

    private WebDriver driver;
    public WebElement cardWebElement;

    @FindBy(xpath = "//div[@class='evnt-card-heading']//div[contains(@class,'online-cell') and following-sibling::div[contains(@class,'language-cell')]]")
    public WebElement LOCATION;
    @FindBy(xpath = "//div[@class='evnt-card-heading']//div[contains(@class,'language-cell') and preceding-sibling::div[contains(@class,'online-cell')]]")
    public WebElement LAUNGUAGE;

    @FindBy(xpath = "//div[@class='evnt-card-body' and preceding-sibling::div[@class='evnt-card-heading']]//div[@class='evnt-event-name' and following-sibling::div[@class='evnt-event-dates']]")
    public WebElement EVENT_NAME;
    @FindBy(xpath = "//div[@class='evnt-card-body' and preceding-sibling::div[@class='evnt-card-heading']]//div[@class='evnt-event-dates' and preceding-sibling::div[@class='evnt-event-name']]//span[@class='date' and following-sibling::span[contains(@class,status)]]")
    public WebElement EVENT_DATE;

    @FindBy(xpath = "//div[@class='evnt-card-body' and preceding-sibling::div[@class='evnt-card-heading']]//div[@class='evnt-event-dates' and preceding-sibling::div[@class='evnt-event-name']]//span[contains(@class,'status') and preceding-sibling::span[@class='date']]")
    public WebElement EVENT_STATUS;

    @FindBy(xpath = "//div[@class='evnt-card-footer' and preceding-sibling::div[@class='evnt-card-body']]//div[@class='evnt-speaker']")
    public List<WebElement> EVENT_SPEAKERS_LIST;

    public EventCardElement(WebDriver driver, WebElement cardWebElement) {
        this.driver=driver;
        this.cardWebElement = cardWebElement;
        PageFactory.initElements(driver,this);
    }



}
