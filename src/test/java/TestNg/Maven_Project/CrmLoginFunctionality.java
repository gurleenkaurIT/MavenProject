package TestNg.Maven_Project;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CrmLoginFunctionality {

	WebDriver driver;
	String browser = "chrome";

	@BeforeMethod
	public void initializeBrwser() {
		openBrowser();
		driver.get("https://classic.freecrm.com/register");
	}

	@Test
	public void validateLoginWithCssSelector() {

		Random random = new Random();
		int randomValue = random.nextInt(1000);

		// Select Edition
		driver.findElement(By.cssSelector("select#payment_plan_id option:nth-of-type(2)")).click();

		// Enter First Name
		driver.findElement(By.cssSelector("input[name='first_name']")).sendKeys("Gurleen");

		// Enter Last Name
		driver.findElement(By.cssSelector("input[name='surname']")).sendKeys("Kaur");

		// Enter Email
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys("gurleen11296" + randomValue + "@gmail.com");

		// Enter Email again
		driver.findElement(By.cssSelector("input[name='email_confirm']"))
				.sendKeys("gurleen11296" + randomValue + "@gmail.com");

		// Enter Username
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Gurleen"+randomValue+"11296");

		// Enter Password
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("Gurleen@"+randomValue+"pwrd1");

		// Enter Password again
		driver.findElement(By.cssSelector("input[name='passwordconfirm']")).sendKeys("Gurleen@"+randomValue+"pwrd1");

		// Click on Check box 'I agree with the terms and conditions.'
		driver.findElement(By.cssSelector("div.checkbox label input")).click();

		// Click Submit button
		driver.findElement(By.cssSelector("button#submitButton")).click();

		// Close browser
		driver.close();

	}

	@Test
	public void validateLoginWithXpath() {
		Random random = new Random();
		int randomValue = random.nextInt(1000);

		// Select Edition
		driver.findElement(By.xpath("//select[@id='payment_plan_id'] //option[text()='Free Edition']")).click();

		// Enter First Name
		driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("Gurleen");

		// Enter Last Name
		driver.findElement(By.xpath("//input[@name='surname']")).sendKeys("Kaur");

		// Enter Email
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("gurleen11296" + randomValue + "@gmail.com");

		// Enter Email again
		driver.findElement(By.xpath("//input[@name='email_confirm']")).sendKeys("gurleen11296" + randomValue + "@gmail.com");

		// Enter Username
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Gurleen" + randomValue + "11296");

		// Enter Password
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Gurleen@" + randomValue + "pwrd1");

		// Enter Password again
		driver.findElement(By.xpath("//input[@name='passwordconfirm']")).sendKeys("Gurleen@" + randomValue + "pwrd1");

		// Click on Check box 'I agree with the terms and conditions.'
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();

		// Click Submit button
		driver.findElement(By.xpath("//button[@id='submitButton']")).click();
	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();

	}

	public void openBrowser() {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			throw new InvalidArgumentException("Invalid Browser:" + browser);

		}
	}
}
