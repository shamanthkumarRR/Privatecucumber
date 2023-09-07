package api.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.restassured.response.Response;
import api.utility.EnvParameters;
import api.utility.UtilityServices;

public class PrelemSearchAPIService extends UtilityServices {

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
	 * Method to return the responses of Service with header and query parameters
	 * 
	 * @return
	 */
	public Response validateResponseStatus(String endPointUrl, Map<String, String> queryParams) {
		Response response = null;
		Map<String, String> headers = new HashMap<String, String>();
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Get method with Header and query params
		response = getCallWithHeaderAndQueryParam(headers, queryParams, baseUrl + endPointUrl);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		//ExtentLog.info("Response body is => " + response.getBody().asString());
		return response;
	}

	/**
	 * Method to return the responses of Service with header and duplicate key query
	 * parameters
	 * 
	 * @return
	 */
	public Response validateResponseStatusWithDuplicateKeyParam(String endPointUrl,
			Map<String, List<String>> queryParams) {
		Response response = null;
		Map<String, String> headers = new HashMap<String, String>();
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Get method with Header and query params
		response = getCallWithHeaderAndDuplicateKeyQueryParam(headers, queryParams, baseUrl + endPointUrl);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		//ExtentLog.info("Response body is => " + response.getBody().asString());
		return response;
	}

