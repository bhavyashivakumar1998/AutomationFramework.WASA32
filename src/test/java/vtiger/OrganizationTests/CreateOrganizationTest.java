package vtiger.OrganizationTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.CreateNewOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
import vtiger.ObjectRepository.OrganizationsInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;

@Listeners(vtiger.GenericUtilities.ListenersImplimentation.class)
public class CreateOrganizationTest extends BaseClass {

	@Test//(groups="SmokeSuite")
	public void CreateOrganizationTest() throws EncryptedDocumentException, IOException
	 {
		
		
		String ORGNAME = eUtil.readDataFromExcel("Organizations", 1, 2)+jUtil.getRandomNumber();
		
		
		

		//step 2: login to application with valid credentials
		
		
		//step 3: naviagte to organization link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLnk();
		
		//step 4: click on create organization look up image

       OrganizationsPage op = new OrganizationsPage(driver);
       op.clickOnCreateOrgImg();
		
		//step 5: create organization with mandatory fields and save

       CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrganization(ORGNAME);
		
		
		// step 7: verification for organization
		OrganizationsInfoPage oip= new OrganizationsInfoPage(driver);
		String OrgHeader = oip.getOrgHeader();
		
		Assert.assertTrue(OrgHeader.contains(ORGNAME));
		System.out.println(OrgHeader+"-------Organization created-----");
		
	
		
	}



}
