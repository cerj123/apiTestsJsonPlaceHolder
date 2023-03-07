package com.apiTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.specification.Specifications.requestSpecification;
import static com.specification.Specifications.responseSpecificationScOk;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetRequestTest {
    @Test
    @DisplayName("Тестирование запроса Get c проверкой status code = 200")
    public void getRequestCheckStatusCode() {
        RestAssured.given()
                .spec(requestSpecification())
                .get("/posts")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Тестирование запроса Get c проверкой status code = 404")
    public void getRequestCheckStatusCodeNotFound() {
        RestAssured.given()
                .spec(requestSpecification())
                .get("/posts/wrong")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Тестирование запроса Get c проверкой key/value по полю title")
    public void getRequestCheckResponseJsonBody() {
        RestAssured.given()
                .spec(requestSpecification())
                .get("/posts")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("title[1]", Matchers.is("qui est esse"));
    }

    @Test
    @DisplayName("Тестирование запроса Get с параметрами c проверкой key/value по полю email")
    public void getRequestWithQueryParamCheckResponseJsonBody() {
        RestAssured.given()
                .spec(requestSpecification())
                .param("postId", "2")
                .get("/comments")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("email[3]", Matchers.is("Meghan_Littel@rene.us"));
    }

    @Test
    @DisplayName("Тестирование запроса Get c валидацией ответа по json схеме")
    public void getRequestCheckResponseWithJsonSchema() {
        RestAssured.given()
                .spec(requestSpecification())
                .get("/posts/1")
                .then()
                .spec(responseSpecificationScOk())
                .assertThat()
                .body(matchesJsonSchemaInClasspath("SchemaPostsById.json"));
    }
}
