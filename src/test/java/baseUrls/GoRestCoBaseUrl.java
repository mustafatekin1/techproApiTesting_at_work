package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class GoRestCoBaseUrl {

	protected RequestSpecification spec;
	
	public void setup () {
		
		spec = new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public/v1").build();
		
	}
	
	
}
