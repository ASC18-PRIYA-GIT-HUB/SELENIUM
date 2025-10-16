package Pack1;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
public class LAB8 {
    private WebDriver driver;
    private WebDriverWait wait;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/index.php");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Reporter.log("Browser opened and navigated to the TutorialsNinja URL.");
    }
    @Test(priority = 1, description = "Automates the basic flow from Lab 3")
    public void lab3BasicFlowTest() {
        driver.findElement(By.linkText("Desktops")).click();
        Reporter.log("Clicked on the 'Desktops' tab.");
        WebElement macLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Mac (1)")));
        macLink.click();
        Reporter.log("Clicked on 'Mac' from the dropdown.");
        Select sortBy = new Select(driver.findElement(By.id("input-sort")));
        sortBy.selectByVisibleText("Name (A - Z)");
        Reporter.log("Selected 'Name (A - Z)' from the Sort By dropdown.");
        driver.findElement(By.xpath("//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']//button[1]")).click();
        Reporter.log("Clicked 'Add to Cart' for the iMac.");
        WebElement successAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
        Assert.assertTrue(successAlert.getText().contains("Success: You have added iMac to your shopping cart!"),
                "Success message for adding iMac to cart was not displayed.");
        Reporter.log("Verified that the product was successfully added to the cart.");
    }
    @Test(priority = 2, description = "Automates the validation flow from Lab 4")
    public void lab4ValidationTest() {
        String expectedTitle = "Your Store";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match.");
        Reporter.log("Verified the page title is '" + expectedTitle + "'.");
        driver.findElement(By.linkText("Desktops")).click();
        Reporter.log("Clicked on the 'Desktops' tab.");
        WebElement macLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Mac (1)")));
        macLink.click();
        Reporter.log("Clicked on 'Mac' from the dropdown.");
        String macHeading = driver.findElement(By.tagName("h2")).getText();
        Assert.assertEquals(macHeading, "Mac", "The page heading is not 'Mac'.");
        Reporter.log("Verified the page heading is 'Mac'.");
        Select sortBy = new Select(driver.findElement(By.id("input-sort")));
        sortBy.selectByVisibleText("Name (A - Z)");
        Reporter.log("Selected 'Name (A - Z)' from the Sort By dropdown.");
        driver.findElement(By.xpath("//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']//button[1]")).click();
        WebElement successAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
        Assert.assertTrue(successAlert.getText().contains("Success: You have added iMac to your shopping cart!"));
        Reporter.log("Clicked 'Add to Cart' and verified the success message.");
        driver.findElement(By.name("search")).sendKeys("Mobile");
        driver.findElement(By.cssSelector("#search button")).click();
        Reporter.log("Searched for 'Mobile'.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Search - Mobile')]")));
        WebElement searchCriteriaInput = driver.findElement(By.id("input-search"));
        searchCriteriaInput.clear();
        Reporter.log("Cleared the search criteria text box.");
        searchCriteriaInput.sendKeys("Monitors");
        Reporter.log("Entered 'Monitors' into the search criteria text box.");
        driver.findElement(By.id("description")).click();
        Reporter.log("Checked the 'Search in product descriptions' checkbox.");
        driver.findElement(By.id("button-search")).click();
        Reporter.log("Clicked the search button to perform a new search.");
        WebElement newSearchHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Search - Monitors')]")));
        Assert.assertTrue(newSearchHeading.isDisplayed(), "Search results for 'Monitors' were not displayed.");
        Reporter.log("Verified that the search results page for 'Monitors' is displayed correctly.");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        Reporter.log("Browser closed.");
    }
}
 