package testNGImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibrary.AutoConstantPath;
import genericLibrary.BaseClass;

public class CreateOrganisationTest extends BaseClass {
	@Test(priority = 1)
	public void createOrganizationTest(){

		SoftAssert soft = new SoftAssert();
		home.clickOrganisationTab();
		soft.assertTrue(org.getOrgnisationHeader().contains("Organizations"));

		org.clickPlusImage();
		soft.assertTrue(createNewOrg.getCreateNewOrgHeaderText().contains("Creating New Organization"));

		Map<String,String> map= excel.fetchDataFromExcel("Test","Create Organization");
		String accountName=map.get("Organization Name")+java.generateRandomNum(100);
		createNewOrg.setorganisationName(accountName);
		createNewOrg.selectAssignedToDropdown(webdriver, map.get("Industry"));
		createNewOrg.clickGroupRadioButton();
		createNewOrg.getIndustryDropdown(webdriver, map.get("Group"));
		createNewOrg.clickSaveButton();

		soft.assertTrue(newOrgInfo.getNewOrgHeader().contains(accountName));
		newOrgInfo.clickOrgLink();

		soft.assertTrue(org.getNewOrganisation().contains(accountName));
		if(org.getNewOrganisation().contains(accountName)) {
			System.out.println("Testcase Passed");
			excel.writeCellData("Create Organisation", "Test","Pass", AutoConstantPath.EXCEL_FILE_PATH);
		}
		else {
			System.out.println("Testcase Failed");
			excel.writeCellData("Create Organisation", "Test","Fail", AutoConstantPath.EXCEL_FILE_PATH);
		}
		soft.assertAll();
	}
}
