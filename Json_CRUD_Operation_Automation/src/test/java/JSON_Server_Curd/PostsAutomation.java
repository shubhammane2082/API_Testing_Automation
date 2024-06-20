package JSON_Server_Curd;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class PostsAutomation
{
    @Test
    public void postdata()
    {
        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"id\": \"4\",\n" +
                        "        \"title\": \"Testing\",\n" +
                        "        \"author\": \"Jos\"\n" +
                        "}")
                .when()
                .post("http://localhost:3000/posts");

        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void getPostData()
    {
        Response response = given()
                .header("Accept", "*/*")
                .get("http://localhost:3000/posts");

        response.prettyPrint();
    }

    @Test
    public void updatePostData(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .body("{\n" +
                        "       \"title\": \"Testing1\",\n" +
                        "        \"author\": \"Das\"\n" +
                        "}")
                .when()
                .put("http://localhost:3000/posts/4");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void Deletepost()
    {
        Response response = given()
                .header("Accept", "*/*")
                .delete("http://localhost:3000/posts/4");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void partialUpdatePostData()
    {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .body("{\n" +
                        "        \"author\": \"Sanga\"\n" +
                        "}")
                .patch("http://localhost:3000/posts/3");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
