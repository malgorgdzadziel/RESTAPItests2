import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;

public class GetUsersTest {


    @Test
    public void getTest(){

                 given().
                when().
                get("https://reqres.in/api/users").
                then().
                         statusCode(200).
                         body("total",equalTo(12)).
                         body("data.id", hasItems(1,2,3)).
                         log().all();

    }

    @Test
    public void jsonSchemaValidation(){
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV4)
                .freeze()).freeze();

        get("https://reqres.in/api/users").
                then().assertThat().body(matchesJsonSchemaInClasspath("users-schema.json").
                using(jsonSchemaFactory));

    }

    @Test
    public void testComplexParsing(){
        when().
        get("https://reqres.in/api/users").
        then().
        body("data.findAll {it.id<3}.first_name", hasItems("George")).
        body("data.find {it.first_name == 'Emma' }.email ", equalTo("emma.wong@reqres.in") ).
        body("data.id.max() ", equalTo(6));
    }

    @Test
    public void usersAsListMaps(){

        List<Map< String, Object>> userData=
                given().
                when().
                get("https://reqres.in/api/users").
                then().
                extract().path("data");

        Assert.assertEquals("George", userData.get(0).get("first_name"));

    }

    @Test
    public void userFirstName(){

      String name= given().
                        when().
                        get("https://reqres.in/api/users").
                        then().
                        extract().
                        path("data[0].first_name");

        Assert.assertEquals("George", name);
    }

    @Test
    public void userFirstNameFromResponse(){

        Response response= given().
                when().
                get("https://reqres.in/api/users").
                then().
                extract().
                response();

        Assert.assertEquals("George", response.path("data[0].first_name"));
    }

    @Test
    public void responseHeader(){

        Response response= given().
                when().
                get("https://reqres.in/api/users").
                then().
                extract().
                response();

        System.out.println(response.header("Connection"));
    }
}
