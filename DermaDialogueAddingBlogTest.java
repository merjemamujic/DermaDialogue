package ibu.svvt;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class DermaDialogueAddingBlogTest {
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
		
		// assert that it says "Add New Skincare" on the page
		String skincare = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/h2")).getText();
		assertEquals("Add New Skincare", skincare);

		// click on the "Add New Blog Post" button
		webDriver.findElement(By.id("/html/body/div[1]/div/div[2]/div/div/a")).click();
		Thread.sleep(2000);
		
		// click on Enter Blog Name input field
		webDriver.findElement(By.id("blogName")).sendKeys("Beginner's Guide to SPF");
		Thread.sleep(2000);
		
		// add Blog text
		webDriver.findElement(By.id("description")).sendKeys("SPF stands for Sun Protection Factor. It's a measure of how well a sunscreen will protect skin from the sun's harmful UV rays, which are always there, even on cloudy days. By protecting your skin every day with a moisturiser formulated with sunscreen rated SPF 30 or higher, you will be keeping your skin beautiful, as well as healthy.");
		Thread.sleep(3000);
		
		// add type
		webDriver.findElement(By.id("type")).sendKeys("SPF Importance");
		Thread.sleep(2000);
		
		// click on the "Add Post" button
		webDriver.findElement(By.xpath("/html/body/div/form/button")).click();
		Thread.sleep(2000);
		
	}

}
