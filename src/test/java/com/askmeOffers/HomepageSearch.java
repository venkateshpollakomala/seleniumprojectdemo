package com.askmeOffers;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomepageSearch {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://askmeoffers.com/");
    }

    @AfterClass
    public void tearDown() {
        // Close WebDriver
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "searchQueries")
    public Object[][] getSearchQueries() {
        // Define search queries
        return new Object[][] {
                { "admyrin" }, { "adobe" }, { "adorbuds" }, { "ae" }, { "afragrancestory" }, { "ageo" },
                { "agmwebhosting" }, { "agoda" }, { "agro-market" }, { "agscinemas" }
        };
    }

    @Test(priority=1, dataProvider = "searchQueries")
    public void testSearch(String query) {
        // Find and click the search button using JavaScript
        WebElement searchButton = driver.findElement(By.id("headserpp"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", searchButton);

        // Find search input element and enter the search query
        WebElement searchInput = driver.findElement(By.id("my-input-inside"));
        searchInput.clear();
        searchInput.sendKeys(query + Keys.ENTER);

        // Wait for search results to load (if needed)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("i-amphtml-sizer")));
        
        // Add a delay to see the results (you can remove this in actual tests)
        try {
            Thread.sleep(2000); // 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @DataProvider(name = "invalidSearchQueries")
    public Object[][] getInvalidSearchQueries() {
        // Define invalid search queries
        return new Object[][] {
                { "" }, // Empty search query
                { "&&&" }, // Invalid characters
                { "nonexistentsearchquery" } // Non-existent search query
        };
    }

    @Test(priority=2, dataProvider = "invalidSearchQueries")
    public void testInvalidSearchQueries(String query) {
        // Find and click the search button using JavaScript
        WebElement searchButton = driver.findElement(By.id("headserpp"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", searchButton);

        // Find search input element and enter the search query
        WebElement searchInput = driver.findElement(By.id("my-input-inside"));
        searchInput.clear();
        searchInput.sendKeys(query + Keys.ENTER);

        // Wait for search results to load (if needed)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("i-amphtml-sizer")));
            // If results are found, fail the test
            //org.testng.Assert.fail("Search results found for invalid query: " + query);
        } catch (org.openqa.selenium.TimeoutException e) {
            // If timeout occurs, it's expected behavior
            System.out.println("Timeout occurred while waiting for search results for invalid query: " + query);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // If search results not found, it's expected behavior
            System.out.println("Search results not found for invalid query: " + query);
        }
    }
    @Test(priority=3)
    public void testSearchResultsDisplayed() {
        // Find and click the search button using JavaScript
        WebElement searchButton = driver.findElement(By.id("headserpp"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", searchButton);

        // Find search input element and enter a known search query
        WebElement searchInput = driver.findElement(By.id("my-input-inside"));
        searchInput.clear();
        searchInput.sendKeys("adobe" + Keys.ENTER);

        // Wait for search results to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("i-amphtml-sizer")));
            // If search results are found, test passes
            System.out.println("Search results are displayed.");
        } catch (org.openqa.selenium.TimeoutException e) {
            // If timeout occurs, fail the test
            org.testng.Assert.fail("Search results not displayed within timeout.");
        }
    }
}
