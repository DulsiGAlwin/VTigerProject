package testNGImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibrary.AutoConstantPath;
import genericLibrary.BaseClass;

public class CreateNewLeadTest extends BaseClass{
	@Test
	public  void CteateNewLead() {
		SoftAssert soft = new SoftAssert();
		home.clickLeaadTab();

		soft.assertTrue(leads.getLeadHeader().contains("Leads"));

		leads.clickPlusImage();
		soft.assertTrue(createNewlead.getLeadsHeaderText().contains("Creating New Lead"));

		Map<String, String> map = excel.fetchDataFromExcel("Test", "Create Lead");
		WebElement salutation= driver.findElement(By.name("salutationtype"));
		String value = map.get("Salutation");
		webdriver.dropDown(value, salutation);
		String firstName = map.get("First Name");
		String lastName = map.get("Last Name")+java.generateRandomNum(100);
		createNewlead.setfirstNameText(firstName);
		createNewlead.setlastNameText(lastName);
		String company =map.get("Company");
		createNewlead.setCompanyText(company);
		createNewlead.clickSaveButton();

		soft.assertTrue(newleadInfo.getleadHeader().contains(lastName));

		newleadInfo.clickDuplicateLink();
		soft.assertTrue(duplicateLead.getDuplicateHeader().contains("Duplicating"));

		duplicateLead.clearLastNameText();
		String updatedName = map.get("New Last Name");
		duplicateLead.setLastNameText(updatedName);
		duplicateLead.clickSaveButton();

		soft.assertTrue(newleadInfo.getleadHeader().contains(updatedName));

		createNewlead.clickleadLink();
		soft.assertTrue(leads.getTableValidation().contains(updatedName));
		if(leads.getTableValidation().contains(updatedName)) {
			System.out.println("Testcase Passed");
			excel.writeCellData("Create Lead","Test","Pass",AutoConstantPath.EXCEL_FILE_PATH);
		}
		else {
			System.out.println("Testcase Failed");
			excel.writeCellData("Create Lead","Test","Fail",AutoConstantPath.EXCEL_FILE_PATH);
		}
		soft.assertAll();
	}
}
