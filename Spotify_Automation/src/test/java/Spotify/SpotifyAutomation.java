package Spotify;

import com.beust.ah.A;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

public class SpotifyAutomation
{
    String token="BQAuT1aLh1A41Wg2d0ylxHf6XRBKnwoyk_L-zOEp-P-OL0wSvYCGTF-JcyHqKFrdfY_ZsBKS93uI2OKbHq08I8kHs6S7iVspPhlx3-BG548E57ESimljA8AZJ-ywtR1WE5ETr_sw_OgV0SqpLCJX756TYpnCaYRIzUfVstS1nL4-q9G18jM5cHvDK99LspgXgF1Zq9gcczxtmE_UWpw35aU_hfdiPcnNM_N8mSVae_M6wszrYGv8LN6DB_LIhDAqAlw5ldgBRLMT9XS4xhcFkDzbiJ1iKo_0Ny9husc1wkMFyFFjpPr2JUCg2o1KqoMlmISOd78RlwyjRAE";
    @BeforeSuite
    public void suite() {System.out.println("Before suite Annotation...");}

    @BeforeTest
    public void beforeTest(){System.out.println("Before Test Annotation...");}

    @BeforeClass
    public void beforeClass(){System.out.println("Before Class Annotation...");}

    @BeforeMethod
    public void BeforeMethod(){System.out.println("Before Method Annotation...");}

    @AfterMethod
    public void AfterMethod(){System.out.println("After Method Annotation...");}

    @AfterTest
    public void AfterTest(){System.out.println("After Test Annotation...");}

    @AfterClass
    public void afterclass(){System.out.println("After class Annotation...");}

