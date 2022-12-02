package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewOrganisationCreatedPageInfo {

	@FindBy(className = "dvHeaderText")private WebElement newOrgHeader;
	
	@FindBy(xpath = "//a[.='Organizations']") private WebElement orgLink;
	
	public NewOrganisationCreatedPageInfo(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	public String getNewOrgHeader() {
		return newOrgHeader.getText();
	}

	public void clickOrgLink() {
		orgLink.click();;
	}
	
}
