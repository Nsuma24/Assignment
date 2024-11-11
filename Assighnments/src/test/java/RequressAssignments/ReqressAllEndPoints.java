package RequressAssignments;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class ReqressAllEndPoints {
	@Test
	public void listUsers() {
	given().get("https://reqres.in/api/users?page=2")
	.then().assertThat().statusCode(200)
	.contentType(ContentType.JSON).log().all();
	
	
}
@Test
public void singleUser() {
	given().get("https://reqres.in/api/users/3")
	.then().assertThat().statusCode(200)
	.contentType(ContentType.JSON).log().all();
}
@Test
public void singleUserNotFound() {
	given().get("https://reqres.in/api/users/23")
	.then().assertThat().statusCode(404)
	.contentType(ContentType.JSON).log().all();
}
@Test
public void listOfResourse() {
	given().get("https://reqres.in/api/unknown")
	.then().assertThat().statusCode(200)
	.contentType(ContentType.JSON).log().all();
}
@Test
public void singleResourse() {
	given().get("https://reqres.in/api/unknown/2")
	.then().assertThat().statusCode(200)
	.contentType(ContentType.JSON).log().all();
}
@Test
public void singleResourseNotFound() {
	given().get("https://reqres.in/api/unknown/23")
	.then().assertThat().statusCode(404)
	.contentType(ContentType.JSON).log().all();
}
@Test
public void create() {
	String obj="{\r\n"
			+ "    \"name\": \"morpheus\",\r\n"
			+ "    \"job\": \"leader\"\r\n"
			+ "}";
	given().contentType(ContentType.JSON)
	.body(obj)
	.post("https://reqres.in/api/users")
	.then().assertThat().statusCode(201)
	.contentType(ContentType.JSON).log().all();
}
@Test
public void update() {
	String obj="{\r\n"
			+ "    \"name\": \"morpheus\",\r\n"
			+ "    \"job\": \"zion resident\"\r\n"
			+ "}";
	given().contentType(ContentType.JSON)
	.body(obj)
	.put("https://reqres.in/api/users/2")
	.then().assertThat().statusCode(200)
	.contentType(ContentType.JSON).log().all();
}
@Test
public void update1() {
	String obj="{\r\n"
			+ "    \"name\": \"morpheus\",\r\n"
			+ "    \"job\": \"zion resident\"\r\n"
			+ "}";
	given().contentType(ContentType.JSON)
	.body(obj)
	.patch("https://reqres.in/api/users/2")
	.then().assertThat().statusCode(200)
	.contentType(ContentType.JSON).log().all();
}
@Test
public void delete() {
	
	given()
	.delete("https://reqres.in/api/users/2")
	.then().assertThat().statusCode(204)
	.log().all();
}
@Test
public void registerSuccessfull() {
	String obj="{\r\n"
			+ "    \"email\": \"eve.holt@reqres.in\",\r\n"
			+ "    \"password\": \"pistol\"\r\n"
			+ "}";
	given().contentType(ContentType.JSON)
	.body(obj)
	.post("https://reqres.in/api/register")
	.then().assertThat().statusCode(200)
	.contentType(ContentType.JSON).log().all();
}
@Test
public void registerUnSuccessfull() {
	String obj="{\r\n"
			+ "    \"email\": \"sydney@fife\"\r\n"
			+ "}";
	given().contentType(ContentType.JSON)
	.body(obj)
	.post("https://reqres.in/api/register")
	.then().assertThat().statusCode(400)
	.contentType(ContentType.JSON).log().all();
}
@Test
public void loginSuccessfull() {
	String obj="{\r\n"
			+ "    \"email\": \"eve.holt@reqres.in\",\r\n"
			+ "    \"password\": \"cityslicka\"\r\n"
			+ "}";
	given().contentType(ContentType.JSON)
	.body(obj)
	.post("https://reqres.in/api/login")
	.then().assertThat().statusCode(200)
	.contentType(ContentType.JSON).log().all();
}


@Test
public void loginUnSuccessfull() {
	String obj="{\r\n"
			+ "    \"email\": \"peter@klaven\"\r\n"
			+ "}";
	given().contentType(ContentType.JSON)
	.body(obj)
	.post("https://reqres.in/api/login")
	.then().assertThat().statusCode(400)
	.contentType(ContentType.JSON).log().all();
}
@Test
public void delayResponse() {
	
	given()
	.get("https://reqres.in/api/users?delay=3")
	.then().assertThat().statusCode(200)
	.contentType(ContentType.JSON).log().all();
}
}
	