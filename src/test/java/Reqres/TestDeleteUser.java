package Reqres;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestDeleteUser {

@Test
    public void deleteSingleUser(){
    String baseUri = "https://reqres.in/api/";
    given().baseUri(baseUri)
            .when().delete("users/2")
            .then().log().all().
            statusCode(204)
            .time(lessThan(3000L));


}
}
