package com.lambdatest.automation;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClassWithoutErrorCollector {

	public String username = "sripriyapkulkarni";
	public String accesskey = "uZ6HzeU8B0IRGOHNM62SKflls6ciT35ZOzRoWlhuS1ZEppPE5z";
	public static RemoteWebDriver driver = null;
	public String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("version", "70.0");
		capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get the any
															// available one
		capabilities.setCapability("build", "LambdaTestSampleApp");
		capabilities.setCapability("name", "LambdaTestJavaSample");
		capabilities.setCapability("network", true); // To enable network logs
		capabilities.setCapability("visual", true); // To enable step by step screenshot
		capabilities.setCapability("video", true); // To enable video recording
		capabilities.setCapability("console", true); // To capture console logs
		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testSimple() throws Exception {

		// navigating to the application under test
		driver.get("https://ecommerce-playground.lambdatest.io/");

		// maximize window
		driver.manage().window().maximize();

		Thread.sleep(1000);

		// validating the Page Title
		String ActualString = "Your Stores";
		String ExpectedString = driver.getTitle();
		System.out.println(ExpectedString);

		assertEquals(ExpectedString, ActualString);

		Thread.sleep(1000);

		driver.findElement(By.cssSelector("div[id='entry_217822'] input[placeholder='Search For Products']")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div[id='entry_217822'] input[placeholder='Search For Products']"))
				.sendKeys("IPod");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
		Thread.sleep(1000);
		String ActualProductPageTitle = "Search - iPod";
		String ExpectedProductPageTitle = driver.getTitle();
		System.out.println(ExpectedProductPageTitle);
		Thread.sleep(3000);

	}

	@After
	public void tearDown() throws Exception {
		if (driver != null) {
			((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
			driver.quit();
		}
	}

}
