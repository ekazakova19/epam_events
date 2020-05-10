package helpers;

import org.openqa.selenium.By;

public class LocatorsHelper {

    public static By generateXpathLocatorForItem(String pattern, String itemValue){
        String generatedLocator = pattern.replace("%VALUE%",itemValue);
        return By.xpath(generatedLocator) ;
    }
}
