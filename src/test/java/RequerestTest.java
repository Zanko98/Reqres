import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequerestTest {

    @Test
    public void listUsers(){
        given().
        when().
                get("https://reqres.in/api/users?page=2").
        then().
                log().all().
                statusCode(200);
    }

    @Test
    public void singleUser(){
        given().
        when().
                get("https://reqres.in/api/users/2").
        then().
                log().all().
                statusCode(200);
    }

    @Test
    public void listUsersNotFound(){
        given().
        when().
                get("https://reqres.in/api/users/23").
        then().
                log().all().
                statusCode(404);
    }

    @Test
    public void listResource(){
        given().
        when().
                get("https://reqres.in/api/unknown").
        then().
                log().all().
                statusCode(200);
    }

    @Test
    public void singleUsers(){
        given().
        when().
                get("https://reqres.in/api/unknown/2").
        then().
                log().all().
                statusCode(200);
    }

    @Test
    public void singleResourceNotFound(){
        given().
        when().
                get("https://reqres.in/api/unknown/23").
        then().
                log().all().
                statusCode(404);
    }

    @Test
    public void create(){
        given().
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}").
        when().
                post("https://reqres.in/api/users").
        then().
                log().all().
                statusCode(201);
    }

    @Test
    public void updatePut(){
        given().
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}").
        when().
                put("https://reqres.in/api/users/2").
        then().
                log().all().
                statusCode(200);
    }

    @Test
    public void updatePatch(){
        given().
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}").
        when().
                patch("https://reqres.in/api/users/2").
        then().
                log().all().
                statusCode(200);
    }

    @Test
    public void delete(){
        given().
        when().
                delete("https://reqres.in/api/users/2").
        then().
                log().all().
                statusCode(204);
    }

    @Test
    public void registerSuccessful(){
        given().
                body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}").
                header("Content-type", "application/json").
        when().
                post("https://reqres.in/api/register").
        then().
                log().all().
                statusCode(200);
    }

    @Test
    public void registerUnsuccessful(){
        given().
                body("{\n" +
                        "    \"email\": \"sydney@fife\"\n" +
                        "}").
        when().
                post("https://reqres.in/api/register").
        then().
                log().all().
                statusCode(400);
    }

    @Test
    public void loginSuccessful(){
        given().
                body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}").
                header("Content-type", "application/json").
        when().
                post("https://reqres.in/api/login").
        then().
                log().all().
                statusCode(200);
    }

    @Test
    public void loginUnsuccessful(){
        given().
                body("{\n" +
                        "    \"email\": \"peter@klaven\"\n" +
                        "}").
        when().
                post("https://reqres.in/api/login").
        then().
                log().all().
                statusCode(400);
    }

    @Test
    public void delayedResponse(){
        given().
        when().
                get("https://reqres.in/api/users?delay=3").
        then().
                log().all().
                statusCode(200);
    }
}
