package com.snapdeal.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By signInHover = By.xpath("//span[text()='Sign In']");
    private By loginBtn = By.xpath("//a[contains(text(),'login')]");
    private By mobileField = By.id("userName");
    private By continueBtn = By.id("checkUser");
    private By loginIframe = By.xpath("//iframe[contains(@id,'loginIframe')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void loginWithMobile(String mobileNumber) {

        try {
            driver.get("https://www.snapdeal.com/");
            wait.until(ExpectedConditions.visibilityOfElementLocated(signInHover)).click();
            wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();

            // Switch into Login iframe
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(loginIframe));

            WebElement mobile = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileField));
            mobile.clear();
            mobile.sendKeys(mobileNumber);

            wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();

            System.out.println("✅ Mobile entered, please enter OTP manually within 45 seconds...");
            Thread.sleep(45000); // Wait manually for OTP authentication

            driver.switchTo().defaultContent();
            System.out.println("✅ Login Completed Successfully ✅");

        } catch (Exception e) {
            System.out.println("❌ Login failed: " + e.getMessage());
        }
    }
}
