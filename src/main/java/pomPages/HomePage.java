package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibrary.WebDriverUtility;

public class HomePage {

	@FindBy(className= "hdrLink") private WebElement homepageHeaderText;
	
	@FindBy(xpath = "//a[.='Organizations']") private WebElement organisationTab;

	@FindBy(xpath = "//a[.='Contacts']") private WebElement contactsTab;

	@FindBy(xpath = "//a[.='Leads']")private WebElement leadTab;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']") private WebElement administratorImage;

	@FindBy(xpath = "//a[.='Sign Out']") private WebElement signoutLink;


	public HomePage(WebDriver driver){
		PageFactory.initElements(driver,this);
	}
	public String getHomepageHeaderText() {
		return homepageHeaderText.getText();
	}
	public void clickContactsTab() {
		contactsTab.click();
	}
	public void clickOrganisationTab() {
		organisationTab.click();
	}
	public void clickLeaadTab() {
		leadTab.click();
	}
	public void signoutApplication(WebDriverUtility webdriver) {
		webdriver.mousehoverAction(administratorImage);
		signoutLink.click();	
	}
}
