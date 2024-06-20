package SpotifyAutomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Markets extends TokenStore
{
    @Test
    public void GetAvailableMarkets()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .get("https://api.spotify.com/v1/markets");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
