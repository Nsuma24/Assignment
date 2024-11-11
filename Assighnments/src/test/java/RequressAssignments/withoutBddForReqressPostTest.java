package RequressAssignments;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class withoutBddForReqressPostTest {
	
	@Test
	
	public void postDataToServer(){
	
	JSONObject jsonObj = new JSONObject();
	
	jsonObj.put("name", "morp");
	jsonObj.put("job", "TE");
	
	RequestSpecification req= RestAssured.given();
	
	req.contentType(ContentType.JSON);
	req.body(jsonObj.toJSONString());
	
	Response resp = req.post("https://reqres.in/api/users");
	
	resp.then().log().all();
	resp.then().assertThat().statusCode(201);
	
	
	}
	

}
