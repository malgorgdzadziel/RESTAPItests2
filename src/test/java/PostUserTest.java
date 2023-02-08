
import io.restassured.http.ContentType;

import org.junit.Test;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.*;


import static io.restassured.RestAssured.*;
import junitparams.Parameters;


public class PostUserTest {

    @Test
    public void postUserBodyString() {

        baseURI = "https://reqres.in/";

        String body = "{\n" +
                "    \"name\": \"gosia\",\n" +
                "    \"job\": \"teacher\"\n" +
                "}";

        given().
                contentType(ContentType.JSON).
                body(body).
                when().
                post("api/users").
                then().
                statusCode(201).
                log().all();
    }


    public void postUserBodyMap() {

        baseURI = "https://reqres.in/";

        Map<String, String> body = new HashMap<>();
        body.put("name", "Gosia");
        body.put("job", "teacher");

        given().
                contentType(ContentType.JSON).
                body(body).
                when().
                post("api/users").
                then().
                statusCode(201).
                log().all();
    }

    @Test
    public void postUserBodyObject() {

        baseURI = "https://reqres.in/";

        User body = new User("Gosia", "teacher");

        given().
                contentType(ContentType.JSON).
                body(body).
                when().
                post("api/users").
                then().
                statusCode(201).
                log().all();
    }

    @Test
    @Parameters({"Ania","Kasia"} )
    public void postUsersList(String name) {

        baseURI = "https://reqres.in/";

        User body =new User(name, "cook");

        given().
                contentType(ContentType.JSON).
                body(body).
                when().
                post("api/users").
                then().
                statusCode(201).
                log().all();
    }


/*
   public String[][] getUsersToCreate(){
        String users[][]=
                {
                        {"Ania","cook"},
                        {"Kasia", "horse rider"}
                };
        return users;
    }
*/


    public static Set<User> getUsersToCreate() {
        Set<User> usersToCreate = new HashSet<>();
        usersToCreate.add(new User("Ania", "cook"));
        usersToCreate.add(new User("Kasia", "horse rider"));
        return usersToCreate;
    }
}
