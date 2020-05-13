package pages;

import org.openqa.selenium.WebDriver;

public class MainPage {

    public static final String MAIN_PAGE_URL = "https://events.epam.com";
    public HeaderNavBarElement headerNavBarElement;

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        headerNavBarElement = new HeaderNavBarElement(driver);
    }
}
