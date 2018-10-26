package amc.mb.rsassociations.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import amc.mb.rsassociations.domain.Department;
import amc.mb.rsassociations.domain.Division;
import amc.mb.rsassociations.domain.HRAdviseurMedewerkerCouple;
import amc.mb.rsassociations.domain.PrincipalInvestigator;
import amc.mb.rsassociations.domain.ProjectControllerAdministrateurCouple;
import amc.mb.rsassociations.domain.RSEmployee;
import amc.mb.rsassociations.domain.SubDepartment;
import amc.mb.rsassociations.enums.RSFunction;
import amc.mb.rsassociations.enums.XMLSheet;
import amc.mb.rsassociations.utils.FinalLambdaValue;

@Service
@Validated
public class ImportSpreadSheetService {

	private final static Logger LOGGER = LoggerFactory.getLogger(ImportSpreadSheetService.class);

	public static final String SHEET_NAME_OVERVIEW = "Overview";

	// public static final String SHEET_NAME_CONTACT_GEGEVENS = "Contactgegevens RS+ medewerkers";

	private final RSEmployeeService rsEmployeeService;

	private final OrganisationalUnitService organisationalUnitService;

	private final PrincipalInvestigatorService principalInvestigatorService;

	public ImportSpreadSheetService(RSEmployeeService rsEmployeeService, OrganisationalUnitService organisationalUnitService,
			PrincipalInvestigatorService principalInvestigatorService) {
		this.rsEmployeeService = rsEmployeeService;
		this.organisationalUnitService = organisationalUnitService;
		this.principalInvestigatorService = principalInvestigatorService;
	}

	public void importDataFromFile(@NotNull @NotEmpty String fileName) {
		rsEmployeeService.deleteAll();
		organisationalUnitService.deleteAll();

		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(new File(fileName));
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			throw new IllegalStateException(String.format("Can not read file '%s'.", fileName));
		}

