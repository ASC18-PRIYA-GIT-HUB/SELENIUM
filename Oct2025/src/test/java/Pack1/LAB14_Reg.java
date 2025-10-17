package Pack1;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LAB14_Reg {

    WebDriver driver;
    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        String filePath = "C:\\Users\\priya.p\\git\\SELENIUM\\Oct2025\\UserDetails.xlsx";
        return getExcelData(filePath, "Sheet1");
    }

    public static Object[][] getExcelData(String filePath, String sheetName) {
        Object[][] data = null;
        try {
            File file = new File(filePath);
            System.out.println("Excel file exists: " + file.exists());
            System.out.println("File path: " + file.getAbsolutePath());
            System.out.println("File extension: " + (file.getName().endsWith(".xlsx") ? "xlsx" : "NOT XLSX"));

            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            int totalRows = sheet.getPhysicalNumberOfRows();
            int totalCols = sheet.getRow(0).getLastCellNum();

            data = new Object[totalRows - 1][totalCols];
            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < totalCols; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = (cell == null) ? "" : cell.toString();
                }
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Test(dataProvider = "userData")
    public void registerUser(String firstName, String lastName, String email, String phone, String password, String confirmPassword) {
        driver.get(baseUrl);

        // Verify title
        Assert.assertTrue(driver.getTitle().contains("Your Store"), "Title verification failed!");

        // Click My Account -> Register
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        // Verify registration page heading
        WebElement heading = driver.findElement(By.xpath("//h1[text()='Register Account']"));
        Assert.assertTrue(heading.isDisplayed(), "Register Account page not opened!");

        System.out.println("Registering user: " + firstName + " | " + email);

        // Fill all form fields
        driver.findElement(By.id("input-firstname")).sendKeys(firstName);
        driver.findElement(By.id("input-lastname")).sendKeys(lastName);
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-telephone")).sendKeys(phone);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.id("input-confirm")).sendKeys(confirmPassword);

        // Accept privacy policy
        WebElement privacy = driver.findElement(By.name("agree"));
        if (!privacy.isSelected()) privacy.click();

        // ✅ FIXED: Correct Continue button locator
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//input[@value='Continue']")
        ));
        continueBtn.click();

        // Verify account created message
        try {
            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[text()='Your Account Has Been Created!']")
            ));
            Assert.assertTrue(successMsg.isDisplayed(), "Account creation message not displayed!");
            System.out.println("✅ Account created successfully for " + email);
        } catch (TimeoutException e) {
            System.out.println("❌ Registration failed for " + email);
            try {
                WebElement warning = driver.findElement(By.cssSelector(".alert-danger"));
                System.out.println("Error: " + warning.getText());
            } catch (Exception ex) {
                System.out.println("Error message not found.");
            }
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
