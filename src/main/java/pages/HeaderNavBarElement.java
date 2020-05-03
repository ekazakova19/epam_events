package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderNavBarElement {
    WebDriver driver;

    @FindBy(css = "div.evnt-platform-header a[href=\"/events\"]")
    public WebElement EVENTS_LINK;

    public HeaderNavBarElement(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
