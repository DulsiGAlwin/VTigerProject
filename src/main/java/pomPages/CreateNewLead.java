package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibrary.WebDriverUtility;

public class CreateNewLead {

	@FindBy(className = "lvtHeaderText") private WebElement leadsHeaderText;
	
	@FindBy(name = "salutationtype") private WebElement salutationDropDown;
	
	@FindBy(name = "firstname")private WebElement firstNameText;

	@FindBy(name = "lastname")private WebElement lastNameText;
	
	@FindBy(xpath = "//input[@type='text'][@name='company']")
	private WebElement companyText;
	
	@FindBy(xpath = "//input[@class='crmbutton small save'][1]")
	private WebElement saveButton;
	
	@FindBy(xpath = "//a[.='Leads']") private WebElement leadLink;
	
	public CreateNewLead(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	public String getLeadsHeaderText() {
		return leadsHeaderText.getText();
	}

	public void getSalutationDropDown(WebDriverUtility webdriver, String value) {
		webdriver.dropDown(value, salutationDropDown);;
	}
	public void setfirstNameText(String firstName) {
		firstNameText.sendKeys(firstName);;
	}
	public void setlastNameText(String lastName) {
		lastNameText.sendKeys(lastName);;
	}
	public void setCompanyText(String companyName) {
		companyText.sendKeys(companyName);;
	}
	public void clickSaveButton() {
		saveButton.click();;
	}
	public void clickleadLink() {
		leadLink.click();;
	}

}
