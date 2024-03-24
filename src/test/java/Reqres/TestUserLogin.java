package Reqres;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestUserLogin {
    String baseUri = "https://reqres.in/api/";

    @Test
    public void userLoginSuccessful(){
        String body = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
        given().baseUri(baseUri).
               header("Content-Type","application/json")
                .body(body)
                .when().post("login")
                .then().log().all()
                 .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .assertThat().body("$",hasKey("token")).
                assertThat().body("token",notNullValue());

    }
    @Test
    public void userLoginUnsuccessful(){
        String body = "{\n" +
                "    \"email\": \"peter@klaven\"\n" +
                "}";
        given().baseUri(baseUri).
                header("Content-Type","application/json")
                .body(body)
                .when().post("login")
                .then().log().all()
                .assertThat().statusCode(400)
                .assertThat().time(lessThan(3000L))
                .assertThat().body("$",hasKey("error"))
                .assertThat().body("error",equalTo("Missing password"));

    }
}
