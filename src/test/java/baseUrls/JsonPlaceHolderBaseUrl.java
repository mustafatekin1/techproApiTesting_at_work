package baseUrls;

import org.junit.Before;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class JsonPlaceHolderBaseUrl {

	protected RequestSpecification spec;
	
	@Before
	public void setup() {
		spec = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
		
	}
	
}
