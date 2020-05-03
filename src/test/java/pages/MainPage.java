package pages;

import org.openqa.selenium.WebDriver;

public class MainPage {
    WebDriver driver;
    public String mainPageUrl = "https://events.epam.com";

    public HeaderNavBarElement headerNavBarElement;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        headerNavBarElement = new HeaderNavBarElement(driver);
    }
}
