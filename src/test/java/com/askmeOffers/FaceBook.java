package com.askmeOffers;


	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	public class FaceBook {
	

	    public static void main(String[] args) {

	        WebDriver driver = new ChromeDriver();
	        driver.get("https://www.facebook.com/");
		    System.out.println("Deepu and venkatesh is commited both");
		    System.out.println("Venkatesh is not commited this changes");

	        String title = driver.getTitle();
	        System.out.println("Facebook Title: " + title);

	        driver.quit();
	    }
	}



