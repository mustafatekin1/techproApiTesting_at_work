package qa01;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class Practice03 {
	/*
	 	 when()
	 		I send a request to the url: http://dummy.restapiexample.com/api/v1/employee 
	 then()
	 		status code 200'dur 
	 and()  
	 		content type  "application/json"   
	 
	 and()  the following messages and info should be got
		
	
	{
	"status": "success",
	"data": {
        "id": 9,
        "employee_name": "Colleen Hurst",
        "employee_salary": 205500,
        "employee_age": 39,
        "profile_image": ""
    },
	"message": "Successfully! All records has been fetched."
	}
	
	*/

	@Test
	public void practice03() {
		
		String url = "http://dummy.restapiexample.com/api/v1/employee/9";
		Response response = given().when().get(url);
		
		response.prettyPrint();
		
		response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
		body("status", Matchers.equalTo("success"),
			"data.id", Matchers.equalTo(9),
			"data.employee_name", Matchers.equalTo("Colleen Hurst"),
			"data.employee_salary", Matchers.equalTo(205500),
			"data.employee_age", Matchers.equalTo(39),
		"message", Matchers.equalTo("Successfully! Record has been fetched."));
		
		
		
		
	}
	
	
	
	
	
}
