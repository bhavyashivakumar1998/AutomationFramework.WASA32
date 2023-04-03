package practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;

public class Scenario2WithDDTandGU {

	public static void main(String[] args) throws IOException {
		// step 1: create object for all utilities
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		JavaUtility jUtil = new JavaUtility();
		
		//step 2: read all the necessary data
		/* read data from property file - common data */
		String URL = pUtil.readDataFromPropertyFile("url");
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		/*read data from excel sheet-test data */
		String ORGNAME = eUtil.readDataFromExcel("Organizations", 1, 2)+jUtil.getRandomNumber();
		
		WebDriver driver = null;
		
		//step 3: launch the browser - runtime polymorphism
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("invalid browser name");
		}
		
		wUtil.maximizewindow(driver);
		wUtil.waitForPage(driver);
		driver.get(URL);
		
		//step 4: login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//step 5: click on organization link
		driver.findElement(By.linkText("Organizations")).click();
		
		//step 6: click on create organization link
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//step 7: create organization with mandatory fileds
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		
		//step 8: save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//step 9: validate
		String organizationHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(organizationHeader.contains(ORGNAME))
		{
			System.out.println(organizationHeader+"----PASS----");
		}
		else
		{
			System.out.println(organizationHeader+"---FAIL-----");
		}
		
		//step 10: logout of application
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverAction(driver, ele);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("sign out successful");
		

		
		
		
		
	}
}
