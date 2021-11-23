package get_http_request;

import org.junit.Assert;
import org.junit.Test;

import baseUrls.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class Get01_PracticeVideo18 extends DummyBaseUrl {
/*
 given http://dummy.restapiexample.com/api/v1/employee
 When I send GET request to the url
 then 
   http status code should be 200
 and 
   content type should be JSON
 and make sure that the salary of Rhona Davidson is more than Herrod Chandler  
 
 */
	
	@Test
	public void get01_PracticeVideo18() {
		
		//spec.pathParams("first", "api","second", "v1", "third", "employee", "final", 7);
		//http://dummy.restapiexample.com için spec(spec) hata veriyor. url direkt kullan
		
		String url = "http://dummy.restapiexample.com/api/v1/employee/7"; 
		Response response = given().when().get(url);
		response.prettyPrint();
	
		response.then().assertThat().statusCode(200);
		
		JsonPath json = response.jsonPath();
		System.out.println(json);//io.restassured.path.json.JsonPath@7d0614f
		
	int herrodSalary = json.getInt("data.employee_salary");
		System.out.println(herrodSalary);
		
		String url2 = "http://dummy.restapiexample.com/api/v1/employee/8";
		Response response2 = given().when().get(url2);
		response2.prettyPrint();	
		
		JsonPath json2 = response2.jsonPath();
		
		int rhonaSalary = json2.getInt("data.employee_salary");	
		System.out.println(rhonaSalary);
		
		Assert.assertTrue(rhonaSalary>herrodSalary);
	}





}
