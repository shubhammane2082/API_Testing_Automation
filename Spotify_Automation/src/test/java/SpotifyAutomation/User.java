package SpotifyAutomation;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.util.CharsetUtils.get;

public class User extends TokenStore
{
    @Test
    public void getcurrent_User_Profile()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .get("https://api.spotify.com/v1/me");

        response.prettyPrint();

        String id=response.path("id");
        Assert.assertEquals(id,"316dwsubkhgxy7t4y4jlvepcycdq");
    }

    @Test
    public void getCurrentUserProfile()
    {
//        https://api.spotify.com/v1/users/316dwsubkhgxy7t4y4jlvepcycdq
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", "316dwsubkhgxy7t4y4jlvepcycdq")
                .get("https://api.spotify.com/v1/users/{id}");

        response.prettyPrint();
        response.then().statusCode(200);

        String name=response.path("display_name");
        Assert.assertEquals(name,"Shubham");
    }

    @Test
    public void GetUserTopItems()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .queryParams("time_range","medium_term",
                        "limit","8",
            "offset","5").get("https://api.spotify.com/v1/me/top/artists");
        response.prettyPrint();

        response.then().statusCode(200);
        int limit=response.path("limit");

        Assert.assertEquals(limit,8);
    }

    @Test
    public void FollowPlaylist()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .pathParam("Id", "3cEYpjA9oz9GiPac4AsH4n")
                .put("https://api.spotify.com/v1/playlists/{Id}/followers");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void GetFollowedArtists()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .queryParams("type","artist",
                        "after","0I2XqVXqHScXjHhk6AYYRe",
                        "limit","8")
                .get("https://api.spotify.com/v1/me/following");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void FollowArtists()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"37i9dQZF1E4oJSdHZrVjxD\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .queryParams("type","artist",
                        "ids","37i9dQZF1E4oJSdHZrVjxD")
                .put("https://api.spotify.com/v1/me/following");

        response.prettyPrint();
        response.then().statusCode(204);
    }

    @Test
    public void CheckIfUserFollows()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .queryParams("type","artist",
                        "ids","37i9dQZF1E4oJSdHZrVjxD")
                .get("https://api.spotify.com/v1/me/following/contains?type=artist&ids=37i9dQZF1E4oJSdHZrVjxD");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void CheckCurrentUserFollowsPlaylist()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .queryParams("ids","316dwsubkhgxy7t4y4jlvepcycdq")
                .get("https://api.spotify.com/v1/playlists/7yNg2YYDfAmrIrutl7Weid/followers/contains");

        response.prettyPrint();
        response.then().statusCode(200);
     }

     @Test
    public void unfollowplaylist()
     {
//         https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/followers
       Response response=  given()
                 .header("Authorization", "Bearer " + token)
                 .pathParam("Id","3cEYpjA9oz9GiPac4AsH4n")
                 .delete("https://api.spotify.com/v1/playlists/{Id}/followers");

       response.prettyPrint();
       response.then().statusCode(200);
     }

     @Test
    public void UnfollowArtists()
     {
         Response response=  given()
                 .header("Authorization", "Bearer " + token)
                 .body("{\n" +
                         "    \"ids\": [\n" +
                         "        \"3cEYpjA9oz9GiPac4AsH4n\"\n" +
                         "    ]\n" +
                         "}")
                 .queryParams("type", "artist",
                         "Id","78aUDzC8OJXelrqmqrtdL9")
                 .delete("https://api.spotify.com/v1/me/following");

         response.prettyPrint();
         response.then().statusCode(204);
     }
}
