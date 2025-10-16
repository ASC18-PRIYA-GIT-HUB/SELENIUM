package Pack1;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LAB9 {

    private WebDriver driver;
    private WebDriverWait wait;
    private String browser = "chrome"; 

    @BeforeEach
    public void setUp() {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            System.out.println("Launching Chrome browser...");
        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
            System.out.println("Launching Internet Explorer...");
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/index.php");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("Browser opened and navigated to TutorialsNinja site.");
    }

    @Test
    @Order(1)
    @DisplayName("Lab 3 - Basic Flow Test")
    public void lab3BasicFlowTest() {
        driver.findElement(By.linkText("Desktops")).click();
        System.out.println("Clicked on 'Desktops' tab.");

        WebElement macLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Mac (1)")));
        macLink.click();
        System.out.println("Clicked on 'Mac' option.");

        Select sortBy = new Select(driver.findElement(By.id("input-sort")));
        sortBy.selectByVisibleText("Name (A - Z)");
        System.out.println("Selected 'Name (A - Z)' from Sort dropdown.");

        driver.findElement(By.xpath("//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']//button[1]")).click();
        System.out.println("Clicked 'Add to Cart' for iMac.");

        WebElement successAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
        Assertions.assertTrue(successAlert.getText().contains("Success: You have added iMac to your shopping cart!"),
                "Success message for iMac was not displayed.");
        System.out.println("Verified success message for iMac.");
    }

    @Test
    @Order(2)
    @DisplayName("Lab 4 - Validation Flow Test")
    public void lab4ValidationTest() {
        String expectedTitle = "Your Store";
        String actualTitle = driver.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle, "Page title does not match!");
        System.out.println("Page title verified: " + actualTitle);

        driver.findElement(By.linkText("Desktops")).click();
        WebElement macLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Mac (1)")));
        macLink.click();

        String macHeading = driver.findElement(By.tagName("h2")).getText();
        Assertions.assertEquals("Mac", macHeading, "Page heading is not 'Mac'.");
        System.out.println("Verified page heading as 'Mac'.");

        Select sortBy = new Select(driver.findElement(By.id("input-sort")));
        sortBy.selectByVisibleText("Name (A - Z)");

        driver.findElement(By.xpath("//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']//button[1]")).click();
        WebElement successAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
        Assertions.assertTrue(successAlert.getText().contains("Success: You have added iMac to your shopping cart!"));
        System.out.println("iMac added to cart verified.");

        // Search operations
        driver.findElement(By.name("search")).sendKeys("Mobile");
        driver.findElement(By.cssSelector("#search button")).click();
        System.out.println("Searched for 'Mobile'.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Search - Mobile')]")));
        WebElement searchBox = driver.findElement(By.id("input-search"));
        searchBox.clear();
        searchBox.sendKeys("Monitors");
        driver.findElement(By.id("description")).click();
        driver.findElement(By.id("button-search")).click();

        WebElement newSearchHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Search - Monitors')]")));
        Assertions.assertTrue(newSearchHeading.isDisplayed(), "Search results for 'Monitors' not displayed.");
        System.out.println("Verified search results for 'Monitors'.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}
