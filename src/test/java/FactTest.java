import org.junit.Test;

import static io.restassured.RestAssured.*;

public class FactTest {

    @Test
    public void  test(){

        Fact fact=
            given().
            get("https://catfact.ninja/fact")
                .then()
                .log().body()
                    .log().headers()
                    .extract().response().as(Fact.class);

        System.out.println("MG Fact "+fact.getFact());

    }


}
