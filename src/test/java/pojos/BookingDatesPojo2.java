package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatesPojo2 {
	/*
	 pojo creation:
	 1. create private variables
	 2. create constructor -- Source>>Generate Constructors using fields
	 	- with all parameters
	 	- with No parameter
	 3. Create getters and setters
	 4. create toString()	
	 
	 */
	
	private String checkin;
	private String checkout;
	
	public BookingDatesPojo2(String checkin, String checkout) {
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public BookingDatesPojo2() {
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	@Override
	public String toString() {
		return "BookingDatesPojo2 [checkin=" + checkin + ", checkout=" + checkout + "]";
	}
	
	
	
	
}
