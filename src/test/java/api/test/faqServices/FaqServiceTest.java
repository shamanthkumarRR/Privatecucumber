package api.test.faqServices;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import org.testng.Assert;
import api.services.FaqAPIService;
import api.testBase.ApiBaseClass;

//@Listeners(web.utility.Listeners.class)	
public class FaqServiceTest extends ApiBaseClass {

	// Page Objects
	public FaqAPIService faqService;
	
	// Reusable Variable Declaration
	public Response response;

	@BeforeTest
	public void generateData() {
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
		Assert.assertEquals(expectedStatusCode, actualStatusCode);
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
}
