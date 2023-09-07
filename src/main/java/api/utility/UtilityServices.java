package api.utility;

import java.util.List;
import java.util.Map;

import api.testBase.WebBaseClass;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UtilityServices extends WebBaseClass {

	public static Logger log = Logger.getLogger(UtilityServices.class);

	/**
	 * Method to make restassured get call
	 * 
	 * @param URL
	 * @return response
	 */
	public static Response getCall(String URL) {

		log.info("Get call on endpoint " + URL);
		return RestAssured.given().relaxedHTTPSValidation().request().when().get(URL);
	}

	/**
	 * Method to make restassured get call with header parameters
	 * 
	 * @param headers
	 * @param URL
	 * @return response
	 */
	public static Response getCallWithHeaderParam(Map<String, String> headers, String URL) {

		log.info("Get call on endpoint " + URL + " with headers " + headers.entrySet());
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).request().when().get(URL);
	}
	
	/**
	 * Method to make restassured get call with header and query parameters
	 * 
	 * @param headers
	 * @param queryParam
	 * @param URL
	 * @return response
	 */
	public static Response getCallWithHeaderAndQueryParam(Map<String, String> headers, Map<String, String> queryParam,
			String URL) {

		log.info("Get call on endpoint " + URL + " with headers " + headers.entrySet() + " and query parameters "
				+ queryParam.entrySet());
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).queryParams(queryParam).request().when()
				.get(URL);
	}

	/**
	 * Method to make restassured get call with header and duplicate key query parameters
	 * 
	 * @param headers
	 * @param queryParams
	 * @param URL
	 * @return response
	 */
	public static Response getCallWithHeaderAndDuplicateKeyQueryParam(Map<String, String> headers, Map<String, List<String>> queryParams,
			String URL) {

		log.info("Get call on endpoint " + URL + " with headers " + headers.entrySet() + " and query parameters "
				+ queryParams.entrySet());
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).queryParams(queryParams).request().when()
				.get(URL);
	}

	/**
	 * Method to make restassured get call with cookies
	 * 
	 * @param URL
	 * @param cookieValue
	 * @return response
	 */
	public static Response getCallWithCookies(String URL, String cookieValue) {

		log.info("Get call on endpoint " + URL + " with cookie " + cookieValue);
		return RestAssured.given().relaxedHTTPSValidation().cookie(cookieValue).request().when().get(URL);
	}

	/**
	 * Method to make restassured get call with headers and cookies
	 * 
	 * @param headers
	 * @param URL
	 * @param cookieValue
	 * @return response
	 */
	public static Response getCallWithHeaderAndCookies(Map<String, String> headers, String URL, String cookieValue) {

		log.info("Get call on endpoint " + URL + " with headers " + headers.entrySet() + " and cookie " + cookieValue);
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).cookie(cookieValue).request().when()
				.get(URL);
	}

	/**
	 * Method to make restassured post call with header and form parameters
	 * 
	 * @param headers
	 * @param formParams
	 * @param URL
	 * @return response
	 */
	public static Response postCallWithHeaderAndFormParam(Map<String, String> headers, Map<String, String> formParams,
			String URL) {

		log.info("POST call on endpoint " + URL + " with headers " + headers.entrySet() + " and Form Parameter "
				+ formParams.entrySet());
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).formParams(formParams).request().when()
				.post(URL);
	}

	/**
	 * Method to make restassured post call with header and JSONobject parameters
	 * 
	 * @param headers
	 * @param jsonObject
	 * @param URL
	 * @return response
	 */
	public static Response postCallWithHeaderAndJsonBodyParam(Map<String, String> headers, JSONObject jsonObject,
			String URL) {

		log.info("POST call on endpoint " + URL + " with headers " + headers.entrySet() + " and JSON String Parameter "
				+ jsonObject);
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).request().body(jsonObject).when()
				.post(URL);
	}

	/**
	 * Method to make restassured post call with header and JSONobject as string
	 * parameters
	 * 
	 * @param headers
	 * @param jsonObject
	 * @param URL
	 * @return response
	 */
	public static Response postCallWithHeaderAndJsonBodyParam(Map<String, String> headers, String jsonObject,
			String URL) {

		log.info("POST call on endpoint " + URL + " with headers " + headers.entrySet() + " and JSON String Parameter "
				+ jsonObject);
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).request().body(jsonObject).when()
				.post(URL);
	}

	/**
	 * Method to make restassured post call with header parameters
	 * 
	 * @param headers
	 * @param URL
	 * @return response
	 */
	public static Response postCallWithHeaderParam(Map<String, String> headers, String URL) {

		log.info("POST call on endpoint " + URL + " with headers " + headers.entrySet());
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).request().when().post(URL);
	}

	/**
	 * Method to make restassured delete call with header and JSONobject parameters
	 * 
	 * @param headers
	 * @param jsonObject
	 * @param URL
	 * @return response
	 */
	public static Response deleteCallWithHeaderAndJsonParam(Map<String, String> headers, JSONObject jsonObject,
			String URL) {

		log.info("DELETE call on endpoint " + URL + " with headers " + headers.entrySet()
				+ " and JSON Object Parameter " + jsonObject);
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).request().body(jsonObject).when()
				.delete(URL);
	}

	/**
	 * Method to make restassured delete call with header and JSONobject as String
	 * parameters
	 * 
	 * @param headers
	 * @param jsonObject
	 * @param URL
	 * @return response
	 */
	public static Response deleteCallWithHeaderAndJsonParam(Map<String, String> headers, String jsonObject,
			String URL) {

		log.info("DELETE call on endpoint " + URL + " with headers " + headers.entrySet()
				+ " and JSON String Parameter " + jsonObject);
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).request().body(jsonObject).when()
				.delete(URL);
	}

	/**
	 * Method to make restassured delete call with header parameter
	 * 
	 * @param headers
	 * @param URL
	 * @return response
	 */
	public static Response deleteCallWithHeaderWithoutRequestBody(Map<String, String> headers, String URL) {

		log.info("DELETE call on endpoint " + URL + " with headers " + headers.entrySet());
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).request().when().delete(URL);
	}

	/**
	 * Method to make restassured put call with header and JSON object parameters
	 * 
	 * @param headers
	 * @param jsonObject
	 * @param URL
	 * @return response
	 */
	public static Response putCallWithHeaderAndJsonParam(Map<String, String> headers, JSONObject jsonObject,
			String URL) {

		log.info("PUT call on endpoint " + URL + " with headers " + headers.entrySet() + " and JSON Object Parameter "
				+ jsonObject);
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).request().body(jsonObject).when().put(URL);
	}

	/**
	 * Method to make restassured put call with header and JSON string parameters
	 * 
	 * @param headers
	 * @param jsonObject
	 * @param URL
	 * @return response
	 */
	public static Response putCallWithHeaderAndJsonParam(Map<String, String> headers, String jsonObject, String URL) {

		log.info("PUT call on endpoint " + URL + " with headers " + headers.entrySet() + " and JSON String Parameter "
				+ jsonObject);
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).request().body(jsonObject).when().put(URL);
	}

	/**
	 * Method to make restassured patch call with header and JSON object parameters
	 * 
	 * @param headers
	 * @param jsonObject
	 * @param URL
	 * @return response
	 */
	public static Response patchCallWithHeaderAndJsonParam(Map<String, String> headers, JSONObject jsonObject,
			String URL) {

		log.info("PATCH call on endpoint " + URL + " with headers " + headers.entrySet() + " and JSON Object Parameter "
				+ jsonObject);
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).request().body(jsonObject).when()
				.patch(URL);
	}

	/**
	 * Method to make restassured patch call with header and JSON string parameters
	 * 
	 * @param headers
	 * @param jsonObject
	 * @param URL
	 * @return response
	 */
	public static Response patchCallWithHeaderAndJsonParam(Map<String, String> headers, String jsonObject, String URL) {

		log.info("PATCH call on endpoint " + URL + " with headers " + headers.entrySet() + " and JSON String Parameter "
				+ jsonObject);
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).request().body(jsonObject).when()
				.patch(URL);
	}
}
