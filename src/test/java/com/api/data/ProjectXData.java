package com.api.data;


import com.api.utils.BaseClass;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;

import java.io.*;
import java.util.Random;

public class ProjectXData extends BaseClass {
    InputStream inputStream = new FileInputStream(new File("src/test/java/resources/ProjectXdata.xlsx"));
    Workbook wb = WorkbookFactory.create(inputStream);
    private String[] membershipTypes = {"669b36c1-ddfc-e611-8100-005056bf72c1", "f6665f9d-441c-e511-80c7-005056bf2f1c", "7fd94cb0-ff3e-e811-a956-002248072cc3", "f8665f9d-441c-e511-80c7-005056bf2f1c"};
    private String[] membershipReasonForJoiningIds = {"1a0c49f4-9733-ea11-a813-000d3a7ed588", "d1756576-87f6-e511-80f2-005056bf72c1", "0517f962-87f6-e511-80f2-005056bf72c1", "5532e343-9833-ea11-a813-000d3a7ed588"};
    private String[] fundIds = {"573a4b21-6c7d-e911-a81d-000d3a0b6c55","d5806a3c-d386-e911-a81f-000d3a0b6439","9b51d63e-b891-e911-a822-000d3a0b6439"};
    private String[] methodOfPaymentIds = {"77c3741e-2b1c-e511-80c7-005056bf2f1c", "85c3741e-2b1c-e511-80c7-005056bf2f1c", "e47595b7-a9e7-e611-8100-005056bf72c1", "7bc3741e-2b1c-e511-80c7-005056bf2f1c"};

    public ProjectXData() throws IOException {
    }

    public String membershipType(){
        Random random = new Random();
        int memTypes = random.nextInt(membershipTypes.length);
        membershipTypeId = membershipTypes[memTypes];
           if(membershipTypeId.contains("669b36c1-ddfc-e611-8100-005056bf72c1")){
               membershipGrade = "0d462ccb-ddfc-e611-8100-005056bf72c1";
               membershipBand = "cdbb5ced-ddfc-e611-8100-005056bf72c1";

        }
        else if (membershipTypeId.contains("f6665f9d-441c-e511-80c7-005056bf2f1c")){
            membershipGrade = "1c306f87-02b0-e711-80e3-000d3ab05959";
            membershipBand = "41aaad99-02b0-e711-80e3-000d3ab05959";

        }
        else if (membershipTypeId.contains("f8665f9d-441c-e511-80c7-005056bf2f1c")){
            membershipGrade = "7a03a913-e1d3-e511-80ee-005056bf72c1";
            membershipBand = "cede854c-e1d3-e511-80ee-005056bf72c1";

        }
        else if (membershipTypeId.contains("7fd94cb0-ff3e-e811-a956-002248072cc3")){
            membershipGrade = "0d3d9d05-013f-e811-a956-002248072cc3";
            membershipBand = "02cf171f-023f-e811-a956-002248072cc3";

        }


        return membershipTypeId;
    }

    public String reasonForJoining(){
        Random random = new Random();
        int resonIds = random.nextInt(membershipReasonForJoiningIds.length);
        membershipReasonForJoiningId = membershipReasonForJoiningIds[resonIds];
        return membershipReasonForJoiningId;
    }

    public String fundId(){
        Random random = new Random();
        int fundIdss = random.nextInt(fundIds.length);
        fundId = fundIds[fundIdss];
        return fundId;
    }

    public String methodOfPaymentId(){
        Random random = new Random();
        int methodOfPaymentIdss = random.nextInt(methodOfPaymentIds.length);
        methodOfPaymentId = methodOfPaymentIds[methodOfPaymentIdss];
        return methodOfPaymentId;
    }

