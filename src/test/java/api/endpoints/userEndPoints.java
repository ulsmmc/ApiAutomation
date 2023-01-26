package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java
//Created for perform Create, Read, Update, Delete request to the use API.

public class userEndPoints {

	public static Response createUSer(User payload) // Bu kismi User pojo clasini urettukten sonra gelip import ettik
	{
		Response response =given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)   // Bu kisim Headers de yaziyor hederleri gondemren gerekiyor
			.body(payload)
		.when()
			.post(Routes.post_url);
		
		return response;
	}
	
	public static Response readUser(String userName)
	{
		Response response =given()
			.pathParam("username", userName)
		.when()
			.get(Routes.get_url);
		
		return response;
	}
	
	public static Response updateUser(String userName, User payload) // should2 parameter one is whic user we want to update 
																	// other one payloud because this is put request.
	{
		Response response =given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)   // Bu kisim Headers de yaziyor hederleri gondemren gerekiyor
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(Routes.get_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName)  // Bunu path param icin birakiyotuz.
	{
		Response response =given()
			.pathParam("username", userName)
		.when()
			.delete(Routes.delete_url);
		
		return response;
	}
	
	
	

}
