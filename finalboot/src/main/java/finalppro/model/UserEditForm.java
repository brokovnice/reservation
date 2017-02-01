package finalppro.model;

public class UserEditForm {

	private String name, surname, userType, street, city, postal_code, email, password, username;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public UserEditForm(){
		
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public UserEditForm(String name, String surname, String userType, String street, String city, String postal_code,
			String email, String password, String username) {
		super();
		this.name = name;
		this.surname = surname;
		this.userType = userType;
		this.street = street;
		this.city = city;
		this.postal_code = postal_code;
		this.email = email;
		this.password = password;
		this.username = username;
	}
	

	
	
	
	
	
	
	
}
