   package shopperStackProject;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoClassUtilityforShoppers.ShopperLoginPojo;

public class ShoppersLogin {

	@Test
	public void reqChainingTest() {

		ShopperLoginPojo slpObj = new ShopperLoginPojo("nsuma242000@gmail.com", "Nsuma@24", "SHOPPER");

		// API-1 ==> Shopper Login
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(slpObj)
				.when()
				.post("https://www.shoppersstack.com/shopping/users/login");
		resp.then().statusCode(200)
		.log().all();
		
		 //Assertion on Response Header
		 resp.then().assertThat().contentType(ContentType.JSON);
		 resp.then().assertThat().statusLine("HTTP/1.1 200 ");
		  resp.then().assertThat().statusCode(200);
		 resp.then().assertThat().header("Connection","keep-alive");
		 
		 //Assertion on Response Time
		// resp.then().assertThat().time(Matchers.both(Matchers.greaterThan(500L)).and(Matchers.lessThan(7000L)));
		 
		 // Assertion on Response Body 
		 resp.then().assertThat().body("data.zoneId",Matchers.equalTo("ALPHA")); 
		 
		  
		 //Capture the shopperId and jwtToken from the response 
		 int shopperId =
		  resp.jsonPath().get("data.userId");
		 String jwtToken =
		  resp.jsonPath().get("data.jwtToken");
		/* 
		 //API-2 ==> Get User Data
		  given() .auth().oauth2(jwtToken) .when()
		 .get("https://www.shoppersstack.com/shopping/shoppers/"+shopperId) .then()
		 .statusCode(200) .log().all();
		 
		  //Assertion on Response Header
		  resp.then().assertThat().contentType(ContentType.JSON);
		 resp.then().assertThat().statusLine("HTTP/1.1 200 ");
		 resp.then().assertThat().statusCode(200);
		 resp.then().assertThat().header("Transfer-Encoding","chunked");
		 
		  
		 
		 // Assertion on Response Body 
		  resp.then().assertThat().body("message",
		 Matchers.equalTo("Success")); 
		 resp.then().assertThat().body("data.status",
		  Matchers.equalTo("ACTIVE"));
		  */
		 
		
		 //  Get the view products
		Response productres= given()
		   .auth().oauth2(jwtToken).when()
		 .get("https://www.shoppersstack.com/shopping/products/alpha");
		productres.then()
		 .assertThat().statusCode(200)
		 .log().all();
		 
		 //to capture productId
		Object productId = productres.jsonPath().get("data[2].productId");	
		System.out.println(productId);
		
		
		
		
		//add product to Wishlist
		
		String bodyData = "{\r\n"
				+ "  \""+productId+"\": 0,\r\n"
				+ "  \"quantity\":5\"\r\n"
				+ "}";
			
			Response wishlistres=given()
			   .auth().oauth2(jwtToken)
			  .contentType(ContentType.JSON)
			  .body(bodyData)
			.when()
			  .post("https://www.shoppersstack.com/shopping/shoppers/"+shopperId+"/wishlist");
			  
			wishlistres.then()
			 .assertThat().statusCode(201)
			  .log().all();
			  
			System.out.println("======added product to wishlist====");
			
		
	//GET product from wishlist*/
	
    }
	
	
}

		
		







 