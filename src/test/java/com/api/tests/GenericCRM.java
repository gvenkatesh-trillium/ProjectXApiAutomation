package com.api.tests;

import com.api.utils.BaseClass;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Random;


public class GenericCRM extends BaseClass {
    @Test
    public void CreateContactActivatedAndValidated(Method method) {

        testCase = method.getName();
        extentTest = extent.createTest( "Test case : Create contact, Activate and Validate");
        request.header("Content-Type", "application/json");
        rNum = String.valueOf(new Random().nextInt(100000 - 10 + 1) + 1);
        jsonBody = "{\n" +
                "  \n" +
                "  \"firstName\": \"API Automation FirstName\",\n" +
                "  \"lastName\": \"API Automation LasttName\",\n" +
                "\n" +
                "  \"personalemail\": \"personalemail." + rNum + "GV@Automation.com\",\n" +
                "  \"preferredEmailAddress\": {\n" +
                "    \"value\": 167410000,\n" +

                "  },\n" +
                "\n" +
                "  \"title\": {\n" +
                "    \"value\": 167410032,\n" +
                "    \"description\": \"string\"\n" +
                "},\n" +
                " \n" +
                "\n" +
                "  \"jobRoleOther\": \"API jobRoleOther\",\n" +
                "  \"jobTitle\": \"API jobTitle\",\n" +
                "  \"organisationText\": \"OrganisationText\"\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO, " POST request Body : <br />" + jsonBody);
        response = request.post(BASE_URL + testCase);

        try {
            contactId = response.asString().split("\"")[9];
            eMail = response.asString().split("\"")[17];
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "Failed to extract to Contact ID and eMail from response : <br />" + response.asString());
            e.printStackTrace();
        }
        if (!eMail.contains("@")) {
            extentTest.log(Status.FAIL, " no email found in response : <br/>" + response.asString());
        }
        Assert.assertTrue(response.asString().contains(contactId));

