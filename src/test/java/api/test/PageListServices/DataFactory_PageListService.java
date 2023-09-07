package api.test.PageListServices;

import api.utility.EnvParameters;

public class DataFactory_PageListService {

	protected static int statusCode1 = 200;
	protected static String commonEndpointUrl = "/v1/authoring";

	public static void dataFactory() {

		if (EnvParameters.getData("Env").equalsIgnoreCase("QA")) {

		} else if (EnvParameters.getData("Env").equalsIgnoreCase("DEV")) {

		}
	}
}
