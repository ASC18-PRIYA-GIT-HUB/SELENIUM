package com.snapdeal.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By addToCartButton = By.xpath("//span[@class='intialtext' and text()='add to cart']");
    private By cartIcon = By.cssSelector("i.sd-icon.sd-icon-cart-icon-white-2");
    private By pincodeField = By.xpath("//input[@placeholder='Enter your pincode' and @maxlength='6']");
    private By checkPincodeBtn = By.xpath("//button[contains(@class,'pincode-check')]");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void autoFillPincode(String pincode) {
        try {
            WebElement pinInput = wait.until(ExpectedConditions.visibilityOfElementLocated(pincodeField));
            pinInput.clear();
            pinInput.sendKeys(pincode);
            System.out.println("Pincode entered: " + pincode);

            WebElement checkBtn = wait.until(ExpectedConditions.elementToBeClickable(checkPincodeBtn));
            checkBtn.click();
            System.out.println("'Check' button clicked");
            wait.until(ExpectedConditions.invisibilityOf(checkBtn));
            System.out.println("Pincode validated successfully");

        } catch (Exception e) {
            System.out.println("Pincode validation skipped or popup not found: " + e.getMessage());
        }
    }

    public void addToCart() {
        try {
            WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
            System.out.println("Product added to cart");
        } catch (Exception e) {
            System.out.println("Add to Cart failed: " + e.getMessage());
        }
    }

    public CartPage goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        System.out.println("Navigating to Cart page.");
        return new CartPage(driver);
    }
}