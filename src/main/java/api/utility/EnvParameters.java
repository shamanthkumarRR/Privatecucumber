package api.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvParameters {

	public static Properties envDetails = new Properties();

	public static void loadData() throws IOException {
		FileInputStream config = new FileInputStream("./config.properties");
		envDetails.load(config);

		// Load Application values based on Environment
		if (envDetails.getProperty("Env").equalsIgnoreCase("QA")) {
			FileInputStream testDataQa = new FileInputStream("./TestData/TestData_QA.properties");
			envDetails.load(testDataQa);
		} else if (envDetails.getProperty("Env").equalsIgnoreCase("DEV")) {
			FileInputStream testDataDev = new FileInputStream("./TestData/TestData_DEV.properties");
			envDetails.load(testDataDev);
		} else if (envDetails.getProperty("Env").equalsIgnoreCase("STG")) {
			FileInputStream testDataStg = new FileInputStream("./TestData/TestData_STG.properties");
			envDetails.load(testDataStg);
		}
	}

	/**
	 * Retrieve the property value based on Property name
	 * 
	 * @param key
	 * @return
	 */
	public static String getData(String key) {
		String locValue = envDetails.getProperty(key);
		return locValue;
	}
}
