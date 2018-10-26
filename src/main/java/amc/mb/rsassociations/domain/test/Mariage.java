package amc.mb.rsassociations.domain.test;

public class Mariage {

	private final Person husband;

	private final Person wife;

	public Mariage(Person husband, Person wife) {
		this.husband = husband;
		this.wife = wife;
	}

	public Person getHusband() {
		return husband;
	}

	public Person getWife() {
		return wife;
	}

}
