package testSteps;


import helpers.LocatorsHelper;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EventFilterPanelELement;
import pages.TalkPage;

import java.util.HashMap;
import java.util.Map;

public class FilterSteps {

    private WebDriver driver;
    private WebDriverWait wait;
    EventFilterPanelELement eventFilterPanelELement;
    Map<FilterType,String> appliedFilters = new HashMap<>();
    TalkPage talkPage;

    public FilterSteps(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,5);
        eventFilterPanelELement = new EventFilterPanelELement(driver);
        talkPage = new TalkPage(driver);
    }

    public void clickOnMoreFilters(){
        eventFilterPanelELement.EVENT_TOOGLE_BUTTON.click();
    }

    public void applyFilters(FilterType filterType, String value) {
        switch (filterType.getTypeAsString().toUpperCase()){
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
    }

    private void filterByLocation(String value){
        eventFilterPanelELement.LOCATION_FIELD.click();
        wait.until(ExpectedConditions.visibilityOf(eventFilterPanelELement.LOCATION_INPUT_FIELD));
        eventFilterPanelELement.LOCATION_INPUT_FIELD.sendKeys(value);
        Assertions.assertTrue(eventFilterPanelELement.LOCATION_FOUND_CHECKBOX.getText().equalsIgnoreCase(value));
        eventFilterPanelELement.LOCATION_FOUND_CHECKBOX.click();
        waitUntilFilterApply();
        eventFilterPanelELement.LOCATION_FIELD.click();
    }

    private void filterByCategory(String value){
        eventFilterPanelELement.CATEGORY_FIELD.click();
        driver.findElement(LocatorsHelper.generateXpathLocatorForItem(eventFilterPanelELement.CATEGORIES_GROUP_ITEM_PATTERN,value)).click();
        waitUntilFilterApply();
        eventFilterPanelELement.CATEGORY_FIELD.click();
    }

    private void filterByLanguage(String value){
        eventFilterPanelELement.LAUNGUAGE_FIELD.click();
        driver.findElement(LocatorsHelper.generateXpathLocatorForItem(eventFilterPanelELement.LAUNGUAGE_ITEM_PATTERN,value)).click();
        waitUntilFilterApply();
        eventFilterPanelELement.LAUNGUAGE_FIELD.click();
    }

    private void waitUntilFilterApply(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(eventFilterPanelELement.LOADER));
    }

    public void searchTalk(String value){
        eventFilterPanelELement.SEARCH_FIELD.sendKeys(value);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(eventFilterPanelELement.LOADER));
    }

}
