package api.services;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.restassured.response.Response;
import api.utility.EnvParameters;
import api.utility.UtilityServices;

public class PageListAPIService extends UtilityServices {

	// Test Data
	public static final String baseUrl = EnvParameters.getData("apiBaseUrl");
	public static final String getContentType = EnvParameters.getData("contentType");

	/**
	 * Method to return the responses of Service with header parameters
	 * 
	 * @return
	 */
	public Response validateResponseStatusForPostCall(String endPointUrl, JSONObject responseBody) {
		Response response = null;
		Map<String, String> headers = new HashMap<String, String>();
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Post method with Header & response body
		response = postCallWithHeaderAndJsonBodyParam(headers, responseBody, baseUrl + endPointUrl);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		return response;
	}

	/**
	 * Method to validate the fields in PageListByScheduledPublish/Unpublish service
	 * are not NULL
	 * 
	 * @param responseBody
	 * 
	 */
	public void validateFieldsOfPageListBySchPublishUnpublishNotNull(String endPointUrl, String responseBody,
			String pageStatus) {
		Map<String, String> headers = new HashMap<String, String>();
		Response response = null;

		// Pass Header Info
		headers.put("Content-Type", getContentType);
		JSONObject json = new JSONObject();
		json.put("query", responseBody);

		// Call Post method with Header & response body
		response = postCallWithHeaderAndJsonBodyParam(headers, json, baseUrl + endPointUrl);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		//ExtentLog.info("Response body is => " + response.getBody().asString());
		for (int i = 0; i < response.jsonPath().getList("data.pageList").size(); i++) {

			// Validating all fields are not NULL
			Assert.assertNotNull(response.jsonPath().getString("data.pageList[" + i + "].Page"),
					"Page field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.pageList[" + i + "].CurrentPageUrl"),
					"CurrentPageUrl field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.pageList[" + i + "].ParentPageUrl"),
					"ParentPageUrl field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.pageList[" + i + "].Status"),
					"Status field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.pageList[" + i + "].Title"),
					"Title field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.pageList[" + i + "].LastModificationDate"),
					"LastModificationDate field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.pageList[" + i + "].LastModifiedBy"),
					"LastModifiedBy field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.pageList[" + i + "].PublishedBy"),
					"PublishedBy field " + i + 1 + " is null");
			if (pageStatus.equals("Scheduled Publish")) {
				Assert.assertNotNull(
						response.jsonPath().getString("data.pageList[" + i + "].SchduledPublishTriggerDateTime"),
						"ScheduledPublishTriggerDateTime field " + i + 1 + " is null");
			} else if (pageStatus.equals("Scheduled Unpublish")) {
				Assert.assertNotNull(
						response.jsonPath().getString("data.pageList[" + i + "].SchduledUnPublishTriggerDateTime"),
						"ScheduledUnPublishTriggerDateTime field " + i + 1 + " is null");
			}
			Assert.assertNotNull(response.jsonPath().getString("data.pageList[" + i + "].PageSettings.PageName"),
					"PageName field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.pageList[" + i + "].Path"),
					"Path field " + i + 1 + " is null");
		}
		//ExtentLog.info("Validated all fields in PageList " + pageStatus + " service are not NULL");
	}
}
