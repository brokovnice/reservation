package finalppro.model;

public class UserEditForm {

	private String name, surname, test;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public UserEditForm(){
		
	}
	
	public UserEditForm(String name, String surname, String test) {
		super();
		this.name = name;
		this.surname = surname;
		this.test=test;
	}
	
	
	
	
}
