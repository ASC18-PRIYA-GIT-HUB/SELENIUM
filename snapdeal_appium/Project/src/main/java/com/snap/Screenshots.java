package com.snapdeal.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    static String projectPath = System.getProperty("user.dir");

    public static String capture(WebDriver driver, String testName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dest = projectPath + "\\src\\test\\resources\\Screenshots\\" + testName + ".png";
        File destFile = new File(dest);
        FileUtils.copyFile(src, destFile);
        System.out.println("📸 Screenshot saved at: " + dest);
        return dest;
    }

	public static void takeScreenshot(AndroidDriver<MobileElement> driver, String string) {
		// TODO Auto-generated method stub
		
	}
}
