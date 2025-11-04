package org.acme.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

@QuarkusTest
class CourseControllerTest {

    @Test
    void shouldCreateCourse() {
        String courseJson = """
                    {
                        "name": "Java POO com projetos",
                        "description": "Curso do professor Nélio Alves ensinando POO com Java",
                        "instructor": "Nélio Alves",
                        "platform": "Udemy",
                        "category": "Desenvolvimento de software",
                        "url": "https://www.udemy.com/course/java-curso-completo/"
                    }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(courseJson)
        .when()
                .post("/courses")
        .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .body("$", hasKey("id"))
                .body("name", equalTo("Java POO com projetos"))
                .body("description", equalTo("Curso do professor Nélio Alves ensinando POO com Java"))
                .body("instructor", equalTo("Nélio Alves"))
                .body("platform", equalTo("Udemy"))
                .body("url", equalTo("https://www.udemy.com/course/java-curso-completo/"));
    }

    @Test
    void shouldCreateAndEditCourse() {
        String courseJson = """
                    {
                        "name": "Java POO com projetos",
                        "description": "Curso do professor Nélio Alves ensinando POO com Java",
                        "instructor": "Nélio Alves",
                        "platform": "Udemy",
                        "category": "Desenvolvimento de software",
                        "url": "https://www.udemy.com/course/java-curso-completo/"
                    }
                """;

            Integer courseId =
                given()
                    .contentType(ContentType.JSON)
                    .body(courseJson)
                .when()
                    .post("/courses")
                .then()
                    .statusCode(Response.Status.CREATED.getStatusCode())
                    .body("$", hasKey("id"))
                    .extract()
                    .path("id");

        String courseJsonToEdit = """
                    {
                        "name": "TESTE",
                        "instructor": "TESTE PROFESSOR"
                    }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(courseJsonToEdit)
            .when()
                .patch("/courses/" + courseId)
            .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("$", hasKey("id"))
                .body("name", equalTo("TESTE"))
                .body("instructor", equalTo("TESTE PROFESSOR"))
                .body("description", equalTo("Curso do professor Nélio Alves ensinando POO com Java"))
                .body("platform", equalTo("Udemy"))
                .body("url", equalTo("https://www.udemy.com/course/java-curso-completo/"));
    }
}