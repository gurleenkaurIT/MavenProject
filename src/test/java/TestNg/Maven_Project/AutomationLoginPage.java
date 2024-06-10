package TestNg.Maven_Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomationLoginPage {

	WebDriver driver;

	@BeforeMethod
	public void setUpBrowser() {

		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		//driver.navigate().to("");
	}

	@Test
	public void validateLogin() {

		// Enter email and password
		driver.findElement(By.id("input-email")).sendKeys("gurleen11296@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Password123");

		// Click Login
		driver.findElement(By.cssSelector("input[type='submit']")).submit();

		// validate login is successful
		String myAccountText = driver.findElement(By.cssSelector("div#content h2:first-of-type")).getText();

		Assert.assertEquals(myAccountText, "My Account");
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
