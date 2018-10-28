package amc.mb.rsassociations.domain;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import amc.mb.rsassociations.enums.RSFunction;

public class RSEmployee extends ExcelRow {

	private Long rsEmployeeId;

	@NotNull
	@NotEmpty
	private String fullName;

	@NotNull
	@NotEmpty
	private String phoneNumber;

	@NotNull
	@NotEmpty
	private String email;

	private String secondaryEmail;

	private Set<RSFunction> rsFunctions = new HashSet<>();

	/** Constructor used by constructing an instance from a spreadsheet row. */
	public RSEmployee(@NotNull Long rowNumber, String fullName) {
		super(rowNumber);
		this.fullName = fullName;
	}

	/** Constructor used by constructing an instance from a database record. */
	public RSEmployee(Long rsEmployeeId, Long rowNumber, String fullName) {
		this(rowNumber, fullName);
		this.rsEmployeeId = rsEmployeeId;
	}

	public Long getRsEmployeeId() {
		return rsEmployeeId;
	}

	public void setRsEmployeeId(Long rsEmployeeId) {
		this.rsEmployeeId = rsEmployeeId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String name) {
		this.fullName = name;
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

	public String getSecondaryEmail() {
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
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

	@Override
	public String toString() {
		return "RSEmployee [rsEmployeeId=" + rsEmployeeId + ", fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", secondaryEmail=" + secondaryEmail
				+ ", rsFunctions=" + rsFunctions + "]";
	}

}
