package amc.mb.rsassociations.enums;

public enum RSFunctionCouple implements WebEnum {

	PROJECTCONTROLLER_ADMINISTRATEUR_COUPLE(
			"project_controller_administrator_link",
			"Projectcontroller - administrateur",
			"controller_id",
			"administrator_id",
			RSFunction.PROJECTCONTROLLER,
			RSFunction.PROJECTADMINISTRATEUR),
	HRADVISEUR_MEDEWERKER_COUPLE("hr_advisor_employee_link", "HRadministrateur - medewerker", "advisor_id", "employee_id", RSFunction.HR_ADVISEUR, RSFunction.HR_MEDEWERKER);

	private final String tableName;

	private final String description;

	private final String columnNameFirstRSEmployee;

	private final String columnNameSecondRSEmployee;

	private final RSFunction firstRSFunction;

	private final RSFunction secondRSFunction;

	private RSFunctionCouple(String tableName, String description, String columnNameFirstRSEmployee, String columnNameSecondRSEmployee, RSFunction firstRSFunction,
			RSFunction secondRSFunction) {
		this.tableName = tableName;
		this.description = description;
		this.columnNameFirstRSEmployee = columnNameFirstRSEmployee;
		this.columnNameSecondRSEmployee = columnNameSecondRSEmployee;
		this.firstRSFunction = firstRSFunction;
		this.secondRSFunction = secondRSFunction;
	}

	public String getTableName() {
		return tableName;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public String getColumnNameFirstRSEmployee() {
		return columnNameFirstRSEmployee;
	}

	public String getColumnNameSecondRSEmployee() {
		return columnNameSecondRSEmployee;
	}

	public RSFunction getFirstRSFunction() {
		return firstRSFunction;
	}

	public RSFunction getSecondRSFunction() {
		return secondRSFunction;
	}
}
