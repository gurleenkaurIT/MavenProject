package TestNg.Maven_Project;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrderConfirmationFunctionality {

	WebDriver driver;
	String browser = "chrome";
	Actions actions;
	Random randomValue = new Random(100);
	Select sc;

	@BeforeMethod
	public void setUpBrowser() {
		openBrowser();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void validateAddToCart() throws InterruptedException {
		validateLogin();

		// Navigate to Laptops and Notebooks
		driver.findElement(By.xpath("//a[text()='Laptops & Notebooks']")).click();
		driver.findElement(By.xpath("//a[text()='Show All Laptops & Notebooks']")).click();
		Assert.assertEquals(driver.getTitle(), "Laptops & Notebooks", "Wrong page");
		driver.findElement(By.id("list-view")).click();

		// Check if Laptop list is available or not
		try {

			driver.findElement(By.xpath("//p[text ()='There are no products to list in this category.']"))
					.isDisplayed();
			System.out.println("There are no products to list in this category.");

		} catch (Exception e) {
			driver.findElement(By
					.cssSelector("#content div.row:nth-of-type(4) div.product-list:first-of-type button:first-of-type"))
					.isDisplayed();

			String laptopNameSelected = driver.findElement(
					By.cssSelector("#content div.row:nth-of-type(4) div.product-list:first-of-type div.caption a"))
					.getText();

			// Add laptop to cart
			driver.findElement(By
					.cssSelector("#content div.row:nth-of-type(4) div.product-list:first-of-type button:first-of-type"))
					.click();

			Thread.sleep(5000);

			Assert.assertEquals(driver.getTitle(), laptopNameSelected, "Product is not added to cart.");

			driver.findElement(By.cssSelector("button#button-cart")).click();

			driver.findElement(By.cssSelector("div.alert-success")).isDisplayed();

		}
	}

	@Test(dependsOnMethods = "validateAddToCart")
	public void validateProductCheckOut() throws InterruptedException {
		validateLogin();

		driver.findElement(By.cssSelector("a[title='Shopping Cart'] i")).click();

		// Check if shopping cart is empty or not
		try {

			driver.findElement(By.xpath("// div[@id='content'] //p[text()='Your shopping cart is empty!']"))
					.isDisplayed();
			System.out.println("Your shopping cart is empty!");

		} catch (Exception e) {
			driver.findElement(By.xpath("//a[text()='Checkout']")).click();

			// Enter Billing Details
			if (driver.findElement(By.cssSelector("input[name ='payment_address'][value ='existing']")) != null) {
				driver.findElement(By.cssSelector("input[name ='payment_address'][value ='existing']")).click();
				driver.findElement(By.id("button-payment-address")).click();

			} else {

				driver.findElement(By.cssSelector("input[name ='payment_address'][value ='new']")).click();
				driver.findElement(By.id("input-payment-firstname")).sendKeys("Gurleen" + randomValue);
				driver.findElement(By.id("input-payment-lastname")).sendKeys("Kaur" + randomValue);
				driver.findElement(By.id("input-payment-address-1")).sendKeys("5 Main" + randomValue);
				driver.findElement(By.id("input-payment-city")).sendKeys("Brampton");
				driver.findElement(By.id("input-payment-postcode")).sendKeys("L4K 2N1");

				WebElement selectCountry = driver.findElement(By.id("input-payment-country"));

				sc = new Select(selectCountry);
				sc.selectByValue("Canada");

				WebElement selectRegion = driver.findElement(By.id("input-payment-zone"));
				sc = new Select(selectRegion);
				sc.selectByValue("Ontario");

				driver.findElement(By.id("button-payment-address")).click();
			}

			// Enter Delivery Details
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[name ='shipping_address'][value ='existing']")).click();
			driver.findElement(By.id("button-shipping-address")).click();

			// Delivery Method
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[name ='shipping_method']")).click();
			driver.findElement(By.id("button-shipping-method")).click();

			// Payment Method
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[name ='payment_method'][value ='cod']")).click();
			driver.findElement(By.cssSelector("input[name ='agree']")).click();
			driver.findElement(By.id("button-payment-method")).click();

			// Confirm Order
			Thread.sleep(2000);
			driver.findElement(By.id("button-confirm")).isDisplayed();
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
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
			throw new InvalidArgumentException("Invalid Browser: " + browser);

		}
	}

	public void validateLogin() {
		driver.findElement(By.id("input-email")).sendKeys("gurleen11296@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Password123");

		driver.findElement(By.cssSelector("input[type='submit']")).submit();

		Assert.assertEquals(driver.getTitle(), "My Account", "Invalid credentials");
	}

}
