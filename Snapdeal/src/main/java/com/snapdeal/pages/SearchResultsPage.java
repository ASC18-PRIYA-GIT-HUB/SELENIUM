package com.snapdeal.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class SearchResultsPage {

    WebDriver driver;
    WebDriverWait wait;
    By sortDropdown = By.xpath("//div[@class='sort-selected']");
    By sortLowToHigh = By.xpath("//li[@data-sorttype='plth']");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void sortByLowPrice() {
        WebElement drop = wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", drop);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", drop);

        WebElement lowToHigh = wait.until(ExpectedConditions.elementToBeClickable(sortLowToHigh));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lowToHigh);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", lowToHigh);

        System.out.println("✅ Sorting applied: Price Low to High");
    }

    public void applyFilter(String filterType, String value) {
        // Example implementation: adjust locators based on Snapdeal filter UI
        By filterLocator = By.xpath("//div[contains(@class,'filters')]//label[contains(text(),'" + value + "')]");
        WebElement filterOption = wait.until(ExpectedConditions.elementToBeClickable(filterLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", filterOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", filterOption);
    }

    public ProductPage selectFirstProduct() {
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[contains(@class,'product-tuple')])[1]")));
        firstProduct.click();

        // Wait for new tab and switch
        String currentWindow = driver.getWindowHandle();
        for(String win : driver.getWindowHandles()) {
            if(!win.equals(currentWindow)) {
                driver.switchTo().window(win);
            }
        }
        System.out.println("✅ Switched to product tab");
        return new ProductPage(driver);
    }

}
