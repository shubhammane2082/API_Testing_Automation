package Automation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class User_Automation
{
    @Test
    public void creator_user()
    {
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"username\": \"Sanju\",\n" +
                        "  \"firstName\": \"Sanju\",\n" +
                        "  \"lastName\": \"S\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \"Sanju\",\n" +
                        "  \"phone\": \"string\",\n" +
                        "  \"userStatus\": 111\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/user\n");

        response.prettyPrint();
    }

    @Test
    public void login()
    {
        Response response = given()
                .header("accept", "application/json")
                .when()
                .get("https://petstore.swagger.io/v2/user/login?username=Shubham&password=Shubham\n");

        response.prettyPrint();
    }

    @Test
    public void updateUSer()
    {
        Response response = given().
                header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"username\": \"Riyan\",\n" +
                        "  \"firstName\": \"Riyan\",\n" +
                        "  \"lastName\": \"Parag\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \"Riyan\",\n" +
                        "  \"phone\": \"9880987\",\n" +
                        "  \"userStatus\": 1122\n" +
                        "}")
                .when()
                .put("https://petstore.swagger.io/v2/user/Shubham\n");

        response.prettyPrint();
    }

    @Test
    public void Get_User_By_UserName()
    {
        Response response = given().
                header("accept", "application/json")
                .when()
                .get("https://petstore.swagger.io/v2/user/Riyan\n");

        response.prettyPrint();
    }

    @Test
    public void createUserList() {
        java.lang.String jsonBody = "[\n" +
                "    {\n" +
                "        \"id\": 345,\n" +
                "        \"username\": \"Dhurav\",\n" +
                "        \"firstName\": \"Dhurav\",\n" +
                "        \"lastName\": \"Jurel\",\n" +
                "        \"email\": \"dhurav1234@gmail.com\",\n" +
                "        \"password\": \"Dhurav345\",\n" +
                "        \"phone\": \"89078678\",\n" +
                "        \"userStatus\": 4567\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 533,\n" +
                "        \"username\": \"Jos\",\n" +
                "        \"firstName\": \"Jos\",\n" +
                "        \"lastName\": \"Buttler\",\n" +
                "        \"email\": \"jos1234@gmail.com\",\n" +
                "        \"password\": \"Jos345\",\n" +
                "        \"phone\": \"7089765432\",\n" +
                "        \"userStatus\": 6789\n" +
                "    }\n" +
                "]";

        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithList");

        response.prettyPrint();
    }

    @Test
    public void DeleteUser()
    {
        Response response = given()
                .header("accept", "application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/user/Dhurav\n");

        response.prettyPrint();
    }

    @Test
    public  void Logout_User()
    {
        Response response = given()
                .header("accept", "application/json")
                .when()
                .get("https://petstore.swagger.io/v2/user/logout\n");

        response.prettyPrint();

    }
}
