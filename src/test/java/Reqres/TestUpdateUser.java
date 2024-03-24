package Reqres;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestUpdateUser {
    String baseUri = "https://reqres.in/api/";
    @Test
    public void updateUser(){
        String body= "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        given().baseUri(baseUri)
                .header("Content-Type","application/json")
                .body(body)
                .when().put("users/2")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .assertThat().body("name", isA(String.class),
                        "job", isA(String.class)
                        , "updatedAt", isA(String.class))
                .assertThat().body("$", hasKey("name"),
                        "$", hasKey("job"),
                        "$", hasKey("updatedAt"))
                .assertThat().body("name", equalTo("morpheus"),
                        "job", equalTo("zion resident"));


    }
    @Test
    public void updateSomeInfoForUser(){
        String body="{\n" +
                "    \"name\": \"morpheu\"\n" +
                "  \n" +
                "}";
        given().baseUri(baseUri).
                contentType(ContentType.JSON)
                .body(body)
                .when().patch("users/2")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .assertThat().body("name", isA(String.class)
                        , "updatedAt", isA(String.class))
                .assertThat().body("$", hasKey("name"),
                        "$", hasKey("updatedAt"))
                .assertThat().body("name", equalTo("morpheu"));
        ;

    }



}

