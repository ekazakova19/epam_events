package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TalkPage {
    private WebDriver driver;

    @FindBy(css = "div.location")
    public WebElement LOCATION;
    @FindBy(css = "div.language")
    public WebElement LANGUAGE;

    public String TOPIC_PATTERN = "//div[contains(@class,'evnt-topic')]//label[contains(text(),'%VALUE%')]";


    public TalkPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
