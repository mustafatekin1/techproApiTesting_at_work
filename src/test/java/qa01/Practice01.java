package qa01;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Practice01 {

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
            "id": 24,
            "employee_name": "Doris Wilder",
            "employee_salary": 85600,
            "employee_age": 23,
            "profile_image": ""
        }
    ],
    "message": "Successfully! All records has been fetched."
		     
	*/
	
	@Test
	public void practice01 () {
		
	String url = "http://dummy.restapiexample.com/api/v1/employee/15";
	
	Response response = given().when().get(url);
	
	response.prettyPrint();
	
	response.then().
	assertThat().
	statusCode(200).
	contentType(ContentType.JSON).
	body("status", Matchers.equalTo("success")).
	body("message", Matchers.equalTo("Successfully! Record has been fetched."),
	"data.id", Matchers.equalTo(15), "data.employee_name", Matchers.equalTo("Tatyana Fitzpatrick"),
	"data.employee_salary", Matchers.equalTo(385750),
	"data.profile_image", Matchers.equalTo(""));

	
		
	}
	
	
	
	
}
