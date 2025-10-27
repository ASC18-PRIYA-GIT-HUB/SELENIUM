package com.snapdeal.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Updated locators
    private By mobileInput = By.name("userName"); 
    private By continueButton = By.xpath("//button[contains(text(),'CONTINUE')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Login method with manual OTP
    public void loginWithMobile(String mobileNumber) {
        try {
            WebElement mobile = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileInput));
            mobile.clear();
            mobile.sendKeys(mobileNumber);

            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
            continueBtn.click();

            System.out.println("✅ Mobile entered and Continue clicked. Enter OTP manually.");

            // Pause for manual OTP input
            Thread.sleep(60000); // 60 seconds to enter OTP manually
            System.out.println("✅ Continue manually after OTP entry.");
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }
}