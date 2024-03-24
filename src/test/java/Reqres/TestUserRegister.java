package Reqres;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestUserRegister {
    String baseUri = "https://reqres.in/api/";
    @Test
    public void userRegisterSuccessful(){

        String body ="{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("register")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .assertThat().body("$",hasKey("id"),
                "$",hasKey("token"))
                .assertThat().body("id",notNullValue(),
                        "token",notNullValue());

    }
    @Test
    public void  userRegisterUnSuccessful(){
        String body="{\n" +
                "    \"email\": \"sydney@fife\"\n" +
                "}";
        given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("register")
                .then().log().all()
                .assertThat().statusCode(400)
                .assertThat().time(lessThan(3000L))
                .assertThat().body("$",hasKey("error"))
                .assertThat().body("error",equalTo("Missing password"));
    }
}
