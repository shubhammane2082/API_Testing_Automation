package Automation;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Pet_Automation
{
    @Test
    public void add_New_Pet()
    {
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": 304,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 4,\n" +
                        "    \"name\": \"Tom\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 4,\n" +
                        "      \"name\": \"Tom\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/pet\n");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void Find_Pet_By_Id()
    {
        Response response = given()
                .header("accept","application/json")
                .get("https://petstore.swagger.io/v2/pet/1");

        response.prettyPrint();
    }

    @Test
    public void Find_Pet_By_Status()
    {
        Response response = given()
                .header("accept", "application/json")
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");

        response.prettyPrint();

        Assert.assertEquals(200,response.statusCode());
    }

    @Test
    public void Update_Existing_Pet()
    {
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"Dogggy\"\n" +
                        "  },\n" +
                        "  \"name\": \"Doggy\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"1233\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .put("https://petstore.swagger.io/v2/pet\n");

        response.prettyPrint();
    }

    @Test
    public void update_Pet_Data()
    {
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body("name=Jerry&status=Pending")
                .when()
                .post("https://petstore.swagger.io/v2/pet/1\n");

        response.prettyPrint();
    }

    @Test
    public  void DeletePet()
    {
        Response response=given()
                .header("accept", "application/json")
                .delete("https://petstore.swagger.io/v2/pet/5\n");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void uploadPetImage()
    {
        File file=new File("C:\\Users\\shubh\\OneDrive\\Pictures\\Dog.jpg");

        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "multipart/form-data")
                .multiPart(file)
                .when()
                .post("https://petstore.swagger.io/v2/pet/10/uploadImage\n");

        response.prettyPrint();
    }
}