        verifyResponse.validateAssertion();

    }

    @Test
    public void RetrieveEntities(Method method) {

        testCase = method.getName();
        extentTest = extent.createTest(" Retrieve Entities and Validate");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "\"entityName\": \"contact\",\n" +
                "  \"retrieveAllAttributesValues\": true,\n" +
                "  \"pagingInformation\": {\n" +
                "    \"currentPage\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"sortBy\": \"tri_title_options\",\n" +
                "   \n" +
                "\n" +
                "}";

        extentTest.log(Status.INFO," POST request Body : <br /> "+ jsonBody);
        request.body(jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test
    public void RetrieveOptionSet(Method method) {

        testCase = method.getName();
        extentTest = extent.createTest(" Retrieve OptionSet and Validate");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"entityName\": \"contact\",\n" +
                "  \"optionSetName\": \"tri_title_options\"\n" +
                "}";

        extentTest.log(Status.INFO," POST request Body : <br /> "+ jsonBody);
        request.body(jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test
    public void RetrieveMultiplesOptionsSet(Method method) {

        testCase = method.getName();
        extentTest = extent.createTest(" Retrieve Multiples OptionsSet and Validate the response");
        request.header("Content-Type", "application/json");

         jsonBody = " { \"optionsSet\": [ { \"entityName\": \"contact\", \"optionSetName\": \"preferredcontactmethodcode\" } ], \"optionsSet\": [ { \"entityName\": \"contact\", \"optionSetName\": \"tri_engagementlevel\" } ] }";


        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br /> "+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();


    }

    @Test
    public void RetrieveCountries(Method method) {

        testCase = method.getName();
        extentTest = extent.createTest("Retrieve Countries and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"pagingInformation\": {\n" +
                "    \"currentPage\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"sortBy\": \"tri_name\",\n" +
                "    \"orderBy\": \"ASC\",\n" +
                "    \"maxResultCount\": 1000,\n" +
                "    \"totalCountLimitExceeded\": true\n" +
                "  }\n" +
                "}";


        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : "+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();



    }

    @Test
    public void RetrieveCounties(Method method) {

        testCase = method.getName();
        extentTest = extent.createTest("Retrieve Counties and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"pagingInformation\": {\n" +
                "    \"currentPage\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"sortBy\": \"tri_name\",\n" +
                "    \"orderBy\": \"ASC\",\n" +
                "    \"maxResultCount\": 1000,\n" +
                "    \"totalCountLimitExceeded\": true\n" +
                "  }\n" +
                "}";


        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();



    }

    @Test
    public void RetrieveAllBranches(Method method) {

        testCase = method.getName();
        extentTest = extent.createTest("Retrieve All Branches and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"pagingInformation\": {\n" +
                "    \"currentPage\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"sortBy\": \"tri_name\",\n" +
                "    \"orderBy\": \"ASC\",\n" +
                "    \"maxResultCount\": 1000,\n" +
                "    \"totalCountLimitExceeded\": true\n" +
                "  }\n" +
                "}";


        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();


    }

    @Test
    public void LogMemberValue(Method method) {

        testCase = method.getName();
        extentTest = extent.createTest("Log Member Value and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"subject\": \"aaaa\",\n" +
                "  \"contactId\": \"52f749c7-5e8c-e911-a820-000d3a0b5822\",\n" +
                "  \"memberusageTypeId\": \"1666d028-b052-e811-a958-002248072fe8\"\n" +
                "}";


        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : "+ jsonBody);

        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();


    }

    @Test
    public void GetAllContactRoles(Method method) {

        testCase = method.getName();
        extentTest = extent.createTest("Get All Contact Roles and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"pagingInformation\": {\n" +
                "    \"currentPage\": 1,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"sortBy\": \"tri_name\",\n" +
                "    \"orderBy\": \"ASC\",\n" +
                "    \"maxResultCount\": 1000,\n" +
                "    \"totalCountLimitExceeded\": true\n" +
                "  }\n" +
                "}";


        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br /> "+ jsonBody);
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : "+ jsonBody);
        response = request.post(BASE_URL + testCase);

        verifyResponse.validateAssertion();



    }

    @Test(dependsOnMethods = {"CreateContactActivatedAndValidated"})
    public void GetContactRolesByContactId(Method method) {

        testCase = method.getName();
        extentTest = extent.createTest("Get Contact Roles By ContactId and Validate(GET Request)");
        request.body(jsonBody);
        extentTest.log(Status.INFO,"Get request Endpoint URL : "+ BASE_URL +"/"+ testCase);
        response = request.get(BASE_URL + testCase+"/"+contactId);
        verifyResponse.validateAssertion();



    }
    @Test
    public void RetrieveAllPeriods(Method method) {

        testCase = method.getName();
        extentTest = extent.createTest("Retrieve All Periods and Validate(GET Request)");
//        request.body(jsonBody);
        extentTest.log(Status.INFO,"Get request Endpoint URL : "+ BASE_URL +"/"+ testCase);
        response = request.get(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test
    public void PayTransaction(Method method) throws IOException {
// response code is 200 but throwing an error "An error occurred calling the Payment Service: Received an unmapped Provider Type."
        testCase = method.getName();
        extentTest = extent.createTest("Pay Transaction and Validate the response(f)");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"transactionId\":\""+ data.UnPaidTransactions() +"\",\n" +
                "  \"methodOfPaymentId\":  \""+ data.methodOfPaymentId() +"\",\n" +
                "  \"onlinePaymentSuccessURL\": \"https://trilliumx.trilliumsystems.net/TrilliumX_Dev/v1.0/PaymentGateways/PaymentGateway/Success.aspx\",\n" +
                "  \"onlinePaymentFailureURL\": \"www.onlinePaymentFailureURL.com\"\n" +
                "}";


        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : "+ jsonBody);

        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();


    }
}
