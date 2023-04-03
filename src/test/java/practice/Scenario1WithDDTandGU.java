package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;

public class Scenario1WithDDTandGU {

	public static void main(String[] args) throws IOException {
		
		//step 1: create object for all utilitues
		PropertyFileUtility pUtil= new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		JavaUtility jUtil = new JavaUtility();
		
		//step 2: read all the necessary data
		/* read data from proeprty file - common data*/
		String URL = pUtil.readDataFromPropertyFile("url");
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		/*read data from excel sheet - test data */
		String LASTNAME = eUtil.readDataFromExcel("Contacts", 1, 2)+jUtil.getRandomNumber();
		
		WebDriver driver =null;
				
				
//step 2: launch the browser  runtime polymorphism
	if(BROWSER.equalsIgnoreCase("chrome"))
	{
	
	driver = new ChromeDriver();
	}
	else if(BROWSER.equalsIgnoreCase("firefox"))
	{
   driver = new FirefoxDriver();
				
	}
				
	else
				
	{
				
		System.out.println("Invalid browser name");
	
		}
		 wUtil.maximizewindow(driver);
		 wUtil.waitForPage(driver);
		driver.get(URL);
		
		//step 2:login to application
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				//step 3: navigate to contacts
				driver.findElement(By.linkText("Contacts")).click();
				
				//step 4: click on create contact lookup image
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
				//step 5: create contact with mandatory fields and save it
				driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//step 6: verification for contact
				 String ContactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				 if(ContactHeader.contains("LASTNAME"))
				 {
					 System.out.println(ContactHeader+"---PASS-----");
				 }
				 else
				 {
					 System.out.println(ContactHeader+"----FAIL---");
				 }
				 
				 //step 7: logout of application
				 WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				 wUtil.mouseHoverAction(driver, ele);
				 driver.findElement(By.linkText("Sign Out")).click();
				 System.out.println("sign out successful");
				
	}
}




 
