package hardCodedTestcase;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewContact {

	public static void main(String[] args) throws InterruptedException {
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();
	driver.get("http://localhost:8888/index.php?action=Login&module=Users");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	WebElement title=driver.findElement(By.xpath("//img[@alt='logo']"));
	if(title.isDisplayed())
		System.out.println("Pass: Login page is Displayed");
	else
		System.out.println("Fail: Login page  is not Displayed");
	
	driver.findElement(By.name("user_name")).sendKeys("admin");
	driver.findElement(By.name("user_password")).sendKeys("admin");
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
	
	WebElement salutation= driver.findElement(By.name("salutationtype"));
	Select select =new Select(salutation);
	select.selectByValue("Prof.");
	driver.findElement(By.name("firstname")).sendKeys("Dulsi");
	driver.findElement(By.name("lastname")).sendKeys("Alwin");
	
	driver.findElement(By.xpath("//input[@name='account_name']/..//img[@src='themes/softed/images/select.gif']")).click();
	String parentwindow =driver.getWindowHandle();
	Set<String> allwindow = driver.getWindowHandles();
	for(String childwindow :allwindow) {
		driver.switchTo().window(childwindow);
	}
	driver.findElement(By.xpath("//a[contains(text(),'CTS')]")).click();
	driver.switchTo().window(parentwindow);
	driver.findElement(By.name("imagename")).sendKeys("C:\\Users\\dulsi\\OneDrive\\Desktop\\cross.png");
	driver.findElement(By.xpath("//input[@class='crmbutton small save'][1]")).click();

	String contactInfoHeader=driver.findElement(By.className("dvHeaderText")).getText();
	if(contactInfoHeader.contains("Alwin"))
		System.out.println("Pass: Contact Created");
	else
		System.out.println("Fail: Contact not Created");
	
	driver.findElement(By.xpath("//a[.='Contacts']")).click();
	String contactInfo= driver.findElement(By.xpath("//table[@class='lvt small']//tbody//tr[last()]//td[4]")).getText();
	if(contactInfo.equalsIgnoreCase("Alwin")) 
		System.out.println("Testcase Passed");
	else
		System.out.println("Testcase Failed");

	WebElement signout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	Actions a = new Actions(driver);
	a.moveToElement(signout).perform();
	driver.findElement(By.xpath("//a[.='Sign Out']")).click();
	driver.quit();

	
	
	
	}

}
