package api.test.PrelemSearchServices;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import org.testng.Assert;
import api.services.PrelemSearchAPIService;
import api.testBase.ApiBaseClass;

//@Listeners(web.utility.Listeners.class)	
public class FetchLayoutsServiceTest extends ApiBaseClass {

	// Page Objects
	public PrelemSearchAPIService fetchLayoutsService;

	// Reusable Variable Declaration
	public Response response;

	@BeforeTest
	public void generateData() {
		DataFactory_PrelemSearchService.dataFactory();
	}

	@BeforeMethod
	public void initializeObjects() {
		fetchLayoutsService = new PrelemSearchAPIService();
	}

	/**
	 * Author: Senthil
	 * 
	 */
	@Test(enabled = true, priority = 1, description = "Validate the response status code of Fetch layouts Service")
	public void VerifyFetchlayoutsResponseStatus() {
		int actualStatusCode;
		int expectedStatusCode = DataFactory_PrelemSearchService.statusCode1;

		response = fetchLayoutsService.validateResponseStatus(DataFactory_PrelemSearchService.allLayoutsEndpointUrl);
		actualStatusCode = response.getStatusCode();
		Assert.assertEquals(expectedStatusCode, actualStatusCode);
		//ExtentLog.info("Validated the response status code of Fetch layouts Service");
	}

	@Test(enabled = true, priority = 2, description = "Validate Thumbnail field & all fields in Fetch layouts service are not NULL")
	public void VerifyFetchLayoutsFieldsNotNull() {
		
		fetchLayoutsService.validateThumbnailFieldAndFieldsOfFetchLayoutsNotNull(
				DataFactory_PrelemSearchService.allLayoutsEndpointUrl);
	}
}
