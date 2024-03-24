package Reqres;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class TestCreateUser
{
    @Test
    public void createUser() {
        String BaseUri = "https://reqres.in/api/";
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}\n";

        given().baseUri(BaseUri)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("users")
                .then().log().all()
                .assertThat().statusCode(201)
                .assertThat().time(lessThan(3000L))
                .assertThat().body("name", isA(String.class),
                        "job", isA(String.class),
                        "id", isA(String.class),
                        "createdAt", isA(String.class))
                .assertThat().body("$", hasKey("name"),
                        "$", hasKey("job"),
                        "$", hasKey("id"),
                        "$", hasKey("createdAt"))
                .assertThat().body("name", equalTo("morpheus"),
                        "job", equalTo("leader"));
    }

}
