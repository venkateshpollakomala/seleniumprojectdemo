package com.askmeOffers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class MyTestListener implements ITestListener, ISuiteListener, IInvokedMethodListener {
    private WebDriver driver;
    public MyTestListener() {
        // WebDriver initialization
        driver = new ChromeDriver();
        new ArrayList<>();
        new ArrayList<>();
    }

    // ITestListener methods
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        captureScreenshot(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
    }

    // ISuiteListener methods
    @Override
    public void onStart(ISuite suite) {
        System.out.println("Suite Started: " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Suite Finished: " + suite.getName());
    }

    // IInvokedMethodListener methods
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // Not used in this example
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // Not used in this example
    }

    // Capture Screenshot method
    private void captureScreenshot(ITestResult result) {
        try {
            // Convert WebDriver object to TakeScreenshot
            TakesScreenshot ts = (TakesScreenshot) driver;

            // Capture screenshot as File
            File srcFile = ts.getScreenshotAs(OutputType.FILE);

            // Define destination file path
            String destFilePath = "screenshots/" + result.getName() + "_failure.png";

            // Copy file to destination
            Files.copy(srcFile.toPath(), new File(destFilePath).toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Log screenshot path
            System.out.println("Screenshot captured: " + destFilePath);

            // Embed screenshot in HTML report
            Reporter.log("<br><img src='" + destFilePath + "' height='200' width='200'/><br>");
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }
}
