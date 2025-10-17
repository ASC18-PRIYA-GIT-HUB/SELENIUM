package Pack1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class LAB6 {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://tutorialsninja.com/demo/");
        System.out.println("Opened TutorialNinja demo site.");

        String title = driver.getTitle();
        if (title.equals("Your Store")) {
            System.out.println("Title matched: " + title);
        } else {
            System.out.println("Title mismatch! Found: " + title);
        }

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys("priya1760502875195@gmail.com"); 
        driver.findElement(By.id("input-password")).sendKeys("P@ssw0rd");       
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        System.out.println("Logged in successfully.");

        driver.findElement(By.linkText("Components")).click();
        driver.findElement(By.linkText("Monitors (2)")).click();
        System.out.println("Opened 'Monitors' section under Components.");
        Select showDropdown = new Select(driver.findElement(By.id("input-limit")));
        showDropdown.selectByVisibleText("25");
        System.out.println("Selected '25' from Show dropdown.");

        Thread.sleep(10);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300)");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement addToCartButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("(//button[.//span[contains(text(),'Add to Cart')]])[1]"))
        );
        addToCartButton.click();
        System.out.println("Clicked 'Add to Cart' on first item (Apple Cinema 30”).");

        driver.findElement(By.xpath("//a[text()='Specification']")).click();
        System.out.println("Opened 'Specification' tab.");

        WebElement specTable = driver.findElement(By.xpath("//table[contains(@class,'table-bordered')]"));
        if (specTable.isDisplayed()) {
            System.out.println("Specification details are visible.");
        } else {
            System.out.println("Specification details not visible.");
        }

        driver.findElement(By.xpath("(//button[@data-original-title='Add to Wish List'])[1]")).click();
        WebElement wishMsg = driver.findElement(By.cssSelector(".alert-success"));
        System.out.println("Wishlist message: " + wishMsg.getText());

        if (wishMsg.getText().contains("Success: You have added Apple Cinema 30")) {
            System.out.println("Apple Cinema 30 added to wishlist successfully.");
        } else {
            System.out.println("Wishlist message not matched.");
        }

        driver.findElement(By.name("search")).clear();
        driver.findElement(By.name("search")).sendKeys("Mobile");
        driver.findElement(By.cssSelector(".btn-default.btn-lg")).click();
        System.out.println("Searched for 'Mobile'.");

        driver.findElement(By.name("description")).click();
        driver.findElement(By.id("button-search")).click();
        System.out.println("Enabled 'Search in product descriptions' and searched again.");

        driver.findElement(By.linkText("HTC Touch HD")).click();
        System.out.println("Opened 'HTC Touch HD' product page.");

        WebElement qty = driver.findElement(By.id("input-quantity"));
        qty.clear();
        qty.sendKeys("3");
        System.out.println("Updated quantity to 3.");

        driver.findElement(By.id("button-cart")).click();
        WebElement successMsg = driver.findElement(By.cssSelector(".alert-success"));
        System.out.println("Add to Cart message: " + successMsg.getText());

        if (successMsg.getText().contains("Success: You have added HTC Touch HD")) {
            System.out.println("HTC Touch HD added to cart successfully.");
        } else {
            System.out.println("Cart message not matched.");
        }

        driver.findElement(By.id("cart")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//strong[contains(.,'View Cart')]")).click();
        System.out.println("Opened 'View Cart'.");

        WebElement cartItem = driver.findElement(By.linkText("HTC Touch HD"));
        if (cartItem.isDisplayed()) {
            System.out.println("HTC Touch HD is present in the cart.");
        } else {
            System.out.println("HTC Touch HD not found in the cart.");
        }

        driver.findElement(By.linkText("Checkout")).click();
        System.out.println("Clicked on 'Checkout' button.");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Logout")).click();
        System.out.println("Clicked on 'Logout'.");

        WebElement logoutHeading = driver.findElement(By.xpath("//h1[text()='Account Logout']"));
        if (logoutHeading.isDisplayed()) {
            System.out.println("Account Logout verified successfully.");
        } else {
            System.out.println("Account Logout heading not found.");
        }

        driver.findElement(By.linkText("Continue")).click();
        System.out.println("Clicked 'Continue' after logout.");

        System.out.println("LAB6 test completed successfully!");

        //driver.quit();
    }
}
