package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewContactCreatedPageInfo {

	@FindBy(className = "dvHeaderText")private WebElement contactInfoHeader;
	
	@FindBy(xpath = "//a[.='Contacts']") private WebElement contactLink;
	
	public NewContactCreatedPageInfo(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	public String getContactInfoHeader() {
		return contactInfoHeader.getText();
	}

	public void clickContactLink() {
		contactLink.click();;
	}
	
}
