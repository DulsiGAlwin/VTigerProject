
package pomPagesImplementation;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import genericLibrary.AutoConstantPath;
import genericLibrary.ExcelUtility;
import genericLibrary.JavaUtility;
import genericLibrary.PropertyFileUtility;
import genericLibrary.WebDriverUtility;
import pomPages.ContactPage;
import pomPages.CreateNewContactPage;
import pomPages.HomePage;
import pomPages.LoginPage;
import pomPages.NewContactCreatedPageInfo;

public class CreateNewContactTest {
	public static void main(String[] args){

		PropertyFileUtility property =new PropertyFileUtility();
		ExcelUtility excel = new ExcelUtility();
		JavaUtility java = new JavaUtility();
		WebDriverUtility webdriver = new WebDriverUtility();

		property.propertyFileInitialization(AutoConstantPath.PROPERTIES_FILE_PATH);
		excel.excelFileinitialisation(AutoConstantPath.EXCEL_FILE_PATH);

		String url = property.getDataFromProperty("url");
		String time = property.getDataFromProperty("timeout");
		WebDriver driver = webdriver.openingBrowserAndApplication(url,Long.parseLong(time));

		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		ContactPage contact = new ContactPage(driver);
		CreateNewContactPage createNewContact = new CreateNewContactPage(driver);
		NewContactCreatedPageInfo newContactInfo = new NewContactCreatedPageInfo(driver);

		if(login.getLogoimage().isDisplayed())
			System.out.println("Pass: Login page is Displayed");
		else
			System.out.println("Fail: Login page  is not Displayed");

		String userName = property.getDataFromProperty("username");
		String password = property.getDataFromProperty("password");
		login.loginCredentials(userName, password);
		
         if(home.getHomepageHeaderText().contains("Home"))
			System.out.println("Pass: Homelink is matched");
		else
			System.out.println("Fail: Homelink is not matched");
home.clickContactsTab();

		if(contact.getContactHeader().contains("Contacts"))
			System.out.println("Pass: Contacts page is matched");
		else
			System.out.println("Fail: Contacts page is not matched");
contact.clickPlusImage();

		if(createNewContact.getCreateContactHeader().contains("Creating New Contact"))
			System.out.println("Pass: Creating Contacts is matched");
		else
			System.out.println("Fail: Creating Contacts is not matched");

		Map <String,String> map= excel.fetchDataFromExcel("Test","Create Contact");
		String value = map.get("Salutation");
		createNewContact.selectSalutationDropdown(webdriver,value);
		String firstName=map.get("First Name");
		String lastName=map.get("Last Name")+java.generateRandomNum(100);
		createNewContact.setFirstNameText(firstName);
		createNewContact.setLastameText(lastName);
		createNewContact.selectExistingOrg(webdriver, driver, map.get("Organisation Name"));
		createNewContact.setUploadImage(map.get("Contact Image"));
		createNewContact.clickSaveButton();

		if(newContactInfo.getContactInfoHeader().contains(lastName)) 
			System.out.println("Pass: Contact Created");	
		else 
			System.out.println("Fail: Contact not Created");
		newContactInfo.clickContactLink();

		if(contact.getTableValidation().contains(lastName)) {
			System.out.println("Testcase Passed");
			excel.writeCellData("Create Contact", "Test","Pass", AutoConstantPath.EXCEL_FILE_PATH);
		}
		else {
			System.out.println("Testcase Failed");
			excel.writeCellData("Create Contact", "Test","Fail", AutoConstantPath.EXCEL_FILE_PATH);
		}

		home.signoutApplication(webdriver);
		excel.closeWorkBook();
		webdriver.closeBrowser();
	}
}
