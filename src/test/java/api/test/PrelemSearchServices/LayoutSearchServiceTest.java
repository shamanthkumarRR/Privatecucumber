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
public class LayoutSearchServiceTest extends ApiBaseClass {

	// Page Objects
	public PrelemSearchAPIService layoutSearchService;

	// Reusable Variable Declaration
	public Response response;

	@BeforeTest
	public void generateData() {
		DataFactory_PrelemSearchService.dataFactory();
	}

	@BeforeMethod
	public void initializeObjects() {
		layoutSearchService = new PrelemSearchAPIService();
	}

	/**
	 * Author: Senthil
	 * 
	 */
	@Test(enabled = true, priority = 1, description = "Validate the response status code of layoutSearch Service")
	public void VerifylayoutSearchResponseStatus() {
		int actualStatusCode;
		int expectedStatusCode = DataFactory_PrelemSearchService.statusCode1;
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("prelemSearchText", "contact");
		queryParams.put("prelemTag", "gallery");

		response = layoutSearchService.validateResponseStatus(DataFactory_PrelemSearchService.allLayoutsEndpointUrl,
				queryParams);
		actualStatusCode = response.getStatusCode();
		Assert.assertEquals(expectedStatusCode, actualStatusCode);
		//ExtentLog.info("Validated the response status code of layoutSearch Service");
	}

	@Test(enabled = true, priority = 2, description = "Validate Thumbnail field & all fields in layoutSearch service are not NULL")
	public void VerifylayoutSearchFieldsNotNull() {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("prelemSearchText", "contact");
		queryParams.put("prelemTag", "gallery");

		layoutSearchService.validateThumbnailFieldAndFieldsOfLayoutSearchNotNull(
				DataFactory_PrelemSearchService.allLayoutsEndpointUrl, queryParams);
	}
}
