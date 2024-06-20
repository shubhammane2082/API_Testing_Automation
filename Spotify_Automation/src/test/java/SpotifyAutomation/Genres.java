package SpotifyAutomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Genres extends TokenStore
{
    @Test
    public void GetAvailableGenre()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .get("https://api.spotify.com/v1/recommendations/available-genre-seeds");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
