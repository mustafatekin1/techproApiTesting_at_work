package get_http_request;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.Test;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;

public class Get03_PracticeVideo18 extends JsonPlaceHolderBaseUrl{
	/*
	given 	https://jsonplaceholder.typicode.com/comments
	When 	I send GET request to the url
 	then 
   			http status code should be 200
 	and 
		user can see following emails in the system
	*/
	
	@Test
	public void Get03_PracticeVideo18 () {
		
		spec.pathParam("first", "comments");
		
		Response response = given().spec(spec).when().get("/{first}");
		//response.prettyPrint();
		
		response.then().assertThat().statusCode(200).
		body("email", Matchers.hasItems("Zola@lizzie.com", "Dolly@mandy.co.uk", "Davion@eldora.net"));
	}
	
	
}
