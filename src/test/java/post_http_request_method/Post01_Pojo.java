package post_http_request_method;

import static org.junit.Assert.assertEquals;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.junit.Test;

import baseUrls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.BookingDatesPojo2;
import pojos.BookingPojo2;

import static io.restassured.RestAssured.*;

public class Post01_Pojo extends HerOkuAppBaseUrl {
/*
 
  given
    https:"https://restful-booker.herokuapp.com/booking"
  and the following data
		 	{
			"firstname": "Ali",
			"lastname": "Can",
			"totalprice": 1000,
			"depositpaid": true,
			"bookingdates": {
			    "checkin": "2013-02-23",
			    "checkout": "2014-10-23"
			},
		"additionalneeds": "juice"
		}
	when
    user send a "post" request (with body) to the url
    
then
    HTTP status code should be 200
and
    content type should be JSON
and
    status line should be HTTP/1.1 200 OK (note that this is case sensitive. http failed)
 and the body should be as follows 
	 {
	"firstname": "Ali",
	"lastname": "Can",
	"totalprice": 1000,
	"depositpaid": true,
	"bookingdates": {
	    "checkin": "2013-02-23",
	    "checkout": "2014-10-23"
	},
	"additionalneeds": "juice"
	}
 * */

	@Test
	public void Post01_Pojo() {
		// 1.  set the url
		spec.pathParam("first", "booking");
		// 2. set the expected data
		BookingDatesPojo2 expectedBookingDate = new BookingDatesPojo2("2013-02-23","2014-10-23");
		System.out.println(expectedBookingDate);
		
		BookingPojo2 expectedData = new BookingPojo2("Ali", "Can", 1000, true, expectedBookingDate, "juice");
		System.out.println(expectedData);
		// 3. send the request with body and get the response
		Response response = given().spec(spec).contentType(ContentType.JSON).
				body(expectedData).
				when().post("/{first}");
		response.prettyPrint();
		
		// 4. do the assertions 
		// lets just use pojo: We have expectedData as pojo created from BookingPojo2
		//					: we also create a pojo object from the response body usıng the same class. 
		// do not forget to add @JsonIgnoreProperties(ignoreUnknown = true) just before the class names of the pojo classes
		
		BookingPojo2 actualData = response.as(BookingPojo2.class);
		System.out.println(actualData);
		assertEquals("firstname does not match", expectedData.getFirstname(), actualData.getFirstname()); 
		assertEquals("lastname does not match",expectedData.getLastname(), actualData.getLastname()); 
		assertEquals("totalprice does not match",expectedData.getTotalprice(), actualData.getTotalprice()); 
		assertEquals("additionalneeds does not match",expectedData.getAdditionalneeds(), actualData.getAdditionalneeds()); 
		assertEquals("checkin date does not match",expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin()); 
		assertEquals("checkout date does not match",expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin()); 

		System.out.println(expectedData);
	}

}
