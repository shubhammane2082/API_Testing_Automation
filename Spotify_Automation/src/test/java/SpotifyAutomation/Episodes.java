package SpotifyAutomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Episodes extends TokenStore
{
    @Test
    public void getEpisode()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .queryParams("market","ES")
                .get("https://api.spotify.com/v1/episodes/3X3KLHQCtceJrqFseIQa4m");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getSeveralEpisodes()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .queryParams("ids","3X3KLHQCtceJrqFseIQa4m",
                        "market","IN")
                .get("https://api.spotify.com/v1/episodes");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getUserSavedEpisode()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .queryParams("market","IN",
                        "limit","8",
                        "offset","5")
                .get("https://api.spotify.com/v1/me/episodes?market=IN&limit=8&offset=5");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void saveEpisodeCurrentUser()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .queryParams("ids","77o6BIVlYM3msb4MMIL1jH")
                .put("https://api.spotify.com/v1/me/episodes");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void checkUsersavedEpisode()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .queryParams("ids","3X3KLHQCtceJrqFseIQa4m")
                .get("https://api.spotify.com/v1/me/episodes/contains");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void RemoveuserEpisode()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .queryParams("ids","3X3KLHQCtceJrqFseIQa4m")
                .get("https://api.spotify.com/v1/me/episodes/contains");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void RemoveUserEpisode()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .queryParams("ids","3X3KLHQCtceJrqFseIQa4m")
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"3X3KLHQCtceJrqFseIQa4m\n" +
                        "    ]\n" +
                        "}")
                .delete("https://api.spotify.com/v1/me/episodes");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
