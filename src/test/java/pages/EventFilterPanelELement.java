package pages;

import org.openqa.selenium.By;
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
    @FindBy(id = "filter_category")
    public WebElement CATEGORY_FIELD;
    @FindBy(id = "filter_language")
    public WebElement LAUNGUAGE_FIELD;

    @FindBy(xpath = "//div[contains(@class,'evnt-filter-menu') and preceding-sibling::div[@id='filter_location']]//input[@type='text']")
    public WebElement LOCATION_INPUT_FIELD;

    @FindBy(xpath = "//div[contains(@class,'evnt-filter-menu') and preceding-sibling::div[@id='filter_location']]//div[@class='evnt-filter-item']//div[@class='evnt-filter-item-collapse']//div[@class='evnt-checkbox form-check']")
    public WebElement LOCATION_FOUND_CHECKBOX;

    @FindBy(xpath = "//div[contains(@class,'evnt-filter-menu') and preceding-sibling::div[@id='filter_category']]//div[@class='evnt-filter-item']//label[@data-value='Design']")
    public WebElement DESIGN_GROUP_ITEM;

    public String CATEGORIES_GROUP_ITEM_PATTERN = "//div[contains(@class,'evnt-filter-menu') and preceding-sibling::div[@id='filter_category']]//div[@class='evnt-filter-item']//label[@data-value='%VALUE%']";

    public String LAUNGUAGE_ITEM_PATTERN = "//div[contains(@class,'evnt-filter-menu') and preceding-sibling::div[@id='filter_language']]//div[@class='evnt-filter-item']//label[@data-value='%VALUE%']";

    @FindBy(xpath = "//div[contains(@class,'evnt-filter-menu') and preceding-sibling::div[@id='filter_language']]//div[@class='evnt-filter-item']//label[@data-value='ENGLISH']")
    public WebElement ENGLISH_ITEM;


    @FindBy(xpath = "//div[contains(@class,'evnt-toggle-filters-button')]")
    public WebElement EVENT_TOOGLE_BUTTON;

    public By LOADER = By.cssSelector("div.evnt-global-loader");


}
