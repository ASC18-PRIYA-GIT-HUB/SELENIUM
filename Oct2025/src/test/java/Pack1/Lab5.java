package Pack1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Lab5 {
	private static final char[] Confirm = null;

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/index.php?");
		driver.manage().window().maximize();
		String title=driver.getTitle();
		if(title.equals("Your Store"))
		{
			System.out.println("Title is matched");
		}
		else
		{
			System.out.println("Title is not matched");
		}
		driver.findElement(By.partialLinkText("Account")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.xpath("//input[@type='submit' and @value='Continue']")).click();
		String warning=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		System.out.println("Warning message is:"+warning);
		if(warning.equals("You must agree to the Privacy Policy!"))
		{
			System.out.println("warning is matched");
			
		}
		else
		{
			System.out.println("warning is not matched");
		}
		WebElement subs=driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']"));
		
		if(subs.isSelected())
		{
			System.out.println("yes is selected");
		}
		else
		{
			System.out.println("yes is not selected");
		}
		driver.findElement(By.partialLinkText("Account")).click();
        driver.findElement(By.linkText("Register")).click();
        WebElement firstName = driver.findElement(By.id("input-firstname"));
        String LFirstName = "Priiya"; 
        firstName.sendKeys(LFirstName);
        System.out.println("Entered 33 characters in First Name field.");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        if (driver.findElements(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]")).size() > 0) {
            WebElement firstNameError = driver.findElement(
                By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]"));
            System.out.println("First Name Error: " + firstNameError.getText());
        } else {
            System.out.println(" 33 characters accepted in First Name (no error message shown).");
        }
        WebElement lastName = driver.findElement(By.id("input-lastname"));
        String LLastName = "Palraj"; 
        lastName.sendKeys(LLastName);
        System.out.println("Entered 33 characters in Last Name field.");

        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        if (driver.findElements(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")).size() > 0) {
            WebElement lastNameError = driver.findElement(
                By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]"));
            System.out.println("Last Name Error: " + lastNameError.getText());
        } else {
            System.out.println("33 characters accepted in Last Name (no error message shown).");
        }
        String uniqueEmail = "priya" + System.currentTimeMillis() + "@gmail.com";
        WebElement email = driver.findElement(By.id("input-email"));
        email.sendKeys(uniqueEmail);
        System.out.println("Entered unique email: " + uniqueEmail);
        WebElement phone = driver.findElement(By.id("input-telephone"));
        phone.sendKeys("12345"); 
        System.out.println("Entered valid Telephone");

        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        if (driver.findElements(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")).size() > 0) {
            WebElement phoneError = driver.findElement(
                By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]"));
            System.out.println("Telephone Number Error: " + phoneError.getText());
        } else {
            System.out.println("Telephone Number accepted (no error message shown).");
        }
        
        WebElement password = driver.findElement(By.id("input-password"));
        password.sendKeys("P@ssw0rd"); 
        System.out.println("My Password.");

        WebElement confirmPassword = driver.findElement(By.id("input-confirm"));
        confirmPassword.sendKeys("P@ssw0rd");
        System.out.println("Confirm Password.");

        WebElement newsletterYes = driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']"));
        newsletterYes.click();
        System.out.println("Selected 'Yes' for Newsletter");

        WebElement privacyPolicy = driver.findElement(By.name("agree"));
        privacyPolicy.click();
        System.out.println("Checked 'I have read and agree to the Privacy Policy'.");

        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String successMessage = driver.findElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")).getText();

        if (successMessage.equals("Your Account Has Been Created!")) {
            System.out.println("Registration successful: " + successMessage);
        } else {
            System.out.println("Registration failed. Message found: " + successMessage);
        }
        driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();
        System.out.println("Clicked Continue after account creation.");

        driver.findElement(By.linkText("View your order history")).click();
        System.out.println("Opened 'View of your order history' under My Orders.");
	}
	

}
