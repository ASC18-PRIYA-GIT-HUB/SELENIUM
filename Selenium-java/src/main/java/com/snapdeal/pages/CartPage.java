package com.snapdeal.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cartHeader = By.xpath("//h3[contains(text(),'Shopping Cart') or contains(text(),'cart')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isCartPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(cartHeader)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public int getCartItemCount() {
        try {
            return driver.findElements(By.xpath("//div[contains(@class,'cart-item')]")).size();
        } catch (Exception e) {
            return 0;
        }
    }
}