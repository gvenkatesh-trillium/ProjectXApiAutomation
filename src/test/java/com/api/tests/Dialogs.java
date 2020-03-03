package com.api.tests;

import com.api.utils.BaseClass;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Random;

public class Dialogs extends BaseClass {

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

    @Test(priority = 1, dependsOnMethods = {"CreateContactActivatedAndValidated"})
    public void CreateMembershipFromDialog(Method method){
        testCase = method.getName();
        extentTest = extent.createTest("Create Membership From Dialog and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"contactId\": \" " + contactId + "\",\n" +
                "\"typeId\": \" " + data.membershipType() + "\",\n" +
                "\"gradeId\": \" " + membershipGrade + "\",\n" +
                "\"bandId\": \" " + membershipBand + "\",\n" +
                "    \"methodOfPaymentId\": \" " + data.methodOfPaymentId() +"\",\n" +
                "  \"paymentFrequency\": 167410000,\n" +
                "  \"startDate\": \" "+ LocalDateTime.now() +"\",\n" +
                "  \"createTransaction\": true,\n" +
                "  \"createTransactionAsComplete\": true,\n" +
                "  \"processPayment\": true,\n" +
                "  \"createTransactionAsInvoice\": true,\n" +
                "  \"paymentReference\": \"paymentRef\",\n" +
                "  \"paymentReferenceDescription\": \"PaymentRefDescription\",\n" +
                "  \"processOnlinePayment\": true\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test(priority = 2)
    public void CreateDirectDebitMandate(Method method){
        testCase = method.getName();
        extentTest = extent.createTest("Create Direct Debit Mandate and Validate the response");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"contactId\": \" " + contactId + "\",\n" +
                "  \"type\": 167410000,\n" +
                "  \"accountNumber\": \"12345678\",\n" +
                "  \"accountName\": \"APITesting\",\n" +
                "  \"sortCode\": \"123123\",\n" +
                "  \"bankName\": \"API Bank Ltd\",\n" +
                "  \"branchAddress\": \"London\",\n" +
                "  \"isUpFront\": true,\n" +
                "  \"createdFromWeb\": true\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test(priority = 3)
    public void CreateTransactionAllInOne(Method method) throws IOException {
        testCase = method.getName();
        extentTest = extent.createTest("Create Transaction All In One and Validate the response()f");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"contactId\": \" " + contactId + "\",\n" +
                "  \"transactionType\": \"Membership Invoice\",\n" +
                "  \"period\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "  \"vat\":  \" " + data.vat() + "\",\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"itemName\": \"ItemName\",\n" +
                "      \"itemNetAmount\": 0,\n" +
                "      \"itemAllocationCode\": \"string\",\n" +
                "      \"itemDescription\": \"string\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test(priority = 4)
    //response is 400 even for correct membershipBatchId "aacf3687-ef7d-e811-a95d-002248072abd"
    public void ExecuteMembershipBatch(Method method){
        testCase = method.getName();
        extentTest = extent.createTest("Execute Membership Batch and Validate the response(f)");
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"membershipBatchId\": \"f0f7d035-c457-ea11-a811-000d3a7ed518\"\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test(priority = 5, dependsOnMethods = {"CreateContactActivatedAndValidated"})
    public void ProcessDonation(Method method){
        testCase = method.getName();
        extentTest = extent.createTest("Process Donation and Validate the response");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"createDonationRequest\": {\n" +
                "    \"donationDate\":  \" "+ LocalDateTime.now() +"\",\n" +
                "    \"fundId\": \" " + data.fundId() + "\",\n" +
                "    \"contactId\": \" " + contactId + "\",\n" +
                "    \"amount\":  \" "+ rNum +"\",\n" +
                "    \"periodicAmount\": 0,\n" +
                "    \"methodOfPaymentId\": \"77c3741e-2b1c-e511-80c7-005056bf2f1c\",\n" +
                "    \"paymentType\": 167410004,\n" +
                "    \"paymentFrequency\": 167410000,\n" +
                "    \"recurringDayOfMonth\":1,\n" +
                "    \"firstInstallmentDate\": \" "+ LocalDateTime.now() +"\",\n" +
                "    \"lastInstallmentDateDue\":  \" "+ LocalDateTime.now() +"\",\n" +
                "\n" +
                "  },\n" +
                "  \"createDonationDebitTransaction\": true,\n" +
                "  \"createDonationPaymentTransaction\": true,\n" +
                "  \"createTransactionAsComplete\": true,\n" +
                "\n" +
                "  \"donationPaymentPlanCollectionType\": 0\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

            }

    @Test(priority = 5, dependsOnMethods = {"CreateContactActivatedAndValidated"})
    public void PayMultipleTransactions(Method method){
        // response code is 200 but throwing an error "Transaction Amounts cannot be empty.\r\n   at Business.Services.DialogsService.PayMultipleTransactions(PayMultipleTransactionsDto request, Nullable`1 callerId) in C:\\VS\\ProjectX\\Dev\\Main\\TrilliumWebAPI\\Business\\Services\\DialogsService.cs:line 937",
        testCase = method.getName();
        extentTest = extent.createTest("Pay Multiple Transactions and Validate the response(f)");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"txAmounts\": { \"amount\": 123456},\n" +
                "  \"methodOfPaymentId\":  \" "+ data.methodOfPaymentId() +"\",\n" +
                "  \"createAsComplete\": true,\n" +
                "  \"paymentReference\": \"pymtRef\",\n" +
                "  \"paymentReferenceDescription\": \"pymtRefDes\",\n" +
                "\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

    @Test(priority = 6)
    public void GetTransactionForMembership(Method method) throws IOException {
        testCase = method.getName();
        extentTest = extent.createTest("Get Transaction For Membership and Validate the response");
        request.header("Content-Type", "application/json");
        jsonBody = "{\n" +
                "  \"membershipId\": \" "+ data.MembershipsWithTransactions() +"\",\n" +
                "}";
        request.body(jsonBody);
        extentTest.log(Status.INFO," POST request Body : <br />"+ jsonBody);
        response = request.post(BASE_URL + testCase);
        verifyResponse.validateAssertion();

    }

}
