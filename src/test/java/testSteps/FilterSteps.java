package testSteps;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EventFilterPanelELement;

import java.util.HashMap;
import java.util.Map;

public class FilterSteps {

    WebDriver driver;
    WebDriverWait wait;
    EventFilterPanelELement eventFilterPanelELement;
    Map<FilterType,String> appliedFilters = new HashMap<>();

    public FilterSteps(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,5);
        eventFilterPanelELement = new EventFilterPanelELement(driver);
    }

    public void clickOnMoreFilters(){
        eventFilterPanelELement.EVENT_TOOGLE_BUTTON.click();
    }

    public void applyFilters(FilterType filterType, String value) {
        switch (filterType.getType().toUpperCase()){
                case "LOCATION" :
                    filterByLocation(value);
                    break;
                case "CATEGORY" :
                    filterByCategory(value);
                    break;
                case "LANGUAGE" :
                    filterByLanguage(value);
                    break;
            }
          appliedFilters.put(filterType,value);
//        for (Map.Entry<String, String> entry : appliedFilters.entrySet()) {
//            System.out.println(entry.getKey() + " = " + entry.getValue());

//        }
    }
    public void filterByLocation(String value){
        eventFilterPanelELement.LOCATION_FIELD.click();
        wait.until(ExpectedConditions.visibilityOf(eventFilterPanelELement.LOCATION_INPUT_FIELD));
        eventFilterPanelELement.LOCATION_INPUT_FIELD.sendKeys(value);
        Assertions.assertTrue(eventFilterPanelELement.LOCATION_FOUND_CHECKBOX.getText().equalsIgnoreCase(value));
        eventFilterPanelELement.LOCATION_FOUND_CHECKBOX.click();
        waitUntilFilterApply();
        eventFilterPanelELement.LOCATION_FIELD.click();
    }

    public void filterByCategory(String value){ ;
        eventFilterPanelELement.CATEGORY_FIELD.click();
        driver.findElement(generateXpathLocatorForItem(eventFilterPanelELement.CATEGORIES_GROUP_ITEM_PATTERN,value)).click();
        waitUntilFilterApply();
        eventFilterPanelELement.CATEGORY_FIELD.click();
    }

    public void filterByLanguage(String value){
        eventFilterPanelELement.LAUNGUAGE_FIELD.click();
        driver.findElement(generateXpathLocatorForItem(eventFilterPanelELement.LAUNGUAGE_ITEM_PATTERN,value)).click();
        waitUntilFilterApply();
        eventFilterPanelELement.LAUNGUAGE_FIELD.click();
    }

    public void waitUntilFilterApply(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(eventFilterPanelELement.LOADER));
    }
    public By generateXpathLocatorForItem(String pattern,String itemValue){
        String generatedLocator = pattern.replace("%VALUE%",itemValue);
        return By.xpath(generatedLocator) ;
    }

}
