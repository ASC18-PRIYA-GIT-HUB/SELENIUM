package com.snapdeal.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.snapdeal.utilities.ExtentReportManager;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extent;

    @BeforeSuite
    public void initReport() {
        extent = ExtentReportManager.getExtentReports();
    }

    @BeforeClass
    public void setupDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // ✅ Run in headless mode
        options.addArguments("--disable-gpu"); // ✅ Disable GPU rendering
        options.addArguments("--window-size=1920,1080"); // ✅ Set screen size
        options.addArguments("--no-sandbox"); // ✅ Recommended for Jenkins
        options.addArguments("--disable-dev-shm-usage"); // ✅ Prevent memory issues

        driver = new ChromeDriver(options);
        System.out.println("✅ Headless Chrome launched");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
        if (extent != null) extent.flush();
    }
}
