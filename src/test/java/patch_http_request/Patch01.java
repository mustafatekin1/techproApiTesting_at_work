package patch_http_request;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import test_data.JsonPlaceHolderTestData;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl{
/*
 Patch just changes part of the data
 Put changes the whole data. Please note that it may cause some problems:
 	- when we "put" the request with some missing data, the corresponding data is updated and missing data is DELETED. 
 	- When we "patch" the request with  missing data, the corresponding data is updated and missing data is kept (NOT DELETED)
   
   Given ()
      https://jsonplaceholder.typicode.com/todos/198
    and()  the following data
         {
             
             "title": "tidy your house",
             
         }

    when()
         send the url and the data above to update just the above data (use Patch() )

    then()
         status code 200
    and()
            response body is as follows:
            {
             "userId": 10,
             "title": "tidy your house",
             "completed": true,
             "id": 198
             }
  */

	@Test
	public void patch01() {
	//1. set the url
	spec.pathParams("first", "todos", "second", 198);
	
	//2. Set the expected data (we create object from test data package>>test data)
		// I create a new method with the name patchRequest() in the testdata package. 
	JsonPlaceHolderTestData patchBody = new JsonPlaceHolderTestData();
	Map<String, Object> patchBodyMap = patchBody.patchRequest("tidy your house");
	System.out.println(patchBodyMap); //{title=tidy your house}
	
	//3. send the request and the body and get the response
	Response response = given().spec(spec).contentType(ContentType.JSON).
			body(patchBodyMap).when().patch("/{first}/{second}");
	response.prettyPrint();
	
	//4. do the assertion
	//1st way with response body
	
	response.then().assertThat().statusCode(200).
	body("10", Matchers.equalTo(patchBodyMap.get("userId")),
			"198", Matchers.equalTo(patchBodyMap.get("id")),
			"title", Matchers.equalTo(patchBodyMap.get("title")),
			"true", Matchers.equalTo(patchBodyMap.get("completed")));
	
	//2nd way with Gson
	
	Map<String, Object> responseBodyMap = response.as(HashMap.class);
	System.out.println(responseBodyMap);//{id=198, completed=true, title=tidy your house, userId=10}
	
	assertEquals(patchBodyMap.get("title"), responseBodyMap.get("title"));
	assertEquals(10, responseBodyMap.get("userId"));
	assertEquals(true, responseBodyMap.get("completed"));
	
	}
}
