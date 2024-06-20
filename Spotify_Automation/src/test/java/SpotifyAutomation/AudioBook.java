package SpotifyAutomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AudioBook extends TokenStore
{
    @Test
    public void GetAudiobook()
    {
      Response response=given()
              .header("Authorization","Bearer "+token)
              .queryParams("market","IN")
              .get("https://api.spotify.com/v1/audiobooks/7iHfbu1YPACw6oZPAFJtqe");

      response.prettyPrint();
      response.then().statusCode(404);
    }

    @Test
    public void GetSeveralAudiobooks()
    {
        Response response=given()
                .header("Authorization","Bearer "+token)
                .queryParams("ids","1HGw3J3NxZO1TP1BTtVhpZ",
                "market","ES")
                .get("https://api.spotify.com/v1/audiobooks");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void GetUserSaveAudiobooks()
    {
        Response response=given()
                .header("Authorization","Bearer "+token)
                .queryParams("limit","8",
                        "offset","5")
                .get("https://api.spotify.com/v1/me/audiobooks");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void SaveAudiobooksCurrentUser()
    {
        Response response=given()
                .header("Authorization","Bearer "+token)
                .queryParams("ids","18yVqkdbdRvS24c0Ilj2ci")
                .put("https://api.spotify.com/v1/me/audiobooks");

        response.then().statusCode(200);
    }
    @Test
    public void CheckUserSavedAudiobooks()
    {
        Response response=given()
                .header("Authorization","Bearer "+token)
                .queryParams("ids","18yVqkdbdRvS24c0Ilj2ci")
                .get("https://api.spotify.com/v1/me/audiobooks/contains");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void RemoveUserSavedAudiobooks()
    {
        Response response=given()
                .header("Authorization","Bearer "+token)
                .queryParams("ids","1HGw3J3NxZO1TP1BTtVhpZ")
                .delete("https://api.spotify.com/v1/me/audiobooks");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
