package com.snapdeal.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String projectPath = System.getProperty("user.dir");
            String reportPath = projectPath + "\\src\\test\\resources\\Reports\\Snapdeal_Report.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("Snapdeal Automation Report");
            spark.config().setReportName("Snapdeal Android Automation");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}
