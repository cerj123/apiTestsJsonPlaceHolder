package com.apiTests;

import com.model.PostDTO;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.specification.Specifications.requestSpecification;

public class PostRequestTest {

    private final PostDTO postDTO = new PostDTO(1, "foo", "bar");

    @Test
    @DisplayName("Тестирование тестового запроса Post с проверкой status code = 201")
    public void postRequestCheckStatusCode() {
        RestAssured.given()
                .spec(requestSpecification())
                .body(postDTO)
                .post("/posts")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    @DisplayName("Тестирование тестового запроса Post c проверкой key/value по полям title, body, userId")
    public void postRequestCheckResponseJsonBody() {
        RestAssured.given()
                .spec(requestSpecification())
                .body(postDTO)
                .post("/posts")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .body("title", Matchers.is("foo"))
                .body("body", Matchers.is("bar"))
                .body("userId", Matchers.is(1));
    }
}
