package qa01;

import org.hamcrest.Matchers;
import org.junit.Test;

import baseUrls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.*;
import static io.restassured.RestAssured.*; 

public class Practice02 extends DummyBaseUrl {

	/*
	 
	 when()
	 		I send a request to the url: http://dummy.restapiexample.com/api/v1/employees 
	 then()
	 		status code 200'dur 
	 and()  
	 		content type  "application/json"   
	 
	 and()  
		this user exists in the system:
		{
        "id": 15,
        "employee_name": "Tatyana Fitzpatrick",
        "employee_salary": 385750,
        "employee_age": 19,
        "profile_image": ""
	  }
	  
	  and()
	  	there should be the following employees:
	  	"Doris Wilder", "Bradley Greer", "Jenette Caldwell"
	  
	*/
	
	@Test
	public void practice02() {
		
		
		//I could not manage to use spec. Normally I should use this code:
		//spec.pathParams ("first", "api", "second", "v1", "third", "employees"); 
		//Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
		String url = "http://dummy.restapiexample.com/api/v1/employees";
		Response response = given().when().get(url);
		
		response.prettyPrint();
		
		response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
		body("data.id", Matchers.hasItem(1),
				"data.employee_name", Matchers.hasItem("Tiger Nixon"),
				"data.employee_salary", Matchers.hasItem(320800)).
		body("data.employee_name", Matchers.hasItems("Jenette Caldwell","Bradley Greer","Doris Wilder"));
		
		
	}
	
	
	
	
}
