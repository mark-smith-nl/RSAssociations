package amc.mb.rsassociations.domain.test;

import java.util.ArrayList;
import java.util.List;

public class Person {

	private long personId;

	private String lastName;

	private String firstName;

	private List<String> belongings = new ArrayList<>();

	public Person(String lastName, String firstName) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public Person(Long personId, String lastName, String firstName) {
		this(lastName, firstName);
		this.personId = personId;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long id) {
		this.personId = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<String> getBelongings() {
		return belongings;
	}

	public void setBelongings(List<String> belongings) {
		this.belongings = belongings;
	}

}
