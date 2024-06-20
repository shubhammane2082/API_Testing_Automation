package JSON_Server_Curd;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProfileAutomation
{
    @Test
    public void getprofiles()
    {
        Response response = given()
                .header("Accept", "application/json")
                .get("http://localhost:3000/profile");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void updateProfile()
    {
        Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"name\":\"sm\"\n" +
                        "}")
                .when()
                .put("http://localhost:3000/profile");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
