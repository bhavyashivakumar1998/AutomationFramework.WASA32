package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtilities.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility {

	//declaration
	@FindBy(name="accountname")
	private WebElement OrgNameEdt;
	
	@FindBy(name="industry")
	private WebElement IndustryDropDown;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	//initialization
	public CreateNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	
	//utilization

	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}

	

	public WebElement getIndustryDropDown() {
		return IndustryDropDown;
	}

	
	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	
	//Business libarray
	public void createOrganization(String OrgName)
	{
		OrgNameEdt.sendKeys(OrgName);
		SaveBtn.click();
		
	}
	
	
	public void createOrganization(String OrgName, String InsutryType)
	{
		OrgNameEdt.sendKeys(OrgName);
		handleDropDown(IndustryDropDown, InsutryType);
		SaveBtn.click();
	}
	
	
}
