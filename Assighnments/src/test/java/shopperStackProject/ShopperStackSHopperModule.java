package shopperStackProject;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ShopperStackSHopperModule {

	//LOGIN
	
	JSONObject jobj = new JSONObject();
	jobj.put("email", email);
	jobj.put("password", "DummyFather01");
	jobj.put("role", "SHOPPER");
	Response resp1 = given().contentType(ContentType.JSON).body(jobj).when()
			.post("https://www.shoppersstack.com/shopping/users/login");
	resp1.then().log().all();
	resp1.then().statusCode(200);
	jwtToken = resp1.jsonPath().get("data.jwtToken");
	System.out.println(jwtToken);
	String message1 = resp1.jsonPath().get("message");
	System.out.println(message1);
	resp1.then().assertThat().body("message", Matchers.equalTo("OK"));
	resp1.then().assertThat().body("data.email", Matchers.equalTo(email));
ShopperId=resp1.jsonPath().get("data.userId");


System.out.println("======get product details=======");




}
