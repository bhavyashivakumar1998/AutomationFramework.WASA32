package vtiger.ContactsTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.ContactsInfoPage;
import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.CreateNewContactPage;
import vtiger.ObjectRepository.HomePage;


public class CreateContactTest extends BaseClass {

	@Test
	public void CreateContactTest() throws EncryptedDocumentException, IOException
	 {
		
	         	String LASTNAME = eUtil.readDataFromExcel("Contacts", 4, 3);
				
				//step 3: navigate to contacts
				HomePage hp = new HomePage(driver);
				hp.clickOnContactsLnk();
				
				//step 4: click on create contact lookup image

                  ContactsPage cp = new ContactsPage(driver);
                  cp.clickOnCreateContactImg();
				
				//step 5: create contact with mandatory fields and save it
				CreateNewContactPage cncp = new CreateNewContactPage(driver);
				cncp.createNewContact(LASTNAME);
				cncp.SaveContact();
				
				//step 6: verification for contact
				ContactsInfoPage cip = new ContactsInfoPage(driver);
				String contactHeader = cip.getContactHeader();
				
				Assert.assertTrue(contactHeader.contains(LASTNAME));
				System.out.println(contactHeader+"----contact created-----");

				
				
	}
	
	
}
