package practice;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class Scenario5 {

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
		
		//step 5: create contact with mandatory fields 
		driver.findElement(By.name("lastname")).sendKeys("punithbhavya");
		
		//step 6: select the organization from organization look up image and save
		  driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		  Set<String> handles = driver.getWindowHandles();
		  Iterator it=handles.iterator();
		  String parentid = (String) it.next();
		  String childid=(String) it.next();
		  driver.switchTo().window(childid);
		   
		  driver.findElement(By.linkText("SRPB1998")).click();
		  driver.switchTo().window(parentid);
		  driver.findElement(By.name("button")).click();
		  System.out.println("organization selected");
		
		//step 7: verification for contact
		 String ContactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		 if(ContactHeader.contains("punithbhavya"))
		 {
			 System.out.println(ContactHeader+"---PASS-----");
		 }
		 else
		 {
			 System.out.println(ContactHeader+"----FAIL---");
		 }
		 
		 //step 8: logout of application
		 WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		 Actions act= new Actions(driver);
		 act.moveToElement(ele).perform();
		 driver.findElement(By.linkText("Sign Out")).click();
		 System.out.println("sign out successful");
	}
}
