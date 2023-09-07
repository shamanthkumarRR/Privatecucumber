package api.test.PrelemSearchServices;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import api.services.PrelemSearchAPIService;
import api.testBase.ApiBaseClass;

//@Listeners(web.utility.Listeners.class)	
public class AllLayoutsServiceTest extends ApiBaseClass {

	// Page Objects
	public PrelemSearchAPIService allLayoutsService;

	// Reusable Variable Declaration
	public Response response;

	@BeforeTest
	public void generateData() {
		DataFactory_PrelemSearchService.dataFactory();
	}

	@BeforeMethod
	public void initializeObjects() {
		allLayoutsService = new PrelemSearchAPIService();
	}

	/**
	 * Author: Senthil
	 * 
	 */
	@Test(enabled = true, priority = 1, description = "Validate the response status code of All layouts Service")
	public void VerifyAlllayoutsResponseStatus() {
		int actualStatusCode;
		int expectedStatusCode = DataFactory_PrelemSearchService.statusCode1;
		List<String> list = new ArrayList<>();
		list.add("video");
		list.add("globalnav");
		Map<String, List<String>> queryParams = new HashMap<String, List<String>>();
		queryParams.put("prelemTag", list);

		response = allLayoutsService.validateResponseStatusWithDuplicateKeyParam(
				DataFactory_PrelemSearchService.allLayoutsEndpointUrl, queryParams);
		actualStatusCode = response.getStatusCode();
		Assert.assertEquals(expectedStatusCode, actualStatusCode);
		//ExtentLog.info("Validated the response status code of All layouts Service");
	}

	@Test(enabled = true, priority = 2, description = "Validate Thumbnail field & all fields in All layouts service are not NULL")
	public void VerifyAllLayoutsFieldsNotNull() {
		List<String> list = new ArrayList<>();
		list.add("video");
		list.add("globalnav");
		Map<String, List<String>> queryParams = new HashMap<String, List<String>>();
		queryParams.put("prelemTag", list);

		allLayoutsService.validateThumbnailFieldAndFieldsOfAllLayoutsNotNull(
				DataFactory_PrelemSearchService.allLayoutsEndpointUrl, queryParams);
	}
}
