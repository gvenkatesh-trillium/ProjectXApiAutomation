package com.api.utils;

import com.aventstack.extentreports.Status;
import org.testng.Assert;

public class VerifyResponse extends BaseClass{



    public void validateAssertion(){
        extentTest.log(Status.INFO, "endPoint URL :  <br />"+BASE_URL + testCase);
        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            extentTest.log(Status.PASS, " Check GET/POST response Code : <br />" + response.getStatusCode());
        } catch (AssertionError StatusCodeError) {
            extentTest.log(Status.FAIL, "Expected Response Code \"200\" but returned : <br />" + response.getStatusCode());
            extentTest.log(Status.FAIL, "Expected Response Success is \"true\" and messageError is \"null\" but returned : <br />" + response.asString());
            throw StatusCodeError;
        }
        try {
            Assert.assertTrue(response.asString().contains("\"success\":" + "true"));
            extentTest.log(Status.PASS, " Check GET/POST response Body  has Success is \"true\" and messageError is \"null\" : <br />" + response.asString());
        } catch (AssertionError SuccessError) {
            extentTest.log(Status.FAIL, "Expected Response Success is \"true\" and messageError is \"null\" but returned : <br />" + response.asString());
            throw SuccessError;
        }
    }
}
