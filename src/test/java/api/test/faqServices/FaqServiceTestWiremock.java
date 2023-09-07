package api.test.faqServices;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import io.restassured.response.Response;
import org.testng.Assert;
import api.services.FaqAPIService;
import api.testBase.ApiBaseClass;
import api.utility.EnvParameters;

//@Listeners(web.utility.Listeners.class)	
public class FaqServiceTestWiremock extends ApiBaseClass {

	// Page Objects
	private static final String HOST = "localhost";
	private static final int PORT = 8089;
	private static String ENDPOINT;
	private static WireMockServer server = new WireMockServer(PORT);
	public FaqAPIService faqService;

	// Reusable Variable Declaration
	public Response response;

	@BeforeClass
	public void generateData() {

		ENDPOINT = EnvParameters.getData("faqEndpointUrl");

		// Start Wiremock server via code and configure it.
		server.start();
		configureFor(HOST, PORT);

		// Response mapping
		ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
		mockResponse.withStatus(200);
//		mockResponse.withBodyFile("__files/jsonresponse/faqResponse.json");

		System.out.println("ENDPOINT:" + ENDPOINT);

		// Mocking
		stubFor(get(urlPathEqualTo("/api/v1/web/en/faqs/0/11")).willReturn(mockResponse));

		// To load datafactory
		DataFactory_FaqService.dataFactory();
	}

	@BeforeMethod
	public void inItializeObjects() {
		faqService = new FaqAPIService();
	}

	/**
	 * Author: Senthil
	 * 
	 */
	@Test(enabled = true, priority = 1, description = "Validate the response status code of FAQ Service")
	public void VerifyFaqResponseStatus() {
		int actualStatusCode;
		int expectedStatusCode = DataFactory_FaqService.statusCode1;

		response = faqService.validateResponseStatus();
		actualStatusCode = response.getStatusCode();
		Assert.assertEquals(actualStatusCode, expectedStatusCode);
		//ExtentLog.info("Validated the response status code of FAQ Service");
	}

	@Test(enabled = true, priority = 2, description = "Validate Question field length and its value is not null")
	public void VerifyFaqQuestionFieldLength() {
		int expectedQuestionFieldLength = DataFactory_FaqService.questionFieldCharCount;
		faqService.validateQuestionFieldLength(expectedQuestionFieldLength);
	}

	@Test(enabled = true, priority = 3, description = "Validate Answer field length and its value is not null")
	public void VerifyFaqAnswerFieldLength() {
		int expectedAnswerFieldLength = DataFactory_FaqService.answerFieldCharCount;
		faqService.validateAnswerFieldLength(expectedAnswerFieldLength);
	}

	@AfterClass
	public void closeServer() {
		if (server.isRunning() && server != null) {
			System.out.println("Connection Closed");
			server.shutdown();
		}
	}
}
