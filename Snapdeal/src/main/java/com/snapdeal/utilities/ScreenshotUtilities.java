package com.snapdeal.utilities;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenshotUtilities {

    public static String captureScreenshot(WebDriver driver, String folderPath, String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File folder = new File(folderPath);
            if (!folder.exists()) folder.mkdirs();

            String dest = folderPath + "\\" + testName + ".png";
            File destFile = new File(dest);
            FileUtils.copyFile(src, destFile);

            return dest;
        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }
}
