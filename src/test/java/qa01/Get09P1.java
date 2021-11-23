package qa01;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class Get09P1 {
	/*
	 when()
		I send a request to the url: https://jsonplaceholder.typicode.com/todos
	and ()
		accept type is "application/json"
then()
		status code 200
and()  
		response type format "application/json"   

and() 
	there should be 200 todos

and ()
	"numqum repellendus a magnam" is one of the todos
	
and ()
	194, 192, 186 should be among the id

*/

	@Test
	public void get06P1() {
		
		String url = "https://jsonplaceholder.typicode.com/todos";
		
		Response response = given().accept(ContentType.JSON).when().get(url);
		response.prettyPrint();
				
		response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
		body("id", Matchers.hasItems(186, 194, 192),
				"id", Matchers.hasSize(200),
				"title", Matchers.hasItem("numquam repellendus a magnam"));
		
	}
	
	
}
