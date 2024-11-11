package shopperStackProject;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class WishlistTest {
	
	/**
	
	@Test
	 
	public void wishList() {
		
		JSONObject jObj = new JSONObject();
		jObj.put("productId", )
		jObj.put("quantity", jObj)
		
		given()
		  .contentType(ContentType.JSON)
		  .body(null)
		.when()
		  .post("https://www.shoppersstack.com/shopping/shoppers/{shopperId}/wishlist")
		  
		.then()
		  .assertThat().statusCode(201)
		  .log().all();
		
	}**/
	
	public static  int ShopperId;
	public static String jwtToken;
	public static String firstName;
	public static String lastName;
	public static String email="sampleCreatedemail5@gmail.com";
	public static String password="DummyFather01*";
	public static String phone;
	public static int productId;
	public static int itemId;
	public static int addressId;
	public static int orderId;
	
	@Test
	public void createShopper()
	{/*
		
		//REGISTER SHOPPER
		
		
		Random r=new Random();
		int rr=r.nextInt(100);
		
		ShopperPojoclass obj=new ShopperPojoclass("Bangalore", "India", "sampleCreatedemail"+rr+"@gmail.com", "Dummy_"+rr, "FEMALE", "Father", "DummyFather01*", 9876543257L, "Karnataka", "ALPHA");
		Response resp=given()
		    .contentType(ContentType.JSON)
		    .body(obj)
		.when()
		   .post("https://www.shoppersstack.com/shopping/shoppers");
        resp.then().log().all();
        resp.then().assertThat().statusCode(201);
    
       //resp.then().time(Matchers.both(Matchers.lessThan(300L)).and(Matchers.greaterThan(1000L)));
		resp.then().statusLine("HTTP/1.1 201 ");
		firstName = resp.jsonPath().get("data.firstName");
		System.out.println(firstName);
		lastName = resp.jsonPath().get("data.lastName");
		System.out.println(lastName);
		email = resp.jsonPath().get("data.email");
		System.out.println(email);
		String message = resp.jsonPath().get("message");
		System.out.println(message);

		ShopperId = resp.jsonPath().get("data.userId");
		System.out.println(ShopperId);
		resp.then().assertThat().body("message", Matchers.equalTo("Created"));
		resp.then().assertThat().body("data.firstName", Matchers.equalTo(firstName));
		resp.then().assertThat().body("data.lastName", Matchers.equalTo(lastName));
		*/

		
         //LOGIN
	
			JSONObject jobj = new JSONObject();
			jobj.put("email", email);
			jobj.put("password", "DummyFather01*");
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
		
		
		// GET PRODUCT DETAILS
		
		Response productres = given(). auth().oauth2(jwtToken).when().get("https://www.shoppersstack.com/shopping/products/alpha");
		productres.then().log().all();
		int productId = JsonPath.read(productres.asString(), "data[1].productId");
		productres.then().assertThat().statusCode(200);
		//productres.then().assertThat().time(Matchers.both(Matchers.lessThan(1000L)).and(Matchers.greaterThan(400L)));
		productres.then().assertThat().body("message", Matchers.equalTo("Success"));
		
		
		System.out.println("======Add product to wishlist=======");
		
		
		 // ADD  PRODUCT TO WISHLIST
			String wishlistreqBody = "{\r\n" + "  \"productId\": " + productId + ",\r\n" + "  \"quantity\": 2\r\n"
					+ "}";

			Response addtowishlistresp = given(). auth().oauth2(jwtToken).contentType(ContentType.JSON).body(wishlistreqBody).when()
					.post("https://www.shoppersstack.com/shopping/shoppers/" + ShopperId + "/wishlist");
			addtowishlistresp.then().log().all();
			addtowishlistresp.then().assertThat().body("data.productId", Matchers.equalTo(productId));
			addtowishlistresp.then().assertThat().statusCode(201);
			addtowishlistresp.then().assertThat().body("message", Matchers.equalTo("Created"));
			//addtowishlistresp.then().assertThat()
					//.time(Matchers.both(Matchers.lessThan(1000L)).and(Matchers.greaterThan(400L)));
			
			System.out.println("======get product from wishlist=======");
			
	  // GET PRODUCT FROM WISHLIST
			
		Response wishlistres = given(). auth().oauth2(jwtToken).when()
				.get("https://www.shoppersstack.com/shopping/shoppers/" + ShopperId + "/wishlist");
		wishlistres.then().log().all();
		wishlistres.then().statusCode(200);
		//wishlistres.then().assertThat().time(Matchers.both(Matchers.lessThan(1000L)).and(Matchers.greaterThan(400L)));
		wishlistres.then().assertThat().body("message", Matchers.equalTo("Success"));

		System.out.println("======delete product from wishlist=======");
		
		
	//DELETE PRODUCT FROM WISHLIST

	Response wishlistdeleteres = given(). auth().oauth2(jwtToken).when()
			.delete("https://www.shoppersstack.com/shopping/shoppers/" + ShopperId + "/wishlist/" + productId);
	wishlistdeleteres.then().log().all();
	wishlistdeleteres.then().statusCode(204);
	//wishlistdeleteres.then().assertThat().time(Matchers.both(Matchers.lessThan(1000L)).and(Matchers.greaterThan(400L)));
	
	System.out.println("======add to cart=======");
	
	//CART
	String addtocartReq="{\r\n" + "  \"productId\": " + productId + ",\r\n" + "  \"quantity\": 2\r\n"
			+ "}";

		
	Response addtoCartresp = given(). auth().oauth2(jwtToken).contentType(ContentType.JSON).body(addtocartReq).when()
			.post("https://www.shoppersstack.com/shopping/shoppers/" + ShopperId + "/carts");
	addtoCartresp.then().log().all();
	addtoCartresp.then().assertThat().body("data.productId", Matchers.equalTo(productId));
	addtoCartresp.then().assertThat().statusCode(201);
	addtoCartresp.then().assertThat().body("message", Matchers.equalTo("Created"));
	//addtoCartresp.then().assertThat().time(Matchers.both(Matchers.lessThan(1000L)).and(Matchers.greaterThan(400L)));
	 itemId=addtoCartresp.jsonPath().get("data.itemId");
	
	 System.out.println("======get product from cart=======");
	 
	//GET  PRODUCT FROM CART
	Response  getProductfromcartResp=given()
			. auth().oauth2(jwtToken)
			          .when().get("https://www.shoppersstack.com/shopping/shoppers/"+ShopperId+"/carts");
	
	getProductfromcartResp.then().log().all();
	getProductfromcartResp.then().statusCode(200);
	getProductfromcartResp.then().assertThat().body("message", Matchers.equalTo("Success"));
	//getProductfromcartResp.then().assertThat().time(Matchers.both(Matchers.lessThan(1000L)).and(Matchers.greaterThan(400L)));
	getProductfromcartResp.then().assertThat().body("data[0].productId", Matchers.equalTo(productId));
	
	System.out.println("======update product in cart=======");
	
	
	// UPDATE PRODUCT IN CART
	
	String updatecartreqBody="{\r\n"
			+ "  \"productId\": "+productId+",\r\n"
			+ "  \"quantity\": 5\r\n"
			+ "}";
	
	Response updatecartresp=given()
			.contentType(ContentType.JSON)
			.body(updatecartreqBody)
			. auth().oauth2(jwtToken)
		.when()
		  .put("https://www.shoppersstack.com/shopping/shoppers/"+ShopperId+"/carts/"+itemId);
	updatecartresp.then().log().all();
	updatecartresp.then().statusCode(200);
	updatecartresp.then().assertThat().body("message", Matchers.equalTo("Data Updated"));
	//updatecartresp.then().assertThat().time(Matchers.both(Matchers.lessThan(1000L)).and(Matchers.greaterThan(400L)));
	updatecartresp.then().assertThat().body("data.quantity", Matchers.equalTo(5));
	
	
	System.out.println("======add address  details=======");
	
	
	// ADD ADDRESS
	JSONObject jsonobj=new JSONObject();
	jsonobj.put("addressId", 0);
	jsonobj.put("buildingInfo","21,3rd floor");
	jsonobj.put("city", "Bangalore");
	jsonobj.put("country", "India");
	jsonobj.put("landmark", "Above smart Bazzar");
	jsonobj.put("name","GopalanCo works");
	jsonobj.put("phone", "9876543210");
	jsonobj.put("pincode", "560010");
	jsonobj.put("state","karnataka");
	jsonobj.put("streetInfo","12th main");
	jsonobj.put("type", "office");
	Response addaddressresp=given()
			              .contentType(ContentType.JSON)
			              .body(jsonobj)
			              . auth().oauth2(jwtToken)
			         .when()
			            .post("https://www.shoppersstack.com/shopping/shoppers/"+ShopperId+"/address");
	
	addaddressresp.then().log().all();
	addaddressresp.then().statusCode(201);
	addaddressresp.then().assertThat().body("message", Matchers.equalTo("Created"));
	//addaddressresp.then().assertThat().time(Matchers.both(Matchers.lessThan(1000L)).and(Matchers.greaterThan(400L)));	
	 addressId=addaddressresp.jsonPath().get("data.addressId");
	 
	 System.out.println("======get all address details=======");
	 
	 //GET ALL ADDRESS
	 
	 Response getAlladdressresp=given(). auth().oauth2(jwtToken).when()
			 .get("https://www.shoppersstack.com/shopping/shoppers/"+ShopperId+"/address");
	 getAlladdressresp.then().log().all();
	 getAlladdressresp.then().statusCode(200);
	// getAlladdressresp.then().assertThat().time(Matchers.both(Matchers.lessThan(1000L)).and(Matchers.greaterThan(400L)));
	 
	 
	 System.out.println("======get particular address details=======");
	 
	 //GET PARTICULAR ADDRESS
	 
	 Response getparticluaraddressresp=given(). auth().oauth2(jwtToken).when()
			 .get("https://www.shoppersstack.com/shopping/shoppers/"+ShopperId+"/address/"+addressId);
	 getparticluaraddressresp.then().log().all();
	 getparticluaraddressresp.then().statusCode(200);
	// getparticluaraddressresp.then().assertThat().time(Matchers.both(Matchers.lessThan(1000L)).and(Matchers.greaterThan(400L)));
	 
	 System.out.println("====== place order=======");		           
	 
    //ORDER
	 
	 String placeOrderReq="{\r\n"
	 		+ "  \"address\": {\r\n"
	 		+ "    \"addressId\": "+addressId+",\r\n"
	 		+ "    \"buildingInfo\": \"string\",\r\n"
	 		+ "    \"city\": \"string\",\r\n"
	 		+ "    \"country\": \"string\",\r\n"
	 		+ "    \"landmark\": \"string\",\r\n"
	 		+ "    \"name\": \"string\",\r\n"
	 		+ "    \"phone\": \"string\",\r\n"
	 		+ "    \"pincode\": \"string\",\r\n"
	 		+ "    \"state\": \"string\",\r\n"
	 		+ "    \"streetInfo\": \"string\",\r\n"
	 		+ "    \"type\": \"string\"\r\n"
	 		+ "  },\r\n"
	 		+ "  \"paymentMode\": \"COD\"\r\n"
	 		+ "}";
	 Response placeOrderResp=given().contentType(ContentType.JSON)
			 .body(placeOrderReq)
			 . auth().oauth2(jwtToken)
		.when()
		  .post("https://www.shoppersstack.com/shopping/shoppers/"+ShopperId+"/orders");
	 placeOrderResp.then().log().all();
	 placeOrderResp.then().statusCode(201);
	  orderId=placeOrderResp.jsonPath().get("data.orderId");
	 placeOrderResp.then().assertThat().body("message", Matchers.equalTo("Created"));
	 
	 
	 System.out.println("======order history=======");
	 
	 // GET ORDER HISTORY
	 
	 Response OrderHistoryResp=given()
			 .auth().oauth2(jwtToken)
		.when()
		  .get("https://www.shoppersstack.com/shopping/shoppers/"+ShopperId+"/orders");
	 placeOrderResp.then().log().all();
	 placeOrderResp.then().statusCode(201);

	 placeOrderResp.then().assertThat().body("message", Matchers.equalTo("Created"));
	 
	 System.out.println("======update order status=======");
	 
	 // UPDATE ORDER STATUS
	 
	 Response orderUpdateResp=given()
			. auth().oauth2(jwtToken).
			 queryParam("status","DELIVERED")
			
		.when()
		  .patch("https://www.shoppersstack.com/shopping/shoppers/"+ShopperId+"/orders/"+orderId);
	 placeOrderResp.then().log().all();
	 placeOrderResp.then().statusCode(201);
	 
	 placeOrderResp.then().assertThat().body("message", Matchers.equalTo("Created"));
	 
	 System.out.println("======generate invoice=======");
	 
	 // GENERATE INVOICE
	 
	 
	 Response generateInvoiceresp=given().
			 auth().oauth2(jwtToken).
			 when().get("https://www.shoppersstack.com/shopping/shoppers/"+ShopperId+"/orders/"+orderId+"/invoice");
	 generateInvoiceresp.then().log().all();
	 generateInvoiceresp.then().statusCode(200);
	generateInvoiceresp .then().assertThat().body("message", Matchers.equalTo("OK"));
	 
	  
		
	    }
}
	
	
	
	
	
	
	
	
	
	
	

}
