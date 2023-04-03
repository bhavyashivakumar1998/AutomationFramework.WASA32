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

public class Scenario1WithDDT {

	public static void main(String[] args) throws IOException {
		
		//step 1: Read all the necessary data
		/* read data from proeprty file - common data*/
		FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pObj = new Properties();
		pObj.load(fisp);
		String URL = pObj.getProperty("url");
		String BROWSER = pObj.getProperty("browser");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		/*read data from excel sheet - test data */
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		String LASTNAME = wb.getSheet("Contacts").getRow(1).getCell(2).getStringCellValue();
		
		WebDriver driver =null;
				
				
//step 2: launch the browser  runtime polymorphism
	if(BROWSER.equalsIgnoreCase("chrome"))
	{
	WebDriverManager.chromedriver().setup();
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
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(URL);
		
		//step 2:login to application
				driver.findElement(By.name("user_name")).sendKeys("USERNAME");
				driver.findElement(By.name("user_password")).sendKeys("PASSWORD");
				driver.findElement(By.id("submitButton")).click();
				
				//step 3: navigate to contacts
				driver.findElement(By.linkText("Contacts")).click();
				
				//step 4: click on create contact lookup image
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
				//step 5: create contact with mandatory fields and save it
				driver.findElement(By.name("lastname")).sendKeys("LASTNAME");
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
				 Actions act= new Actions(driver);
				 act.moveToElement(ele).perform();
				 driver.findElement(By.linkText("Sign Out")).click();
				 System.out.println("sign out successful");
				
	}
}




 
