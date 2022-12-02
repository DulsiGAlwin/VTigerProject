
package genericUtilityImplementation;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import genericLibrary.AutoConstantPath;
import genericLibrary.ExcelUtility;
import genericLibrary.JavaUtility;
import genericLibrary.PropertyFileUtility;
import genericLibrary.WebDriverUtility;

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

		WebElement title=driver.findElement(By.xpath("//img[@alt='logo']"));
		if(title.isDisplayed())
			System.out.println("Pass: Login page is Displayed");
		else
			System.out.println("Fail: Login page  is not Displayed");

		String userName = property.getDataFromProperty("username");
		String password = property.getDataFromProperty("password");
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		String homeLink=driver.findElement(By.className("hdrLink")).getText();
		if(homeLink.contains("Home"))
			System.out.println("Pass: Homelink is matched");
		else
			System.out.println("Fail: Homelink is not matched");

		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		String contactsHeader=driver.findElement(By.className("hdrLink")).getText();
		if(contactsHeader.contains("Contacts"))
			System.out.println("Pass: Contacts page is matched");
		else
			System.out.println("Fail: Contacts page is not matched");

		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		String createContactHeader=driver.findElement(By.className("lvtHeaderText")).getText();
		if(createContactHeader.contains("Creating New Contact"))
			System.out.println("Pass: Creating Contacts is matched");
		else
			System.out.println("Fail: Creating Contacts is not matched");

		Map <String,String> map= excel.fetchDataFromExcel("Test","Create Contact");
		String value = map.get("Salutation");
		WebElement salutation= driver.findElement(By.name("salutationtype"));
		webdriver.dropDown(value, salutation);
		String firstName=map.get("First Name");
		String lastName=map.get("Last Name")+java.generateRandomNum(100);
		driver.findElement(By.name("firstname")).sendKeys(firstName);
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@name='account_name']/..//img[@src='themes/softed/images/select.gif']")).click();

		String parentwindow =webdriver.parentWindow();
		webdriver.childBrowserPopup("Organizations");
		String organisationName= map.get("Organisation Name");
		String orgTableRowPath = "//table[@class='small'][@cellpadding=\"5\"]/tbody/tr";
		List<WebElement> organizationList =driver.findElements(By.xpath(orgTableRowPath));
		for(int i = 2; i<organizationList.size(); i++) {
			WebElement organization = driver.findElement(By.xpath(orgTableRowPath+"["+i+"]/td[1]"));
			String organizationsName = organization.getText();
			if(organizationsName.equals(organisationName)) {
				organization.click();
				break;
			}
		}
		webdriver.switchToWindow(parentwindow);
		WebElement contactImage = driver.findElement(By.name("imagename"));
		contactImage.sendKeys(map.get("Contact Image"));
		driver.findElement(By.xpath("//input[@class='crmbutton small save'][1]")).click();

		String contactInfoHeader=driver.findElement(By.className("dvHeaderText")).getText();
		if(contactInfoHeader.contains(lastName)) 
			System.out.println("Pass: Contact Created");	
		else 
			System.out.println("Fail: Contact not Created");

		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		String contactInfo= driver.findElement(By.xpath("//table[@class='lvt small']//tbody//tr[last()]//td[4]")).getText();
		if(contactInfo.contains(lastName)) {
			System.out.println("Testcase Passed");
			excel.writeCellData("Create Contact", "Test", "Pass", AutoConstantPath.EXCEL_FILE_PATH);
		}
		else {
			System.out.println("Testcase Failed");
			excel.writeCellData("Create Contact", "Test", "Fail", AutoConstantPath.EXCEL_FILE_PATH);
		}

		WebElement signout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriver.mousehoverAction(signout);

		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		excel.closeWorkBook();
		webdriver.closeBrowser();
	}
}
