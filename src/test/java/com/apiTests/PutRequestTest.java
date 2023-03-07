package com.apiTests;

import com.model.PostDTO;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.specification.Specifications.requestSpecification;

public class PutRequestTest {

    @Test
    @DisplayName("Тестирование тестового запроса Put c обновлением данных PostDTO по полю title и body")
    public void putRequestCheckStatusCodeAndJsonBodyForExistingPost() {

    RestAssured.given()
                .spec(requestSpecification())
                .body(new PostDTO(1, "foo", "bar"))
                .put("/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("title", Matchers.is("foo"))
                .body("body", Matchers.is("bar"))
                .body("userId", Matchers.is(1))
                .body("id", Matchers.is(1));
    }
    @Test
    @DisplayName("Тестирование тестового запроса Put c обновлением данных PostDTO по полю title")
    public void putRequestCheckStatusCodeAndJsonBody() {
        int id;
        id = RestAssured.given()
                .spec(requestSpecification())
                .body(new PostDTO(2, "foo", "baz"))
                .post("/posts")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .body("title", Matchers.is("foo"))
                .body("body", Matchers.is("baz"))
                .body("userId", Matchers.is(2))
                .extract()
                .response()
                .body()
                .path("id");

        RestAssured.given()
                .spec(requestSpecification())
                .body(new PostDTO(2, "newFoo", "baz"))
                .put("/posts/" + id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("title", Matchers.is("newFoo"))
                .body("body", Matchers.is("baz"))
                .body("userId", Matchers.is(2));
    }
}
