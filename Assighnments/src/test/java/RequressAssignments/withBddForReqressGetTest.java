package RequressAssignments;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class withBddForReqressGetTest {
	
	@Test
	
	public void postDataToServer(){
	
    JSONObject jsonObj = new JSONObject();
	
	jsonObj.put("name", "morp");
	jsonObj.put("job", "TE");
	
	given()
	  .contentType(ContentType.JSON)
	  .body(jsonObj.toJSONString())
	.when()
	  .post("https://reqres.in/api/users")
	.then()
	 .assertThat().statusCode(201)
	 .log().all();
	
	
	}

}
