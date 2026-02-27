package api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserApiTest {

    private static final String BASE_URL = "https://reqres.in/api";
    private static final String API_KEY = System.getenv("REQRES_API_KEY");

    @Test
    public void shouldReturnUsersList() {

        RestAssured.baseURI = BASE_URL;

        given()
            .header("x-api-key", API_KEY)
        .when()
            .get("/users?page=2")
        .then()
            .statusCode(200)
            .body("page", equalTo(2));
    }

    @Test
    public void shouldCreateUser() {

        RestAssured.baseURI = BASE_URL;

        String requestBody = """
        {
            "name": "Mayana",
            "job": "QA Engineer"
        }
        """;

        given()
            .header("x-api-key", API_KEY)
            .header("Content-Type", "application/json")
            .body(requestBody)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body("name", equalTo("Mayana"));
    }
}