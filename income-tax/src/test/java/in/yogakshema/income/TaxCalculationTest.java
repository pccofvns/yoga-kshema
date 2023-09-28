/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.yogakshema.income;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // reset spring context after each test method
public class TaxCalculationTest {

    @LocalServerPort
    private int port;

    @Test
    public void testTaxCalculation() throws JSONException {
        RestAssured.port = port;
        String response = given()
                .body("{" +
                        "  \"IncomeTaxRequest\": {" +
                        "    \"gender\": \"male\"," +
                        "    \"taxableIncome\": 900000," +
                        "    \"age\": 23," +
                        "    \"regime\": \"OLD\"" +
                        "  }" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/TaxCalculation").andReturn().getBody().asString();
        JSONObject resJson = new JSONObject(response);
        if(resJson.has("IncomeTaxResponse")){
            JSONObject incomeTaxResponse = (JSONObject) resJson.get("IncomeTaxResponse");
            assertEquals("Amount doesn't match...",incomeTaxResponse.getDouble("amount"), 96200.0);
            assertEquals("Slab doesn't match...",incomeTaxResponse.getDouble("slab"), 20.0);
        }
    }
}
