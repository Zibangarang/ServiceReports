package server;

//This is a POJO that is used to easily read the data in submitted json
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Report {
	private String person;
	private String customer;
	private String equipment;
	private String description;
	
	public Report() {}
	
	public String getPerson() {
		return person;	
	}
	public void setPerson(String person) {
	    this.person = person;
	  }
	public String getCustomer() {
		return customer;	
	}
	public void setCustomer(String customer) {
	    this.customer = customer;
	  }
	public String getEquipment() {
		return equipment;	
	}
	public void setEquipment(String equipment) {
	    this.equipment = equipment;
	  }
	public String getDescription() {
		return description;	
	}
	public void setDescription(String description) {
	    this.description = description;
	  }
	
	
}
