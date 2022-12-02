package genericLibrary;

import java.util.Random;

public class JavaUtility {

	public int generateRandomNum(int limit) {
		Random random =new Random();
		int randomNum =random.nextInt(limit);
		return randomNum;
	}
}
