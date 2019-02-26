package com.qainfotech.seleniumdemo;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestGoogleSearch {

	WebDriver driver;

	@BeforeClass
	public void setup() {
		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectpath + "/chromedriver/chromedriver");
		driver = new ChromeDriver();

	}

	@Test
	public void testGoogleSearch () {
		
		String baseURL = "https://www.google.com";
		String searchString = "qa infotech";
		driver.get(baseURL);
		WebElement searchbar = driver.findElement(By.name("q"));
		searchbar.sendKeys(searchString);
		
		//searchbar.sendKeys(Keys.RETURN);
		
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("btnK"))).click();
				
		String actualResult = (new WebDriverWait(driver, 4)).until(ExpectedConditions.elementToBeClickable(By.className("srg"))).
				findElement(By.cssSelector("#rso > div > div > div:nth-child(1) > div > div > div.r > a > h3")).getText();

		Assert.assertTrue(actualResult.toLowerCase().contains(searchString.toLowerCase()), "The search string is not present in the first result");
				
	}

	@AfterClass
	public void teardown() {
		driver.close();
	}

}
