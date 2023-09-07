package api.services;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import io.restassured.response.Response;
import api.utility.EnvParameters;
import api.utility.UtilityServices;

public class PrelemInfoAPIService extends UtilityServices {

	// Test Data
	public static final String baseUrl = EnvParameters.getData("apiBaseUrl");
	public static final String getContentType = EnvParameters.getData("contentType");

	/**
	 * Method to return the responses of Service with header parameters
	 * 
	 * @return
	 */
	public Response validateResponseStatus(String endPointUrl) {
		Response response = null;
		Map<String, String> headers = new HashMap<String, String>();
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Get method with Header
		response = getCallWithHeaderParam(headers, baseUrl + endPointUrl);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		return response;
	}

	/**
	 * Method to validate the fields in PrelemInfo service are not NULL
	 * 
	 */
	public void validateFieldsOfPrelemInfoNotNull(String endPointUrl) {
		Response response = null;
		Map<String, String> headers = new HashMap<String, String>();
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Get method with Header
		response = getCallWithHeaderParam(headers, baseUrl + endPointUrl);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		//ExtentLog.info("Response body is => " + response.getBody().asString());
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.PrelemId"),
				"Id field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.PrelemName"),
				"Name field is null");
		for (int i = 0; i < response.jsonPath().getList("data.prelemByName.Tags").size(); i++) {
			Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.Tags[" + i + "]"),
					"Tags field " + i + 1 + " is null");
		}
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.PreviewThumbnail"),
				"PreviewThumbnail field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.Thumbnails.Desktop"),
				"Thumbnails Desktop field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.Thumbnails.Mobile"),
				"Thumbnails Mobile field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.Thumbnails.Tab"),
				"Thumbnails Tab field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.Description"),
				"Description field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.DocumentType"),
				"DocumentType field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.DocumentPath"),
				"DocumentPath field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.DocumentCreationPath"),
				"DocumentCreationPath field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.DevelopedBy"),
				"DevelopedBy field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.DevelopedDate"),
				"DevelopedDate field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.SeoEnabled"),
				"SeoEnabled field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.AnalyticsEnabled"),
				"AnalyticsEnabled field is null");
		
		// Validating whether Image has loaded from DSpace
		String thumbnailValue = response.jsonPath().getString("data.prelemByName.PreviewThumbnail");
		if (thumbnailValue.contains("platx-dspace")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("Image has not loaded from DSpace");
		}		
		//ExtentLog.info("Validated all fields in PrelemInfo services are not NULL");
	}
}
