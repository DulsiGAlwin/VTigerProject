package genericLibrary;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import pomPages.ContactPage;
import pomPages.CreateNewContactPage;
import pomPages.CreateNewLead;
import pomPages.CreateNewOrganisationPage;
import pomPages.DuplicatingLead;
import pomPages.HomePage;
import pomPages.LeadsPage;
import pomPages.LoginPage;
import pomPages.NewContactCreatedPageInfo;
import pomPages.NewLeadCreatedPageInfo;
import pomPages.NewOrganisationCreatedPageInfo;
import pomPages.OrganisationPage;

public class BaseClass {
	protected ExcelUtility excel;
	protected PropertyFileUtility property;
	protected WebDriverUtility webdriver;
	protected JavaUtility java;
	protected WebDriver driver;
	protected LoginPage login;
	protected HomePage home;
	protected OrganisationPage org;
	protected CreateNewOrganisationPage createNewOrg;
	protected NewOrganisationCreatedPageInfo newOrgInfo;
	protected ContactPage contact;
	protected CreateNewContactPage createNewContact;
	protected NewContactCreatedPageInfo newContactInfo;
	protected LeadsPage leads;
	protected CreateNewLead createNewlead;
	protected NewLeadCreatedPageInfo newleadInfo;
	protected DuplicatingLead duplicateLead;
	
	//@BeforeSuite
	@BeforeTest
	public void testSetup() {
		excel = new ExcelUtility();
		property = new PropertyFileUtility();
		webdriver = new WebDriverUtility();
		java = new JavaUtility();

		excel.excelFileinitialisation(AutoConstantPath.EXCEL_FILE_PATH);
		property.propertyFileInitialization(AutoConstantPath.PROPERTIES_FILE_PATH);

	}
	@BeforeClass
	public void classSetUp() {
		String url = property.getDataFromProperty("url");
		String time = property.getDataFromProperty("timeout");
		long timeout = Long.parseLong(time);

		driver = webdriver.openingBrowserAndApplication(url, timeout);

		login = new LoginPage(driver);
		home = new HomePage(driver);
		org = new OrganisationPage(driver);
		createNewOrg = new CreateNewOrganisationPage(driver);
		newOrgInfo = new NewOrganisationCreatedPageInfo(driver);
		contact = new ContactPage(driver);
		createNewContact = new CreateNewContactPage(driver);
		newContactInfo = new NewContactCreatedPageInfo(driver);
		leads = new LeadsPage(driver);
		createNewlead = new CreateNewLead(driver);
		newleadInfo = new NewLeadCreatedPageInfo(driver);
		duplicateLead = new DuplicatingLead(driver);
		
		Assert.assertTrue(login.getLogoimage().isDisplayed());
	}
	@BeforeMethod
	public void methodSetUp() {
		String username = property.getDataFromProperty("username");
		String password = property.getDataFromProperty("password");

		login.loginCredentials(username, password);
		
		Assert.assertTrue(home.getHomepageHeaderText().contains("Home"));
	}
	//@Test
	@AfterMethod
	public void methodTearDown() {
		home.signoutApplication(webdriver);
	}
	@AfterClass
	public void classTearDown() {
		webdriver.closeBrowser();
	}
	@AfterTest
	public void testTearDown() {
		excel.closeWorkBook();
	}
	//@AfterSuite




}
