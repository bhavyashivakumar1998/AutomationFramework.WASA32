package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindAll;


/**
 * 
 * @author admin
 *
 */
public class LoginPage { //rule 1: create a pom class for every page

	//rule 2: identify the web element with @FinfBy, @FindAll, and @FindBys
	 @FindBy(name="user_name")
	 private WebElement UsernameEdt;
	 
	 @FindAll({@FindBy(name="user_password"), @FindBy(xpath="//input[@type='password']")})
	 private WebElement PasswordEdt;
	 
	 @FindBy(id="submitButton")
	 private WebElement SubmitBtn;
	 
	 @FindBy(linkText="Contacts")
		private WebElement Contacts;
		

	 //rule 3: create a constructor to initialize these web elements
	 public LoginPage(WebDriver driver)
	 {
		 PageFactory.initElements(driver, this);
	 }
	 
	 //rule4: provide getters to access these elements
	 public WebElement getUsernameEdt() {
		 return UsernameEdt;
	 }
	 
	 public WebElement getPasswordEdt()
	 {
		 return PasswordEdt;
	 }
	 
	 public WebElement getSubmitBtn()
	 {
		 return SubmitBtn;
	 }
	 
	 public WebElement getContacts()
		{
			return Contacts;
		}
	 
	 //Business libraries- generic methods specific to current project
	 
	 public void loginToApp(String Username, String Password)
	 {
		 UsernameEdt.sendKeys(Username);
		 PasswordEdt.sendKeys(Password);
		 SubmitBtn.click();
	 }
	 
	 
		
	
		
		
		public void loginToHome()
		{
			Contacts.click();
		}
	 
		
	 
	 
	 
	 
	 
	 
}
