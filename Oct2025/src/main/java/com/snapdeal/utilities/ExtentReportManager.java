package com.snapdeal.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            String projectPath = System.getProperty("user.dir");
            String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = projectPath + "\\src\\test\\resources\\Reports\\SnapdealReport_" + date + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("Test");
            spark.config().setReportName("Snapdeal_Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("QA", "Priya P");
        }
        return extent;
    }
}
