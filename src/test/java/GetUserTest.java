import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;

public class GetUserTest {

    @Test
    public void getUserTest(){
        baseURI = "https://reqres.in";

        String body =
        given().contentType("application/json").
         when().
        get("/users/2").asPrettyString();


        System.out.println("MG body" + body);
    }

}
