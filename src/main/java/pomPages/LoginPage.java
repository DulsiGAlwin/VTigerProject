package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(xpath = "//img[@alt='logo']") private WebElement logoimage;

	@FindBy(name = "user_name") private WebElement userNameTextField;
	
	@FindBy(name = "user_password") private WebElement passwordTextField;

	@FindBy(id = "submitButton") private WebElement loginButton;
	
public LoginPage(WebDriver driver) {
	PageFactory.initElements( driver,this);
}
public WebElement getLogoimage() {
	return logoimage;
}

public void loginCredentials(String userName, String password) {
	userNameTextField.sendKeys(userName);
	passwordTextField.sendKeys(password);
	loginButton.click();
}
}
