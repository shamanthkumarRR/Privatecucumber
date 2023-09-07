package api.test.PrelemSearchServices;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import org.testng.Assert;

import api.services.PrelemInfoAPIService;
import api.testBase.ApiBaseClass;

//@Listeners(web.utility.Listeners.class)	
public class PrelemInfoServiceTest extends ApiBaseClass {

	// Page Objects
	public PrelemInfoAPIService prelemInfoService;

	// Reusable Variable Declaration
	public Response response;

	@BeforeTest
	public void generateData() {
		DataFactory_PrelemSearchService.dataFactory();
	}

	@BeforeMethod
	public void initializeObjects() {
		prelemInfoService = new PrelemInfoAPIService();
	}

	/**
	 * Author: Senthil
	 * 
	 */
	@Test(enabled = true, priority = 1, description = "Validate the response status code of PrelemInfo Service")
	public void VerifyPrelemInfoResponseStatus() {
		int actualStatusCode;
		int expectedStatusCode = DataFactory_PrelemSearchService.statusCode1;

		response = prelemInfoService.validateResponseStatus(DataFactory_PrelemSearchService.prelemInfoEndpointUrl);
		actualStatusCode = response.getStatusCode();
		Assert.assertEquals(expectedStatusCode, actualStatusCode);
		//ExtentLog.info("Validated the response status code of PrelemInfo Service");
	}

	@Test(enabled = true, priority = 2, description = "Validate Thumbnail field & all fields in PrelemInfo service are not NULL")
	public void VerifyPrelemInfoFieldsNotNull() {

		prelemInfoService.validateFieldsOfPrelemInfoNotNull(
				DataFactory_PrelemSearchService.prelemInfoEndpointUrl);
	}
}
