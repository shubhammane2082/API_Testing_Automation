package SpotifyAutomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Playlist extends TokenStore {

    @Test
    public void createPlaylist() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", "316dwsubkhgxy7t4y4jlvepcycdq")
                .body("{\n" +
                        "    \"name\": \"Movie Playlist\",\n" +
                        "    \"description\": \"Movie playlist description\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .post("https://api.spotify.com/v1/users/{id}/playlists");

        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void getPlaylist() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("Playlist_Id", "7yNg2YYDfAmrIrutl7Weid")
                .get("https://api.spotify.com/v1/playlists/{Playlist_Id}?market=IN");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getUserPlaylists()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", "316dwsubkhgxy7t4y4jlvepcycdq")
                .pathParam("limit", "8")
                .pathParam("offset", "5")
                .get("https://api.spotify.com/v1/users/{id}/playlists?limit={limit}&offset={offset}");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void get_Current_User_Profile()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("l", "8")
                .pathParam("o", "5")
                .get("https://api.spotify.com/v1/me/playlists?limit={l}&offset={o}");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void GetFeaturedPlaylists()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("local", "IN")
                .pathParam("l", "8")
                .pathParam("o", "5")
                .get("https://api.spotify.com/v1/browse/featured-playlists?locale={local}&limit={l}&offset={o}");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void GetCategoryPlaylists()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .get("https://api.spotify.com/v1/browse/categories/dinner/playlists");

        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void GetPlaylistCoverImage()
    {
//        https://api.spotify.com/v1/playlists/7yNg2YYDfAmrIrutl7Weid/images
        Response response = given()
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("playlistId","7yNg2YYDfAmrIrutl7Weid")
                .get("https://api.spotify.com/v1/playlists/{playlistId}/images");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void ChangePlaylistDetails()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"name\": \"MyPlaylist Name\",\n" +
                        "    \"description\": \"Myplaylist description\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .pathParam("playlistId","7yNg2YYDfAmrIrutl7Weid")
                .when()
                .put(" https://api.spotify.com/v1/playlists/{playlistId}");

        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void AddItemsToPlaylist()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"uris\": [\n" +
                        "        \"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\"\n" +
                        "    ],\n" +
                        "    \"position\": 0\n" +
                        "}")
                .queryParams("position","0",
                        "uris","spotify:track:4iV5W9uYEdYUVa79Axb7Rh")
                .post("https://api.spotify.com/v1/playlists/7yNg2YYDfAmrIrutl7Weid/tracks");

        response.prettyPrint();
    }

    @Test
    public void updatePlaylistItems()
    {
        Response response = given()
                               .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"range_start\": 11,\n" +
                        "    \"insert_before\": 33,\n" +
                        "    \"range_length\": 22\n" +
                        "}")
                .when()
                .queryParams("uris","spotify:track:4iV5W9uYEdYUVa79Axb7Rh")
                .put("https://api.spotify.com/v1/playlists/7yNg2YYDfAmrIrutl7Weid/tracks");

        response.prettyPrint();
        response.then().statusCode(200);

    }

    @Test
    public void getPlaylistItems()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .queryParams("market","IN",
                        "limit","8",
                        "offset","5")
                .get("https://api.spotify.com/v1/playlists/7yNg2YYDfAmrIrutl7Weid/tracks");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void removeplalistItems()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"tracks\": [\n" +
                        "        {\n" +
                        "            \"uri\": \"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"snapshot_id\": \"AAAAKzsetlxzlvsFQm373RBJCo94wJFw\"\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/playlists/7yNg2YYDfAmrIrutl7Weid/tracks");

        response.prettyPrint();
        response.then().statusCode(200);
    }

}
