package com.snapdeal.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if(driver != null) driver.quit();
        if(extent != null) extent.flush();
    }
}
