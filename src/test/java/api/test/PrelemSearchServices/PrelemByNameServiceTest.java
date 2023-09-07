package api.test.PrelemSearchServices;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import org.testng.Assert;
import api.services.PrelemSearchAPIService;
import api.testBase.ApiBaseClass;

//@Listeners(web.utility.Listeners.class)	
public class PrelemByNameServiceTest extends ApiBaseClass {

	// Page Objects
	public PrelemSearchAPIService prelemByNameService;

	// Reusable Variable Declaration
	public Response response;

	@BeforeTest
	public void generateData() {
		DataFactory_PrelemSearchService.dataFactory();
	}

	@BeforeMethod
	public void initializeObjects() {
		prelemByNameService = new PrelemSearchAPIService();
	}

	/**
	 * Author: Senthil
	 * 
	 */
	@Test(enabled = true, priority = 1, description = "Validate the response status code of PrelemByName Service")
	public void VerifyprelemByNameResponseStatus() {
		int actualStatusCode;
		int expectedStatusCode = DataFactory_PrelemSearchService.statusCode1;

		response = prelemByNameService.validateResponseStatus(DataFactory_PrelemSearchService.prelemByNameEndpointUrl);
		actualStatusCode = response.getStatusCode();
		Assert.assertEquals(expectedStatusCode, actualStatusCode);
		//ExtentLog.info("Validated the response status code of PrelemByName Service");
	}

	@Test(enabled = true, priority = 2, description = "Validate Thumbnail field & all fields in PrelemByName service are not NULL")
	public void VerifyprelemByNameFieldsNotNull() {
		
		prelemByNameService.validateThumbnailFieldAndFieldsOfPrelemByNameNotNull(
				DataFactory_PrelemSearchService.prelemByNameEndpointUrl);
	}
}
