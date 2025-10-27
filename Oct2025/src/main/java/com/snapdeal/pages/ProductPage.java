package com.snapdeal.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private CartPage cart;

    private By addToCartButton = By.xpath("//span[@class='intialtext' and text()='add to cart']");
    private By cartIcon = By.cssSelector("i.sd-icon.sd-icon-cart-icon-white-2");
    private By pincodeInput = By.id("pincode_value");
    private By pincodeSubmit = By.xpath("//button[contains(text(),'Check') or contains(text(),'Submit')]");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        autoFillPincode("600119");
    }

    private void autoFillPincode(String pincode) {
        try {
            WebElement pinInput = wait.until(ExpectedConditions.visibilityOfElementLocated(pincodeInput));
            pinInput.clear();
            pinInput.sendKeys(pincode);
            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(pincodeSubmit));
            submitBtn.click();
        } catch (Exception e) {
            // No popup → ignore
        }
    }

    public void addToCart() {
        try {
            WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
        } catch (Exception e) {
            System.out.println("Add to Cart failed: " + e.getMessage());
        }
    }

    public CartPage goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        System.out.println("✅ Cart icon clicked → Navigating to cart page");
        return new CartPage(driver);
    }

}
