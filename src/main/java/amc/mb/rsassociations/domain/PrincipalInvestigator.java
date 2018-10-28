package amc.mb.rsassociations.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PrincipalInvestigator extends ExcelRow {

	private Long principalInvestigatorId;

	private String title;

	// Molenaar 155 heeft geen initialen
	// @NotNull
	// @NotEmpty
	private final String initials;

	private String firstName;

	private String middleName;

	@NotNull
	@NotEmpty
	private final String lastName;

	private String gender;

	private String roomNumber;

	private String email;

	private String function;

	private String phoneNumber;

	private String address;

	public PrincipalInvestigator(@NotNull Long rowNumber, @NotNull @NotEmpty String initials, @NotNull @NotEmpty String lastName) {
		super(rowNumber);
		this.initials = initials;
		this.lastName = lastName;
	}

	public PrincipalInvestigator(Long principalInvestigatorId, Long rowNumber, @NotNull @NotEmpty String initials, @NotNull @NotEmpty String lastName) {
		this(rowNumber, initials, lastName);
		this.principalInvestigatorId = principalInvestigatorId;
	}

	public Long getPrincipalInvestigatorId() {
		return principalInvestigatorId;
	}

	public void setPrincipalInvestigatorId(Long principalInvestigatorId) {
		this.principalInvestigatorId = principalInvestigatorId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInitials() {
		return initials;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "PrincipalInvestigator [principalInvestigatorId=" + principalInvestigatorId + ", title=" + title + ", initials=" + initials + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", gender=" + gender + ", roomNumber=" + roomNumber + ", email=" + email + ", function=" + function
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + "]";
	}

}
