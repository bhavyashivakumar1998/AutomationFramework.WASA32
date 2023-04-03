package vtiger.GenericUtilities;

import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;

/**
 * this class consists of basic configuration annotations of TestNG
 * @author admin
 *
 */
public class BaseClass {

	public PropertyFileUtility pUtil= new PropertyFileUtility();
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public JavaUtility jUtil = new JavaUtility();
	public static WebDriver sDriver; // listeners
	
	public WebDriver driver;
	
	@BeforeSuite(groups= {"SmokeSuite","RegressionSuite","RegionalRegressionsuite"})
	public void bsConfig()
	{
		System.out.println("---- database connection successful----");
	}
	
	//@Parameters("browser")
	//@BeforeTest
	@BeforeClass(/*groups= {"SmokeSuite","RegressionSuite","RegionalRegressionsuite"}*/)
	public void bcConfig(/*String BROWSER*/) throws IOException
	{
		String URL = pUtil.readDataFromPropertyFile("url");
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			
			driver = new ChromeDriver();
			System.out.println("----"+BROWSER+" Launches Successfully------");
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
						driver = new FirefoxDriver();
						System.out.println("-------"+BROWSER+" Launches successfully");
		}
		else
		{
			System.out.println("invalid browser name");
		}
		
		sDriver=driver; // listeners
		wUtil.maximizewindow(driver);
		wUtil.waitForPage(driver);
		driver.get(URL);
	}
	
	@BeforeMethod(groups= {"SmokeSuite","RegressionSuite","RegionalRegressionsuite"})
	public void bmConfig() throws IOException
	{
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		System.out.println("-----Login Successful-----");
	}
	
	@AfterMethod(groups= {"SmokeSuite","RegressionSuite","RegionalRegressionsuite"})
	public void amConfig()
	{
		HomePage hp= new HomePage(driver);
		hp.logoutOfApp(driver);
		System.out.println("----Logout Successful-------");
	}
	
	@AfterClass(groups= {"SmokeSuite","RegressionSuite","RegionalRegressionsuite"})
	public void acConfig()
	{
		driver.quit();
		System.out.println("---- browser closed successfully-----");
	}
	
	@AfterSuite(groups= {"SmokeSuite","RegressionSuite","RegionalRegressionsuite"})
	public void asConfig()
	{
		System.out.println("----- Database connection successful");
	}
	
	
	
}
