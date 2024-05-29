package com.askmeOffers;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Myntra {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void TestAllCoupans() {
        driver.get("https://askmeoffers.com/myntra-coupons/");
        driver.findElement(By.xpath("//body/div[@id='page']/div[@id='content']/div[1]/div[3]/div[1]/span[1]/a[1]"))
                .click();

        List<WebElement> links = driver.findElements(By.xpath("//span[@class=\"hidespan\"]"));

        System.out.println("Number of links with 'Redeem' text: " + links.size());

        for (WebElement link : links) {
            System.out.println("Link Text: " + link.getText());
            System.out.println("Link: " + link.getAttribute("href"));
        }
    }

    @Test(priority = 2)
    public void TestDeals() {
        driver.get("https://askmeoffers.com/myntra-coupons/");
        driver.findElement(By.xpath("//body/div[@id='page']/div[@id='content']/div[1]/div[3]/div[1]/span[3]/a[1]"))
                .click();

        List<WebElement> deals = driver.findElements(By.xpath("//body/div[@id='page']/div[@id='content']/div[2]/div[2]/div[99]/span[1]"));

        System.out.println("Number of links with 'Redeem' text: " + deals.size());

        for (WebElement deal : deals) {
            System.out.println("Link Text: " + deal.getText());
            System.out.println("Link: " + deal.getAttribute("href"));
        }
    }

    @Test(priority = 3)
    public void AllLinks() {
        driver.get("https://askmeoffers.com/myntra-coupons/");

        driver.findElement(By.xpath("//body/div[@id='page']/div[@id='content']/div[1]/div[3]/div[1]/span[1]/a[1]"))
                .click();

        Actions actions = new Actions(driver);

        List<WebElement> allCoupons = driver.findElements(
                By.xpath("//body/div[@id='page']/div[@id='content']/div[2]/div[2]/div[3]/span[1]/span[1]"));

        Assert.assertFalse(allCoupons.isEmpty(), "No coupons found on the page");

        System.out.println("Number of coupons found: " + allCoupons.size());

        for (WebElement coupon : allCoupons) {
            System.out.println("Coupon Name: " + coupon.getText());
            System.out.println("Coupon URL: " + coupon.getAttribute("href"));

            actions.moveToElement(coupon).perform();
        }

        List<WebElement> allDeals = driver
                .findElements(By.xpath("//body/div[@id='page']/div[@id='content']/div[2]/div[2]/div[99]/span[1]"));

        Assert.assertFalse(allDeals.isEmpty(), "No deals found on the page");

        System.out.println("Number of deals found: " + allDeals.size());

        for (WebElement deal : allDeals) {
            System.out.println("Deal Name: " + deal.getText());
            System.out.println("Deal URL: " + deal.getAttribute("href"));

            actions.moveToElement(deal).perform();
        }
    }
}
