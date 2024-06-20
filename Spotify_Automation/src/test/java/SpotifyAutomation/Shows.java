package SpotifyAutomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Shows extends TokenStore
{
    @Test
    public void GetShow()
    {
       Response response= given()
                .header("Authorization", "Bearer " + token)
                .queryParams("market","IN")
                .get("https://api.spotify.com/v1/shows/0jCWG5oU6BvRtlLwusgLv5");

       response.prettyPrint();
       response.then().statusCode(200);
    }

    @Test
    public  void GetSeveralShows()
    {
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .queryParams("market","IN",
                        "ids","0jCWG5oU6BvRtlLwusgLv5")
                .get("https://api.spotify.com/v1/shows");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void GetShowEpisodes()
    {
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .queryParams("market","IN",
                        "limit","8","offset","5")
                .get("https://api.spotify.com/v1/shows/0jCWG5oU6BvRtlLwusgLv5/episodes");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getUserSaveShow()
    {
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .queryParams("limit","8","offset","5")
                .get("https://api.spotify.com/v1/me/shows");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void SaveShowsforCurrentUser()
    {
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .queryParams("ids","7yNg2YYDfAmrIrutl7Weid")
                .put("https://api.spotify.com/v1/me/shows");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void checkUserSavedShow()
    {
        //https://api.spotify.com/v1/me/shows/contains?ids=7yNg2YYDfAmrIrutl7Weid
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .queryParams("ids","7yNg2YYDfAmrIrutl7Weid")
                .get("https://api.spotify.com/v1/me/shows/contains");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void RemoveUsersavedShow()
    {
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .queryParams("ids","5CfCWKI5pZ28U0uOzXkDHe",
                        "market","IN")
                .get("https://api.spotify.com/v1/me/shows");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
