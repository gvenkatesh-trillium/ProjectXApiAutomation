package com.api.tests;

import com.api.utils.BaseClass;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Random;

public class FundRaising extends BaseClass {

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
            extentTest.log(Status.FAIL, "Failed to extract to Contact ID and eMail from response : <br />" + response.asString());
            e.printStackTrace();
        }
        if (!eMail.contains("@")) {
            extentTest.log(Status.FAIL, " no email found in response : <br/>" + response.asString());
        }
        Assert.assertTrue(response.asString().contains(contactId));

        verifyResponse.validateAssertion();

    }

    @Test(priority = 1)
    public void CreateDonation(Method method){
        testCase = method.getName();
        extentTest = extent.createTest("Create Donation and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"donationDate\": \"2020-02-18T12:39:09.775Z\",\n" +
                " \"fundId\": \" " + data.fundId() + "\",\n" +
                "  \"fundAllocationCode\": \"string\",\n" +
                " \"contactId\": \" " + contactId + "\",\n" +
                "  \"amount\": \" "+ rNum +"\",\n" +
                "  \"periodicAmount\": 12,\n" +
                "  \"methodOfPaymentId\": \" " +data.methodOfPaymentId()  + "\",\n" +
                "  \"paymentType\": 167410001,\n" +
                "  \"paymentFrequency\": 167410000,\n" +
                "  \"recurringDayOfMonth\": 1,\n" +
                "  \"firstInstallmentDate\": \" "+ LocalDateTime.now() +"\",\n" +
                "  \"lastInstallmentDateDue\": \" "+ LocalDateTime.now() +"\",\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test(priority = 2, dependsOnMethods = {"CreateContactActivatedAndValidated"})
    public void CreateFundraisingDonation(Method method){
        testCase = method.getName();
        extentTest = extent.createTest("Create Fundraising Donation and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"isGift\": true,\n" +
                "  \"donor\": {\n" +
                " \"contactId\": \" " + contactId + "\",\n" +
                "    \"emailAddress\":  \"personalemail." + rNum + "GV@Automation.com\",\n" +
                "    \"fistName\": \"CreateFundraisingDonation\",\n" +
                "    \"lastName\": \"CreateFundraisingDonation\",\n" +
                "  },\n" +
                "  \"billingContact\": {\n" +
                "    \"contactId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "    \"emailAddress\": \"contactId@email.com\",\n" +
                "  },\n" +
                "  \"giftAidDeclaration\": {\n" +
                "    \"optIn\": true,\n" +
                "    \"date\": \"2020-02-18T12:39:09.784Z\"\n" +
                "  },\n" +
                "  \"paymentDetails\": {\n" +
                "    \"onlinePayment\": true,\n" +
                "  \"amount\": \" "+ rNum +"\",\n" +
                "  },\n" +
                "  \n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        try {
            donationId = response.asString().split("\"")[13];
            System.out.println("donationId = "+ donationId);
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "Failed to extract to Donation ID from response : <br />" + response.asString());
            e.printStackTrace();
        }
        verifyResponse.validateAssertion();

    }
    @Test(priority = 3, dependsOnMethods = "CreateFundraisingDonation")
    public void UpdateDonation(Method method){
        testCase = method.getName();
        extentTest = extent.createTest("Update Donation and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"donationId\": \" " + donationId + "\",\n" +
                "  \"amount\": 155,\n" +
                "  \"periodicAmount\": 0,\n" +
                "    \"methodOfPaymentId\": \" " + data.methodOfPaymentId() + "\",\n" +
                "  \"paymentType\": 167410001,\n" +
                "  \"paymentFrequency\": 167410000,\n" +
                "  \"recurringDayOfMonth\": 1,\n" +
                "  \"firstInstallmentDate\": \"2020-02-18T12:39:09.797Z\",\n" +
                "  \"lastInstallmentDateDue\": \"2020-02-18T12:39:09.797Z\",\n" +
                " }";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test(priority = 4, dependsOnMethods = "CreateFundraisingDonation")
    public void CreateDonationDebitTransaction(Method method){
        testCase = method.getName();
        extentTest = extent.createTest("Create Donation Debit Transaction and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"donationId\": \" " + donationId + "\",\n" +
                "  \"createTransactionAsComplete\": true\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();


    }

    @Test(priority = 5, dependsOnMethods = "CreateFundraisingDonation")
    // throwing an error "Cannot Create Donation Payment Plan. Donation payment frequency needs to be set as 'Monthly' for 'FixedTotal' payment type"
    public void CreateDonationPaymentPlan(Method method){
        testCase = method.getName();
        extentTest = extent.createTest("Create Donation Payment Plan and Validate the response(f)");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"donationId\": \" " + donationId + "\",\n" +
                "  \"collectionType\": 167410001\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();


    }


}
