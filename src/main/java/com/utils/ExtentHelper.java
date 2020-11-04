package com.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.util.Date;

public class ExtentHelper {
    

    public static ExtentReports extent;
    public static String reportPath = null;

    public static ExtentReports getInstance() {
        if (extent == null) {
            Date d = new Date();
            String todayDate = String.valueOf(d.getDate());
            File folderName = new File("reports/"+todayDate);
            if (!folderName.exists()) {
                if (folderName.mkdirs()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }
            String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".html";
            reportPath = folderName+"/"+fileName;
            System.out.println(folderName+"/"+fileName);
            extent = new ExtentReports();

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            extent.attachReporter(spark);
            extent.flush();

        }
        return extent;

    }

}
