package SpotifyAutomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Categories extends TokenStore
{
    @Test
    public void getSeveralCategories()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("l","IN")
                .pathParam("lim","8")
                .pathParam("o","5")
                .get("https://api.spotify.com/v1/browse/categories?locale={l}&limit={lim}&offset={o}");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void GetSingleBrowseCategory()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("l","IN")
                .get("https://api.spotify.com/v1/browse/categories/dinner?locale={l}");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
