package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibrary.WebDriverUtility;

public class CreateNewOrganisationPage {

	@FindBy(className = "lvtHeaderText") private WebElement createNewOrgHeaderText;
	
	@FindBy(name = "accountname")private WebElement organisationNameText;
	
	@FindBy(name = "industry")private WebElement industryDropdown;
	
	@FindBy(xpath = "//input[@value='T']")private WebElement groupRadioButton;
	
	@FindBy(name = "assigned_group_id") private WebElement assignedToDropdown;
	
	@FindBy(xpath = "//input[@class='crmbutton small save'][1]")
    private WebElement saveButton;
	
	public CreateNewOrganisationPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	public String getCreateNewOrgHeaderText() {
		return createNewOrgHeaderText.getText();
	}
	public void setorganisationName(String organisationName) {
	organisationNameText.sendKeys(organisationName);
	}	
	public WebElement getOrganisationNameText() {
		return organisationNameText;
	}
	public void getIndustryDropdown(WebDriverUtility webdriver,String text ) {
		webdriver.dropDown(assignedToDropdown, text);
	}
	public void clickGroupRadioButton() {
		groupRadioButton.click();;
	}
	public void selectAssignedToDropdown(WebDriverUtility webdriver, String value ) {
		webdriver.dropDown(value,industryDropdown);;
	}
	public void clickSaveButton() {
		saveButton.click();;
	}

}
