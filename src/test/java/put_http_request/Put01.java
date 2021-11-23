package put_http_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import com.google.gson.Gson;

import baseUrls.JsonPlaceHolderBaseUrl;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {
    /*
   Put is used to fully update a data
   Given ()
      https://jsonplaceholder.typicode.com/todos/198
    and()  the following data
         {
             "userId": 21,
             "title": "wash the dishes",
             "completed": false
         }

    when()
         send the url and the data above

    then()
         status code 200
    and()
            response body is as follows:
            {
             "userId": 21,
             "title": "wash the dishes",
             "completed": false
             }
    */
@Test
    public void put01(){
   // 1 set the url
    spec.pathParams("first","todos","second",198);
    // 2 set the request body (we form the method in the test data package and then create an object using that class constructor)
    JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
    Map<String, Object> requestBodyMap = requestBody.requestBodySetup(21, "wash the dishes", false);

    // 3 send the request with body and get the response
    Response response = given().spec(spec).contentType(ContentType.JSON).
            body(requestBodyMap).when().put("/{first}/{second}");
    
    response.prettyPrint();
    
    // do the assertions
// 1st way by using response... assertThat()
    response.then().assertThat().statusCode(200).
            body("userId", equalTo(requestBodyMap.get("userId")),
                    "title", equalTo(requestBodyMap.get("title")),
                    "completed", equalTo(requestBodyMap.get("completed")) );

// 2nd way by using GSON then with assertEquals() -- more common and preferred way 
    Map<String, Object> actualDataMap = response.as(HashMap.class); // we first get the json data and convert it to the map (de-serialisation)
    								// we have another option for de-serialisation with object-mapper(I think we will learn later)
    assertEquals(requestBodyMap.get("userId"), actualDataMap.get("userId"));
    assertEquals(requestBodyMap.get("title"), actualDataMap.get("title"));
    assertEquals(requestBodyMap.get("completed"), actualDataMap.get("completed"));
   //assertEquals(requestBodyMap.get("id"), actualDataMap.get("id"));// we mostly do not assert for id because the API itself creates it.
    																		// Thats why test mostly fails.
	
 // Task is over.
    /* Lets learn serialisation 
      How to use GSON in serialisation: Java Object => Json Data
        */
    Map<String, Object> ages = new HashMap<>();// HashMap orders randomly so it is fast
    ages.put("Ali", 13);
    ages.put("Jack", 20);
    ages.put("Mike", 11);
    ages.put("Ayse", 15);
    
    System.out.println("This is Map data:  " + ages);//{Mike=11, Ayse=15, Jack=20, Ali=13}
  
    //1.step we create Gson object
    Gson gson = new Gson(); 
    
    //2. step we convert Java Object to json data by using "gson.toJson(Java Map Object)"     
    String agesJsonFromMap = gson.toJson(ages); // we can easily see that we get String from this method via (ctrl>> Open Declaration)
    System.out.println("This is json data: " + agesJsonFromMap); //{"Mike":11,"Ayse":15,"Jack":20,"Ali":13}
    
    
    
    
}


}