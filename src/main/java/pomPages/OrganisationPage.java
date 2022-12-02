package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganisationPage {

	@FindBy(xpath = "//a[@class='hdrLink']") private WebElement orgnisationHeader;
	
	@FindBy(xpath = "//img[@title='Create Organization...']") private WebElement plusImage;
	
	@FindBy(xpath = "//table[@class='lvt small']//tbody//tr[last()]//td[3]")
	private WebElement newOrganisation;
	
	
	public OrganisationPage(WebDriver driver) {
	PageFactory.initElements(driver,this);
	}
	public String getOrgnisationHeader() {
		return orgnisationHeader.getText();
	}
	public void clickPlusImage() {
		plusImage.click();
	}
	public String getNewOrganisation() {
		return newOrganisation.getText();
	}
}
