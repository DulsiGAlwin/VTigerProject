package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewLeadCreatedPageInfo {

	@FindBy(className = "dvHeaderText")private WebElement leadHeader;
	
	@FindBy(xpath = "//td[@class='dvtTabCache']//input[@accesskey='U']")
	private WebElement duplicateLink;
	
	@FindBy(xpath = "//a[.='Leads']") private WebElement leadLink;
	
	@FindBy(xpath = "//table[@class='lvt small']/tbody/tr[last()]/td[3]")
	private WebElement tableValidation;
	
	public NewLeadCreatedPageInfo(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	public String getleadHeader() {
		return leadHeader.getText();
	}

	public void clickDuplicateLink() {
		duplicateLink.click();;
	}
	public void clickLeadLink() {
		leadLink.click();;
	}
	public String gettableValidation() {
		return tableValidation.getText();
	}

}
