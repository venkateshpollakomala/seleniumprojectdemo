package com.askmeOffers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OurHistoryPage {
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://askmeoffers.com/pages/our-history/");
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(priority = 1)
	public void checkImages() {

		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());

		// Find all image elements
		List<WebElement> images = driver.findElements(By.xpath("//amp-img"));

		// Assertion - Check if there are any images found
		Assert.assertFalse(images.isEmpty(), "No images found on the page");

		// Print the number of images found
		System.out.println("Number of images found: " + images.size());

		// Iterate through each image
		for (WebElement image : images) {
			String imageUrl = image.getAttribute("src");
			if (imageUrl != null && !imageUrl.isEmpty()) {
				// Create a HttpURLConnection object to send a request to the image URL
				try {
					HttpURLConnection connection = (HttpURLConnection) (new URL(imageUrl).openConnection());
					connection.setRequestMethod("HEAD");
					connection.connect();

					// Get the response code
					int responseCode = connection.getResponseCode();
					// Check if the response code is not 200 (OK)
					if (responseCode != HttpURLConnection.HTTP_OK) {
						System.out.println(
								"Broken Image Found - Image Src: " + imageUrl + ", Response Code: " + responseCode);
					} else {
						System.out.println("Image is OK - Image Src: " + imageUrl);
					}
					connection.disconnect();
				} catch (IOException e) {
					System.out.println("IOException occurred while checking image: " + e.getMessage());
				}
			} else {
				System.out.println("Skipping image with empty source");
			}
		}
	}

	@Test(priority = 2)
	public void verifyBrokenImages() {

		// Verify the title of the page
		String pageTitle = driver.getTitle();
		System.out.println("The page title is : " + pageTitle);
		// Assert.assertEquals(pageTitle, "Your Source for the Latest News, Reviews &
		// Coupon");

		// Verify the current URL
		String currentUrl = driver.getCurrentUrl();
		System.out.println("The Current url is : " + currentUrl);
		// Assert.assertEquals(currentUrl, "https://askmeoffers.com/");

		// Find all image elements
		List<WebElement> images = driver.findElements(By.tagName("img"));

		// Assertion - Check if there are any images found
		Assert.assertFalse(images.isEmpty(), "No images found on the page");

		// Print the number of images found
		System.out.println("Number of images found: " + images.size());

		// Iterate through each image
		for (WebElement image : images) {
			String imageUrl = image.getAttribute("src");
			if (imageUrl != null && !imageUrl.isEmpty()) {
				// Create a HttpURLConnection object to send a request to the image URL
				try {
					HttpURLConnection connection = (HttpURLConnection) (new URL(imageUrl).openConnection());
					connection.setRequestMethod("HEAD");
					connection.connect();

					// Get the response code
					int responseCode = connection.getResponseCode();
					// Check if the response code is not 200 (OK)
					if (responseCode != HttpURLConnection.HTTP_OK) {
						System.out.println(
								"Broken Image Found - Image Src: " + imageUrl + ", Response Code: " + responseCode);
					} else {
						System.out.println("Image is OK - Image Src: " + imageUrl);
					}
					connection.disconnect();
				} catch (IOException e) {
					System.out.println("IOException occurred while checking image: " + e.getMessage());
				}
			} else {
				System.out.println("Skipping image with empty source");
			}
		}
	}

	@Test(priority = 3)
	public void TestAllLinksOnHomepage() {

		// Find all links on the homepage
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));

		// Assertion - Check if there are any links found
		Assert.assertFalse(allLinks.isEmpty(), "No links found on the homepage");

		// Print the number of links found
		System.out.println("Number of links found on the homepage: " + allLinks.size());

		// Print all the links found
		for (WebElement link : allLinks) {
			System.out.println("Link Text: " + link.getText());
			System.out.println("Link URL: " + link.getAttribute("href"));
		}
	}

	@Test(priority = 4)
	public void verifyBrokenLinks() {

		// Find all links on the homepage
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));

		// Assertion - Check if there are any links found
		Assert.assertFalse(allLinks.isEmpty(), "No links found on the homepage");

		// Print the number of links found
		System.out.println("Number of links found on the homepage: " + allLinks.size());

		// Iterate through each link
		for (WebElement link : allLinks) {
			String url = link.getAttribute("href");
			if (url != null && !url.isEmpty()) {
				// Check if the URL starts with "http" or "https"
				if (!url.startsWith("http")) {
					System.out.println("Skipping link with URL: " + url + " as it does not start with 'http'");
					continue;
				}

				// Create a HttpURLConnection object to send a request to the URL
				try {
					HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());
					connection.setRequestMethod("HEAD");
					connection.connect();

					// Get the response code
					int responseCode = connection.getResponseCode();
					// Check if the response code is not 200 (OK)
					if (responseCode != HttpURLConnection.HTTP_OK) {
						System.out.println("Broken Link Found - URL: " + url + ", Response Code: " + responseCode);
					} else {
						System.out.println("Link is OK - URL: " + url);
					}
					connection.disconnect();
				} catch (IOException e) {
					System.out.println("IOException occurred while checking link: " + e.getMessage());
				}
			} else {
				System.out.println("Skipping link with empty URL");
			}
		}
	}

	}


