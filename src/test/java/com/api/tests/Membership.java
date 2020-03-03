 package com.api.tests;

import com.api.utils.BaseClass;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Membership extends BaseClass {


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


    @Test
    public void RetrieveAllMembershipsByContactId(Method method) throws IOException {

        testCase = method.getName();
        extentTest = extent.createTest("Retrieve All Memberships By ContactId and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"id\":  \" " + data.ContactsWithActiveMemberships() + "\",\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test
    public void RetrieveActiveMembershipsByContactId(Method method) throws IOException {

        testCase = method.getName();
        extentTest = extent.createTest("Retrieve Active Memberships By ContactId and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"id\":  \" " + data.ContactsWithActiveMemberships() + "\",\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();


    }


    @Test
    public void RetrieveAllMembershipTypes(Method method) {

        testCase = method.getName();
        extentTest = extent.createTest("Retrieve All Membership Types and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"attributesName\": [\n" +
                "    \"string\"\n" +
                "  ]\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();


    }

    @Test
    public void RetrieveMembershipGradesByTypeId(Method method) throws IOException {

        testCase = method.getName();
        extentTest = extent.createTest("Retrieve Membership Grades By TypeId and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"id\":  \" " + data.ContactsWithActiveMemberships() + "\",\n" +
                "  \"attributesName\": [\n" +
                "    \"string\"\n" +
                "  ]\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);

        verifyResponse.validateAssertion();


    }

    @Test
    public void RetrieveMembershipBands(Method method) {
// response code is 200 but response body has no values except "success" and "messageError"
        testCase = method.getName();
        extentTest = extent.createTest("Retrieve Membership Bands and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"gradeId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "  \"attributesName\": [\n" +
                "    \"string\"\n" +
                "  ]\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test
    public void RetrieveValidMembershipDiscountCodeForWeb(Method method) throws IOException {
// response code is 200 but response body has an error ""Object reference not set to an instance of an object.","
        testCase = method.getName();
        extentTest = extent.createTest("Retrieve Valid Membership Discount Code For Web and Validate the response(f)");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"code\": \"string\",\n" +
                "  \"membershipId\": \" "+ data.MembershipsWithTransactions() +"\",\n" +
                "  \"attributesName\": [\n" +
                "    \"string\"\n" +
                "  ]\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.FAIL, "retrieves all membership Grades instead of by membership type ID");
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();


    }


    @Test
    public void ApplyMembershipDiscountCodeForWeb(Method method) throws IOException {
// response code is 200 but response body has an error ""Object reference not set to an instance of an object."
        testCase = method.getName();
        extentTest = extent.createTest("Apply Membership Discount Code For Web and Validate the response(f)");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"code\": \"string\",\n" +
                "  \"membershipId\": \" "+ data.MembershipsWithTransactions() +"\",\n" +
                "  \"tri_discountcodeid\": [\n" +
                "    \"CodeODcASA4YLuw\"\n" +
                "  ]\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();


    }

        @Test(dependsOnMethods = {"CreateContactActivatedAndValidated"})
        public void CreateMembership(Method method) {

        testCase = method.getName();
        extentTest = extent.createTest("Create Membership for Contact and Validate the response");
        request.header("Content-Type", "application/json");

            jsonBody = "{\n" +
                    " \"contactId\": \" " + contactId + "\",\n" +
                    "\"typeId\": \" " + data.membershipType() + "\",\n" +
                    "\"gradeId\": \" " + membershipGrade + "\",\n" +
                    "\"bandId\": \" " + membershipBand + "\",\n" +
                    "    \"methodOfPaymentId\": \" " + data.methodOfPaymentId() + "\",\n" +
                    "  \"paymentFrequency\": 167410000,\n" +
                    "  \"reasonCodeId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                    "  \"membershipReasonForJoiningId\": \" " + data.reasonForJoining() + "\",\n" +
                    "  \"startDate\": \" " + LocalDateTime.now() + "\",\n" +
                    "  \"membershipStatus\": 167410000\n" +
                    "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO, " POST request Body : <br />" + jsonBody);
        response = request.post(BASE_URL + testCase);


        Pattern pattern = Pattern.compile("[a-zA-Z0-9-]{36}");
        Matcher m = pattern.matcher(response.asString());
        while (m.find()){
//            System.out.println(m.group());
            membershipId = m.group();
        }

            verifyResponse.validateAssertion();

    }

    @Test(dependsOnMethods = {"CreateContactActivatedAndValidated"})
    // response code is 200 but response body has an error "Subsetting 'Fundraising_PaymentFrequency' must be an integer"
    public void CreateFundraisingMembership(Method method) throws IOException {
        testCase = method.getName();
        extentTest = extent.createTest("Create Fundraising Membership and Validate the response(f)");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"isGift\": true,\n" +
                "  \"member\": {\n" +
                "    \"contactId\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "    \"emailAddress\": \"string\",\n" +
                "    \"title\": {\n" +
                "      \"value\": 0,\n" +
                "      \"description\": \"string\"\n" +
                "    },\n" +
                "    \"fistName\": \"API test\",\n" +
                "    \"lastName\": \"API Last Name\",\n" +
                "    \"telephone\": \"45546435132\",\n" +
                "    \"addressPostCode\": \"S1W36GH\",\n" +
                "    \"addressLine\": \"London \",\n" +
                "    \"addressCountryId\": \" "+ data.Country() + "\",\n" +
                "  },\n" +
                "  \"billingContact\": {\n" +
                "    \"contactId\": \" "+ contactId + "\",\n" +
                "    \"title\": {\n" +
                "      \"value\": 167410032,\n" +
                "      \"description\": \"Mr\"\n" +
                "    },\n" +
                "    \"fistName\": \"API test\",\n" +
                "    \"lastName\": \"API Last Name\",\n" +
                "    \"telephone\": \"45546435132\",\n" +
                "    \"addressPostCode\": \"S1W36GH\",\n" +
                "    \"addressLine\": \"London \",\n" +
                "    \"addressCountryId\": \" "+ data.Country() + "\",\n" +
                "  },\n" +
                " \n" +
                "  \"giftAidDeclaration\": {\n" +
                "    \"optIn\": true,\n" +
                "    \"date\": \"2020-01-23T16:00:01.289Z\"\n" +
                "  },\n" +
                "  \"paymentDetails\": {\n" +
                "    \"onlinePayment\": true,\n" +
                "    \"amount\": 122\n" +
                "  },\n" +
                "  \"directDebit\": {\n" +
                "    \"accountName\": \"API Testing\",\n" +
                "    \"accountNumber\": \"12341234\",\n" +
                "    \"sortCode\": \"121212\"\n" +
                "  }\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);

        verifyResponse.validateAssertion();
    }
    @Test(dependsOnMethods = {"CreateContactActivatedAndValidated","CreateMembership"})
    public void CreateMembershipTransactionAndProcessPayment(Method method) {
        testCase = method.getName();
        extentTest = extent.createTest("Create Membership Transaction And Process Payment and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"isDirectDebit\": true,\n" +
                "  \"directDebitCallbackURL\": \"string\",\n" +
                "  \"isOnlinePayment\": true,\n" +
                "  \"contactId\": \" "+ contactId + "\",\n" +
                "  \"membershipID\": \""+ membershipId + "\",\n" +
                "  \"frequencyOfPayment\": {\n" +
                "    \"value\": 167410000,\n" +
                "    \"description\": \"Annually\"\n" +
                "  },\n" +
                "    \"methodOfPaymentID\": \""+ data.methodOfPaymentId() + "\",\n" +
                " \"onlinePaymentSuccessURL\":\"https://www.onlinePaymentSuccessURL.co.uk\",\n" +
                "  \"onlinePaymentFailureURL\": \"https://www.onlinePaymentFailureURL.co.uk\"\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);

        verifyResponse.validateAssertion();
    }

    @Test(dependsOnMethods = {"CreateContactActivatedAndValidated","CreateMembership"})
    public void CreateMembershipTransactionAndDirectDebit(Method method) {
        testCase = method.getName();
        extentTest = extent.createTest("Create Membership Transaction And DirectDebit and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"directDebitInfo\": {\n" +
                "    \"type\": 167410000,\n" +
                "    \"accountNumber\": \"12341234\",\n" +
                "    \"accountName\": \"API Testing\",\n" +
                "    \"sortCode\": \"121212\",\n" +
                "    \"bankName\": \"API Bank Ltd\",\n" +
                "    \"branchAddress\": \"string\",\n" +
                "    \"paymentFrequency\": 167410000\n" +
                "  },\n" +
                "  \"contactId\": \" "+ contactId + "\",\n" +
                "  \"membershipID\": \""+ membershipId + "\",\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);

        verifyResponse.validateAssertion();
    }

    @Test(dependsOnMethods = {"CreateContactActivatedAndValidated","CreateMembership"})
    public void CreateGenericMembershipTransaction(Method method) throws IOException {
        testCase = method.getName();
        extentTest = extent.createTest("Create Generic Membership Transaction and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"netAmount\": 1025,\n" +
                "  \"vatId\": \" "+ data.vat() +"\",\n" +
                "  \"periodId\": \"ed70628e-8455-e611-80f8-005056bf72c1\",\n" +
                "  \"createAsComplete\": true,\n" +
                "  \"createAsInvoice\": true,\n" +
                "  \"description\": \"CreateGenericMembershipTransaction API Automation\",\n" +
                " \n" +
                "  \"contactId\": \" "+ contactId + "\",\n" +
                "  \"membershipId\": \" "+ data.MembershipsWithTransactions() +"\",\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);

        verifyResponse.validateAssertion();
    }

    @Test()
    public void RetrieveAllMethodsOfPayment(Method method) {
        testCase = method.getName();
        extentTest = extent.createTest("Retrieve All Methods Of Payment and Validate the response(GET Request)");
        request.header("Content-Type", "application/json");

        response = request.get(BASE_URL + testCase);
        extentTest.log(Status.INFO,"Get request Endpoint URL : "+ BASE_URL + testCase);

        try {
            Assert.assertEquals(response.getStatusCode(), 200);
            extentTest.log(Status.PASS," Check GET response Code : <br />"+ response.getStatusCode());
        } catch (AssertionError StatusCodeError) {
            extentTest.log(Status.FAIL,"Expected Response Code \"200\" but returned : <br />"+ response.getStatusCode());
            throw StatusCodeError;
        }
        try {
            Assert.assertTrue(response.asString().contains("\"success\":" + "true"));
            extentTest.log(Status.PASS," Check GET response Body  has Success is \"true\" and messageError is \"null\" : <br />"+ response.asString());
        } catch (AssertionError SuccessError) {
            extentTest.log(Status.FAIL,"Expected Response Success is \"true\" and messageError is \"null\" but returned : <br />"+ response.asString());
            throw SuccessError;
        }
    }

    @Test(dependsOnMethods = {"CreateContactActivatedAndValidated","CreateMembership"})
    public void LapseMembership(Method method) {
        testCase = method.getName();
        extentTest = extent.createTest("Lapse Membership and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"membershipID\": \""+ membershipId + "\",\n" +
                "  \"expiryType\": 167410004,\n" +
                "  \"expirePaymentPlans\": true\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);

        verifyResponse.validateAssertion();
    }

    @Test(dependsOnMethods = {"CreateContactActivatedAndValidated","CreateMembership"})
    public void UpgradeMembership(Method method) {
        testCase = method.getName();
        extentTest = extent.createTest("Upgrade Membership and Validate the response(spelling wrong 'mothodOfPayment')");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"membershipID\": \""+ membershipId + "\",\n" +
                "\"membershipType\": \" " + data.membershipType() + "\",\n" +
                "\"membershipGrade\": \" " + membershipGrade + "\",\n" +
                "\"membershipBand\": \" " + membershipBand + "\",\n" +
                "  \"createTransaction\": true,\n" +
                "  \"writeoff\": true,\n" +
                "  \"createPayment\": true,\n" +
                "  \"createPaymentPlan\": true,\n" +
                "    \"mothodOfPayment\": \""+ data.methodOfPaymentId() + "\",\n" +
                "  \"feeOverride\": 0\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);

        verifyResponse.validateAssertion();
    }
    @Test()
    public void RetrieveMembershipTransactions(Method method) throws IOException {
        testCase = method.getName();
        extentTest = extent.createTest("Retrieve Membership Transactions and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"contactId\": \" "+ data.ContactsWithActiveMemberships() + "\",\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);

        verifyResponse.validateAssertion();
    }

    @Test(dependsOnMethods = {"CreateContactActivatedAndValidated","CreateMembership"})
    // Response status is 200 but throwing an error "VAT is mandatory and cannot be empty"
    public void CreateMultipleMembershipInvoiceTransaction(Method method) {
        testCase = method.getName();
        extentTest = extent.createTest("Create Multiple Membership Invoice Transaction and Validate the response(f)");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"contactId\": \" "+ contactId + "\",\n" +
                "  \"poNumber\": \"poNumber\",\n" +
                "  \"transactionIds\": \"1a1a5b0d-194c-ea11-a812-000d3a7ed588\",\n" +
                "\"transactionIds\": \"5523ba2f-450c-438c-b1d0-459a91780a15\",\n" +
                "  \"membershipID\": \""+ membershipId + "\",\n" +
                "\"membershipIds\": \"e896fa0a-fb40-ea11-a812-000d3a7ed588\"\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);

        verifyResponse.validateAssertion();
    }

    @Test(dependsOnMethods = {"CreateContactActivatedAndValidated","CreateMembership"})
    public void CreateMembershipTransactionsAsInvoice(Method method){
        // status code 200 and result is success but not transaction are created in CRM
        testCase = method.getName();
        extentTest = extent.createTest("Create Membership Transactions As Invoice and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"contactId\": \" "+ contactId + "\",\n" +
                "   \"membershipGuids\": [\""+ membershipId + "\"\n" +
                "  ],\n" +
                "  \"createTransactionAsComplete\": true\n" +
                "}\n" +
                "ctionAsComplete\": true }";

        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);

        verifyResponse.validateAssertion();

    }

}
