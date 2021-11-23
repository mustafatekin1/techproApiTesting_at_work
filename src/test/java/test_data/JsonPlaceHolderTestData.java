package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {
/*
 It is better to separate expected test data and test execution.
 That's why, we create a new package just like base_urls package 
 and we collect our expected test data inside that package.	 
 */
	public Map<String, Object> expectedDataSetup() {
		Map<String, Object> expectedData = new HashMap<>();
		expectedData.put("userId", 5);
		expectedData.put("id", 201);
		expectedData.put("title", "nema problema");
		expectedData.put("completed", true);
//		expectedData.put("Status Code", 201);   // These must not be here. We have to move these 2 lines after sending the request
//		expectedData.put("Content Type", "application/json[]; charset=utf-8");
		
		System.out.println(expectedData);
		return expectedData;
	}
	//lets make it this method more dynamic:
	public Map<String, Object> requestBodySetup(Integer userId, String title, Boolean isCompleted) {
		// It is better to use Boolean wrapper data type. Because boolean is primitive and not accept "null" 
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("userId", userId);
		requestBody.put("title", title);
		requestBody.put("completed", isCompleted);
//		expectedData.put("Status Code", 201);   // These must not be here. We have to move these 2 lines after sending the request
//		expectedData.put("Content Type", "application/json[]; charset=utf-8");
		
		System.out.println(requestBody);
		return requestBody;
	}
	
	public Map<String, Object> patchRequest (String title) {
		Map<String, Object> patchRequestBody = new HashMap<>();
		//patchRequestBody.put("userId", userId);
		patchRequestBody.put("title", title);
		//patchRequestBody.put("completed", completed);
		return patchRequestBody; 
	}
	
	//forming the method to make it similar to teacher's solution for Patch01
	public Map<String, Object> requestBodySetup2(Integer userId, String title, Boolean isCompleted) {
		// It is better to use Boolean wrapper data type. Because boolean is primitive and not accept "null" 
		Map<String, Object> requestBody = new HashMap<>();
		if (userId==null&&isCompleted==null) {
			requestBody.put("title", title);
		}
		else {
		requestBody.put("userId", userId);
		requestBody.put("title", title);
		requestBody.put("completed", isCompleted);
//		expectedData.put("Status Code", 201);   // These must not be here. We have to move these 2 lines after sending the request
//		expectedData.put("Content Type", "application/json[]; charset=utf-8");
		}
		System.out.println(requestBody);
		return requestBody;
	}
	
	
}
