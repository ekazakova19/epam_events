package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum Drivers {
    CHROME ("chrome"),
    FIREFOX("firefox");

    private String browser;

    Drivers(String browser) {
        this.browser = browser;
    }

    public String getBrowserType(){
        return browser;
    }

    public WebDriver createDriver(){
        switch (this.getBrowserType()){
            case "chrome" :
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "firefox" :
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            default :
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }
}
