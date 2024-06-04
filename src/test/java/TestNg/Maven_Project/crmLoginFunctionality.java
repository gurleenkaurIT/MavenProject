package TestNg.Maven_Project;

import java.awt.Desktop.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class crmLoginFunctionality {
	
	@BeforeMethod
	public void initializeBrwser() {
		

		
	}

	@Test
	public void validateLoginWithCssSelector() {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		// Load application URL
		driver.get("https://classic.freecrm.com/register");

		// Select Edition
		driver.findElement(By.cssSelector("select#payment_plan_id option:nth-of-type(2)")).click();

		// Enter First Name
		driver.findElement(By.cssSelector("input[name='first_name']")).sendKeys("Gurleen");

		// Enter Last Name
		driver.findElement(By.cssSelector("input[name='surname']")).sendKeys("Kaur");

		// Enter Email
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys("gurleen11296@gmail.com");

		// Enter Email again
		driver.findElement(By.cssSelector("input[name='email_confirm']")).sendKeys("gurleen11296@gmail.com");

		// Enter Username
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Gurleen11296");

		// Enter Password
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("Gurleen@pwrd1");

		// Enter Password again
		driver.findElement(By.cssSelector("input[name='passwordconfirm']")).sendKeys("Gurleen@pwrd1");

		// Click on Checkbox 'I agree with the terms and conditions.'
		driver.findElement(By.cssSelector("div.checkbox label input")).click();

		// Click Submit button
		driver.findElement(By.cssSelector("button#submitButton")).click();

		// Close browser
		driver.close();

	}

	@Test
	public void validateLoginWithXpath() {
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		// Load application URL
		driver.get("https://classic.freecrm.com/register");

		// Select Edition
		driver.findElement(By.xpath("//select[@id='payment_plan_id'] //option[text()='Free Edition']")).click();

		// Enter First Name
		driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("Gurleen");

		// Enter Last Name
		driver.findElement(By.xpath("//input[@name='surname']")).sendKeys("Kaur");

		// Enter Email
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("gurleen11296@gmail.com");

		// Enter Email again
		driver.findElement(By.xpath("//input[@name='email_confirm']")).sendKeys("gurleen11296@gmail.com");

		// Enter Username
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Gurleen11296");

		// Enter Password
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Gurleen@pwrd1");

		// Enter Password again
		driver.findElement(By.xpath("//input[@name='passwordconfirm']")).sendKeys("Gurleen@pwrd1");

		// Click on Checkbox 'I agree with the terms and conditions.'
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();

		// Click Submit button
		driver.findElement(By.xpath("//button[@id='submitButton']")).click();

		// Close browser
		driver.close();

	}

}
