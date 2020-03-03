package com.api.tests;

import com.api.utils.BaseClass;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class CPD extends BaseClass {

    @Test(groups = "Membership.CreateContactActivatedAndValidated")
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
            extentTest.log(Status.FAIL, "Failed to extract Contact ID and eMail from response : <br />" + response.asString());
            e.printStackTrace();
        }
        if (!eMail.contains("@")) {
            extentTest.log(Status.FAIL, " no email found in response : <br/>" + response.asString());
        }
        Assert.assertTrue(response.asString().contains(contactId));

        verifyResponse.validateAssertion();

    }


    @Test()
    public void RetrieveCpdDiaries(Method method) throws IOException {
        testCase = method.getName();
        extentTest = extent.createTest("Retrieve Cpd Diaries and Validate the response");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"contactId\": \" " + data.ContactsWithCPD() + "\",\n" +
                " }";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test()
    public void RetrieveCpdActivitiesByDiaryId(Method method) throws IOException {
        testCase = method.getName();
        extentTest = extent.createTest("Retrieve Cpd Activities By Diary Id and Validate the response");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"id\": \" " + data.CPDdiary() + "\",\n" +
                " }";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }
    @Test()
    public void RetrieveCpdDiaryById(Method method) throws IOException {
        testCase = method.getName();
        extentTest = extent.createTest("Retrieve Cpd Diary By Id and Validate the response");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"id\": \" " + data.CPDdiary() + "\",\n" +
                " }";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test()
    public void RetrieveCpdActivityById(Method method) throws IOException {
        testCase = method.getName();
        extentTest = extent.createTest("Retrieve Cpd Activity By Id and Validate the response");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"id\": \" " + data.CPDdiary() + "\",\n" +
                " }";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test(dependsOnMethods = {"CreateContactActivatedAndValidated"})
    public void CreateCpdActivity(Method method) throws IOException {
        testCase = method.getName();
        extentTest = extent.createTest("Create Cpd Activity and Validate the response");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"contactId\":  \" " + contactId + "\",\n" +
                "  \"cpdDiaryId\": \" " + data.CPDdiary() + "\",\n" +
                "  \"subject\": \"subject\",\n" +
                "  \"scheduledStart\": \" " + LocalDateTime.now() + "\",\n" +
                "  \"cpdHours\": 50,\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();
        try {
            cpdActivity = response.asString().split("\"")[9];
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "Failed to extract cpd Activity ID from response : <br />" + response.asString());
            e.printStackTrace();
        }

    }

    @Test(dependsOnMethods = {"CreateContactActivatedAndValidated"})
    public void UpdateCpdActivity(Method method) throws IOException {
        testCase = method.getName();
        extentTest = extent.createTest("Update Cpd Activity and Validate the response(f)");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"clearCpdActivityDataForEmptyParams\": true,\n" +
                "  \"id\": \" " + data.CPDdiary() + "\",\n" +
                "  \"contactId\":  \" " + contactId + "\",\n" +
                "  \"cpdDiaryId\": \" " + data.CPDdiary() + "\",\n" +
                "  \"subject\": \"Subject_UpdateCpdActivity\",\n" +
                "  \"scheduledStart\": \"2020-02-18T12:39:09.414Z\",\n" +
                "  \"cpdHours\": 120,\n" +
                " }";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test(dependsOnMethods = {"CreateContactActivatedAndValidated"})
    public void CreateCpdDiary(Method method) throws IOException {
        testCase = method.getName();
        extentTest = extent.createTest("Create Cpd Diary and Validate the response");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"contactId\":  \" " + contactId + "\",\n" +
                "  \"name\": \"CreateCpdDiary\",\n" +
                "  \"appraisalDate\": \" " + LocalDateTime.now() + "\",\n" +
                "  \"hoursRecorded\": 10,\n" +
                "  \"hoursRequired\": 20,\n" +
                " \n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test(dependsOnMethods = {"CreateContactActivatedAndValidated","CreateCpdActivity"})
    public void DeleteCpdActivity(Method method) throws IOException {
        testCase = method.getName();
        extentTest = extent.createTest("Delete Cpd Activity Validate the response(DELETE request)");
        request.header("Content-Type", "application/json");

        request.body(jsonBody);
        response = request.delete(BASE_URL + testCase+"/"+cpdActivity);
        extentTest.log(Status.INFO,"Delete Endpoint URL : "+ BASE_URL + testCase+"/"+cpdActivity);
        verifyResponse.validateAssertion();

    }
}