		getAndSaveRsEmployees(workbook);
		getAndSaveProjectControllerAdministrateurCouples(workbook);
		getAndSaveHRAdviseurMedewerkerCouples(workbook);
		getAndSaveOrganisationalUnits(workbook);
		getAndSavePrincipalInvestigators(workbook);
	}

	private void getAndSaveRsEmployees(Workbook workbook) {
		List<Map<String, String>> rows = getRowsFromExcelFile(workbook, XMLSheet.CONTACT_GEGEVENS);
		validateFunctionExists(rows);

		Set<RSEmployee> rsEmployees = new LinkedHashSet<>();
		rows.forEach(row -> {
			RSEmployee rsEmployee = new RSEmployee(Long.valueOf(row.get("rowNumber")), row.get("Naam"));
			String rsFunctions = row.get("Functie");
			Arrays.asList(rsFunctions.split(";")).forEach(rsFunction -> rsEmployee.addRsFunction(RSFunction.getFunctionForValue(rsFunction.trim())));
			rsEmployee.setPhoneNumber(row.get("Tel. nummer"));
			rsEmployee.setEmail(row.get("E-mail"));
			rsEmployee.setSecondaryEmail(row.get("E-mail 2"));
			rsEmployees.add(rsEmployee);
		});

		rsEmployeeService.saveRSEmployees(rsEmployees);
	}

	private void getAndSaveProjectControllerAdministrateurCouples(Workbook workbook) {
		List<Map<String, String>> rows = getRowsFromExcelFile(workbook, XMLSheet.MAPPING_PC_PA);

		Set<ProjectControllerAdministrateurCouple> controllerAdministrateurCouples = new LinkedHashSet<>();
		rows.forEach(row -> {
			RSEmployee controller = rsEmployeeService.getRSEmployeeByFullName(row.get("PC"));
			RSEmployee administrateur = rsEmployeeService.getRSEmployeeByFullName(row.get("PA"));
			controllerAdministrateurCouples.add(new ProjectControllerAdministrateurCouple(Long.valueOf(row.get("rowNumber")), controller, administrateur));
		});

		rsEmployeeService.saveProjectControllerAdministrateurCouples(controllerAdministrateurCouples);
	}

	private void getAndSaveHRAdviseurMedewerkerCouples(Workbook workbook) {
		List<Map<String, String>> rows = getRowsFromExcelFile(workbook, XMLSheet.MAPPING_HRA_HRM);

		Set<HRAdviseurMedewerkerCouple> adviseurMedewerkerCouples = new LinkedHashSet<>();
		rows.forEach(row -> {
			RSEmployee adviseur = rsEmployeeService.getRSEmployeeByFullName(row.get("HRA"));
			RSEmployee medewerker = rsEmployeeService.getRSEmployeeByFullName(row.get("HRM"));
			adviseurMedewerkerCouples.add(new HRAdviseurMedewerkerCouple(Long.valueOf(row.get("rowNumber")), adviseur, medewerker));
		});

		rsEmployeeService.saveHRAdviseurMedewerkerCouples(adviseurMedewerkerCouples);
	}

	private void getAndSaveOrganisationalUnits(Workbook workbook) {
		XMLSheet sheet = XMLSheet.OVERVIEW_ORGANISATIONAL_UNITS;
		List<Map<String, String>> rows = getRowsFromExcelFile(workbook, sheet);

		List<Division> divisions = new ArrayList<>();
		List<Department> departments = new ArrayList<>();
		List<SubDepartment> subDepartments = new ArrayList<>();

		// We start at row 2 since row 1 is the columnName row.
		FinalLambdaValue<Integer> rowNumber = new FinalLambdaValue<>(2);
		rows.forEach(row -> {
			String name = row.get("Divisie");
			if (name == null || "".equals(name)) {
				name = "_Not specified";
				// throw new IllegalStateException(String.format("Missing division on sheet %s (row=%d).", sheet.getSheetName(), rowNumber.getValue()));
			}
			Division division = new Division(name);
			int indexOf = divisions.indexOf(division);
			if (indexOf == -1) {
				divisions.add(division);
			} else {
				division = divisions.get(indexOf);
			}

			name = row.get("Afdeling");
			if (name == null || "".equals(name)) {
				name = "_Not specified";
				// throw new IllegalStateException(String.format("Missing division on sheet %s (row=%d).", sheet.getSheetName(), rowNumber.getValue()));
			}
			Department department = new Department(name, division);
			indexOf = departments.indexOf(department);
			if (indexOf == -1) {
				departments.add(department);
			} else {
				department = departments.get(indexOf);
			}

			name = row.get("Unit");
			if (name != null && !"".equals(name)) {
				SubDepartment subDepartment = new SubDepartment(name, department);
				indexOf = subDepartments.indexOf(subDepartment);
				if (indexOf == -1) {
					subDepartments.add(subDepartment);
				}
			}

			rowNumber.setValue(rowNumber.getValue() + 1);
		});

		organisationalUnitService.saveOrganisationalUnits(divisions, departments, subDepartments);
	}

	private void getAndSavePrincipalInvestigators(Workbook workbook) {
		XMLSheet sheet = XMLSheet.OVERVIEW_PI;
		List<Map<String, String>> rows = getRowsFromExcelFile(workbook, sheet);

		Set<PrincipalInvestigator> principalInvestigators = new LinkedHashSet<>();

		rows.forEach(row -> {
			PrincipalInvestigator principalInvestigator = new PrincipalInvestigator(row.get("Titel"), row.get("Voorletters"), row.get("Voornaam"), row.get("Tussenvoegsels"),
					row.get("Achternaam"));
			principalInvestigator.setGender(row.get("m/v"));
			principalInvestigator.setRoomNumber(row.get("Kamernr."));
			principalInvestigator.setEmail(row.get("e-mail"));
			principalInvestigator.setFunction(row.get("Functie"));
			principalInvestigator.setPhone(row.get("Telefoon."));
			principalInvestigator.setAddress(row.get("Postadres"));
			principalInvestigators.add(principalInvestigator);
		});

		principalInvestigators.forEach(System.out::println);
		// principalInvestigatorService.savePrincipalInvestigators(principalInvestigators);

	}

	private static List<Map<String, String>> getRowsFromExcelFile(Workbook workbook, XMLSheet xmlSheet) {
		String sheetName = xmlSheet.getSheetName();
		Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
			throw new IllegalStateException(String.format("Spreadsheet does not contain sheet '%s'.", sheetName));
		}

		Map<Integer, String> columnIndexNameMapping = xmlSheet.getRelevantColumnIndexNameMapping(getColumnIndexNameMapping(sheet));

		final Collection<String> columnNames = columnIndexNameMapping.values();
		DataFormatter dataFormatter = new DataFormatter();

		List<Map<String, String>> rows = new ArrayList<>();
		sheet.forEach(row -> {
			if (row.getRowNum() > 0) {
				Map<String, String> columnValues = new HashMap<>();
				columnIndexNameMapping.forEach((index, columnName) -> {
					Cell cell = row.getCell(index);
					String columValue = null;
					if (cell != null) {
						if (cell.getCellTypeEnum() == CellType.FORMULA) {
							switch (cell.getCachedFormulaResultTypeEnum()) {
							case NUMERIC:
								columValue = String.valueOf(cell.getNumericCellValue());
								break;
							case STRING:
								columValue = String.valueOf(cell.getRichStringCellValue());
								break;
							default:
								System.out.println();
								throw new IllegalStateException("Unsupported formula");
							}
						} else {
							columValue = dataFormatter.formatCellValue(cell).trim();
						}
					}

					columnValues.put(columnName, columValue);
				});

				// Check if the row contains at least one not null not empty value
				for (String columnName : columnNames) {
					String columnValue = columnValues.get(columnName);
					if (columnValue != null && !"".equals(columnValue)) {
						columnValues.put("rowNumber", String.valueOf(row.getRowNum() + 1));
						rows.add(columnValues);
						break;
					}
				}
			}
		});

		if (rows.isEmpty()) {
			throw new IllegalStateException(String.format("Sheet '%s' does not contain data.", sheetName));
		}

		LOGGER.info("Number of rows: {}", rows.size());

		return rows;
	}

	/** Check if every function defined in the spreadsheet is mapped to a {@link RSFunction} instance. */
	private static void validateFunctionExists(List<Map<String, String>> rows) {
		rows.forEach(row -> Arrays.asList(row.get("Functie").split(";")).forEach(rsFunction -> RSFunction.getFunctionForValue(rsFunction.trim())));
	}

	private static Map<Integer, String> getColumnIndexNameMapping(Sheet sheet) {
		Row row = sheet.getRow(0);
		if (row == null) {
			throw new IllegalStateException(String.format("Sheet '%s' has no column data.", sheet.getSheetName()));
		}

		Map<Integer, String> columnIndexNameMapping = new HashMap<>();
		final FinalLambdaValue<Integer> columnIndex = new FinalLambdaValue<>(0);

		row.forEach(cell -> {
			boolean columnHidden = sheet.isColumnHidden(columnIndex.getValue());
			LOGGER.info("Column[{}] = {}. Hidden: {}.", columnIndex.getValue(), cell.getStringCellValue(), columnHidden);
			if (!columnHidden) {
				columnIndexNameMapping.put(columnIndex.getValue(), cell.getStringCellValue().trim());
			}
			columnIndex.setValue(columnIndex.getValue() + 1);
		});

		return columnIndexNameMapping;
	}

}
