package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class DummyBaseUrl {

	protected RequestSpecification spec;
	
	public void setup() {
		
		spec = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com").build();
		
	}
	
	
}
