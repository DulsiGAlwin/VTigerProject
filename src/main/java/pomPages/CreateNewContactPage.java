package pomPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibrary.WebDriverUtility;

public class CreateNewContactPage {
	//Declaration
	@FindBy(className = "lvtHeaderText") private WebElement createContactHeader;

	@FindBy(name = "salutationtype") private WebElement salutationDropdown;

	@FindBy(name = "firstname") private WebElement firstNameText;

	@FindBy(name = "lastname") private WebElement lastameText;

	@FindBy(xpath = "//input[@name='account_name']/..//img[@src='themes/softed/images/select.gif']")
	private WebElement orgPlusButtonImage;

	private String orgTableRowPath = "//table[@class='small'][@cellpadding=\"5\"]/tbody/tr[%d]/td[1]";

	@FindBy(xpath = "//table[@class='small'][@cellpadding=\"5\"]/tbody/tr")
	private List <WebElement> organisationList;

	@FindBy(name = "imagename") private WebElement uploadImage;

	@FindBy(xpath = "//input[@class='crmbutton small save'][1]")
	private WebElement saveButton;

	//Initialization
	public CreateNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	//Business Libraries
	public String getCreateContactHeader() {
		return createContactHeader.getText();
	}
	public void selectSalutationDropdown(WebDriverUtility webdriver,String value ) {
		webdriver.dropDown(value ,salutationDropdown);
	}
	public void setFirstNameText(String firstname) {
		firstNameText.sendKeys(firstname);
	}
	public void setLastameText(String lastname) {
		lastameText.sendKeys(lastname);
	}
	public void selectExistingOrg(WebDriverUtility webdriver, WebDriver driver, String requiredOrganizationName) {
		orgPlusButtonImage.click();
		String parentWindow = webdriver.parentWindow();
		webdriver.childBrowserPopup("Organizations");

		for(int i=2; i<organisationList.size(); i++) {
			String requiredPath = String.format(orgTableRowPath, i);
			WebElement organization = driver.findElement(By.xpath(requiredPath));
			String organizationName = organization.getText();
			if(organizationName.equals(requiredOrganizationName)) {
				organization.click();
				break;
			}
		}
		webdriver.switchToWindow(parentWindow);
	}
	public void setUploadImage(String contactImagePath) {
		uploadImage.sendKeys(contactImagePath);
	}
	public void clickSaveButton() {
		saveButton.click();
	}
}
