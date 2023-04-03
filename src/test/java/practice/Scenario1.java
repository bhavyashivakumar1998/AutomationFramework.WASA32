package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Scenario1 {

	public static void main(String[] args) {
		
		//step 1: Launch the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("http://localhost:8888");
		
		//step 2:login to application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//step 3: navigate to contacts
		driver.findElement(By.linkText("Contacts")).click();
		
		//step 4: click on create contact lookup image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//step 5: create contact with mandatory fields and save it
		driver.findElement(By.name("lastname")).sendKeys("Bhavya");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//step 6: verification for contact
		 String ContactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		 if(ContactHeader.contains("Bhavya"))
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
