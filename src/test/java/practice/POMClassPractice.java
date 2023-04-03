package practice;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.CreateNewContactPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;

public class POMClassPractice {

	public static void main(String[] args) {
		
		
		WebDriver driver = new ChromeDriver();
	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("http://localhost:8888/");
		
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameEdt().sendKeys("admin");
		lp.getPasswordEdt().sendKeys("admin");
		lp.getSubmitBtn().click();
		
		HomePage hp = new HomePage(driver);
		hp.getContactsLnk().click();
		
	   ContactsPage cp = new ContactsPage(driver);
	   cp.getCreateContactLookUpImg().click();
	   
	   CreateNewContactPage  cncp = new CreateNewContactPage(driver);
	   cncp.createNewContact("Bujji");
	   
	   
			   
			
		
		
		
		
	}
	
}
