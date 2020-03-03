package com.api.tests;

import com.api.utils.BaseClass;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends BaseClass {

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
    public void CreateBookings(Method method) {
        testCase = method.getName();
        extentTest = extent.createTest("Create Bookings and Validate");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "                  \"completeAfterCreate\": true,\n" +
                "                  \"bookings\": [\n" +
                "                    {\n" +
                "                      \"addressCompany\": \"string\",\n" +
                "                      \"addressLine1\": \"string\",\n" +
                "                      \"addressLine2\": \"string\",\n" +
                "                      \"addressLine3\": \"string\",\n" +
                "                      \"addressCity\": \"string\",\n" +
                "                      \"addressCountry\": \"string\",\n" +
                "                      \"addressPostcode\": \"string\",\n" +
                "                      \"addressPONumber\": \"string\",\n" +
                "                      \"addressType\": {\n" +
                "                        \"value\": 0,\n" +
                "                        \"description\": \"string\"\n" +
                "                      },\n" +
                "                      \"invoiceRequired\": true,\n" +
                "    \"contactId\": \" " + contactId + "\",\n" +
                "                      \"discountCode\": \"string\",\n" +
                "                      \"discountCodeId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                      \"eventId\": \"0d82b943-e9d5-e711-a949-00224807251a\",\n" +
                "                      \"organisationId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                      \"paymentMethodId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                      \"paymentAmount\": 0,\n" +
                "                      \"paymentReference\": \"string\",\n" +
                "                      \"debitTransactionId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                      \"delegates\": [\n" +
                "                        {\n" +
                "                          \"bookerId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                          \"bookingId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "    \"contactId\": \" " + contactId + "\",\n" +
                "                          \"eventId\": \"0d82b943-e9d5-e711-a949-00224807251a\",\n" +
                "                          \"eventRateId\": \"8d532e6f-c4a4-e911-a827-000d3a0b617e\",\n" +
                "                          \"organisationId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                          \"sessions\": [\n" +
                "                            {\n" +
                "                              \"eventSessionId\": \"e7c57ac4-c3a4-e911-a827-000d3a0b617e\",\n" +
                "    \"contactId\": \" " + contactId + "\",\n" +
                "                              \"delegateId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                              \"organisationId\": \"00000000-0000-0000-0000-000000000000\"\n" +
                "                            }\n" +
                "                          ],\n" +
                "                          \"options\": [\n" +
                "                            {\n" +
                "                              \"bookingId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                              \"delegateId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                              \"eventOptionId\": \"9332209a-b2f4-e911-a813-000d3a7ed677\"\n" +
                "                            }\n" +
                "                          ],\n" +
                "                          \"accessRequirement\": \"string\",\n" +
                "                          \"email\": \"string\",\n" +
                "                          \"fullName\":\"ede\",\n" +
                "                          \"dietaryRequirements\": \"string\",\n" +
                "                          \"jobTitle\": \"string\",\n" +
                "                          \"delegateOrganisationName\": \"string\",\n" +
                "                          \"delegateType\": {\n" +
                "                            \"value\": 167410000,\n" +
                "                            \"description\": \"string\"\n" +
                "                          }\n" +
                "                        }\n" +
                "                      ],\n" +
                "                      \"bookingStatus\": {\n" +
                "                        \"value\":167410000,\n" +
                "                        \"description\": \"string\"\n" +
                "                      },\n" +
                "                      \"bookingMethod\": {\n" +
                "                        \"value\": 167410001,\n" +
                "                        \"description\": \"string\"\n" +
                "                      }\n" +
                "                    }\n" +
                "                  ]\n" +
                "                }";

        extentTest.log(Status.INFO," POST request Body : <br /> "+ jsonBody);
        request.body(jsonBody);
        response = request.post(BASE_URL + testCase);

        verifyResponse.validateAssertion();

        Matcher bid = Pattern.compile("tri_booking\",\"id\":\"([a-zA-Z0-9-]{36})\",").matcher(response.asString());
        System.out.println(bookingId);
        while (bid.find()) { bookingId = bid.group().split("\"")[4];}



    }


}
