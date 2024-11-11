package RequressAssignments;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class withoutBddForReqressGetTest {
	
@Test
	
	public void getDataFromServer() {
		Response resp = RestAssured.get("https://reqres.in/api/users/2");
		System.out.println(resp.prettyPrint());
		
		resp.then().assertThat().statusCode(200);
		
		resp.then().log().all();
	}

}
