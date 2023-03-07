package com.apiTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.specification.Specifications.requestSpecification;

public class PatchRequestTest {
    private final static String requestBody = "{\n  \"title\": \"bax\" \n}";

    @Test
    @DisplayName("Тестирование тестового запроса Patch c обновлением данных PostDTO по полю title")
    public void patchRequest() {
        RestAssured.given()
                .spec(requestSpecification())
                .body(requestBody)
                .patch("/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("title", Matchers.is("bax"))
                .body("userId", Matchers.is(1))
                .body("id", Matchers.is(1));
    }
}