    @AfterSuite
    public void aftrsuite(){System.out.println("After suite annotation...");}
    //User Spotify API Testing

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
                        "offset","5")
                .get("https://api.spotify.com/v1/me/top/artists");

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

    //Playlist Spotify API Testing
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

    //Album Spotify API Automation
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

    //Artists Spotify API Automation

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

    //AudioBook Spotify API AUtomation
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

    //Categories Spotify API Automation
    @Test
    public void getSeveralCategories()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("l","IN")
                .pathParam("lim","8")
                .pathParam("o","5")
                .get("https://api.spotify.com/v1/browse/categories?locale={l}&limit={lim}&offset={o}");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void GetSingleBrowseCategory()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("l","IN")
                .get("https://api.spotify.com/v1/browse/categories/dinner?locale={l}");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    //Chapter SPotify Automation
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

    //Episodes Spotify API Automation
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

    //Spotify Genres Spotify API Automation
    @Test
    public void GetAvailableGenre()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .get("https://api.spotify.com/v1/recommendations/available-genre-seeds");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    //Markets Spotify API Automation
    @Test
    public void GetAvailableMarkets()
    {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .get("https://api.spotify.com/v1/markets");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    //Search API Automation
    @Test
    public void searchItem()
    {
        Response response = given()
                .header("Authorization", "Bearer "+token)
                .queryParams(
                        "q", "facet=music-chip",
                        "type", "album",
                        "market", "IN",
                        "limit", 8,
                        "offset", 5)
                .get("https://api.spotify.com/v1/search");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    //Shows SPotify API Automation
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

    //Track Spotify Automation
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

    //Spotify Player Automation
    @Test
    public void getplayBackState()
    {
       Response response= given()
                .header("Authorization","Bearer " + token)
                .queryParams("market","IN")
                .when()
                .get("https://api.spotify.com/v1/me/player");

       response.prettyPrint();
       response.then().statusCode(204);
    }
    @Test
    public void TransferPlayback()
    {
        Response response= given()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer " + token)
                .queryParams("market","IN")
                .when()
                .put("https://api.spotify.com/v1/me/player");

        response.prettyPrint();
        response.then().statusCode(403);
        String reason=response.path("error.reason");
        Assert.assertEquals(reason,"PREMIUM_REQUIRED");
    }

    @Test
    public void getAvailableDevice()
    {
        Response response= given()
                .header("Authorization","Bearer " + token)
                .queryParams("market","IN")
                .when()
                .get("https://api.spotify.com/v1/me/player/devices");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getcurrentPlayableTrack()
    {
        Response response= given()
                .header("Authorization","Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/currently-playing");

        response.prettyPrint();
        response.then().statusCode(204);
    }

    @Test
    public void startPlayback()
    {
        Response response= given()
                .header("Authorization","Bearer " + token)
                .when()
                .body("{\n" +
                        "    \"context_uri\": \"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\",\n" +
                        "    \"offset\": {\n" +
                        "        \"position\": 4\n" +
                        "    },\n" +
                        "    \"position_ms\": 0\n" +
                        "}")
                .queryParams("device_id","0d1841b0976bae2a3a310dd74c0f3df354899bc8")
                .put("https://api.spotify.com/v1/me/player/play?device_id=0d1841b0976bae2a3a310dd74c0f3df354899bc8");

        response.prettyPrint();
        response.then().statusCode(403);
        String reason=response.path("error.reason");
        Assert.assertEquals(reason,"PREMIUM_REQUIRED");
    }

    @Test
    public void PausePlayback()
    {
        Response response= given()
                .header("Authorization","Bearer " + token)
                .queryParams("device_id","0d1841b0976bae2a3a310dd74c0f3df354899bc8")
                .when()
                .put("https://api.spotify.com/v1/me/player/play");

        response.prettyPrint();
        response.then().statusCode(403);

        String reason=response.path("error.reason");
        Assert.assertEquals(reason,"PREMIUM_REQUIRED");
    }

    @Test
    public void pauseTonext()
    {
        Response response= given()
                .header("Authorization","Bearer " + token)
                .queryParams("device_id","0d1841b0976bae2a3a310dd74c0f3df354899bc8")
                .when()
                .post("https://api.spotify.com/v1/me/player/next");

        response.prettyPrint();
        response.then().statusCode(403);

        String reason=response.path("error.reason");
        Assert.assertEquals(reason,"PREMIUM_REQUIRED");
    }

    @Test
    public void skipToprevious()
    {
        Response response= given()
                .header("Authorization","Bearer " + token)
                .queryParams("device_id","0d1841b0976bae2a3a310dd74c0f3df354899bc8")
                .when()
                .post("https://api.spotify.com/v1/me/player/previous");

        response.prettyPrint();
        response.then().statusCode(403);

        String reason=response.path("error.reason");
        Assert.assertEquals(reason,"PREMIUM_REQUIRED");
    }

    @Test
    public void seekToposition()
    {
        Response response= given()
                .header("Authorization","Bearer " + token)
                .queryParams("device_id","0d1841b0976bae2a3a310dd74c0f3df354899bc8",
                        "position_ms","1200")
                .when()
                .put("https://api.spotify.com/v1/me/player/seek");

        response.prettyPrint();
        response.then().statusCode(403);

        String reason=response.path("error.reason");
        Assert.assertEquals(reason,"PREMIUM_REQUIRED");
    }

    @Test
    public void setTorepeat()
    {
        Response response= given()
                .header("Authorization","Bearer " + token)
                .queryParams("state","true")
                .when()
                .put("https://api.spotify.com/v1/me/player/shuffle?state=true");

        response.prettyPrint();
        response.then().statusCode(403);

        String reason=response.path("error.reason");
        Assert.assertEquals(reason,"PREMIUM_REQUIRED");
    }

    @Test
    public void setPlaybackVolume()
    {
        Response response= given()
                .header("Authorization","Bearer " + token)
                .queryParams("volume_percent",23,
                        "device_id","0d1841b0976bae2a3a310dd74c0f3df354899bc8")
                .when()
                .put("https://api.spotify.com/v1/me/player/volume");

        response.prettyPrint();
        response.then().statusCode(403);

        String reason=response.path("error.reason");
        Assert.assertEquals(reason,"PREMIUM_REQUIRED");
    }

    @Test
    public void ToggleplaybackShufffle()
    {
        Response response= given()
                .header("Authorization","Bearer " + token)
                .queryParams("volume_percent",23,
                        "device_id","0d1841b0976bae2a3a310dd74c0f3df354899bc8")
                .when()
                .put("https://api.spotify.com/v1/me/player/shuffle");

        response.prettyPrint();
        response.then().statusCode(403);

        String reason=response.path("error.reason");
        Assert.assertEquals(reason,"PREMIUM_REQUIRED");
    }

    @Test
    public void getRecentlyPlayedTrack()
    {
        Response response= given()
                .header("Authorization","Bearer " + token)
                .queryParams("limit",8,
                        "after","148")
                .when()
                .get("https://api.spotify.com/v1/me/player/recently-played");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getUserQueue()
    {
        Response response= given()
                .header("Authorization","Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/queue");

        response.prettyPrint();
        response.then().statusCode(403);
    }

    @Test
    public void addItemstoplayback()
    {
        Response response= given()
                .header("Authorization","Bearer " + token)
                .queryParams("uri","spotify%253Atrack%253A4iV5W9uYEdYUVa79Axb7Rh","device_id","0d1841b0976bae2a3a310dd74c0f3df354899bc8")
                .when()
                .post("https://api.spotify.com/v1/me/player/queue");

        response.prettyPrint();
        String reason=response.path("error.reason");
        Assert.assertEquals(reason,"PREMIUM_REQUIRED");
    }

}
