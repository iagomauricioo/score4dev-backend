package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testHelloGreetingEndpoint() {
        String uuid = UUID.randomUUID().toString();

        given()
                .pathParam("name", uuid)
          .when().get("/users/greeting/{name}")
          .then()
             .statusCode(200)
             .body(is("Hello " + uuid + " é um prazer te conhecer"));
    }

}