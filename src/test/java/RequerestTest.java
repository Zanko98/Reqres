import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RequerestTest {

    @Test
    public void listUsers(){
        given().
                header("Content-type", "application/json").
        when().
                get("https://reqres.in/api/users?page=2").
        then().
                statusCode(200);
    }

    @Test
    public void singleUser(){
        given().
                header("Content-type", "application/json").
        when().
                get("https://reqres.in/api/users/2").
        then().
                body(matchesJsonSchemaInClasspath("user.json")).
                statusCode(200);
    }

    @Test
    public void listUsersNotFound(){
        given().
                header("Content-type", "application/json").
        when().
                get("https://reqres.in/api/users/23").
        then().
                log().all().
                statusCode(404);
    }

    @Test
    public void listResource(){
        given().
                header("Content-type", "application/json").
        when().
                get("https://reqres.in/api/unknown").
        then().
                log().all().
                statusCode(200).extract().response().asString();
    }

    @Test
    public void singleResource(){
        given().
                header("Content-type", "application/json").
        when().
                get("https://reqres.in/api/unknown/2").
        then().
                log().all().
                body(matchesJsonSchemaInClasspath("resource.json")).
                statusCode(200);
    }

    @Test
    public void singleResourceNotFound(){
        given().
                header("Content-type", "application/json").
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
                header("Content-type", "application/json").
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
                header("Content-type", "application/json").
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
                header("Content-type", "application/json").
        when().
                patch("https://reqres.in/api/users/2").
        then().
                log().all().
                statusCode(200);
    }

    @Test
    public void delete(){
        given().
                header("Content-type", "application/json").
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
