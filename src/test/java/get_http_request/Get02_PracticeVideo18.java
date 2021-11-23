package get_http_request;

import org.junit.Assert;
import org.junit.Test;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojos.JsonPlacePojo;

import static io.restassured.RestAssured.*;

import java.util.List;

public class Get02_PracticeVideo18 extends JsonPlaceHolderBaseUrl{

	/*
	given 	https://jsonplaceholder.typicode.com/comments
	When 	I send GET request to the url
 	then 
   			http status code should be 200
 	and 
   		search all ids that are less than 30 and list them
   	and
   		assert that the number of ids less than 30 is 29
   		
   We have two ways:
   - JsonPath then groomy language >> list>>loop ...
   - Pojo		
	 */
	
	@Test
	public void get02_PracticeVideo18 () { 
		
		spec.pathParam("first", "comments");
		
		Response response = given().spec(spec).when().get("/{first}");
		//response.prettyPrint();
		
		response.then().assertThat().statusCode(200);
		
		JsonPath json = response.jsonPath();
		
		List <Integer> ids = json.getList("findAll{it.id}.id");//gives all ids; "findAll{it.id}.id": Groomy language 
															// 			it.id => is like "this.id" in constructors
		System.out.println(ids);
	List<Integer> idsLessThan30 = json.getList("findAll{it.id<30}.id");
	System.out.println(idsLessThan30);
	
	Assert.assertTrue(idsLessThan30.size()==29);

	}
 
	@Test
	public void get02_PracticeWithPojoVideo18() {
		spec.pathParam("first", "comments");
		
		Response response = given().spec(spec).when().get("/{first}");
		//response.prettyPrint();
		
		response.then().assertThat().statusCode(200);
		
		JsonPlacePojo [] pojo1 = response.as(JsonPlacePojo[].class);//we use this syntax [] because that is array 
		System.out.println(pojo1);
		
//		for (int i = 0; i<pojo1.length; i++) {
//		System.out.println("ids " + pojo1[i].getId());	// prints all ids 
//		}
		
		int counter= 0;
		for (int i = 0; i<pojo1.length; i++) {
			if(pojo1[i].getId()<30) {
				System.out.println("index number: " + i + "id ==> " + pojo1[i].getId());
				counter++;
			};	
		}
		
		Assert.assertTrue(counter==29);

	}
}
