package shopperStackProject;

import static io.restassured.RestAssured.*;

import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoClassUtilityforShoppers.ShopperAddressPojo;
import pojoClassUtilityforShoppers.ShopperLoginPojo;

public class ViewProducts {
	
	@Test
	public void endToEndTest() {
		
		// Shopper Register API test

		Random random = new Random();
		int ranNum = random.nextInt(1000);

		JSONObject jObj = new JSONObject();
		jObj.put("city", "Bangalore");
		jObj.put("country", "India");
		jObj.put("email", "suma" + ranNum + "@gmail.com");
		jObj.put("firstName", "Suma");
		jObj.put("gender", "Female");
		jObj.put("lastName", "N");
		jObj.put("password", "suma@3");
		jObj.put("phone", 9876543210l);
		jObj.put("state", "Karnataka");
		
		Response response = given()
				.contentType(ContentType.JSON)
				.body(jObj)
				.when()
				.post("https://www.shoppersstack.com/shopping/shoppers?zoneId=ALPHA");
				response.then()
				.assertThat().statusCode(201)
				.log().all();

				int shopperId = response.jsonPath().get("data.userId");
				String email = response.jsonPath().get("data.email");
				String role = response.jsonPath().get("data.role");
				String password = "Rohit@1993";

				// Shopper Login API test

				ShopperLoginPojo slpObj = new ShopperLoginPojo(email, password, role);
				Response response1 = given()
				.contentType(ContentType.JSON)
				.body(slpObj)
				.when()
				.post("https://www.shoppersstack.com/shopping/users/login");
				response1.then()
				.assertThat().statusCode(200)
				.log().all();

				String token = response1.jsonPath().get("data.jwtToken");

				// View Default Products API test
				
				
				Response response2 = given()
						.auth().oauth2(token)
						.when()
						.get("https://www.shoppersstack.com/shopping/products?zoneId=ALPHA");
						response2.then()
						.assertThat().statusCode(200)
						.log().all();
						int productId = response2.jsonPath().get("data[0].productId");

						// Add Product to Wish list API test

						JSONObject jObj1 = new JSONObject();
						jObj1.put("productId", 14);
						jObj1.put("quantity", 1);

						Response response3 = given()
						.auth().oauth2(token)
						//.pathParam("ShopperID", shopperId)
						.contentType(ContentType.JSON)
						.body(jObj1)
						.when()
						
						//.post("https://www.shoppersstack.com/shopping/shoppers/{ShopperID}/wishlist");
						.post("https://www.shoppersstack.com/shopping/shoppers/"+shopperId+"/wishlist");
						response3.then()
						.assertThat().statusCode(201)
						.log().all();

						
						//Get Product from Wish list API test
						  
						Response response4 = given()
						.auth().oauth2(token)
						//.pathParam("shopperID",shopperId)
						.contentType(ContentType.JSON)
						.log().all()
						.when()
						.get("https://www.shoppersstack.com/shopping/shoppers/"+shopperId+"/wishlist");
						response4.then()
						.assertThat().statusCode(200)
						.log().all();
						
						
						//Delete Product from Wish list API test
						  
						Response response5 = given()
						.auth().oauth2(token)
						//.pathParam("shopperID",shopperId)
						.contentType(ContentType.JSON)
						.log().all() 
						.when()
						.delete("https://www.shoppersstack.com/shopping/shoppers/"+shopperId+"/wishlist/"+productId); 
						response5.then() 
						.assertThat().statusCode(204)
						.log().all();
						
						//Add product to Cart API test
						  
						Response response6 = given() 
						.auth().oauth2(token)
						//.pathParam("shopperID",shopperId)
						.body(jObj1)
						.contentType(ContentType.JSON) 
						.log().all() 
						.when()
						.post("https://www.shoppersstack.com/shopping/shoppers/"+shopperId+"/carts");
						response6.then()
						.assertThat().statusCode(201)
						.log().all();
							  
							
						//Get product from Cart API test
							  
						Response response7 = given() 
						.auth().oauth2(token)
						//.pathParam("shopperID",shopperId)
						.contentType(ContentType.JSON)
						.log().all() 
						.when()
						.get("https://www.shoppersstack.com/shopping/shoppers/"+shopperId+"/carts");
						response7.then() 
						.assertThat().statusCode(200) 
						.log().all(); 
						int itemId=response7.jsonPath().get("data[0].itemId");
							  
						//Update product in Cart API test
							  
						JSONObject jObj2=new JSONObject();
						jObj2.put("productId", productId);
						jObj2.put("quantity", 2);
							  
						Response response8 = given() 
						.auth().oauth2(token) 
						//.pathParam("shopperID",shopperId)
						.body(jObj2)
						.contentType(ContentType.JSON) 
						.log().all() 
						.when()
					    .put("https://www.shoppersstack.com/shopping/shoppers/"+shopperId+"/carts/"+itemId); 
						response8.then()
						.assertThat().statusCode(200)
					    .log().all();
						
						//Add New Address API test
					
						ShopperAddressPojo sap = new ShopperAddressPojo(0,"#1, Top Floor", "Bengaluru", "India", "Opp to Vega City Mall", "PG", "9876543210", "560010", "Karnataka", "Benerghatta Main Road", "Home");
					
						Response res9 = given()
						.auth().oauth2(token)
						.body(sap)
						.contentType(ContentType.JSON)
						.log().all()
						.when()
						.post("https://www.shoppersstack.com/shopping/shoppers/"+shopperId+"/address");
						res9.then().log().all()
						.statusCode(201);
						int addressId = res9.jsonPath().get("data.addressId");
						
						//Get All Addresses API test
						
						Response response10 = given() 
						.auth().oauth2(token)
						//.pathParam("shopperID",shopperId)
						.contentType(ContentType.JSON)
						.log().all() .when()
						.get("https://www.shoppersstack.com/shopping/shoppers/"+shopperId+"/address/");
						response10.then() 
						.assertThat().statusCode(200) 
						.log().all();
						
						
						//Get A Particular Address API test
						
						Response response11 = given() 
						.auth().oauth2(token)
						//.pathParam("shopperID",shopperId)
						.contentType(ContentType.JSON)
						.log().all() .when()
						.get("https://www.shoppersstack.com/shopping/shoppers/"+shopperId+"/address/"+addressId);
						response11.then() 
						.assertThat().statusCode(200) 
						.log().all();
						
						//Update Address API test
					
						ShopperAddressPojo sap1=new ShopperAddressPojo(0,"#1, Ground Floor", "Bengaluru", "India", "Opp to Vega City Mall", "PG", "9876543210", "560010", "Karnataka", "Benerghatta Main Road", "Home");
						
						
						Response response12 = given()
								.auth().oauth2(token)
								.body(sap1)
								//.pathParam("shopperID",shopperId)
								.contentType(ContentType.JSON)
								.log().all()
								.when()
								.put("https://www.shoppersstack.com/shopping/shoppers/"+shopperId+"/address/"+addressId);
								response12.then()
								.assertThat().statusCode(200)
								.log().all();
								
								//Place Order API test
								
								String placeOrderBody = "{\r\n"
										+ "  \"address\": {\r\n"
										+ "  \"addressId\": "+addressId+"\r\n"
										+ "  },\r\n"
										+ "  \"paymentMode\": \"COD\"\r\n"
										+ "}"; 
								
								
								
								Response response13 = given()
										.auth().oauth2(token)
										.body(placeOrderBody)
										.contentType(ContentType.JSON)
										.log().all()
										.when()
										.post("https://www.shoppersstack.com/shopping/shoppers/"+shopperId+"/orders");
										response13.then()
										.assertThat().statusCode(201)
										.log().all();
										int orderId = response13.jsonPath().get("data.orderId");
									
									
									    // Update Order Status API test
										
										.get("https://www.shoppersstack.com/shopping/shoppers/"+shopperId+"/orders/"+orderId+"/invoice");
										   response15.then()
										   .assertThat().statusCode(200)
										   .log().all();
										
										   // Add Review to a Delivered Product API test
										
										   String addReviewBody = "{\r\n"
												+ "  \"description\": \"Looks good, appealing and attractive\",\r\n"
												+ "  \"heading\": \"Amazing Product\",\r\n"
												+ "  \"rating\": 3,\r\n"
												+ "  \"shopperId\":"+shopperId+",\r\n"
												+ "  \"shopperName\": \"Rohit\"\r\n"
												+ "}";
										   
										   Response response18 = given()
													  .auth().oauth2(token)
													  .body(updateReviewBody)
													  .contentType(ContentType.JSON)
													  .log().all()
													  .when()
													  .put("https://www.shoppersstack.com/shopping/reviews/"+reviewId+"?productId="+productId);
													  response18.then()
													  .assertThat().statusCode(200)
													  .log().all();
													
													  // Delete An Added Review API test
													
												      Response response19 = given()
													  .auth().oauth2(token)
													  .contentType(ContentType.JSON)
													  .log().all()
													  .when()
													  .delete("https://www.shoppersstack.com/shopping/reviews/"+reviewId+"?productId="+productId);
													  response19.then()
													  .assertThat().statusCode(200)
													  .log().all();
													
													  // Delete Address API test
													  
													  
													  Response response20 = given()
															  .auth().oauth2(token)
															  .body(sap1)
															  .contentType(ContentType.JSON)
															  .log().all()
															  .when()
															  .delete("https://www.shoppersstack.com/shopping/shoppers/"+shopperId+"/address/"+addressId);
															  response20.then()
															  .assertThat().statusCode(204)
															  .log().all();
															
															
															
															
	
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	/**@Test 
	
	public void vewProducts() {
		
		given()
		 .get("https://www.shoppersstack.com/shopping/products/alpha")
		.then()
		 .assertThat().statusCode(200)
		 .log().all();
	}**/

