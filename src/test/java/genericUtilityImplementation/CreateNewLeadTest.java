package genericUtilityImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import genericLibrary.AutoConstantPath;
import genericLibrary.ExcelUtility;
import genericLibrary.JavaUtility;
import genericLibrary.PropertyFileUtility;
import genericLibrary.WebDriverUtility;

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

		WebElement title=driver.findElement(By.xpath("//img[@alt='logo']"));
		if(title.isDisplayed())
			System.out.println("Pass: Login page is Displayed");
		else
			System.out.println("Fail: Login page  is not Displayed");

		String userName= property.getDataFromProperty("username");
		String password =property.getDataFromProperty("password");
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		String homeLink=driver.findElement(By.className("hdrLink")).getText();
		if(homeLink.contains("Home"))
			System.out.println("Pass: Homelink is matched");
		else
			System.out.println("Fail: Homelink is not matched");

		driver.findElement(By.xpath("//a[.='Leads']")).click();
		String leadsHeader=driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
		if(leadsHeader.contains("Leads"))
			System.out.println("Pass: Leads page is present");
		else
			System.out.println("Fail: Leads page is not present");

		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		String createLeadHeader=driver.findElement(By.className("lvtHeaderText")).getText();
		if(createLeadHeader.contains("Creating New Lead"))
			System.out.println("Pass: Creating New Lead is matched");
		else
			System.out.println("Fail: Creating New Lead is not matched");

		Map<String, String> map = excel.fetchDataFromExcel("Test", "Create Lead");
		WebElement salutation= driver.findElement(By.name("salutationtype"));
		String value = map.get("Salutation");
		webdriver.dropDown(value, salutation);
		String firstName = map.get("First Name");
		String lastName = map.get("Last Name")+java.generateRandomNum(100);
		driver.findElement(By.name("firstname")).sendKeys(firstName);
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		String company =map.get("Company");
		driver.findElement(By.xpath("//input[@type='text'][@name='company']")).sendKeys(company);
		driver.findElement(By.xpath("//input[@class='crmbutton small save'][1]")).click();

		String leadHeader=driver.findElement(By.className("dvHeaderText")).getText();
		if(leadHeader.contains(lastName))
			System.out.println("Pass: Lead info created");
		else
			System.out.println("Fail: Lead info not created");

		driver.findElement(By.xpath("//td[@class='dvtTabCache']//input[@accesskey='U']")).click();
		String duplicateHeader=driver.findElement(By.className("lvtHeaderText")).getText();
		if(duplicateHeader.contains("Duplicating"))
			System.out.println("Pass: Duplicating info created");
		else
			System.out.println("Fail: Duplicating info not created");

		driver.findElement(By.name("lastname")).clear();
		String updatedName = map.get("New Last Name");
		driver.findElement(By.name("lastname")).sendKeys(updatedName);
		driver.findElement(By.xpath("//input[@class='crmbutton small save'][1]")).click();

		String duplicateleadHeader=driver.findElement(By.className("dvHeaderText")).getText();
		if(duplicateleadHeader.contains(updatedName))
			System.out.println("Pass: Duplicate Lead info created");
		else
			System.out.println("Fail: Duplicate Lead info not created");

		driver.findElement(By.xpath("//a[.='Leads']")).click();
		String duplicatename= driver.findElement(By.xpath("//table[@class='lvt small']//tbody//tr[last()]//td[3]")).getText();
		if(duplicatename.contains(updatedName)) {
			System.out.println("Testcase Passed");
			excel.writeCellData("Create Lead","Test","Pass",AutoConstantPath.EXCEL_FILE_PATH);
		}
		else {
			System.out.println("Testcase Failed");
			excel.writeCellData("Create Lead","Test","Fail",AutoConstantPath.EXCEL_FILE_PATH);
		}

		WebElement signout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriver.mousehoverAction(signout);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		excel.closeWorkBook();
		webdriver.closeBrowser();
	}
}
