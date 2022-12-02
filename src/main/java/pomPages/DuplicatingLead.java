package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DuplicatingLead {
	
	@FindBy(className = "lvtHeaderText") private WebElement duplicateHeader;
	
	@FindBy(name = "lastname") private WebElement lastNameText;
	
	@FindBy(xpath = "//input[@class='crmbutton small save'][1]")
	private WebElement saveButton;
	
	public DuplicatingLead(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
    public String getDuplicateHeader() {
		return duplicateHeader.getText();
	}
	public void clearLastNameText() {
		lastNameText.clear();;
	}
	public void setLastNameText(String updatedName) {
		lastNameText.sendKeys(updatedName);;
	}

	public void clickSaveButton() {
		saveButton.click();;
	}

}
