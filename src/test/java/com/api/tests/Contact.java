package com.api.tests;


import com.api.utils.BaseClass;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Random;


public class Contact extends BaseClass {

    @Test()
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

    @Test(priority=1, dependsOnMethods = {"CreateContactActivatedAndValidated"})
    public void UpdateContact(Method method){
        testCase = method.getName();
        rNum = String.valueOf(new Random().nextInt(100000 - 10 + 1) + 1);
        extentTest = extent.createTest("Updating  firstName, lastName and eMail for id : <br />"+ contactId);
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"clearContactDataForEmptyParams\": true,\n" +
                " \"id\": \"" + contactId + "\",\n" +
                "  \"validateContact\": true,\n" +
                "  \"lastName\": \"UpDateContactLastName\",\n" +
                "  \"middleName\": \"UpDateMiddleName\",\n" +
                "  \"personalemail\": \"updateContact" + rNum + "@API.com\"\n" +
                " \n" +
                "}";
        request.body(jsonBody);
      extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL+ testCase);

        verifyResponse.validateAssertion();


    }
    @Test(priority=2, dependsOnMethods = {"CreateContactActivatedAndValidated"})
    public void RetrieveContact(Method method){
        testCase = method.getName();
        extentTest = extent.createTest(" Retrieve Contact by eMail and verify the response");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"email\":\""+ eMail + "\",\n" +
                " \n" +
                "}";
      extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        request.body(jsonBody);
        response = request.post(BASE_URL+ testCase);
        verifyResponse.validateAssertion();

    }


    @Test(priority=3, dependsOnMethods = {"RetrieveContact"})
    public void CheckContactExists(Method method) {

        testCase = method.getName();
        extentTest = extent.createTest(" Test case: Check Contact Exists by eMail and verify the response");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"email\": \"test@Automation1.com\"\n" +
                " \n" +
                "}";
      extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        request.body(jsonBody);
        response = request.post(BASE_URL+ testCase);

        verifyResponse.validateAssertion();

    }



}
