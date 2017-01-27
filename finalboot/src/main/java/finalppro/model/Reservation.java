package finalppro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="reservation")
public class Reservation implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private Date date_start, date_end;
	private String note;
	private Court court;
	
	public Reservation(){
		
	}	
	
	public Reservation(int id, Date date_start, Date date_end, String note, Court court) {
		super();
		this.id = id;
		this.date_start = date_start;
		this.date_end = date_end;
		this.note = note;
		this.court = court;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate_start() {
		return date_start;
	}
	public void setDate_start(Date date_start) {
		this.date_start = date_start;
	}
	public Date getDate_end() {
		return date_end;
	}
	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Court getCourt() {
		return court;
	}
	public void setCourt(Court court) {
		this.court = court;
	}
	
	
}
