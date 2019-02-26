package com.qainfotech.seleniumdemo;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;;

public class TestLinkNames {
	
	WebDriver driver;
	
	@BeforeClass
	public void setup() {
		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectpath+"/chromedriver/chromedriver");
		driver = new ChromeDriver();
	}
	
	@Test(dataProvider = "listOfLinks")
	public void testLinkNames(List<String> expectedLinkNames) {
		String baseURL = "http://10.0.14.57:9292";
		Boolean testStatus = false;
		driver.get(baseURL);
		
		List<WebElement> actualLinkNames = (new WebDriverWait(driver, 4)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#content")))
				.findElements(By.cssSelector("#content > ul > li"));
		
		if(actualLinkNames.size() == expectedLinkNames.size()) {
			
			for(int count=0; count<actualLinkNames.size(); count++) {
				if(!actualLinkNames.get(count).getText().equals(expectedLinkNames.get(count))){
					Assert.fail("Names mismatch at link no: "+(count+1));
				}				
			}
			testStatus = true;
			Assert.assertTrue(testStatus);
		}
		else {
			Assert.fail("Links number mismatch");
		}
		
		
		
		
	}
	
	@AfterClass
	public void teardown() {
		driver.close();
	}
	
	@DataProvider(name = "listOfLinks")
	public Object[] dataProviderMethod() {
		List<String> expectedLinkNames = new ArrayList<String>();
		expectedLinkNames.add("A/B Testing");
		expectedLinkNames.add("Basic Auth (user and pass: admin)");
		expectedLinkNames.add("Broken Images");
		expectedLinkNames.add("Challenging DOM");
		expectedLinkNames.add("Checkboxes");
		expectedLinkNames.add("Context Menu");
		expectedLinkNames.add("Disappearing Elements");
		expectedLinkNames.add("Drag and Drop");
		expectedLinkNames.add("Dropdown");
		expectedLinkNames.add("Dynamic Content");
		expectedLinkNames.add("Dynamic Controls");
		expectedLinkNames.add("Dynamic Loading");
		expectedLinkNames.add("Exit Intent");
		expectedLinkNames.add("File Download");
		expectedLinkNames.add("File Upload");
		expectedLinkNames.add("Floating Menu");
		expectedLinkNames.add("Forgot Password");
		expectedLinkNames.add("Form Authentication");
		expectedLinkNames.add("Frames");
		expectedLinkNames.add("Geolocation");
		expectedLinkNames.add("Horizontal Slider");
		expectedLinkNames.add("Hovers");
		expectedLinkNames.add("Infinite Scroll");
		expectedLinkNames.add("JQuery UI Menus");
		expectedLinkNames.add("JavaScript Alerts");
		expectedLinkNames.add("JavaScript onload event error");
		expectedLinkNames.add("Key Presses");
		expectedLinkNames.add("Large & Deep DOM");
		expectedLinkNames.add("Multiple Windows");
		expectedLinkNames.add("Nested Frames");
		expectedLinkNames.add("Notification Messages");
		expectedLinkNames.add("Redirect Link");
		expectedLinkNames.add("Secure File Download");
		expectedLinkNames.add("Shifting Content");
		expectedLinkNames.add("Slow Resources");
		expectedLinkNames.add("Sortable Data Tables");
		expectedLinkNames.add("Status Codes");
		expectedLinkNames.add("Typos");
		expectedLinkNames.add("WYSIWYG Editor");




		return new Object[] {expectedLinkNames};
	}

}
