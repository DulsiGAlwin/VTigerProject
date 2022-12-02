package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	//Declaration
	@FindBy(className = "hdrLink") private WebElement contactHeader;
	
	@FindBy(xpath = "//img[@title='Create Contact...']") 
	private WebElement plusImage;
	
	@FindBy(xpath = "//table[@class='lvt small']//tbody//tr[last()]//td[4]")
	private WebElement tableValidation;
	
	//Initialization
	public ContactPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	//Business Libraries
	public String getContactHeader() {
		return contactHeader.getText();
	}

	public void clickPlusImage() {
		plusImage.click();;
	}	
	public String getTableValidation() {
		return tableValidation.getText();
	}

}
