package SpotifyAutomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Track extends TokenStore
{
    @Test
    public void geTrack()
    {
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .queryParams("market","ES")
                .get("https://api.spotify.com/v1/tracks/3tX9hPhKxKBusddqCTWZo4");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getSeveralTrack()
    {
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .queryParams("market","ES",
                        "ids","3tX9hPhKxKBusddqCTWZo4")
                .get("https://api.spotify.com/v1/tracks");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getUserSavedTrack()
    {
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .queryParams("market","IN",
                        "limit","8","offset","4")
                .get("https://api.spotify.com/v1/me/tracks");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void saveCurrentUserTrack()
    {
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"3tX9hPhKxKBusddqCTWZo4\"\n" +
                        "    ]\n" +
                        "}")
                .queryParams("ids","3tX9hPhKxKBusddqCTWZo4")
                .put("https://api.spotify.com/v1/me/tracks");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void checkuserSavedTrack()
    {
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .queryParams("ids","3tX9hPhKxKBusddqCTWZo4")
                .get("https://api.spotify.com/v1/me/tracks/contains");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getSeveralAudioFeatures()
    {
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .queryParams("ids","3tX9hPhKxKBusddqCTWZo4")
                .get("https://api.spotify.com/v1/audio-features");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getAudioFeatures()
    {
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .get("https://api.spotify.com/v1/audio-features/3tX9hPhKxKBusddqCTWZo4");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getAudionTrackAnalysis()
    {
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .get("https://api.spotify.com/v1/audio-analysis/3tX9hPhKxKBusddqCTWZo4");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getRecommendations()
    {
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .queryParams("limit","10",
                        "market","ES",
                        "seed_artists","4NHQUGzhtTLFvgF5SZesLK",
                        "seed_genres","classical",
                        "seed_tracks","0c6xIDDpzE81m2q797ordA")
                .get("https://api.spotify.com/v1/recommendations?limit=10&market=ES&seed_artists=4NHQUGzhtTLFvgF5SZesLK&seed_genres=classical&seed_tracks=0c6xIDDpzE81m2q797ordA");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void removeUsersaveTrack()
    {
        Response response= given()
                .header("Authorization", "Bearer " + token)
                .queryParams("ids","7ouMYWpwJ422jRcDASZB7P")
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"7ouMYWpwJ422jRcDASZB7P\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/me/tracks");

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
