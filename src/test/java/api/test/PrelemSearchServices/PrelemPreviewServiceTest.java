package api.test.PrelemSearchServices;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import org.testng.Assert;

import api.services.PrelemPreviewAPIService;
import api.testBase.ApiBaseClass;

//@Listeners(web.utility.Listeners.class)	
public class PrelemPreviewServiceTest extends ApiBaseClass {

	// Page Objects
	public PrelemPreviewAPIService prelemPreviewService;

	// Reusable Variable Declaration
	public Response response;

	@BeforeTest
	public void generateData() {
		DataFactory_PrelemSearchService.dataFactory();
	}

	@BeforeMethod
	public void initializeObjects() {
		prelemPreviewService = new PrelemPreviewAPIService();
	}

	/**
	 * Author: Senthil
	 * 
	 */
	@Test(enabled = true, priority = 1, description = "Validate the response status code of PrelemPreview Service")
	public void VerifyPrelemPreviewResponseStatus() {
		int actualStatusCode;
		int expectedStatusCode = DataFactory_PrelemSearchService.statusCode1;

		response = prelemPreviewService.validateResponseStatus(DataFactory_PrelemSearchService.prelemPreviewEndpointUrl);
		actualStatusCode = response.getStatusCode();
		Assert.assertEquals(expectedStatusCode, actualStatusCode);
		//ExtentLog.info("Validated the response status code of PrelemPreview Service");
	}

	@Test(enabled = true, priority = 2, description = "Validate Thumbnail field & all fields in PrelemPreview service are not NULL")
	public void VerifyPrelemPreviewFieldsNotNull() {

		prelemPreviewService.validateFieldsOfPrelemPreviewNotNull(
				DataFactory_PrelemSearchService.prelemPreviewEndpointUrl);
	}
}
