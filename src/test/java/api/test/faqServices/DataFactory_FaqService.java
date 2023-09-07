package api.test.faqServices;

import api.utility.EnvParameters;

public class DataFactory_FaqService {

	protected static int answerFieldCharCount = 500;
	protected static int questionFieldCharCount = 250;
	protected static int statusCode1 = 200;

	public static void dataFactory() {

		if (EnvParameters.getData("Env").equalsIgnoreCase("QA")) {

		} else if (EnvParameters.getData("Env").equalsIgnoreCase("DEV")) {

		}
	}
}
