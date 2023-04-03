package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class Scenario3 {

	public static void main(String[] args) {
       
		//step 1: Launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("http://localhost:8888");
		
		//step 2: login to application with valid credentials
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//step 3: naviagte to organization link
		driver.findElement(By.linkText("Organizations")).click();
		
		//step 4: click on create organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//step 5: create organization with mandatory fields and save
		driver.findElement(By.name("accountname")).sendKeys("shivaroopa");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		
		// step 7: verification for organization
		String organizationHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(organizationHeader.contains("shivaroopa"))
		{
			System.out.println(organizationHeader+"----PASS----");
		}
		else
		{
			System.out.println(organizationHeader+"---FAIL-----");
		}
		
		//step 8: logout of the application
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(ele).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Sign Out successful");
	}
}
