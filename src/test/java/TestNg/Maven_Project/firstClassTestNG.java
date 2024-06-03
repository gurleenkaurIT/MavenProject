package TestNg.Maven_Project;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class firstClassTestNG {

	@BeforeMethod // Executes before the test of the class, if we have 4 test then this method
					// will execute 4 times
	public void openBrowser() {
		System.out.println("Open Browser");
	}

	@Test (priority = 1)// Run as per the priority
	public void firstTest() {
		System.out.println("First Test");
	}
	
	@Test(priority = 2, dependsOnMethods = "thirdTest")
	public void secondTest() {
		System.out.println("Second Test");
	}
	
	@Test
	public void fourthTest() {
		System.out.println("Fourth Test");
	}
	
	@Test
	public void thirdTest() {
		System.out.println("Third Test");
	}

	@AfterMethod // Executes after the test of the class
	public void closeBrowser() {
		System.out.println("Close Browser");
	}

}
