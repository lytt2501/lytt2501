package org.example;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.cookie.SM.COOKIE;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }


	public static Map<String, Object> setHeader() {
    Map<String, Object> headerMap = new HashMap<>();
    headerMap.put("Content-Type", "application/json");
    headerMap.put("cookie", COOKIE);
    headerMap.put("api_token", "e85b3d33-168a-4a65-a66f-1e0e2a1832ee");
    return headerMap;
    }

	public static JSONObject requestBodyJson()  {
        JSONObject jsonObject = new JSONObject();

        JSONObject header = new JSONObject()
                .put("channel", "App")
                .put("lang", "vi")
                .put("transactionCode", genTranCodeLink())
                .put("userWallet", "0963492186")
                .put("transTime",generateTransactionTime());


        jsonObject.put("header", header);

        JSONObject dataJson = new JSONObject()
                .put("bankAccount", "9704050628449931")
                .put("cardNumber", "")
                .put("cardIssueDate", "")
                .put("cardHolder", "TRAN THI LY")
                .put("iCNumber", "040193028408")
                .put("phoneNumber", "0963492186")
                .put("walletType", 1);


        jsonObject.put("data", dataJson);
        return jsonObject;
    }

    private static String genTranCodeLink() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String time = LocalDateTime.now().format(formatter);
        String transCode = "LK" + time;
        System.out.printf("transCode: " + transCode);
        return transCode;

    }
    public static String generateTransactionTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
//    public static void linkBank(Map<String, Object> header, JSONObject body){
//        RestAssured.baseURI="https://adapter-agribank-test.epayservices.com.vn";
//        RequestSpecification httpReq = given().log().all();
//        httpReq.contentType(JSON)
//                .accept("*/*")
//                .headers(header)
//                .body(body.toString());
//
//        Response response = httpReq.when().post("/api/v1/link_bank");
//        response.then().assertThat().statusCode(200)
//        response.prettyPrint();
//    }
}




