package com.qainfotech.seleniumdemo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class TestNestedFrames {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectpath + "/chromedriver/chromedriver");
		driver = new ChromeDriver();
	}

	@Test(dataProvider = "expectedBodyTexts")
	public void testNestedFrames(List<String> expectedBodyTexts) {
		String baseURL = "http://10.0.14.57:9292/nested_frames";
		driver.get(baseURL);
		List<String> actualBodyTexts = new ArrayList<String>();
		List<WebElement> framesList = (new WebDriverWait(driver, 2))
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("html > frameset")))
				.findElements(By.cssSelector("html > frameset > frame"));
		
			// code to fetch the body text of all the frames inside the frame frame-top
			driver.switchTo().frame(framesList.get(0));
			List<WebElement> frameList = driver.findElements(By.cssSelector("html > frameset > frame"));
			for (WebElement topframe : frameList) {
				driver.switchTo().frame(topframe);
				//System.out.println(driver.findElement(By.xpath("/html/body")).getText());
				actualBodyTexts.add(driver.findElement(By.xpath("/html/body")).getText());
				driver.switchTo().parentFrame();
			}
			driver.switchTo().parentFrame();
			
			//code to fetch body text of the bottom frame
			driver.switchTo().frame(framesList.get(1));
			//System.out.println(driver.findElement(By.xpath("/html/body")).getText());
			actualBodyTexts.add(driver.findElement(By.xpath("/html/body")).getText());
			driver.switchTo().parentFrame();
			
			
			Assert.assertEquals(actualBodyTexts, expectedBodyTexts);


		

	}

	@AfterClass
	public void teardown() {
		driver.close();
	}
	
	@DataProvider(name = "expectedBodyTexts")
	public Object[] dataProviderMethod() {
		List<String> expectedBodyTexts= new ArrayList<String>();
		expectedBodyTexts.add("LEFT");
		expectedBodyTexts.add("MIDDLE");
		expectedBodyTexts.add("RIGHT");
		expectedBodyTexts.add("BOTTOM");
		
		return new Object[] {expectedBodyTexts};

		
	}

}
