package finalppro.model;

public class CourtEditForm {

	private String name, street, city, postal_code;
	private boolean active;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public CourtEditForm(String name, String street, String city, String postal_code, boolean active) {
		super();
		this.name = name;
		this.street = street;
		this.city = city;
		this.postal_code = postal_code;
		this.active = active;
	}
	
	public CourtEditForm(){
		
	}
	
	
}
