package testNGImplementation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibrary.AutoConstantPath;
import genericLibrary.BaseClass;

public class DataProviderCreateOrgTest extends BaseClass {
	@Test(dataProvider ="dataForCreateOrg")
	public void createOrganizationTest(String accountName, String Industry, String Group){

		SoftAssert soft = new SoftAssert();
		home.clickOrganisationTab();
		soft.assertTrue(org.getOrgnisationHeader().contains("Organizations"));

		org.clickPlusImage();
		soft.assertTrue(createNewOrg.getCreateNewOrgHeaderText().contains("Creating New Organization"));

		createNewOrg.setorganisationName(accountName);
		createNewOrg.selectAssignedToDropdown(webdriver,Industry);
		createNewOrg.clickGroupRadioButton();
		createNewOrg.getIndustryDropdown(webdriver,Group);
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
	@DataProvider
	public Object[][] dataForCreateOrg(){
		Object [][] obj = new Object[2][3];
		
		obj[0][0]= "ACTS";
		obj[0][1] = "Biotechnology";
		obj[0][2] = "Support Group";
		
		obj[1][0]= "BTCS";
		obj[1][1] = "Biotechnology";
		obj[1][2] = "Team Selling";
		
		return obj;
	}
}
