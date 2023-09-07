package api.test.PageListServices;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import api.test.PrelemSearchServices.DataFactory_PrelemSearchService;
import io.restassured.response.Response;

import org.json.simple.JSONObject;
import org.testng.Assert;

import api.services.PageListAPIService;
import api.testBase.ApiBaseClass;

//@Listeners(web.utility.Listeners.class)	
public class PageListBySchPublishServiceTest extends ApiBaseClass {

	// Page Objects
	public PageListAPIService pageListBySchPublishService;

	// Reusable Variable Declaration
	public Response response;
	public String responseBody;
	
	@BeforeTest
	public void generateData() {
		DataFactory_PrelemSearchService.dataFactory();
	}

	@BeforeMethod
	public void initializeObjects() {
		pageListBySchPublishService = new PageListAPIService();
	}

	/**
	 * Author: Senthil
	 * 
	 */
	@Test(enabled = true, priority = 1, description = "Validate the response status code of pageListByScheduledPublish Service")
	public void verifyPageListByScheduledPublishResponseStatus() {
		int actualStatusCode;
		int expectedStatusCode = DataFactory_PageListService.statusCode1;
		
		// Pass Response body
		responseBody = "query{\r\n"
				+ "pageList(\r\n"
				+ "pagination:{start:0, rows:10},\r\n"
				+ "pageFilter:SCHEDULED_PUBLISH,\r\n"
				+ "){\r\n"
				+ "Page\r\n"
				+ "CurrentPageUrl\r\n"
				+ "ParentPageUrl\r\n"
				+ "Status\r\n"
				+ "Title\r\n"
				+ "Description\r\n"
				+ "LastModificationDate\r\n"
				+ "LastModifiedBy\r\n"
				+ "PublishedBy\r\n"
				+ "SchduledPublishTriggerDateTime\r\n"
				+ "SchduledUnPublishTriggerDateTime\r\n"
				+ "PageSettings{\r\n"
				+ "PageName\r\n"
				+ "PageTags\r\n"
				+ "PageDescription\r\n"
				+ "}\r\n"
				+ "Path\r\n"
				+ "}\r\n"
				+ "}";
		JSONObject json = new JSONObject();
		json.put("query", responseBody);

		response = pageListBySchPublishService.validateResponseStatusForPostCall(DataFactory_PageListService.commonEndpointUrl, json);
		actualStatusCode = response.getStatusCode();
		Assert.assertEquals(expectedStatusCode, actualStatusCode);
		//ExtentLog.info("Validated the response status code of pageListByScheduledPublish Service");
	}

	@Test(enabled = true, priority = 2, description = "Validate all fields in pageListByScheduledPublish service are not NULL")
	public void verifyPageListByScheduledPublishFieldsNotNull() {
		
		// Pass Response body
		responseBody = "query{\r\n"
				+ "pageList(\r\n"
				+ "pagination:{start:0, rows:10},\r\n"
				+ "pageFilter:SCHEDULED_PUBLISH,\r\n"
				+ "){\r\n"
				+ "Page\r\n"
				+ "CurrentPageUrl\r\n"
				+ "ParentPageUrl\r\n"
				+ "Status\r\n"
				+ "Title\r\n"
				+ "Description\r\n"
				+ "LastModificationDate\r\n"
				+ "LastModifiedBy\r\n"
				+ "PublishedBy\r\n"
				+ "SchduledPublishTriggerDateTime\r\n"
				+ "SchduledUnPublishTriggerDateTime\r\n"
				+ "PageSettings{\r\n"
				+ "PageName\r\n"
				+ "PageTags\r\n"
				+ "PageDescription\r\n"
				+ "}\r\n"
				+ "Path\r\n"
				+ "}\r\n"
				+ "}";
		
		pageListBySchPublishService.validateFieldsOfPageListBySchPublishUnpublishNotNull(
				DataFactory_PageListService.commonEndpointUrl, responseBody, "Scheduled Publish");
	}
}
