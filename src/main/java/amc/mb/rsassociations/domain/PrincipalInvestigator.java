package amc.mb.rsassociations.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PrincipalInvestigator {

	private Long principalInvestigatorId;

	private final String title;

	@NotNull
	@NotEmpty
	private final String initials;

	@NotNull
	@NotEmpty
	private final String firstName;

	@NotNull
	@NotEmpty
	private final String middleName;

	@NotNull
	@NotEmpty
	private final String lastName;

	@NotNull
	@NotEmpty
	private String gender;

	@NotNull
	@NotEmpty
	private String roomNumber;

	@NotNull
	@NotEmpty
	private String email;

	@NotNull
	@NotEmpty
	private String function;

	@NotNull
	@NotEmpty
	private String phone;

	@NotNull
	@NotEmpty
	private String address;

	public PrincipalInvestigator(String title, @NotNull @NotEmpty String initials, @NotNull @NotEmpty String firstName, @NotNull @NotEmpty String middleName,
			@NotNull @NotEmpty String lastName) {
		this.title = title;
		this.initials = initials;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public PrincipalInvestigator(Long principalInvestigatorId, String title, @NotNull @NotEmpty String initials, @NotNull @NotEmpty String firstName,
			@NotNull @NotEmpty String middleName, @NotNull @NotEmpty String lastName) {
		this(title, initials, firstName, middleName, lastName);
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

	public String getInitials() {
		return initials;
	}

	public String getMiddleName() {
		return middleName;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "PrincipalInvestigator [principalInvestigatorId=" + principalInvestigatorId + ", title=" + title + ", initials=" + initials + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", gender=" + gender + ", roomNumber=" + roomNumber + ", email=" + email + ", function=" + function
				+ ", phone=" + phone + ", address=" + address + "]";
	}

}
