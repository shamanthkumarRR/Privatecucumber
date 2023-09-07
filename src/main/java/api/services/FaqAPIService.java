package api.services;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import io.restassured.response.Response;
import api.utility.EnvParameters;
import api.utility.UtilityServices;

public class FaqAPIService extends UtilityServices {

	// Test Data
	public static final String baseUrl = EnvParameters.getData("apiBaseUrl");
	public static final String getContentType = EnvParameters.getData("contentType");
	public static final String faqEndpoint = EnvParameters.getData("faqEndpointUrl");

	/**
	 * Method to return the responses of FAQ Service
	 * 
	 * @return
	 */
	public Response validateResponseStatus() {
		Response response = null;
		Map<String, String> headers = new HashMap<String, String>();
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Get method with Header
//		response = getCallWithHeaderParam(headers, baseUrl + faqEndpoint);
		
		response = getCall(baseUrl + faqEndpoint);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		return response;
	}

	/**
	 * Method to validate the length of Question Field
	 * 
	 */
	public void validateQuestionFieldLength(int expectedQuestionLength) {
		Response response = null;
		Map<String, String> headers = new HashMap<String, String>();
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Get method with Header
		response = getCallWithHeaderParam(headers, baseUrl + faqEndpoint);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		//ExtentLog.info("Response body is => " + response.getBody().asString());
		for (int i = 0; i < response.jsonPath().getList("data.faqs").size(); i++) {
			Assert.assertNotNull(response.jsonPath().getString("data.faqs[" + i + "].hclplatformx_Question"),
					"Question field " + i + 1 + " is null");
			Assert.assertTrue(
					response.jsonPath().getString("data.faqs[" + i + "].hclplatformx_Question")
							.length() <= expectedQuestionLength,
					"Question field value length is less than / equal to " + expectedQuestionLength);
		}
		//ExtentLog.info("Validated Question field length and value is not NULL");
	}
	
	/**
	 * Method to validate the length of Answer Field
	 * 
	 */
	public void validateAnswerFieldLength(int expectedAnswerLength) {
		Response response = null;
		Map<String, String> headers = new HashMap<String, String>();
		// Pass Header Info
		headers.put("Content-Type", getContentType);

		// Call Get method with Header
		response = getCallWithHeaderParam(headers, baseUrl + faqEndpoint);

		// retrieve the status code
		//ExtentLog.info("Response code is => " + response.getStatusCode());
		//ExtentLog.info("Response body is => " + response.getBody().asString());
		for (int i = 0; i < response.jsonPath().getList("data.faqs").size(); i++) {
			Assert.assertNotNull(response.jsonPath().getString("data.faqs[" + i + "].hclplatformx_Answer"),
					"Answer field " + i + 1 + " is null");
			Assert.assertTrue(
					response.jsonPath().getString("data.faqs[" + i + "].hclplatformx_Answer")
							.length() <= expectedAnswerLength,
					"Answer field value length is less than / equal to " + expectedAnswerLength);
		}
		//ExtentLog.info("Validated Answer field length and value is not NULL");
	}

}
