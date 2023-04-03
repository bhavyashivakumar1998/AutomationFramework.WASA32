package vtiger.OrganizationTests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;

public class CreateOrgWithInustryAndChemicalsTest {

	@Test
	public void CreateOrgWithIndustryAndChemicalsTest() throws IOException
	 {
		
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
		
		//step 3: read data from excel sheet - test data 
		String INDUSTRYTYPEDROPDOWN =eUtil.readDataFromExcel("Organizations", 4, 3);
		String ORGNAME = eUtil.readDataFromExcel("Organizations", 4, 2)+jUtil.getRandomNumber();
		
		WebDriver driver = null;
		
		//step 4: launch the browser - runtime ploymorphism
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new  ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("invalid browser name");
		}
		wUtil.maximizewindow(driver);
		wUtil.waitForPage(driver);
		driver.get(URL);
		
		//step 5: login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
//step 6: click on organization link
driver.findElement(By.linkText("Organizations")).click();	

//step 7: click on create organization look up image
driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

//step 8: create organization with mandatory fields
 driver.findElement(By.name("accountname")).sendKeys(ORGNAME);	

 WebElement ele = driver.findElement(By.name("industry"));

wUtil.handleDropdown(INDUSTRYTYPEDROPDOWN, ele);
//step 9: save 
driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

//step 10: validate for organization
String OrgHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
if(OrgHeader.contains(INDUSTRYTYPEDROPDOWN))
{
	System.out.println(OrgHeader+"-----PASS----");
}
else
{
	System.out.println(OrgHeader+"------FAIL-----");
}
 //step 11: logout of application
WebElement ele1 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
wUtil.mouseHoverAction(driver, ele1);
driver.findElement(By.linkText("Sign Out")).click();
System.out.println("sign out successful");
		
	}
}
