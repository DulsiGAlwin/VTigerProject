
package testNGImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibrary.AutoConstantPath;
import genericLibrary.BaseClass;

public class CreateNewContactTest extends BaseClass {
	@Test
	public  void CreateNewContact(){
		SoftAssert soft = new SoftAssert();
		home.clickContactsTab();

		soft.assertTrue(contact.getContactHeader().contains("Contacts"));

		contact.clickPlusImage();

		soft.assertTrue(createNewContact.getCreateContactHeader().contains("Creating New Contact"));

		Map <String,String> map= excel.fetchDataFromExcel("Test","Create Contact");
		String value = map.get("Salutation");
		createNewContact.selectSalutationDropdown(webdriver,value);
		String firstName=map.get("First Name");
		String lastName=map.get("Last Name")+java.generateRandomNum(100);
		createNewContact.setFirstNameText(firstName);
		createNewContact.setLastameText(lastName);
		createNewContact.selectExistingOrg(webdriver, driver, map.get("Organisation Name"));
		createNewContact.setUploadImage(map.get("Contact Image"));
		createNewContact.clickSaveButton();

		soft.assertTrue(newContactInfo.getContactInfoHeader().contains(lastName));
		
		newContactInfo.clickContactLink();

		soft.assertTrue(contact.getTableValidation().contains(lastName));
		if(contact.getTableValidation().contains(lastName)) {
			System.out.println("Testcase Passed");
			excel.writeCellData("Create Contact", "Test","Pass", AutoConstantPath.EXCEL_FILE_PATH);
		}
		else {
			System.out.println("Testcase Failed");
			excel.writeCellData("Create Contact", "Test","Fail", AutoConstantPath.EXCEL_FILE_PATH);
		}
		soft.assertAll();
	}
}
