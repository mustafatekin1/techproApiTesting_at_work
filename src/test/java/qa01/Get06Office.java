package qa01;

import static org.junit.Assert.assertEquals;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class Get06Office {

	/*
	 when()
		I send a request to the url: "http://restful-booker.herokuapp.com/booking/1" 
then()
		status code 200'dur 
and()  
		content type  "application/json"   

and() 
	there should be the following object
		{
  {
    "firstname": "Mary",
    "lastname": "Ericsson",
    "totalprice": 332,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2016-05-19",
        "checkout": "2019-04-07"
    }
		}

*/
	
	@Test
	public void get06() {
		String url = "http://restful-booker.herokuapp.com/booking/1";
		Response response = given().when().get(url);
		response.prettyPrint();
		response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
		body("firstname", Matchers.equalTo("Jim"),
				"lastname", Matchers.equalTo("Brown"),
				"totalprice", Matchers.equalTo(208),
				"depositpaid", Matchers.equalTo(true)
			);

	//2nd way, using jsonPath
		
		JsonPath json = response.jsonPath();
		assertEquals("Jim", json.getString("firstname"));
		assertEquals("Brown", json.getString("lastname"));
		assertEquals(208, json.getInt("totalprice"));
		assertEquals(true, json.getBoolean("depositpaid"));
	//Important: json.getInt or json.getBoolean ... should match the datatype!
	
	/*3rd way: Soft Assertion (it is in testng)
		1. create a SoftAssert object
		2. do assertion by using assert methods
		3. use the method: assertAll()   
			// do not forget this. Otherwise you get green but actually you did not test right. 
			// not to forget, type this method at the beginning.	
	*/
	
		SoftAssert soft = new SoftAssert();
		
		soft.assertEquals(json.getString("firstname"), "Jim", "No matching data");
		soft.assertEquals(json.getString("lastname"), "Brown", "No matching data");
		soft.assertEquals(json.getInt("totalprice"), 208, "No matching data");
		soft.assertEquals(json.getBoolean("depositpaid"), true, "No matching data");
		
		soft.assertAll();
	}
	
	

}
