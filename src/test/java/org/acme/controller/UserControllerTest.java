package org.acme.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.acme.entity.UserEntity;
import org.acme.entity.vo.Username;
import org.acme.service.UserService;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;


@QuarkusTest
class UserControllerTest {

    @Inject
    UserService userService;

    @Test
    void createUser() {
        String userJson = """
                {
                    "username": "John Doe",
                    "email": "john.doe@example.com",
                    "password": "123"
                }
            """;

            given()
                .contentType(ContentType.JSON)
                .body(userJson)
            .when()
                .post("/users")
            .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .contentType(ContentType.JSON)
                .body("$", hasKey("userId"))
                .body("username", equalTo("John Doe"))
                .body("email", equalTo("john.doe@example.com"))
                .body("password", equalTo("123"));
    }

    @Test
    void createAndDeleteUser() {
        String userJson = """
                    {
                        "username": "John Doe",
                        "email": "john.doe@example.com",
                        "password": "123"
                    }
                """;
        String userId =
                given()
                        .contentType(ContentType.JSON)
                        .body(userJson)
                        .when()
                        .post("/users")
                        .then()
                        .statusCode(201)
                        .body("$", hasKey("userId"))
                        .extract()
                        .path("userId");

        given()
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        given()
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(404);
    }
}