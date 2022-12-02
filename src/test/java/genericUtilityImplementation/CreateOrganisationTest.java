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

		WebElement title=driver.findElement(By.xpath("//img[@alt='logo']"));
		if(title.isDisplayed())
			System.out.println("Pass: Login page is Displayed");
		else
			System.out.println("Fail: Login page  is not Displayed");

		String username = property.getDataFromProperty("username");
		String password =property.getDataFromProperty("password");
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		String homeLink=driver.findElement(By.className("hdrLink")).getText();
		if(homeLink.contains("Home"))
			System.out.println("Pass: Homelink is matched");
		else
			System.out.println("Fail: Homelink is not matched");

		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		String organizationHeader=driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
		if(organizationHeader.contains("Organizations"))
			System.out.println("Pass: Organizations is matched");
		else
			System.out.println("Fail: Organizations is not matched");

		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		String createOrganizationHeader=driver.findElement(By.className("lvtHeaderText")).getText();
		if(createOrganizationHeader.contains("Creating New Organization"))
			System.out.println("Pass: Creating New Organization is matched");
		else
			System.out.println("Fail: Creating New Organization is not matched");

		Map<String,String> map= excel.fetchDataFromExcel("Test","Create Organization");
		String accountName=map.get("Organization Name")+java.generateRandomNum(100);
		driver.findElement(By.name("accountname")).sendKeys(accountName);
		WebElement industrydropdown =driver.findElement(By.name("industry"));
		webdriver.dropDown(map.get("Industry"), industrydropdown);
		driver.findElement(By.xpath("//input[@value='T']")).click();
		WebElement assignedDropdown=driver.findElement(By.name("assigned_group_id"));
		webdriver.dropDown(assignedDropdown,map.get("Group"));
		driver.findElement(By.xpath("//input[@class='crmbutton small save'][1]")).click();

		String ctsHeader=driver.findElement(By.className("dvHeaderText")).getText();
		if(ctsHeader.contains(accountName))
			System.out.println("Pass: Creating CTS Organization is matched");
		else
			System.out.println("Fail: Creating CTS Organization is not matched");

		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		String organizationname= driver.findElement(By.xpath("//table[@class='lvt small']//tbody//tr[last()]//td[3]")).getText();
		if(organizationname.contains(accountName)) {
			System.out.println("Testcase Passed");
			excel.writeCellData("Create Organization", "Test", "Pass", AutoConstantPath.EXCEL_FILE_PATH);
		}
		else {
			System.out.println("Testcase Failed");
			excel.writeCellData("Create Organization", "Test", "Fail",AutoConstantPath.EXCEL_FILE_PATH);
		}

		WebElement signout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriver.mousehoverAction(signout);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		excel.closeWorkBook();
		webdriver.closeBrowser();
	}
}
