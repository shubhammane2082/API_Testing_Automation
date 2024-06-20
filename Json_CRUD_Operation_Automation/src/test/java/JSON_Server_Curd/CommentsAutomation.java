package JSON_Server_Curd;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CommentsAutomation
{
    @Test
    public void postComments()
    {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .body("{\n" +
                        "        \"body\": \"comment\",\n" +
                        "        \"postId\": 2\n" +
                        "}")
                .when()
                .post("http://localhost:3000/comments");

        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void getcomments()
    {
        Response response = given()
                .header("Accept", "application/json")
                .get("http://localhost:3000/comments");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void updateComments()
    {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .body("{\n" +
                        "    \"body\": \"No comment\",\n" +
                        "        \"postId\": 4,\n" +
                        "        \"id\": \"4\"\n" +
                        "}")
                .when()
                .put("http://localhost:3000/comments/dc53");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void partialUpdatecomments()
    {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .body("{\n" +
                        "    \"body\": \"EveryWhere\"\n" +
                        "}")
                .when()
                .patch("http://localhost:3000/comments/dc53");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void deletecomments()
    {
        Response response = given()
                .header("Accept", "applcation/json")
                .delete("http://localhost:3000/comments/f73c");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
