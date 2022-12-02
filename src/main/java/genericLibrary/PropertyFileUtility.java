package genericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class has all reusable methods to perform operations on property file
 */
public class PropertyFileUtility {
	private Properties property;
/**
 * This method is used to initialize properties file
 * @param propertyFilePath 
 */
	public void propertyFileInitialization(String propertyFilePath) {
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(propertyFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to fetch data from property file	
	 * @param key
	 * @return
	 */

	public String getDataFromProperty(String key) {	
		String data=property.getProperty(key);
		return data;

	}
}