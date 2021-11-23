package qa01;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

import baseUrls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Test01 extends DummyBaseUrl {

	@Test
	public void test01() {
//1. set the url
		//http://dummy.restapiexample.com/api/v1/employees		
	//spec.pathParams("first", "api", "second", "v1", "third", "employees");	
String url = "https://restful-booker.herokuapp.com/booking/3";	

//2. set the expected data-later
	
//3. send the request and get the response
	Response response = given().when().get(url);
	// please note that in Eclipse I can not import the required restassured for given() and others
	//That's why I type the import myself:
	//import static io.restassured.RestAssured.*; (we change .given; with .*; in Intellij- I made the same here)
	
	response.prettyPrint();
	System.out.println(response.asString().contains("Mary"));
		
//4. Do the assertion		,
	
	response.
	then().
	assertThat().
	statusCode(200).
	contentType(ContentType.JSON).
	body("firstname", Matchers.equalTo("Mary"), 
		"bookingdates.checkin", Matchers.equalTo("2016-05-10"));
		
	}
	
}
