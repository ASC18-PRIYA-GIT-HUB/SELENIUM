package Pack1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class tutorial_login_pageobjects {

    WebDriver driver;

    By myAccount = By.linkText("My Account");
    By loginLink = By.linkText("Login");
    By emailField = By.id("input-email");
    By passwordField = By.id("input-password");
    By loginButton = By.xpath("//input[@value='Login']");

    public tutorial_login_pageobjects(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnMyAccount() {
        driver.findElement(myAccount).click();
    }

    public void clickOnLogin() {
        driver.findElement(loginLink).click();
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
