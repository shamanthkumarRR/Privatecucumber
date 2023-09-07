package api.services;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import io.restassured.response.Response;
import api.utility.EnvParameters;
import api.utility.UtilityServices;

public class PrelemPreviewAPIService extends UtilityServices {

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
	 * Method to validate the fields in PrelemPreview service are not NULL
	 * 
	 */
	public void validateFieldsOfPrelemPreviewNotNull(String endPointUrl) {
		Map<String, String> headers = new HashMap<String, String>();
		Response response = null;
		String[] previewValues = {"webPreview", "tabletPreview", "mobilePreview"};
		String thumbnailValue;
		
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Get method with Header
		response = getCallWithHeaderParam(headers, baseUrl + endPointUrl);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		//ExtentLog.info("Response body is => " + response.getBody().asString());
		Assert.assertNotNull(response.jsonPath().getString("data.prelemPreview.webPreview"),
				"WebPreview field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemPreview.tabletPreview"),
				"TabletPreview field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemPreview.mobilePreview"),
				"MobilePreview field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemPreview.name"), "Name field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemPreview.thumbnails[0].desktop"), "Thumbnails Desktop field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemPreview.thumbnails[1].mobile"), "Thumbnails Mobile field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemPreview.thumbnails[2].tab"), "Thumbnails Tab field is null");

		// Validating whether Image has loaded from DSpace for Web/Tablet/Mobile previews
		for (int i = 0; i < previewValues.length; i++) {
			thumbnailValue = response.jsonPath().getString("data.prelemPreview."+previewValues[i]);
			if (thumbnailValue.contains("platx-dspace")) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("Image has not loaded from DSpace");
			}
		}
		// Validating whether Image has loaded from DSpace for Thumbnails Web/Tablet/Mobile previews
		thumbnailValue = response.jsonPath().getString("data.prelemPreview.thumbnails[0].desktop");
		if (thumbnailValue.contains("platx-dspace")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("Image has not loaded from DSpace");
		}
		thumbnailValue = response.jsonPath().getString("data.prelemPreview.thumbnails[1].mobile");
		if (thumbnailValue.contains("platx-dspace")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("Image has not loaded from DSpace");
		}
		thumbnailValue = response.jsonPath().getString("data.prelemPreview.thumbnails[2].tab");
		if (thumbnailValue.contains("platx-dspace")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("Image has not loaded from DSpace");
		}
		
		//ExtentLog.info("Validated all fields in PrelemPreview services are not NULL");
	}
}
