package Reqres;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestGetUser {
    String BaseUri = "https://reqres.in/api/";

   @Test
   public void getAllUsers(){
        given().baseUri(BaseUri)
                .param("page", "2")
                .when().get("users")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .assertThat().body("page", equalTo(2))
                .assertThat().body("per_page", equalTo(6))
                .assertThat().body("total_pages", equalTo(2))
                .assertThat().body("data[2].id", equalTo(9),
                        "data[2].email", equalTo("tobias.funke@reqres.in"),
                        "data[2].first_name", equalTo("Tobias"),
                        "data[2].last_name", equalTo("Funke"),
                        "data[2].avatar", equalTo("https://reqres.in/img/faces/9-image.jpg"));
    }
    @Test
    public void getSingleUser() {
        given().baseUri(BaseUri)
                .when().get("users/2")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .assertThat().body("data.id",isA(Integer.class),
                        "data.email", isA(String.class),
                        "data.first_name",isA(String.class),
                        "data.last_name",isA(String.class),
                        "data.avatar",isA(String.class))
                .assertThat().body("data",hasKey("id"),
                        "data", hasKey("email"),
                        "data",hasKey("first_name"),
                        "data",hasKey("last_name"),
                        "data",hasKey("avatar"))
                .assertThat().body("data.id",equalTo(2),
                        "data.email", equalTo("janet.weaver@reqres.in"),
                        "data.first_name",equalTo("Janet"),
                        "data.last_name",equalTo("Weaver"),
                        "data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"))
        ;



    }
    @Test
    public void getSingleUserNotFound(){
        given().baseUri(BaseUri)
                .when().get("users/23")
                .then().log().all()
                .statusCode(404)
                .time(lessThan(3000L));
    }
}



