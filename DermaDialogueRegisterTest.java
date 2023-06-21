package ibu.svvt;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class DermaDialogueRegisterTest {
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

		// click on the Login button
		webDriver.findElement(By.xpath("/html/body/div[2]/nav/div/div[1]/div/div/div/div[2]/ul/li/a")).click();
		Thread.sleep(2000);
		
		// click on register button
		webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/a")).click();
		Thread.sleep(2000);
		
		// assert that it says Register on the page
		String register = webDriver.findElement(By.xpath("/html/body/div/div/div[1]/a/h2")).getText();
		assertEquals("Registration", register);

		// now register
		webDriver.findElement(By.id("firstNameInput")).sendKeys("merjema");
		Thread.sleep(3000);
		webDriver.findElement(By.id("lastNameInput")).sendKeys("mujic");
		Thread.sleep(2000);
		webDriver.findElement(By.id("emailInput")).sendKeys("merjema@gmail.com");
		Thread.sleep(2000);
		webDriver.findElement(By.id("passwordInput")).sendKeys("password");
		Thread.sleep(2000);

		// click on register button
		webDriver.findElement(By.id("registerButton")).click();
		Thread.sleep(2000);
		
	}

}
