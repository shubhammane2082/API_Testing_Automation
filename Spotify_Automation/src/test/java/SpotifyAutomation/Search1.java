package SpotifyAutomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

    public class Search1 extends TokenStore
    {
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
    }

