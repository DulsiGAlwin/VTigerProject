package genericLibrary;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverUtility {

	private WebDriver driver;

	public WebDriver openingBrowserAndApplication(String url, long time) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		return driver;
	}
	public void mousehoverAction(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).perform();
	}
	public void dropDown(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	public void dropDown(String value,WebElement element) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	public void switchToWindow(String windowId) {
		driver.switchTo().window(windowId);
	}
	public String parentWindow() {
		return driver.getWindowHandle();	 
	}
	public void childBrowserPopup(String expectedTitle){
		Set<String> windowTitles = driver.getWindowHandles();
		for(String windowId :windowTitles )	{
			driver.switchTo().window(windowId);
			String actualTitle = driver.getTitle();
			if (actualTitle.contains(expectedTitle))
				break;
		}
	}
	public void closeBrowser() {
		driver.quit();
	}
}
