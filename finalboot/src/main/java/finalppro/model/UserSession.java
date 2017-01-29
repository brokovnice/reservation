package finalppro.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.annotation.Scope;


public class UserSession implements Serializable{

	private int userId;
	private Date created;
	private String name, surname;
	
	
	public UserSession(){
		created = new Date();
	}
	
	public UserSession(int userId, Date created, String name, String surname) {
		super();
		this.userId = userId;
		this.created = created;
		this.name = name;
		this.surname = surname;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getCreated() {
		return created;
	}

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
	
	
	
	
}
