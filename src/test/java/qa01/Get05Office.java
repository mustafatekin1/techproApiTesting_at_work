package qa01;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Get05Office {
	/*
	 when()
		I send a request to the url: http://restful-booker.herokuapp.com/booking 
then()
		status code 200'dur 
and()  
		content type  "application/json"   

and() 
	there should be a person with firstname is "Eric", lastname is "Jackson"

	ex object: 
		{
    "firstname": "Eric",
    "lastname": "Jackson",
    "totalprice": 819,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2018-12-27",
        "checkout": "2021-04-21"
    			}
		}

*/

@Test
public void get05 () {
	
	/*firstname is "Eric", lastname is "Jackson" are specific data (just a single cell, not a record)
	for specific item (not a category or subcategory). That's why we use queryParams for this part.
	 When we check the queryParams (key-value) in Postman we can easily get the syntax:
	http://restful-booker.herokuapp.com/booking?firstname=Eric&lastname=Jackson
	
	So, if I could manage spec it would be as follows:
	
	spec.pathParam("first", "booking");//first the pathParam or pathParams
	spec.queryParams("firstname","Mary", "lastname", "Brown");//parameter names must be completely the same in queryParams
	Response response = given().spec(spec).when().get("/{first});//there is no need to type anything for queryParams inside get()
	
	*/
	String url = "http://restful-booker.herokuapp.com/booking/?firstname=Mark&lastname=Ericsson";
	Response response = given().when().get(url);
	
	response.prettyPrint();
	
	response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
	
	assertTrue("No matching data", response.asString().contains("bookingid") );
	//Here we set the test logic as follows:
	//if we get "bookingid" in the response body, it means that the searched "firstname" and "lastname" exists.
	//so we type 
	//assertTrue if we get "bookingid" in the body, otherwise give the message



}

	
}