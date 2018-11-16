package amc.mb.rsassociations.domain;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import amc.mb.rsassociations.enums.RSFunction;

public class RSEmployee extends IdsPerson {

	@NotNull
	@NotEmpty
	private String phoneNumber;

	private String email;

	private Set<RSFunction> rsFunctions = new HashSet<>();

	/** Constructor used by constructing an instance from a spreadsheet row and its correponding Ids entry. */
	public RSEmployee(@NotNull Long rowNumber, IdsPerson idsPerson) {
		this(idsPerson.getPersoonId(), rowNumber);
		this.setAmcgebruikersnaam(idsPerson.getAmcgebruikersnaam());
		this.setAmcemail(idsPerson.getAmcemail());
		this.setGeboortedatum(idsPerson.getGeboortedatum());
		this.setGeslacht(idsPerson.getGeslacht());
		this.setRoepnaam(idsPerson.getRoepnaam());
		this.setTussenvoegsel(idsPerson.getTussenvoegsel());
		this.setVoorkeurnaam(idsPerson.getVoorkeurnaam());
		this.setVoorletters(idsPerson.getVoorletters());
		this.setVoornaam(idsPerson.getVoornaam());
	}

	/** Constructor used by constructing an instance from a database record. */
	public RSEmployee(Long persoonId, Long rowNumber) {
		super(persoonId, rowNumber);
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RSFunction> getRsFunctions() {
		return rsFunctions;
	}

	public void setRsFunctions(Set<RSFunction> rsFunctions) {
		this.rsFunctions = rsFunctions;
	}

	public void addRsFunction(RSFunction rsFunction) {
		rsFunctions.add(rsFunction);
	}

}
