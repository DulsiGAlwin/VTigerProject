package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {
	
	@FindBy(xpath = "//a[@class='hdrLink']")private WebElement leadHeader;
	
	@FindBy(xpath = "//img[@title='Create Lead...']") private WebElement plusImage;
	
	@FindBy(xpath= "//table[@class='lvt small']/tbody/tr[last()]/td[3]")
	private WebElement tableValidation;
	
	public LeadsPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	public String getLeadHeader() {
		return leadHeader.getText();
	}
	public void clickPlusImage() {
		plusImage.click();;
	}
	public String getTableValidation() {
		return tableValidation.getText();
	}
	
}
