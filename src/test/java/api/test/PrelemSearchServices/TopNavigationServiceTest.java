package api.test.PrelemSearchServices;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import org.testng.Assert;
import api.services.PrelemSearchAPIService;
import api.testBase.ApiBaseClass;

//@Listeners(web.utility.Listeners.class)	
public class TopNavigationServiceTest extends ApiBaseClass {

	// Page Objects
	public PrelemSearchAPIService topNavService;
	
	// Reusable Variable Declaration
	public Response response;

	@BeforeTest
	public void generateData() {
		DataFactory_PrelemSearchService.dataFactory();
	}
	
	@BeforeMethod
	public void initializeObjects() {
		topNavService = new PrelemSearchAPIService();
	}

	/**
	 * Author: Senthil
	 * 
	 */
	@Test(enabled = true, priority = 1, description = "Validate the response status code of TopNavigation Service")
	public void VerifyTopNavResponseStatus() {
		int actualStatusCode;
		int expectedStatusCode = DataFactory_PrelemSearchService.statusCode1;
		
		response = topNavService.validateResponseStatus(DataFactory_PrelemSearchService.topNavEndpointUrl);
		actualStatusCode = response.getStatusCode();
		Assert.assertEquals(expectedStatusCode, actualStatusCode);
		//ExtentLog.info("Validated the response status code of TopNavigation Service");
	}

	@Test(enabled = true, priority = 2, description = "Validate all fields in TopNavigation service are not NULL")
	public void VerifyFieldsOfTopNavNotNull() {
		topNavService.validateFieldsOfTopNavNotNull(DataFactory_PrelemSearchService.topNavEndpointUrl);
	}
}
