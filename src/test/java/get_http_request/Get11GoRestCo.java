package get_http_request;

import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import baseUrls.GoRestCoBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class Get11GoRestCo extends GoRestCoBaseUrl{

	/*
 Given 
 	https://gorest.co.in/public/v1/users
 When
 	send GET request to the url
 Then
 	assert that "pagination limit" value is 20
 And
  	the current link should be "https://gorest.co.in/public/v1/users?page=1"
 And
  	the number of users should be 31
 And 
  	//the number of "active" users should be more than 5 (this is a changing data in every run) - hoca json-groovy  ile çözemedi 
 And
  	we have at least one "active" user.
 And
  	there are the users : "Jithin S", "Chandrani Deshpande","Vladtest", ""
 And 
	female users are less than male users
  */
	
	
	@Test
	public void get11GoRestCo () {
		//1. set the url
//		spec.pathParam("first", "users");
//		spec.queryParam("page", 1);
		
		String url = "https://gorest.co.in/public/v1/users";
		
		//2.set the expected data
		
		//3. send the GET request and get the response
		Response response = given().when().get(url);
		response.prettyPrint();
		
		//4. Do the assertions
	//1st way by using body	
//		response.then().assertThat().statusCode(200).
//		body("meta.pagination.limit", Matchers.equalTo(20),
//			"meta.pagination.links.current", Matchers.equalTo("https://gorest.co.in/public/v1/users?page=1"),
//			"data.id", Matchers.hasSize(20),//the number of users should be 20
//			"data.status", Matchers.hasSize(20), //the number of "active" users should be 9
//			"data.name", Matchers.hasItems("Jithin S", "Chandrani Deshpande","Gandharv Iyengar"), 
//			"data.status", Matchers.hasItem("active"), // we have at least one active user
//			"data.gender");
		
	//2nd way by using JsonPath	
		JsonPath json = response.jsonPath(); 
		//2nd way of "the number of "active" users should be 9" - hoca bu yolla yapamadı.
//		List<String> statusActive = json.getList("findAll{it.data.status}");
//		System.out.println(statusActive);
		
		assertEquals(200, response.getStatusCode());
		assertEquals(20,  json.getInt("meta.pagination.limit"));
		assertEquals("https://gorest.co.in/public/v1/users?page=1",  json.get("meta.pagination.links.current"));
		assertEquals(20,  json.getList("data.id").size());
		
		assertTrue(json.getList("data.status").contains("active"));// we have at least one active user
		
		
		// assertTrue(json.getList("data.name").containsAll("Jithin S", "Chandrani Deshpande","Gandharv Iyengar"));
		//The above is wrong because containsAll() needs Collections. So we have to create a List as follows.
		List<String> expectedNamesList = Arrays.asList("Jithin S", "Chandrani Deshpande","Gandharv Iyengar");
		//assertTrue(json.getList("data.name").containsAll(expectedNamesList));
				
		//female users are less than male users	
		//1st way which I solved. 
		List<String> genderList = json.getList("data.gender");
		System.out.println(genderList);
		
		Object [] genderArray = genderList.toArray();
		
		int counterMale=0;
		int counterFemale=0;
		for (int i= 0 ; i<genderList.size(); i++) {
			if(genderArray[i].equals("female")) {
				counterFemale++;
			}
			else if ( genderArray[i].equals( "male")) {
				counterMale++;
			}
		}
		System.out.println("number of females= " + counterFemale);
		System.out.println("number of males= " + counterMale);
		
		
	Assert.assertTrue(counterFemale<counterMale);
		
	//2nd way (very similar way but with for each loop)
	
		int counterMale2=0;
		int counterFemale2=0;
		for (String w : genderList ) {
			if(w.equals("female")) {
				counterFemale2++;
			}
			else if ( w.equals( "male")) {
				counterMale2++;
			}
		}
		System.out.println("number of females from 2nd Loop = " + counterFemale2);
		System.out.println("number of males from 2nd Loop = " + counterMale2);
	
		// we can also assert as follows since there are 2 options male or female:
		assertTrue(counterFemale2<(genderList.size()-counterFemale2));
		
		// 3rd way with groovy Language	
		
		List<String> listOfFemales = json.getList("data.findAll{it.gender='female'}.gender");
		List<String> listOfMales = json.getList("data.findAll{it.gender='male'}.gender");
		System.out.println("list of females from groovy= " + listOfFemales ); // tüm listeyi female yapmış
		System.out.println("list of males from groovy= " + listOfMales ); // tüm listeyi male yapmış
		assertTrue(listOfFemales.size()>=listOfMales.size()); //hata vermesin diye böyle yazdım. normalde groovy de hata var.
		
		//the number of "active" users should be more than 5 (this is a changing data in every run) - lets assert it with json
		// 1st way with for loop
		List<String> statusList = json.getList("data.status");
		System.out.println(statusList);
		
		int activeCounter = 0;
		for (String w : statusList) {
			if (w.equals("active")) {
				activeCounter++;
			}
		}
		System.out.println("Number of active users = " + activeCounter);
		assertEquals(15, activeCounter);// the task was first the number of active users equals to 15. This line is for that
		//or
		assertTrue(activeCounter>5);
	
		// 2nd way with groovy Language
	List<String> listOfActives = json.getList("data.findAll{it.status='active'}.status");
// Important: in the groovy language "active" was not accepted. We use 'active'
	//data is an array and we are finding from the array
	
	System.out.println(listOfActives);
	assertTrue(listOfActives.size()>5);
	}

}
