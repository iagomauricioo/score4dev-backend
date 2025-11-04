package org.acme.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;


@QuarkusTest
class UserControllerTest {

    @Test
    void shouldCreateUser() {
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
    void shouldCreateAndDeleteUser() {
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

    @Test
    void shouldCreateAndEditUser() {
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

        String userJson2 = """
                    {
                        "username": "John Doe Doe",
                        "email": "john.doe2@example.com",
                        "password": "12344"
                    }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(userJson2)
        .when()
                .patch("/users/" + userId)
        .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("$", hasKey("userId"))
                .body("username", equalTo("John Doe Doe"))
                .body("email", equalTo("john.doe2@example.com"))
                .body("password", equalTo("12344"));
    }

    @Test
    void shouldCreateAndGetUser() {
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
                .get("/users/" + userId)
            .then()
                .statusCode(200)
                .body("$", hasKey("userId"))
                .body("username", equalTo("John Doe"))
                .body("email", equalTo("john.doe@example.com"))
                .body("password", equalTo("123"));
    }

    //TODO Tem que mudar esse status de retorno ainda quanto ajeitar o retorno da API para um UnprocessableEntityException
    @Test
    void shouldNotCreateUserWithInvalidUsername () {
        String userJson = """
                    {
                        "username": "john@@@",
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
                .statusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    }
}