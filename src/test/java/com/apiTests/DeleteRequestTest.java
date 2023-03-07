package com.apiTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.specification.Specifications.requestSpecification;

public class DeleteRequestTest {

    @Test
    @DisplayName("Тестирование запроса Delete c удалением пользователя")
    public void deleteRequestCheckStatusCode() {
        RestAssured.given()
                .spec(requestSpecification())
                .delete("/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
