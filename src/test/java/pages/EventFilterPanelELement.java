package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EventFilterPanelELement {
    private WebDriver driver;

    public EventFilterPanelELement(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "filter_location")
    public WebElement LOCATION_FIELD;

    @FindBy(xpath = "//div[contains(@class,'evnt-filter-menu') and preceding-sibling::div[@id='filter_location']]//input[@type='text']")
    public WebElement LOCATION_INPUT_FIELD;

    @FindBy(xpath = "//div[contains(@class,'evnt-filter-menu') and preceding-sibling::div[@id='filter_location']]//div[@class='evnt-filter-item']//div[@class='evnt-filter-item-collapse']//div[@class='evnt-checkbox form-check']")
    public WebElement FOUND_CHECKBOX;
}
