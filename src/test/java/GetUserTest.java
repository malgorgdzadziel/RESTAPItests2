import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.junit.Test;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;

public class GetUserTest {


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



}
