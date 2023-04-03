package vtiger.OrganizationTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.CreateNewOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.OrganizationsInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;

public class CreateMultipleOrgWithInustryTest extends BaseClass {

	@Test(dataProvider="getData")
	public void CreateMultipleOrgTest(String org, String INDUSTRY)
	{
		String ORGNAME= org+jUtil.getRandomNumber();
	   HomePage hp = new HomePage(driver);
	   hp.clickOnOrganizationLnk();
	   
	   OrganizationsPage op = new OrganizationsPage(driver);
	   op.clickOnCreateOrgImg();
	   
	   CreateNewOrganizationPage cnop= new CreateNewOrganizationPage(driver);
	   cnop.createOrganization(ORGNAME, INDUSTRY);
	   
	   OrganizationsInfoPage oip= new OrganizationsInfoPage(driver);
	   String OrgHeader = oip.getOrgHeader();
	   Assert.assertTrue(OrgHeader.contains(ORGNAME));
	}
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		Object[][] data= eUtil.readMultipleData("Multiple");
		return data;
	}
	
	
	
}
