package org.example;

import org.json.JSONObject;

import java.util.Map;

import static org.example.AppTest.requestBodyJson;
import static org.example.AppTest.setHeader;
import static org.example.LinkBankTest.linkBanksucces;

public class MainTest {
    public static void main(String[] args) {
        Map<String, Object> header = AppTest.setHeader();
        JSONObject body = AppTest.requestBodyJson();
//        AppTest.linkBank(header, body);
        LinkBankTest.linkBanksucces(header, body);
       // LinkBankTest.linkBankFailed(header, body);
    }
}
