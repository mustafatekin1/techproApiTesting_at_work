package qa01;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Get09P2 {
/*when () 
 * 	http://dummy.restapiexample.com/api/v1/employee

then()
		status code 200
and()  
		response type format "application/json"   

and ()
	the following user should be in the system
	  {
            "id": 18,
            "employee_name": "Gloria Little",
            "employee_salary": 237500,
            "employee_age": 59,
            "profile_image": ""
        },
and ()
	make sure that "Gloria Little" earns more money than Bradley Greer
	{
            "id": 19,
            "employee_name": "Bradley Greer",
            "employee_salary": 132000,
            "employee_age": 41,
            "profile_image": ""
        }
 * 
 * */
	
	
	@Test
	public void get09P2() {
		
		String url = "http://dummy.restapiexample.com/api/v1/employees";
		
		Response response = given().when().get(url);
		response.prettyPrint();
		
		response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
		
	}
	
	
}
