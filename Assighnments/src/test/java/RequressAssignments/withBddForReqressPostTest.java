package RequressAssignments;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class withBddForReqressPostTest {
	
@Test
	
	public void getDataFromServer() {
		
		given()
		  .get("https://reqres.in/api/users/2 ")
		  .then()
		    .assertThat().statusCode(200)
		    .log().all();
	}
	
	
}
