package backendapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.boot.test.web.server.LocalServerPort;	// SBv3

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class SystemTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp(){
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    /*
    These tests below are all for the userController
     */

    @Test
    public void getAllUsersTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/users");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            JSONObject returnObj = returnArr.getJSONObject(0);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getAllStocksUserTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/user/1");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            JSONObject returnObj = returnArr.getJSONObject(0);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getUserByIdTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/users/1");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            JSONObject returnObj = returnArr.getJSONObject(0);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getUserByUsername(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/userByName/user");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            JSONObject returnObj = returnArr.getJSONObject(0);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }


    @Test
    public void purchaseStockTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/buy/4/user/3/amt/4");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            Double returnObj = returnArr.getDouble(0);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void sellStockTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/sell/4/user/3/4");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            Double returnObj = returnArr.getDouble(0);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getFriendGroupTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/friendgroup");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            JSONObject returnObj = returnArr.getJSONObject(0);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void createUserTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                body("{\n" +
                        "    \"dob\": \"08/21/2003\",\n" +
                        "    \"email\": \"sharif@iastate.edu\",\n" +
                        "    \"money\": 98765654,\n" +
                        "    \"name\": \"Sharif Mahdi\",\n" +
                        "    \"id\": 5,\n" +
                        "    \"username\": \"UsErname\",\n" +
                        "    \"password\": \"password\"\n" +
                        "}").
                when().
                post("/users");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            String returnObj = returnArr.getString(0);
            assertEquals("{\"message\":\"success\"}", returnObj);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void createFriendGroupTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                post("/friendgroup/balls/1");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            String returnObj = returnArr.getString(0);
            assertEquals("{\"message\":\"success\"}", returnObj);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getUserFriendGroupTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/friendgroup/get/1");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            JSONObject returnObj = returnArr.getJSONObject(0);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getUsersFromGroupTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/friendgroup/getall/balls");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            JSONObject returnObj = returnArr.getJSONObject(0);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void addUserToGroupTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                put("/friendgroup/balls/2");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            String returnObj = returnArr.getString(0);
            assertEquals("{\"message\":\"success\"}", returnObj);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void removeUserFromGroupTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                put("/friendgroup/remove/balls/1/2");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            String returnObj = returnArr.getString(0);
            assertEquals("{\"message\":\"success\"}", returnObj);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void updateUserTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                put("/update/1");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            JSONObject returnObj = returnArr.getJSONObject(0);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUserTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                delete("/users/3");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            String returnObj = returnArr.getString(0);
            assertEquals("{\"message\":\"success\"}", returnObj);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void createStockTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                body("{\n" +
                        "    \"id\": 5,\n" +
                        "    \"symbol\": \"AMZN\",\n" +
                        "    \"company\": \"Amazon\",\n" +
                        "    \"currValue\": 135.04,\n" +
                        "    \"prevDayChange\": 1.95\n" +
                        "}").
                when().
                post("/newstocks/1");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            String returnObj = returnArr.getString(0);
            assertEquals("{\"message\":\"success\"}", returnObj);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void deleteStockTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                delete("/stocks/5/1");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            String returnObj = returnArr.getString(0);
            assertEquals("{\"message\":\"success\"}", returnObj);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void updateStocksTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                put("/update/1");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            String returnObj = returnArr.getString(0);
            assertEquals("{\"message\":\"success\"}", returnObj);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void banUserTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                put("/banuser/3/byadmin/1");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            String returnObj = returnArr.getString(0);
            assertEquals("{\"message\":\"success\"}", returnObj);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Test
    public void unbanUserTest(){
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                put("/unban/3/byadmin/1");

        assertEquals(200, response.getStatusCode());

        String returnString = response.getBody().asString();
        try{
            JSONArray returnArr = new JSONArray(returnString);
            String returnObj = returnArr.getString(0);
            assertEquals("{\"message\":\"success\"}", returnObj);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    /*
    These following tests will be for the stock controller class
     */
}
