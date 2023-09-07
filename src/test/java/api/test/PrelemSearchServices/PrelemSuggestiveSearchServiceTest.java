package api.test.PrelemSearchServices;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import api.services.PrelemSearchAPIService;
import api.testBase.ApiBaseClass;

//@Listeners(web.utility.Listeners.class)	
public class PrelemSuggestiveSearchServiceTest extends ApiBaseClass {

	// Page Objects
	public PrelemSearchAPIService suggestiveSearchService;

	// Reusable Variable Declaration
	public Response response;

	@BeforeTest
	public void generateData() {
		DataFactory_PrelemSearchService.dataFactory();
	}

	@BeforeMethod
	public void initializeObjects() {
		suggestiveSearchService = new PrelemSearchAPIService();
	}

	/**
	 * Author: Senthil
	 * 
	 */
	@Test(enabled = true, priority = 1, description = "Validate the response status code of SuggestiveSearch Service")
	public void VerifySuggestiveSearchResponseStatus() {
		int actualStatusCode;
		int expectedStatusCode = DataFactory_PrelemSearchService.statusCode1;
		String[] suggestiveKeywords = { DataFactory_PrelemSearchService.suggestiveSearchKeyword1,
				DataFactory_PrelemSearchService.suggestiveSearchKeyword2 };
		Map<String, String> queryParams = new HashMap<String, String>();
		
		for (int i = 0; i < suggestiveKeywords.length; i++) {		
			queryParams.put("searchText", suggestiveKeywords[i]);

			response = suggestiveSearchService
					.validateResponseStatus(DataFactory_PrelemSearchService.suggestiveSearchEndpointUrl, queryParams);
			actualStatusCode = response.getStatusCode();

			Assert.assertEquals(expectedStatusCode, actualStatusCode);
			//ExtentLog.info("Validated the response status code of SuggestiveSearch Service for keyword: "
				//	+ suggestiveKeywords[i]);
		}
	}

	@Test(enabled = true, priority = 2, description = "Validate all fields in TopNavigation service are not NULL")
	public void VerifyFieldsOfTopNavNotNull() {
		String[] suggestiveKeywords = { DataFactory_PrelemSearchService.suggestiveSearchKeyword1,
				DataFactory_PrelemSearchService.suggestiveSearchKeyword2 };	
		Map<String, String> queryParams = new HashMap<String, String>();
		
		for (int i = 0; i < suggestiveKeywords.length; i++) {
		queryParams.put("searchText", suggestiveKeywords[i]);

		suggestiveSearchService.validateFieldsOfSuggestiveSearchNotNull(
				DataFactory_PrelemSearchService.suggestiveSearchEndpointUrl, queryParams);
		}
	}
}
