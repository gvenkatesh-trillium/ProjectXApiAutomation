package com.api.utils;

import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetDataFeed extends BaseClass {

    private String[] membershipTypes = {"669b36c1-ddfc-e611-8100-005056bf72c1", "f6665f9d-441c-e511-80c7-005056bf2f1c", "7fd94cb0-ff3e-e811-a956-002248072cc3", "f8665f9d-441c-e511-80c7-005056bf2f1c"};
    private String[] membershipReasonForJoiningIds = {"1a0c49f4-9733-ea11-a813-000d3a7ed588", "d1756576-87f6-e511-80f2-005056bf72c1", "0517f962-87f6-e511-80f2-005056bf72c1", "5532e343-9833-ea11-a813-000d3a7ed588"};
    private String[] fundIds = {"573a4b21-6c7d-e911-a81d-000d3a0b6c55","d5806a3c-d386-e911-a81f-000d3a0b6439","9b51d63e-b891-e911-a822-000d3a0b6439"};
    private String[] methodOfPaymentIds = {"77c3741e-2b1c-e511-80c7-005056bf2f1c", "85c3741e-2b1c-e511-80c7-005056bf2f1c", "e47595b7-a9e7-e611-8100-005056bf72c1", "7bc3741e-2b1c-e511-80c7-005056bf2f1c"};

    @Test
    public void getMembershipTypes() {
        request.header("Content-Type", "application/json");

        jsonBody = "{\n" +
                "  \"attributesName\": [\n" +
                "    \"string\"\n" +
                "  ]\n" +
                "}";
        request.body(jsonBody);
        response = request.post(BASE_URL + "RetrieveAllMembershipTypes");
//        try {
//            membershipTypeId = response.asString().split("\"id\": \"")[9];
//        } catch (Exception e) {
//            System.out.println("Failed to get Membership IDs : <br />" + response.asString());
//            e.printStackTrace();
//        }
        Matcher m = Pattern.compile("([a-zA-Z0-9-]{36})").matcher(response.asString());
        Matcher bid = Pattern.compile("tri_booking\",\"id\":\"([a-zA-Z0-9-]{36})\",").matcher(response.asString());
        while (m.find()) {
            System.out.println(m.group());
            membershipTypeId = m.group();
        }
        System.out.println("memType id : " + membershipTypeId);


    }

}