    public String MembershipsWithTransactions() throws IOException {
        Sheet sheet = wb.getSheet("MembershipsWithTransactions");
        int firstClm = (sheet.getFirstRowNum() + 2) + (int) (Math.random() * sheet.getLastRowNum());
        CellAddress cellAddress = new CellAddress("A"+firstClm);
        Row row = sheet.getRow(cellAddress.getRow());
        Cell cell = row.getCell(cellAddress.getColumn());
        membershipWithTransactionId = cell.getStringCellValue();
        return membershipWithTransactionId;

    }

    public String UnPaidTransactions() throws IOException {
        Sheet sheet = wb.getSheet("UnPaidTransactions");
        int firstClm = (sheet.getFirstRowNum() + 2) + (int) (Math.random() * sheet.getLastRowNum());
        CellAddress cellAddress = new CellAddress("A"+firstClm);
        Row row = sheet.getRow(cellAddress.getRow());
        Cell cell = row.getCell(cellAddress.getColumn());
        unPaidTransactions = cell.getStringCellValue();
        return unPaidTransactions;

    }

    public String vat() throws IOException {
        Sheet sheet = wb.getSheet("Vat");
        int firstClm = (sheet.getFirstRowNum() + 2) + (int) (Math.random() * sheet.getLastRowNum());
        CellAddress cellAddress = new CellAddress("A"+firstClm);
        Row row = sheet.getRow(cellAddress.getRow());
        Cell cell = row.getCell(cellAddress.getColumn());
        vatId = cell.getStringCellValue();
        return vatId;

    }
    public String Country() throws IOException {
        Sheet sheet = wb.getSheet("Country");
        int firstClm = (sheet.getFirstRowNum() + 2) + (int) (Math.random() * sheet.getLastRowNum());
        CellAddress cellAddress = new CellAddress("A"+firstClm);
        Row row = sheet.getRow(cellAddress.getRow());
        Cell cell = row.getCell(cellAddress.getColumn());
        countryId = cell.getStringCellValue();
        return countryId;

    }

    public String ContactsWithActiveMemberships() throws IOException {
        Sheet sheet = wb.getSheet("ContactsWithActiveMemberships");
        int firstClm = (sheet.getFirstRowNum() + 2) + (int) (Math.random() * sheet.getLastRowNum());
        CellAddress cellAddress = new CellAddress("A"+firstClm);
        Row row = sheet.getRow(cellAddress.getRow());
        Cell cell = row.getCell(cellAddress.getColumn());
        contactsWithActiveMemberships = cell.getStringCellValue();
        return contactsWithActiveMemberships;

    }

    public String GiftAidBatch() throws IOException {
        Sheet sheet = wb.getSheet("GiftAidBatch");
        int firstClm = (sheet.getFirstRowNum() + 2) + (int) (Math.random() * sheet.getLastRowNum());
        CellAddress cellAddress = new CellAddress("A"+firstClm);
        Row row = sheet.getRow(cellAddress.getRow());
        Cell cell = row.getCell(cellAddress.getColumn());
        giftAidBatch = cell.getStringCellValue();
        return giftAidBatch;

    }

    public String ContactsWithCPD() throws IOException {
        Sheet sheet = wb.getSheet("ContactsWithCPD");
        int firstClm = (sheet.getFirstRowNum() + 2) + (int) (Math.random() * sheet.getLastRowNum());
        CellAddress cellAddress = new CellAddress("A"+firstClm);
        Row row = sheet.getRow(cellAddress.getRow());
        Cell cell = row.getCell(cellAddress.getColumn());
        contactsWithCPD = cell.getStringCellValue();
        return contactsWithCPD;

    }

    public String CPDdiary() throws IOException {
        Sheet sheet = wb.getSheet("CPDdiary");
        int firstClm = (sheet.getFirstRowNum() + 2) + (int) (Math.random() * sheet.getLastRowNum());
        CellAddress cellAddress = new CellAddress("A"+firstClm);
        Row row = sheet.getRow(cellAddress.getRow());
        Cell cell = row.getCell(cellAddress.getColumn());
        cPDdiary = cell.getStringCellValue();
        return cPDdiary;

    }


}
