package com.api.utils;

import com.api.data.ProjectXData;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseClass {

    public static String BASE_URL = "https://trilliumx.azurewebsites.net/TrilliumX_Dev/v1.0/Services/TrilliumWebAPI/";
    //    public static String BASE_URL = "https://rcr-uat-services.azurewebsites.net/TrilliumWebAPI/";
    public static RequestSpecification request = RestAssured.given().auth().basic("77628226-A277-47B5-9409-69D0F26A2DC2", "qwerty123");


    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest extentTest;

    public static Response response;

    public static String contactId;
    public static String membershipId;
    public static String organisationId;
    public static String addressId;
    public static String fundId;
    public static String donationId;
    public static String methodOfPaymentId;
    public static String bookingId;

    public static String eMail;
    public static String testCase;
    public static String jsonBody;
    public static String rNum;

    public static ProjectXData data;

    static {
        try {
            data = new ProjectXData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static VerifyResponse verifyResponse = new VerifyResponse();
    public static FTPUploadFile ftpUploadFile = new FTPUploadFile();

    public static String membershipTypeId;
    public static String membershipGrade;
    public static String membershipBand;
    public static String membershipReasonForJoiningId;
    public static String membershipWithTransactionId;
    public static String unPaidTransactions;
    public static String vatId;
    public static String countryId;
    public static String contactsWithActiveMemberships;
    public static String giftAidBatch;
    public static String contactsWithCPD;
    public static String cPDdiary;
    public static String cpdActivity;



    DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
    Date date = new Date();
    public String dateTime = dateformat.format(date);



    @BeforeTest
    public void setExtent() throws UnknownHostException {

        htmlReporter = new ExtentHtmlReporter("target/ProjectXApiTestingReport"+dateTime+".html");
        htmlReporter.config().setDocumentTitle("Project X");
        htmlReporter.config().setReportName("Project X API Testing report");
//        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host name", InetAddress.getLocalHost().getHostName());
        extent.setSystemInfo("Environment", "TrilliumX_Dev");
        extent.setSystemInfo("User", "Gopinath");



    }

    @AfterTest
    public void endReport() throws UnknownHostException {
        extent.flush();
//        ftpUploadFile.uploadReportToFtpServer();
    }


    @BeforeMethod
    public void beforeMethod() {


    }

    @AfterMethod
    public void afterMethod(ITestResult result){



        if (result.getStatus() == ITestResult.FAILURE) {
//            extentTest.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
        }
        else if (result.getStatus() == ITestResult.SUCCESS) {
//            extentTest.log(Status.PASS, "Test Case PASSED IS " + result.getName());
        }

    }
}
