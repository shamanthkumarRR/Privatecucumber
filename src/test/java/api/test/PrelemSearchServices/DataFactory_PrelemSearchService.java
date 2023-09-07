package api.test.PrelemSearchServices;

import api.utility.EnvParameters;

public class DataFactory_PrelemSearchService {

	protected static int statusCode1 = 200;
	protected static String allLayoutsEndpointUrl = "/api/v1/web/en/authoring/layouts/0/10/asc";
	protected static String prelemByIdEndpointUrl = "/v1/authoring";
	protected static String prelemByNameEndpointUrl = "/api/v1/web/en/authoring/prelem/quote";
	protected static String prelemInfoEndpointUrl = "/api/v1/web/en/authoring/prelem/productsummaryviavideo";
	protected static String prelemPreviewEndpointUrl = "/api/v1/web/en/authoring/prelem-preview/quote";
	protected static String prelemSearchEndpointUrl = "/api/v1/web/en/authoring/prelems/0/10/asc";
	protected static String suggestiveSearchEndpointUrl = "/api/v1/web/en/authoring/prelem-suggestions";
	protected static String suggestiveSearchKeyword1 = "vid";
	protected static String suggestiveSearchKeyword2 = "Contact";
	protected static String topNavEndpointUrl = "/api/v1/web/en/authoring/top-navigations";
	

	public static void dataFactory() {

		if (EnvParameters.getData("Env").equalsIgnoreCase("QA")) {

		} else if (EnvParameters.getData("Env").equalsIgnoreCase("DEV")) {

		}
	}
}
