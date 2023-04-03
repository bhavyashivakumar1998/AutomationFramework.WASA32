package vtiger.ContactsTests;

import java.io.IOException;


import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.ContactsInfoPage;
import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.CreateNewContactPage;
import vtiger.ObjectRepository.CreateNewOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.OrganizationsInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;

@Listeners(vtiger.GenericUtilities.ListenersImplimentation.class)

public class CreateContactWithOrganizationTest extends BaseClass {

	@Test(groups="RegressionSuite")
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException
	{
		/* read data from excel sheet - test data*/
		String ORGNAME = eUtil.readDataFromExcel("Contacts", 4, 2)+jUtil.getRandomNumber();
		String LASTNAME = eUtil.readDataFromExcel("Contacts", 4, 3);
		
		//click on organization link
		HomePage hp= new HomePage(driver);
		hp.clickOnOrganizationLnk();
		Reporter.log("click on organization link successful", true);
		
		//click on create organization look up image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgImg();
		Reporter.log("click on create organization look up image successful");
		
		//create organization with mandatory fields
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrganization(ORGNAME);
		Reporter.log("create organization with mandatory fields successful");
		
		
		
		//validate for organization
		OrganizationsInfoPage oip= new OrganizationsInfoPage(driver);
		String OrgHeader = oip.getOrgHeader();
	
		
		Assert.assertTrue(OrgHeader.contains(ORGNAME));
		System.out.println(OrgHeader+"-------Organization created-----");	
		
		//navigate to contacts link
		hp.clickOnContactsLnk();
		Reporter.log("navigate to conatcts link is successful");
		
		//click on create contact look up image
		ContactsPage cp= new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		Reporter.log("click on create contact look up image successsful");
		
		//create contact with mandatory fields
		CreateNewContactPage cncp= new CreateNewContactPage(driver);
		cncp.createNewContact(driver, LASTNAME, ORGNAME);
		Reporter.log("create contact with mandatory fields successful");
		
		
		// validate for contacts
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String contactHeader = cip.getContactHeader();
		
		Assert.assertTrue(contactHeader.contains(LASTNAME));
		System.out.println(contactHeader+"----contact created-----");


	}
	
	

	
}
