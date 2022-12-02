package hardCodedTestcase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewLead {

	public static void main(String[] args) {
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
		
		WebElement salutation= driver.findElement(By.name("salutationtype"));
		Select select =new Select(salutation);
		select.selectByValue("Dr.");
		driver.findElement(By.name("firstname")).sendKeys("Sam");
		driver.findElement(By.name("lastname")).sendKeys("Alwin");
		driver.findElement(By.xpath("//input[@type='text'][@name='company']")).sendKeys("TCS");
		driver.findElement(By.xpath("//input[@class='crmbutton small save'][1]")).click();

		String leadHeader=driver.findElement(By.className("dvHeaderText")).getText();
		if(leadHeader.contains("Alwin"))
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
		driver.findElement(By.name("lastname")).sendKeys("Kath");
		driver.findElement(By.xpath("//input[@class='crmbutton small save'][1]")).click();

		String duplicateleadHeader=driver.findElement(By.className("dvHeaderText")).getText();
		if(duplicateleadHeader.contains("Kath"))
			System.out.println("Pass: Duplicate Lead info created");
		else
			System.out.println("Fail: Duplicate Lead info not created");

		driver.findElement(By.xpath("//a[.='Leads']")).click();
		String duplicatename= driver.findElement(By.xpath("//table[@class='lvt small']//tbody//tr[last()]//td[3]")).getText();
		if(duplicatename.contains("Kath")) 
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
