package SpotifyAutomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Chapters extends TokenStore
{
    @Test
    public void GetSeveralChapters()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("Id","0D5wENdkdwbqlrHoaJ9g29")
                .pathParam("m","ES")
                .get("https://api.spotify.com/v1/chapters?ids={Id}&market={m}");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
