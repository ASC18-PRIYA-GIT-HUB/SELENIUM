package Pack1;
 
import org.testng.annotations.Test;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;
 
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
 
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
 
public class Revision {
	WebDriver driver;
	  String projectpath=System.getProperty("user.dir");
		
  @Test(dataProvider = "logindata")
  public void f(String uname, String pword) throws IOException {
	
	  System.out.println("This is the Test");
	  Properties prob=new Properties();
	  File propfile=new File(projectpath+"//data.properties");
	  FileInputStream fs=new FileInputStream(propfile);
	  prob.load(fs);
	  String url=prob.getProperty("url");
	  ExtentReports extent=new ExtentReports();
	  ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"//loginreport.html");
	  extent.attachReporter(spark);
	  ExtentTest test=extent.createTest("Verify the login");
	  
	  
	  driver.get(url);
	  
	  login_pageobjects obj=new login_pageobjects(driver);
	  obj.enterusername(uname);
	  obj.enterpassword(pword);
	  obj.clickonsubmit();
	 if( obj.dashboarddisplayed())
		
	 {
		 test.pass("Dashboard is Displayed");
			//assertTrue(true,"dashboard is dispalyed");
				
			}
			else
			{
				//assertFalse(true,"dashboard is not dispalyed");
				 test.fail("dashboard is not dispalyed");
			}
		extent.flush();
	 	  
	}
	  
 
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("This is before method");
	 	
	  WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
  }
  
 
  @AfterMethod
  public void afterMethod() {
	  System.out.println("This is after method");
	  driver.quit();
  }
  
  @DataProvider
  public Object[][] logindata() throws InvalidFormatException, IOException {
      File file1 = new File(projectpath + "\\data.xlsx");
      XSSFWorkbook workbook = new XSSFWorkbook(file1);
      XSSFSheet worksheet = workbook.getSheetAt(0);
      int rowcount = worksheet.getPhysicalNumberOfRows();
      System.out.println("rows:" + rowcount);

      // Create dynamic array (skip header if any)
      Object[][] data = new Object[rowcount - 1][2];

      for (int i = 1; i < rowcount; i++) { // Start from 1 (skip header)
          data[i - 1][0] = worksheet.getRow(i).getCell(0).getStringCellValue();
          data[i - 1][1] = worksheet.getRow(i).getCell(1).getStringCellValue();
      }

      workbook.close();
      return data;
  }

				
}
    
  
  
 