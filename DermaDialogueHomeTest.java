package ibu.svvt;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;


class DermaDialogueHomeTest {
	private static WebDriver webDriver;
	private static String baseUrl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\DELL\\Downloads\\chromedriver_win32 (1)");

		ChromeOptions options = new ChromeOptions();
		// to maximize the window
		options.addArguments("--start-maximized");

		webDriver = new ChromeDriver(options);
		baseUrl = "https://benevolent-basbousa-7edc64.netlify.app/frontend/index.html";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}
														  				
	
	@Test
	void testLogin() throws InterruptedException {

		webDriver.get(baseUrl);
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		
		// scroll a little bit, to see products
		WebElement scrollProducts = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]/div/h2"));
		js.executeScript("arguments[0].scrollIntoView(true);", scrollProducts);
		
		// click on the product to visit the link
		webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div[1]/div/a")).click();
		Thread.sleep(2000);
		
		// return to Home page
		webDriver.navigate().back();
		Thread.sleep(2000);
		
		// scroll to About us section to get the message
		WebElement scrollToUs = webDriver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/aside/div/div/div[2]/h3"));
		js.executeScript("arguments[0].scrollIntoView(true);", scrollToUs);
		Thread.sleep(1000);
		js.executeScript("alert('Hello from the other side! Hahaha Greetings, have a nice day!')");	
		Thread.sleep(3000);
		
	}

}
