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
public class PrelemSearchServiceTest extends ApiBaseClass {

	// Page Objects
	public PrelemSearchAPIService prelemSearchService;

	// Reusable Variable Declaration
	public Response response;

	@BeforeTest
	public void generateData() {
		DataFactory_PrelemSearchService.dataFactory();
	}

	@BeforeMethod
	public void initializeObjects() {
		prelemSearchService = new PrelemSearchAPIService();
	}

	/**
	 * Author: Senthil
	 * 
	 */
	@Test(enabled = true, priority = 1, description = "Validate the response status code of PrelemSearch Service")
	public void VerifyPrelemSearchResponseStatus() {
		int actualStatusCode;
		int expectedStatusCode = DataFactory_PrelemSearchService.statusCode1;
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("searchText", "contact");
		queryParams.put("layout", "Layout_ImageCard_Item3");
		queryParams.put("tag", "imagecard");

		response = prelemSearchService.validateResponseStatus(DataFactory_PrelemSearchService.prelemSearchEndpointUrl,
				queryParams);
		actualStatusCode = response.getStatusCode();
		Assert.assertEquals(expectedStatusCode, actualStatusCode);
		//ExtentLog.info("Validated the response status code of PrelemSearch Service");
	}

	@Test(enabled = true, priority = 2, description = "Validate Thumbnail field & all fields in PrelemSearch service are not NULL")
	public void VerifyPrelemSearchFieldsNotNull() {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("searchText", "contact");
		queryParams.put("layout", "Layout_ImageCard_Item3");
		queryParams.put("tag", "imagecard");
		
		prelemSearchService.validateThumbnailFieldAndFieldsOfPrelemSearchNotNull(
				DataFactory_PrelemSearchService.prelemSearchEndpointUrl, queryParams);
	}
}
