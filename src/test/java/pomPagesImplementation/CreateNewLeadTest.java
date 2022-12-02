package pomPagesImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibrary.AutoConstantPath;
import genericLibrary.ExcelUtility;
import genericLibrary.JavaUtility;
import genericLibrary.PropertyFileUtility;
import genericLibrary.WebDriverUtility;
import pomPages.CreateNewLead;
import pomPages.DuplicatingLead;
import pomPages.HomePage;
import pomPages.LeadsPage;
import pomPages.LoginPage;
import pomPages.NewLeadCreatedPageInfo;

public class CreateNewLeadTest {
	public static void main(String[] args) {
		ExcelUtility excel = new ExcelUtility();
		JavaUtility java =new JavaUtility();
		PropertyFileUtility property = new PropertyFileUtility();
		WebDriverUtility webdriver = new WebDriverUtility();

		excel.excelFileinitialisation(AutoConstantPath.EXCEL_FILE_PATH);
		property.propertyFileInitialization(AutoConstantPath.PROPERTIES_FILE_PATH);

		String url= property.getDataFromProperty("url");
		String time =property.getDataFromProperty("timeout");
		WebDriver driver = webdriver.openingBrowserAndApplication(url,Long.parseLong(time));

		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		LeadsPage leads = new LeadsPage(driver);
		CreateNewLead createNewlead = new CreateNewLead(driver);
		NewLeadCreatedPageInfo newleadInfo = new NewLeadCreatedPageInfo(driver);
		DuplicatingLead duplicateLead = new DuplicatingLead(driver);

		if(login.getLogoimage().isDisplayed())
			System.out.println("Pass: Login page is Displayed");
		else
			System.out.println("Fail: Login page  is not Displayed");

		String userName= property.getDataFromProperty("username");
		String password =property.getDataFromProperty("password");
		login.loginCredentials(userName, password);

		if(home.getHomepageHeaderText().contains("Home"))
			System.out.println("Pass: Homelink is matched");
		else
			System.out.println("Fail: Homelink is not matched");
		home.clickLeaadTab();

		if(leads.getLeadHeader().contains("Leads"))
			System.out.println("Pass: Leads page is present");
		else
			System.out.println("Fail: Leads page is not present");

		leads.clickPlusImage();
		if(createNewlead.getLeadsHeaderText().contains("Creating New Lead"))
			System.out.println("Pass: Creating New Lead is matched");
		else
			System.out.println("Fail: Creating New Lead is not matched");

		Map<String, String> map = excel.fetchDataFromExcel("Test", "Create Lead");
		WebElement salutation= driver.findElement(By.name("salutationtype"));
		String value = map.get("Salutation");
		webdriver.dropDown(value, salutation);
		String firstName = map.get("First Name");
		String lastName = map.get("Last Name")+java.generateRandomNum(100);
		createNewlead.setfirstNameText(firstName);
		createNewlead.setlastNameText(lastName);
		String company =map.get("Company");
		createNewlead.setCompanyText(company);
		createNewlead.clickSaveButton();

		if(newleadInfo.getleadHeader().contains(lastName))
			System.out.println("Pass: Lead info created");
		else
			System.out.println("Fail: Lead info not created");

		newleadInfo.clickDuplicateLink();
		if(duplicateLead.getDuplicateHeader().contains("Duplicating"))
			System.out.println("Pass: Duplicating info created");
		else
			System.out.println("Fail: Duplicating info not created");

		duplicateLead.clearLastNameText();
		String updatedName = map.get("New Last Name");
		duplicateLead.setLastNameText(updatedName);
		duplicateLead.clickSaveButton();

		if(newleadInfo.getleadHeader().contains(updatedName))
			System.out.println("Pass: Duplicate Lead info created");
		else
			System.out.println("Fail: Duplicate Lead info not created");

		createNewlead.clickleadLink();
		if(leads.getTableValidation().contains(updatedName)) {
			System.out.println("Testcase Passed");
			excel.writeCellData("Create Lead","Test","Pass",AutoConstantPath.EXCEL_FILE_PATH);
		}
		else {
			System.out.println("Testcase Failed");
			excel.writeCellData("Create Lead","Test","Fail",AutoConstantPath.EXCEL_FILE_PATH);
		}

		home.signoutApplication(webdriver);
		excel.closeWorkBook();
		webdriver.closeBrowser();
	}
}
