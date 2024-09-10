package org.example;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.IsEqual.equalTo;

public class LinkBankTest {
    private static final Logger log = LoggerFactory.getLogger(LinkBankTest.class);

    public static void linkBanksucces(Map<String, Object> header, JSONObject body){
        RestAssured.baseURI="https://adapter-agribank-test.epayservices.com.vn";
        RequestSpecification httpReq = given().log().all();
        httpReq.contentType(JSON)
                .accept("*/*")
                .headers(header)
                .body(body.toString());

        Response response = httpReq.when().post("/api/v1/link_bank");
        int statusCode = response.getStatusCode();
        System.out.printf("status: %d%n", statusCode);

        if (statusCode == 200) {
            int statusCodeInBody = response.jsonPath().getInt("status.code");
            if (statusCodeInBody != 0) {
                System.out.printf("Link khong thanh cong khi tra ve code : %d%n:", statusCodeInBody);
                response.prettyPrint();
            } else {
                response.then().assertThat()
                        .statusCode(200)
                        .body("status.code", equalTo(0))
                        .body("status.desc", equalTo("Thành công"));
                System.out.print("Link Thanh cong: ");
                response.prettyPrint();
            }
        } else {
            System.out.printf("Link khong thanh cong khi tra ve statuscode: %d%n", statusCode);
        }
    }

//    public static void linkBankFailed(Map<String, Object> header, JSONObject body){
//        RestAssured.baseURI="https://adapter-agribank-test.epayservices.com.vn";
//        RequestSpecification httpReq = given().log().all();
//        httpReq.contentType(JSON)
//                .accept("*/*")
//                .headers(header)
//                .body(body.toString());
//
//        Response response = httpReq.when().post("/api/v1/link_bank");
//        //System.out.print(response.then().assertThat().statusCode(200));
//int statusCode = response.getStatusCode();
//        System.out.printf("status: {}", statusCode);
//        response.then().assertThat().statusCode(200)
//                .body("status.code", equalTo(98)).body("status.desc", equalTo("Dữ liệu không hợp lệ"));
//
//        response.prettyPrint();
//    }
}
