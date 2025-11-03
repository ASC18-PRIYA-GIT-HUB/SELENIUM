package com.snapdeal.tests;

import com.snapdeal.base.BaseTest;
import com.snapdeal.utilities.ExtentManager;
import com.snapdeal.utilities.ScreenshotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class LoginSnapdeal extends BaseTest {

    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void startReport() {
        extent = ExtentManager.getInstance();
    }

    @Test(priority = 1)
    public void addItemAndViewCartTest() throws IOException {
        test = extent.createTest("Add Item & View Cart Test");
        WebDriverWait wait = new WebDriverWait(driver, 25);
        test.log(Status.INFO, "Launching Snapdeal app...");

        try {
            // Step 1: Skip login popup
            try {
                By[] skipLocators = {
                    By.id("com.snapdeal.main:id/tvLaterText"),
                    By.xpath("//android.widget.TextView[@text='Later']"),
                    By.xpath("//android.widget.TextView[@text='Skip']")
                };
                for (By skip : skipLocators) {
                    List<WebElement> btns = driver.findElements(skip);
                    if (!btns.isEmpty()) {
                        btns.get(0).click();
                        test.log(Status.INFO, "Skipped login popup.");
                        break;
                    }
                }
            } catch (Exception ignored) {}

            // Step 2: Click search icon
            WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("com.snapdeal.main:id/search_icon")));
            searchIcon.click();
            test.log(Status.PASS, "Clicked on search icon.");

            // Step 3: Wait for search box
            Thread.sleep(1500);
            WebElement searchBox = null;
            By[] searchBoxLocators = {
                By.id("com.snapdeal.main:id/et_search_view"),
                By.id("com.snapdeal.main:id/search_edit_text"),
                By.className("android.widget.EditText")
            };
            for (By locator : searchBoxLocators) {
                try {
                    searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                    break;
                } catch (Exception ignored) {}
            }

            if (searchBox == null) throw new AssertionError("Search box not found.");
            searchBox.sendKeys("Shoes");
            test.log(Status.INFO, "Entered 'Shoes' in search box.");
            driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));

            // Step 4: Wait for search results
            Thread.sleep(5000);
            test.log(Status.INFO, "Waiting for search results...");

            List<WebElement> products = driver.findElements(By.xpath(
                "//android.widget.TextView[contains(@text,'Rs') or contains(@text,'₹')]"
            ));
            if (products.isEmpty()) {
                products = driver.findElements(By.xpath(
                    "//android.widget.TextView[contains(@text,'Shoe') or contains(@text,'shoe')]"
                ));
            }

            if (products.isEmpty()) {
                ScreenshotUtil.capture(driver, "NoProductsFound");
                throw new AssertionError("❌ No products found after searching 'Shoes'.");
            }

            // Step 5: Scroll slightly to ensure visibility
            scrollDown();
            test.log(Status.INFO, "Scrolled down slightly to ensure visibility.");

            // Step 6: Click first product
            products.get(0).click();
            test.log(Status.PASS, "Opened first product.");

            // Step 7: Select size 7 if available
            try {
                Thread.sleep(3000); // wait for product page to load
                List<WebElement> sizeOptions = driver.findElements(By.xpath(
                        "//android.widget.TextView[@text='7' or contains(@text,'7')]"));

                if (!sizeOptions.isEmpty()) {
                    sizeOptions.get(0).click();
                    test.log(Status.PASS, "👟 Selected shoe size 7 successfully.");
                } else {
                    test.log(Status.WARNING, "⚠️ Size 7 not available for this product.");
                }
            } catch (Exception e) {
                test.log(Status.WARNING, "⚠️ Could not select size 7: " + e.getMessage());
            }

            // Step 8: Scroll and click Add to Cart
            try {
                Thread.sleep(2000); // small wait for size selection UI update
                boolean buttonFound = false;

                for (int i = 0; i < 4; i++) {
                    List<WebElement> addButtons = driver.findElements(By.xpath(
                            "//android.widget.TextView[@text='ADD TO CART' or @text='Add to Cart' or contains(@text,'Cart')]"));
                    if (!addButtons.isEmpty()) {
                        addButtons.get(0).click();
                        test.log(Status.PASS, "🛒 Product added to cart successfully!");
                        buttonFound = true;
                        break;
                    } else {
                        scrollDown();
                        Thread.sleep(1500);
                    }
                }

                if (!buttonFound) {
                    ScreenshotUtil.capture(driver, "AddToCartNotFound");
                    throw new AssertionError("❌ 'Add to Cart' button not found even after scrolling.");
                }

                // Step 9: Wait for and click View Cart / My Cart
                WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.widget.TextView[@text='VIEW CART' or contains(@text,'My Cart') or contains(@text,'Cart')]")));
                viewCart.click();
                test.log(Status.PASS, "🛍️ Opened cart successfully!");

                // Step 10: Screenshot after adding to cart
                String screenshotPath = ScreenshotUtil.capture(driver, "CartAfterAddingItem");
                test.addScreenCaptureFromPath(screenshotPath);
                test.log(Status.PASS, "✅ Test Completed Successfully.");

            } catch (Exception e) {
                String path = ScreenshotUtil.capture(driver, "Error_AfterAddToCart");
                test.addScreenCaptureFromPath(path);
                test.log(Status.FAIL, "❌ Error after product click: " + e.getMessage());
                Assert.fail(e.getMessage());
            }

        } catch (Exception e) {
            String path = ScreenshotUtil.capture(driver, "Error_AddItemCart");
            test.addScreenCaptureFromPath(path);
            test.log(Status.FAIL, "❌ Error: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    // ✅ Fixed scroll method
    private void scrollDown() {
        try {
            driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
                "direction", "up",
                "percent", 0.7
            ));
        } catch (Exception e) {
            System.out.println("⚠️ Scroll skipped: " + e.getMessage());
        }
    }

    @AfterTest
    public void tearDownReport() {
        extent.flush();
    }
}
