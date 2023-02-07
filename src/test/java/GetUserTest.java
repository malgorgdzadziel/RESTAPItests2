import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
public class GetUserTest {


    @Test
    public void getTest(){

                 given().
                when().
                get("https://reqres.in/api/users").
                then().log().all();

    }



}
