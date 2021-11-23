package get_http_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.junit.Test;

import baseUrls.HerOkuAppBaseUrl;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
;
public class Get01_Pojo extends HerOkuAppBaseUrl {

    /*
In API testing we will use Gherkin Language.
We use some keywords:
-	given, when, then, and.
We will form test cases with these four keywords.
-	given : prerequisites
-	when : is used to declare actions
-	then : is used for outputs
-	and : is used to combine multiple given, when or then.

example:
given
    https:"https://restful-booker.herokuapp.com/booking/2"
when
    user send a GET request to the url
then
    HTTP status code should be 200
and
    content type should be JSON
and
    status line should be HTTP/1.1 200 OK (note that this is case sensitive. http failed)
{
    "firstname": "Mark",
    "lastname": "Jackson",
    "totalprice": 932,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2021-02-13",
        "checkout": "2021-08-07"
    }
}


 */

    /*
     * to automate test case
     * we have to create a method for every test case
     * */
@Test
    public void get01_Pojo() {
    //1 set the url
    spec.pathParams("first", "booking","second",2);

    //2 set the expected data or request body (if there is - here no request body because it is get)
    BookingDatesPojo bookingDates = new BookingDatesPojo("2019-03-09","2019-04-05");
    System.out.println(bookingDates); // BookingDatesPojo{checkin='2019-10-26', checkout='2020-06-26'}

    BookingPojo booking = new BookingPojo("Jim","Jones", 381, false, bookingDates, "juice111");
    System.out.println(booking);

    //3 send the request and get the response
    Response response = given().spec(spec).when().get("/{first}/{second}");
response.prettyPrint();

//4. do the assertion
    //1st way by using body
    response.then().assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK").contentType(ContentType.JSON).
            body("firstname", equalTo(booking.getFirstname()),
                    "lastname", equalTo(booking.getLastname()),
                    "totalprice", equalTo(booking.getTotalprice()),
                    "depositpaid", equalTo(booking.getDepositpaid()),
                    "bookingdates.checkin", equalTo(bookingDates.getCheckin()),
                    "bookingdates.checkout", equalTo(bookingDates.getCheckout()));


// 2nd way by using Map (converted with Gson from json to Java Map) within assertEquals() 
    Map<String, Object> responseMap = response.as(HashMap.class);
    assertEquals("firstname does not match", booking.getFirstname(), responseMap.get("firstname") );
    assertEquals("lastname does not match",booking.getLastname(), responseMap.get("lastname") );
    assertEquals("totalprice does not match",booking.getTotalprice(), responseMap.get("totalprice") );
    assertEquals("depositpaid does not match",booking.getDepositpaid(), responseMap.get("depositpaid") );
    assertEquals("checkin date does not match",bookingDates.getCheckin(), ((Map)responseMap.get("bookingdates")).get("checkin") );
    assertEquals("firstname does not match",bookingDates.getCheckout(), ((Map)responseMap.get("bookingdates")).get("checkout") );
    
 //3rd way by creating pojo object from pojo class - the most preferred way
    //(converted to a Pojo object that Java can understand and easily manipulate -- this time by using pojo class)
    BookingPojo actualData = response.as(BookingPojo.class);
    System.out.println(actualData);//BookingPojo{firstname='Sally', lastname='Ericsson' ...
	
    assertEquals(200, response.getStatusCode());
    assertEquals(booking.getFirstname(), actualData.getFirstname() );
    assertEquals(booking.getLastname(), actualData.getLastname() );
    assertEquals(booking.getTotalprice(), actualData.getTotalprice() );
    assertEquals(booking.getDepositpaid(), actualData.getDepositpaid() );
    //We can see below that no need to use many paranthesis ((Map) ... with pojo. It is a lot easier to type and read. 
    assertEquals(booking.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin() );
    assertEquals(booking.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout() );
  
}
}

