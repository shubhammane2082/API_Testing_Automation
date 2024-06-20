package Automation;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Store_Automation
{
    @Test
    public void Place_An_Order_Pet() {
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": 102,\n" +
                        "  \"petId\": 1122,\n" +
                        "  \"quantity\": 12,\n" +
                        "  \"shipDate\": \"2024-04-28T11:46:08.092Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": false\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/store/order\n");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void get_Purchase_By_Id()
    {
        Response response = given()
                .header("accept", "application/json")
                .get("https://petstore.swagger.io/v2/store/order/4\n");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void get_Pet_Inventory()
    {
        Response response = given()
                .header("accept", "application/json")
                .get("https://petstore.swagger.io/v2/store/inventory\n");

        response.prettyPrint();
    }

    @Test
    public void Delete_Purchase()
    {
        Response response = given()
                .header("accept", "application/json")
                .delete("https://petstore.swagger.io/v2/store/order/320\n");

        response.prettyPrint();
    }

}