	/**
	 * Method to validate the fields in TopNav service are not NULL
	 * 
	 */
	public void validateFieldsOfTopNavNotNull(String endPointUrl) {
		Response response = null;
		Map<String, String> headers = new HashMap<String, String>();
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Get method with Header
		response = getCallWithHeaderParam(headers, baseUrl + endPointUrl);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		//ExtentLog.info("Response body is => " + response.getBody().asString());
		for (int i = 0; i < response.jsonPath().getList("data.topnavigation").size(); i++) {
			Assert.assertNotNull(response.jsonPath().getString("data.topnavigation[" + i + "].title"),
					"Title field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.topnavigation[" + i + "].tag"),
					"Tag field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.topnavigation[" + i + "].score"),
					"Score field " + i + 1 + " is null");
		}
		//ExtentLog.info("Validated all fields in TopNavigation service are not NULL");
	}

	/**
	 * Method to validate the fields in LayoutSearch service are not NULL
	 * 
	 */
	public void validateThumbnailFieldAndFieldsOfLayoutSearchNotNull(String endPointUrl,
			Map<String, String> queryParams) {
		Response response = null;
		Map<String, String> headers = new HashMap<String, String>();
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Get method with Header and query params
		response = getCallWithHeaderAndQueryParam(headers, queryParams, baseUrl + endPointUrl);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		//ExtentLog.info("Response body is => " + response.getBody().asString());
		for (int i = 0; i < response.jsonPath().getList("data.layoutSearch").size(); i++) {

			// Validating all fields are not NULL
			Assert.assertNotNull(response.jsonPath().getString("data.layoutSearch[" + i + "].id"),
					"Id field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.layoutSearch[" + i + "].title"),
					"Title field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.layoutSearch[" + i + "].thumbnail"),
					"Thumbnail field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.layoutSearch[" + i + "].mapping"),
					"Mapping field " + i + 1 + " is null");

			// Validating Thumbnail field extensions
			String thumbnailValue = response.jsonPath().getString("data.layoutSearch[" + i + "].thumbnail");
			if (thumbnailValue.contains(".jpg") || thumbnailValue.contains(".png")
					|| thumbnailValue.contains(".jpeg")) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("Thumbnail extension is not in specified format");
			}
		}

		//ExtentLog.info("Validated Thumbnail field & all fields in LayoutSearch service are not NULL");
	}

	/**
	 * Method to validate the fields in SuggestiveSearch service are not NULL
	 * 
	 */
	public void validateFieldsOfSuggestiveSearchNotNull(String endPointUrl, Map<String, String> queryParams) {
		Response response = null;
		Map<String, String> headers = new HashMap<String, String>();
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Get method with Header
		response = getCallWithHeaderAndQueryParam(headers, queryParams, baseUrl + endPointUrl);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		//ExtentLog.info("Response body is => " + response.getBody().asString());
		for (int i = 0; i < response.jsonPath().getList("data.prelemSuggestiveSearch").size(); i++) {
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSuggestiveSearch[" + i + "].text"),
					"Title field " + i + 1 + " is null");
		}
		//ExtentLog.info("Validated all fields in SuggestiveSearch service are not NULL");
	}

	/**
	 * Method to validate the fields in Fetch Layouts service are not NULL
	 * 
	 */
	public void validateThumbnailFieldAndFieldsOfFetchLayoutsNotNull(String endPointUrl) {
		Response response = null;
		Map<String, String> headers = new HashMap<String, String>();
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Get method with Header and query params
		response = getCallWithHeaderParam(headers, baseUrl + endPointUrl);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		//ExtentLog.info("Response body is => " + response.getBody().asString());
		for (int i = 0; i < response.jsonPath().getList("data.layoutSearch").size(); i++) {

			// Validating all fields are not NULL
			Assert.assertNotNull(response.jsonPath().getString("data.layoutSearch[" + i + "].id"),
					"Id field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.layoutSearch[" + i + "].title"),
					"Title field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.layoutSearch[" + i + "].thumbnail"),
					"Thumbnail field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.layoutSearch[" + i + "].mapping"),
					"Mapping field " + i + 1 + " is null");

			// Validating Thumbnail field extensions
			String thumbnailValue = response.jsonPath().getString("data.layoutSearch[" + i + "].thumbnail");
			if (thumbnailValue.contains(".jpg") || thumbnailValue.contains(".png")
					|| thumbnailValue.contains(".jpeg")) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("Thumbnail extension is not in specified format");
			}
		}

		//ExtentLog.info("Validated Thumbnail field & all fields in Fetch Layouts service are not NULL");
	}

	/**
	 * Method to validate the fields in All Layouts service are not NULL
	 * 
	 */
	public void validateThumbnailFieldAndFieldsOfAllLayoutsNotNull(String endPointUrl,
			Map<String, List<String>> queryParams) {
		Response response = null;
		Map<String, String> headers = new HashMap<String, String>();
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Get method with Header and query params
		response = getCallWithHeaderAndDuplicateKeyQueryParam(headers, queryParams, baseUrl + endPointUrl);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		//ExtentLog.info("Response body is => " + response.getBody().asString());
		for (int i = 0; i < response.jsonPath().getList("data.layoutSearch").size(); i++) {

			// Validating all fields are not NULL
			Assert.assertNotNull(response.jsonPath().getString("data.layoutSearch[" + i + "].id"),
					"Id field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.layoutSearch[" + i + "].title"),
					"Title field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.layoutSearch[" + i + "].thumbnail"),
					"Thumbnail field " + i + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.layoutSearch[" + i + "].mapping"),
					"Mapping field " + i + 1 + " is null");

			// Validating Thumbnail field extensions
			String thumbnailValue = response.jsonPath().getString("data.layoutSearch[" + i + "].thumbnail");
			if (thumbnailValue.contains(".jpg") || thumbnailValue.contains(".png")
					|| thumbnailValue.contains(".jpeg")) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("Thumbnail extension is not in specified format");
			}
		}

		//ExtentLog.info("Validated Thumbnail field & all fields in All Layouts service are not NULL");
	}

	/**
	 * Method to validate the fields in PrelemByName service are not NULL
	 * 
	 */
	public void validateThumbnailFieldAndFieldsOfPrelemByNameNotNull(String endPointUrl) {
		Response response = null;
		String thumbnailValue;
		Map<String, String> headers = new HashMap<String, String>();
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Get method with Header and query params
		response = getCallWithHeaderParam(headers, baseUrl + endPointUrl);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		//ExtentLog.info("Response body is => " + response.getBody().asString());

		// Validating all fields are not NULL
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.PrelemId"), "Prelem Id field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.PrelemName"), "PrelemName field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.Tags"), "Tags field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.PreviewThumbnail"),
				"PreviewThumbnail field is null");
		// Validating Thumbnail field extensions
		thumbnailValue = response.jsonPath().getString("data.prelemByName.PreviewThumbnail");
		if (thumbnailValue.contains("platx-dspace")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("Image has not loaded from DSpace");
		}
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.Thumbnails.Desktop"),
				"Desktop Thumbnail field is null");
		// Validating Thumbnail field extensions
		thumbnailValue = response.jsonPath().getString("data.prelemByName.Thumbnails.Desktop");
		if (thumbnailValue.contains("platx-dspace")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("Image has not loaded from DSpace");
		}
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.Thumbnails.Mobile"),
				"Mobile Thumbnail field is null");
		// Validating Thumbnail field extensions
		thumbnailValue = response.jsonPath().getString("data.prelemByName.Thumbnails.Mobile");
		if (thumbnailValue.contains("platx-dspace")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("Image has not loaded from DSpace");
		}
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.Thumbnails.Tab"),
				"Tab Thumbnail field is null");
		// Validating Thumbnail field extensions
		thumbnailValue = response.jsonPath().getString("data.prelemByName.Thumbnails.Tab");
		if (thumbnailValue.contains("platx-dspace")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("Image has not loaded from DSpace");
		}
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
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.SeoEnabled"), "SeoEnabled field is null");
		Assert.assertNotNull(response.jsonPath().getString("data.prelemByName.AnalyticsEnabled"),
				"AnalyticsEnabled field is null");

		//ExtentLog.info("Validated Thumbnail field & all fields in PrelemByName service are not NULL");
	}

	/**
	 * Method to validate the fields in PrelemSearch services are not NULL
	 * 
	 */
	public void validateThumbnailFieldAndFieldsOfPrelemSearchNotNull(String endPointUrl,
			Map<String, String> queryParams) {
		Response response = null;
		String thumbnailValue;
		Map<String, String> headers = new HashMap<String, String>();
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Get method with Header and query params
		response = getCallWithHeaderAndQueryParam(headers, queryParams, baseUrl + endPointUrl);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		//ExtentLog.info("Response body is => " + response.getBody().asString());
		for (int j = 0; j < response.jsonPath().getList("data.prelemSearch.prelems").size(); j++) {
			// Validating all fields are not NULL
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].PrelemId"),
					"PrelemId field " + j + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].PrelemName"),
					"PrelemName field " + j + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].Tags"),
					"Tags field " + j + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].PreviewThumbnail"),
					"PreviewThumbnail field " + j + 1 + " is null");
			// Validating Thumbnail field extensions
			thumbnailValue = response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].PreviewThumbnail");
			if (thumbnailValue.contains("platx-dspace")) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("Image has not loaded from DSpace");
			}
			Assert.assertNotNull(
					response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].Thumbnails.Desktop"),
					"Thumbnails-Desktop field " + j + 1 + " is null");
			// Validating Thumbnail field extensions
			thumbnailValue = response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].Thumbnails.Desktop");
			if (thumbnailValue.contains("platx-dspace")) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("Image has not loaded from DSpace");
			}
			Assert.assertNotNull(
					response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].Thumbnails.Mobile"),
					"Thumbnails-Mobile field " + j + 1 + " is null");
			// Validating Thumbnail field extensions
			thumbnailValue = response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].Thumbnails.Mobile");
			if (thumbnailValue.contains("platx-dspace")) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("Image has not loaded from DSpace");
			}
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].Thumbnails.Tab"),
					"Thumbnails-Tab field " + j + 1 + " is null");
			// Validating Thumbnail field extensions
			thumbnailValue = response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].Thumbnails.Tab");
			if (thumbnailValue.contains("platx-dspace")) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("Image has not loaded from DSpace");
			}
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].Description"),
					"Description field " + j + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].DocumentType"),
					"DocumentType field " + j + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].DocumentPath"),
					"DocumentPath field " + j + 1 + " is null");
			Assert.assertNotNull(
					response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].DocumentCreationPath"),
					"DocumentCreationPath field " + j + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].DevelopedBy"),
					"DevelopedBy field " + j + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].DevelopedDate"),
					"DevelopedDate field " + j + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].SeoEnabled"),
					"SeoEnabled field " + j + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.prelems[" + j + "].AnalyticsEnabled"),
					"AnalyticsEnabled field " + j + 1 + " is null");
		}
		for (int k = 0; k < response.jsonPath().getList("data.prelemSearch.layout").size(); k++) {
			// Validating all fields are not NULL
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.layout[" + k + "].id"),
					"Id field " + k + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.layout[" + k + "].title"),
					"Title field " + k + 1 + " is null");
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.layout[" + k + "].thumbnail"),
					"Thumbnail field " + k + 1 + " is null");
			// Validating Thumbnail field extensions
			thumbnailValue = response.jsonPath().getString("data.prelemSearch.layout[" + k + "].thumbnail");
			if (thumbnailValue.contains(".jpg") || thumbnailValue.contains(".png")
					|| thumbnailValue.contains(".jpeg")) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("Thumbnail extension is not in specified format");
			}
			Assert.assertNotNull(response.jsonPath().getString("data.prelemSearch.layout[" + k + "].mapping"),
					"Mapping field " + k + 1 + " is null");
		}
		//ExtentLog.info("Validated Thumbnail field & all fields in PrelemSearch service are not NULL");
	}

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
	 * Method to validate the fields in PrelemById service are not NULL
	 * 
	 */
	public void validateFieldsOfPrelemByIdNotNull(String endPointUrl) {
		Map<String, String> headers = new HashMap<String, String>();
		Response response = null;
		String isAnalyticsEnabled;
		String isSeoEnabled;
		String responseBody;

		// Pass Header Info
		headers.put("Content-Type", getContentType);
		// Pass Response body
		responseBody = "query{\r\n" + "prelemById(prelemId:\"Prelem_001\"){\r\n" + "PrelemId\r\n"
				+ "AnalyticsEnabled\r\n" + "SeoEnabled\r\n" + "}\r\n" + "}";
		JSONObject json = new JSONObject();
		json.put("query", responseBody);

		// Call Post method with Header & response body
		response = postCallWithHeaderAndJsonBodyParam(headers, json, baseUrl + endPointUrl);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		//ExtentLog.info("Response body is => " + response.getBody().asString());
		Assert.assertNotNull(response.jsonPath().getString("data.prelemById.PrelemId"), "PrelemId field is null");
		isAnalyticsEnabled = response.jsonPath().getString("data.prelemById.AnalyticsEnabled");
		if (isAnalyticsEnabled.contains("true") || isAnalyticsEnabled.contains("false")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("AnalyticsEnabled field contains an Incorrect data");
		}
		isSeoEnabled = response.jsonPath().getString("data.prelemById.SeoEnabled");
		if (isSeoEnabled.contains("true") || isSeoEnabled.contains("false")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("SeoEnabled field contains an Incorrect data");
		}

		//ExtentLog.info("Validated all fields in PrelemById service are not NULL");
	}

}
