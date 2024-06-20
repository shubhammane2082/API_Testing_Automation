package SpotifyAutomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Artists extends TokenStore
{
    @Test
    public void GetArtist()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id","4yX6mpMyBGf9UfvBB8JJrc")
                .get("https://api.spotify.com/v1/artists/{id}");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void GetSeveralArtists()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id","4yX6mpMyBGf9UfvBB8JJrc")
                .get("https://api.spotify.com/v1/artists?ids={id}");

        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void GetArtistAlbums()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("Id","4yX6mpMyBGf9UfvBB8JJrc")
                .pathParam("m","IN")
                .pathParam("l","8")
                .pathParam("o","5")
                .get("https://api.spotify.com/v1/artists/{Id}/albums?include_groups=Single&market={m}&limit={l}&offset={o}");

        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void GetArtistTopTracks()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("Id","4yX6mpMyBGf9UfvBB8JJrc")
                .pathParam("m","IN")
                .get("https://api.spotify.com/v1/artists/{Id}/top-tracks?market={m}");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void GetArtistRelatedArtists()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("Id","4yX6mpMyBGf9UfvBB8JJrc")
                .get("https://api.spotify.com/v1/artists/{Id}/related-artists");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
