package pomPagesImplementation;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import genericLibrary.AutoConstantPath;
import genericLibrary.ExcelUtility;
import genericLibrary.JavaUtility;
import genericLibrary.PropertyFileUtility;
import genericLibrary.WebDriverUtility;
import pomPages.CreateNewOrganisationPage;
import pomPages.HomePage;
import pomPages.LoginPage;
import pomPages.NewOrganisationCreatedPageInfo;
import pomPages.OrganisationPage;

public class CreateOrganisationTest {
	public static void main(String[] args){
		ExcelUtility excel = new ExcelUtility();
		JavaUtility java = new JavaUtility();
		PropertyFileUtility property = new PropertyFileUtility();
		WebDriverUtility webdriver = new WebDriverUtility();

		property.propertyFileInitialization(AutoConstantPath.PROPERTIES_FILE_PATH);
		excel.excelFileinitialisation(AutoConstantPath.EXCEL_FILE_PATH);

		String url = property.getDataFromProperty("url");
		String time = property.getDataFromProperty("timeout");
		WebDriver driver = webdriver.openingBrowserAndApplication(url, Long.parseLong(time));

		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		OrganisationPage org = new OrganisationPage(driver);
		CreateNewOrganisationPage createNewOrg = new CreateNewOrganisationPage(driver);
		NewOrganisationCreatedPageInfo newOrgInfo = new NewOrganisationCreatedPageInfo(driver);

		if(login.getLogoimage().isDisplayed())
			System.out.println("Pass: Login page is Displayed");
		else
			System.out.println("Fail: Login page  is not Displayed");

		String username = property.getDataFromProperty("username");
		String password =property.getDataFromProperty("password");

		login.loginCredentials(username, password);

		if(home.getHomepageHeaderText().contains("Home"))
			System.out.println("Pass: Homelink is matched");
		else
			System.out.println("Fail: Homelink is not matched");

		home.clickOrganisationTab();
		if(org.getOrgnisationHeader().contains("Organizations"))
			System.out.println("Pass: Organizations is matched");
		else
			System.out.println("Fail: Organizations is not matched");

		org.clickPlusImage();
		if(createNewOrg.getCreateNewOrgHeaderText().contains("Creating New Organization"))
			System.out.println("Pass: Creating New Organization is matched");
		else
			System.out.println("Fail: Creating New Organization is not matched");

		Map<String,String> map= excel.fetchDataFromExcel("Test","Create Organization");
		String accountName=map.get("Organization Name")+java.generateRandomNum(100);
		createNewOrg.setorganisationName(accountName);
		createNewOrg.selectAssignedToDropdown(webdriver, map.get("Industry"));
		createNewOrg.clickGroupRadioButton();
		createNewOrg.getIndustryDropdown(webdriver, map.get("Group"));
		createNewOrg.clickSaveButton();

		if(newOrgInfo.getNewOrgHeader().contains(accountName))
			System.out.println("Pass: Creating new Organization is matched");
		else
			System.out.println("Fail: Creating new Organization is not matched");

		newOrgInfo.clickOrgLink();

		if(org.getNewOrganisation().contains(accountName)) {
			System.out.println("Testcase Passed");
			excel.writeCellData("Create Organisation", "Test","Pass", AutoConstantPath.EXCEL_FILE_PATH);
		}
		else {
			System.out.println("Testcase Failed");
			excel.writeCellData("Create Organisation", "Test","Fail", AutoConstantPath.EXCEL_FILE_PATH);
		}

		home.signoutApplication(webdriver);
		excel.closeWorkBook();
		webdriver.closeBrowser();
	}
}
