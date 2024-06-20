package SpotifyAutomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Album extends TokenStore
{
    @Test
    public void GetAlbum()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", "4aawyAB9vmqN3uQ7FjRGTy")
                .get("https://api.spotify.com/v1/albums/{id}?market=IN");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void GetSeveralAlbums()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", "4aawyAB9vmqN3uQ7FjRGTy")
                .get("https://api.spotify.com/v1/albums/{id}?market=IN");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void GetAlbumTracks(){
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", "4aawyAB9vmqN3uQ7FjRGTy")
                .pathParam("m","IN")
                .pathParam("o","5")
                .get("https://api.spotify.com/v1/albums/{id}/tracks?market={m}&limit=8&offset={o}");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void GetUserSavedAlbums()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("m","IN")
                .pathParam("o","5")
                .get("https://api.spotify.com/v1/me/albums?limit=10&offset={o}&market={m}");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void SaveAlbumsCurrentUser()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id","4aawyAB9vmqN3uQ7FjRGTy")
                .get("https://api.spotify.com/v1/me/albums?ids={id}");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void CheckUserSavedAlbums(){
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id","4aawyAB9vmqN3uQ7FjRGTy")
                .get("https://api.spotify.com/v1/me/albums/contains?ids={id}");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void GetNewReleases()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("l","8")
                .pathParam("o","5")
                .get("https://api.spotify.com/v1/browse/new-releases?limit={l}&offset={o}");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void RemoveUsersSavedAlbums()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"1A2GTWGtFfWp7KSQTwWOyo\"\n" +
                        "    ]\n" +
                        "}")
                .pathParam("id","2noRn2Aes5aoNVsU6iWThc")
                .delete("https://api.spotify.com/v1/me/albums?ids={id}");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
