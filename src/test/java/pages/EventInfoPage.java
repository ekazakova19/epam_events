package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EventInfoPage {

    @FindBy(css="div.evnt-main-container.evnt-event-page")
    public WebElement EVENT_PAGE_CONTAINER;

    @FindBy(css = "section[id=home] button.reg-button.attend")
    public WebElement REG_BUTTON;

    @FindBy(css = "div[id=agenda]")
    public WebElement EVENT_AGENDA;

    @FindBy(css = "div[id=agenda] li.evnt-day-tab")
    public WebElement EVENT_DAY;

    /* ISSUE - Element located by driver, but it always return isDisplayed=False, location = 0,0
     * Looks like it might be overlapped by other element
     */
    @FindBy(xpath="//*[@id='agenda']//div[@class='evnt-agenda-content-cell card-cell' and .//div[@id='13690']]/div[1]")
    public WebElement EVENT_TIME;

    @FindBy(css = "div[id=agenda] div.evnt-agenda-card.promo")
    public WebElement EVENT_CARD_PROMO;

    private WebDriver driver;

    public EventInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
