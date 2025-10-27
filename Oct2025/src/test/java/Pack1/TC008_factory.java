package Pack1;
 
import org.testng.annotations.Test;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;
 
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
 
import java.time.Duration;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
 
public class TC008_factory {
	WebDriver driver;
	@Test
	public void test2()
	{
		System.out.println("this is test2");
	}
	public void test3()
	{
		System.out.println("This is test3");
	}

  @Test(dataProvider = "logindata")
  public void f(String uname, String pword) {
	  System.out.println("This is the Test");
	  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	  login_pagefactory obj=PageFactory.initElements(driver, login_pagefactory.class);
	  obj.enterusername(uname);
	  obj.enterpassword(pword);
	  obj.clickonsubmit();
	 if( obj.dashboarddisplayed())
	 {
			assertTrue(true,"dashboard is dispalyed");
			}
			else
			{
				assertFalse(true,"dashboard is not dispalyed");
			}
	 
	  driver.findElement(By.name("username")).sendKeys(uname);
		driver.findElement(By.name("password")).sendKeys(pword);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		WebElement dashborad=driver.findElement(By.xpath("//h6[text()='Dashboard']"));
		if(dashborad.isDisplayed())
		{
		assertTrue(true,"dashboard is dispalyed");
		}
		else
		{
			assertFalse(true,"dashboard is not dispalyed");
		}


	}
  @Parameters("browser")
  @BeforeMethod
  public void beforeMethod(String browser) {
	  System.out.println("This is before method");
	  if(browser.equals("chrome"))
	  {
	  WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	  }
	  else if(browser.equals("edge"))
	  {
	  WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
}
	  else if(browser.equals("firefox"))
	  {
	  WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
} 
  }

 
  @AfterMethod
  public void afterMethod() {
	  System.out.println("This is after method");
	  driver.quit();
  }

 
 
  @DataProvider
  public Object[][] logindata() {
    return new Object[][] {
      new Object[] { "Admin", "admin123" },
      new Object[] { "Admin", "admin123" },
    };
  }
  @BeforeClass
  public void beforeClass() {
	  System.out.println("This is before class");
  }
 
  @AfterClass
  public void afterClass() {
	  System.out.println("This is after class");
  }
 
  @BeforeTest
  public void beforeTest() {
	  System.out.println("This is before test");
  }
 
  @AfterTest
  public void afterTest() {
	  System.out.println("This is after test");
  }
 
  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("This is before suite");
  }
 
  @AfterSuite
  public void afterSuite() {
	  System.out.println("This is after suite");
  }
 
}