package hardCodedTestcase;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganisation {
	public static void main(String[] args) throws InterruptedException {
		Random random =new Random();
		int randomNum =random.nextInt(100);

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

		String accountName= "CTS"+randomNum;
		driver.findElement(By.name("accountname")).sendKeys(accountName);
		WebElement dropdown =driver.findElement(By.name("industry"));
		Select select =new Select(dropdown);
		select.selectByValue("Biotechnology");
		driver.findElement(By.xpath("//input[@value='T']")).click();
		WebElement assignedDropdown=driver.findElement(By.name("assigned_group_id"));
		Select assign = new Select(assignedDropdown);
		assign.selectByVisibleText("Marketing Group");
		driver.findElement(By.xpath("//input[@class='crmbutton small save'][1]")).click();

		String ctsHeader=driver.findElement(By.className("dvHeaderText")).getText();
		if(ctsHeader.contains(accountName))
			System.out.println("Pass: Creating CTS Organization is matched");
		else
			System.out.println("Fail: Creating CTS Organization is not matched");

		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		String organizationname= driver.findElement(By.xpath("//table[@class='lvt small']//tbody//tr[last()]//td[3]")).getText();
		if(organizationname.contains(accountName)) 
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
