package ibu.svvt;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class DermaDialogueContactUsTest {
	private static WebDriver webDriver;
	private static String baseUrl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\DELL\\Downloads\\chromedriver_win32 (1)");

		ChromeOptions options = new ChromeOptions();
		// to maximize the window
		options.addArguments("--start-maximized");

		webDriver = new ChromeDriver(options);
		baseUrl = "https://benevolent-basbousa-7edc64.netlify.app/frontend/blogdashboard.html";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}
														  				
	
	@Test
	void testLogin() throws InterruptedException {

		webDriver.get(baseUrl);
		Thread.sleep(2000);
		
		// assert that it says "Need Expert help with skin care?" on the page
		String skincareHelp = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/h2")).getText();
		assertEquals("Need Expert help with skin care?", skincareHelp);

		// click on the "Contact Us" button
		webDriver.findElement(By.id("/html/body/div[2]/div/div/a")).click();
		Thread.sleep(2000);
		
		// now register
		webDriver.findElement(By.id("firstName")).sendKeys("contact");
		Thread.sleep(3000);
		webDriver.findElement(By.id("lastName")).sendKeys("is contacting");
		Thread.sleep(2000);
		webDriver.findElement(By.id("email")).sendKeys("contactisemailing@gmail.com");
		Thread.sleep(2000);
		webDriver.findElement(By.id("subject")).sendKeys("Please DO work!");
		Thread.sleep(2000);

		// click on save button
		webDriver.findElement(By.id("saveContactForm")).click();
		Thread.sleep(2000);
				
	}

}
