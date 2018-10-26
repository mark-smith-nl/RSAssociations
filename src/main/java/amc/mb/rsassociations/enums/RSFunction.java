package amc.mb.rsassociations.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import amc.mb.rsassociations.services.ImportSpreadSheetService;

public enum RSFunction implements WebEnum {
	PROJECTCONTROLLER("function_project_controller", "1. Projectcontroller", "Projectcontroller"),
	PROJECTADMINISTRATEUR("function_project_administrateur", "2. Projectadministrateur", "Projectadministrateur"),
	JURIST_MTA_CDA("function_jurist_mta_cda", "3. MTA & CDA Jurist", "MTA & CDA Jurist"),
	JURIST_IP_EU("function_jurist_ip_eu", "4. IP & EU Jurist", "IP & EU Jurist"),
	JURIST_KLINISCH("function_jurist_klinisch", "5. Klinisch Jurist", "Klinisch Jurist", "Klinische Jurist"),
	HR_ADVISEUR("function_hr_adviseur", "6. HR Adviseur", "HR Adviseur", "HR Adviseur "),
	HR_MEDEWERKER("function_hr_medewerker", "7. HR Medewerker", "HR Medewerker", "HR medewerker"),
	BUSINESS_DEVELOPER("function_business_developer", "8. Business Developer", "Business Developer", null),
	OCTROOIGEMACHTIGDE("function_octrooi_gemachtigde", "9. Octrooigemachtigde", "Octrooigemachtigde", null),
	CONSORTIUMCONTROLLER("function_consortium_controller", "10. Consortium controller", "Consortium controller", null);

	private final String tableName;

	private final String description;

	/** The column name on the spreedsheet see: {@link ImportSpreadSheetService#SHEET_NAME_CONTACT_GEGEVENS}*/
	private final String functionColumnValueSheetContactgegevens;

	/** The column name on the spreedsheet see: {@link ImportSpreadSheetService#SHEET_NAME_OVERVIEW}*/
	private final String columnNameSheetOverview;

	private RSFunction(String tableName, String desription, String functionColumnValueSheetContactgegevens) {
		this(tableName, desription, functionColumnValueSheetContactgegevens, functionColumnValueSheetContactgegevens);
	}

	private RSFunction(String tableName, String description, String functionColumnValueSheetContactgegevens, String columnNameSheetOverview) {
		this.tableName = tableName;
		this.description = description;
		this.functionColumnValueSheetContactgegevens = functionColumnValueSheetContactgegevens;
		this.columnNameSheetOverview = columnNameSheetOverview;
	}

	public String getTableName() {
		return tableName;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public String getFunctionColumnValueSheetContactgegevens() {
		return functionColumnValueSheetContactgegevens;
	}

	public String getColumnNameSheetOverview() {
		return columnNameSheetOverview;
	}

	public static RSFunction getFunctionForValue(String functionValue) {
		for (RSFunction rsFunction : RSFunction.values()) {
			if (rsFunction.functionColumnValueSheetContactgegevens.equals(functionValue)) {
				return rsFunction;
			}
		}

		throw new IllegalStateException(String.format("Unknown role/function: '%s'.", functionValue));
	}

	public static List<RSFunction> valuesAsList() {
		return Arrays.asList(RSFunction.values());
	}

	public static String getFunctionEmployeeLinks() {
		List<String> functionJEmployeeLinks = new ArrayList<>();
		for (RSFunction rsFunction : RSFunction.values()) {
			StringBuilder sql = new StringBuilder();
			//@formatter:off
			sql
			.append("SELECT '")
			.append(rsFunction.name())
			.append("' function_name")
			.append(", rs_employee_id ")
			.append("FROM rsassociations.")
			.append(rsFunction.tableName);
			//@formatter:on
			functionJEmployeeLinks.add(sql.toString());
		}

		return String.join(" UNION \n", functionJEmployeeLinks);
	}

}
