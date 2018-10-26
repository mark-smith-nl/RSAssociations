package amc.mb.rsassociations.enums;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public enum XMLSheet {
	OVERVIEW_ORGANISATIONAL_UNITS("Overview", "Achternaam", "Divisie", "Afdeling", "Unit"),
	OVERVIEW_PI("Overview", "Titel", "Voorletters", "Achternaam", "m/v", "Kamernr.", "e-mail", "Functie", "Telefoon", "Postadres"),
	CONTACT_GEGEVENS("Contactgegevens RS+ medewerkers", "Naam", "Functie", "Tel. nummer", "E-mail", "E-mail 2"),
	MAPPING_PC_PA("Mapping PC-PA", "PC", "PA"),
	MAPPING_HRA_HRM("Mapping HRA-HRM", "HRA", "HRM");

	private final String sheetName;

	private final Set<String> columnNames;

	private XMLSheet(String sheetName, String... columnNames) {
		this.sheetName = sheetName;
		this.columnNames = new HashSet<>(Arrays.asList(columnNames));
	}

	public String getSheetName() {
		return sheetName;
	}

	public void assertValidColumns(Collection<String> columnNames) {
		if (columnNames == null || columnNames.size() == 0) {
			throw new IllegalStateException(String.format("Sheet %s has no columnnames.", sheetName));
		}

		Set<String> uniqueColumnNames = new HashSet<>(columnNames);

		if (columnNames.size() != uniqueColumnNames.size()) {
			throw new IllegalStateException(String.format("Sheet %s has dupicate columnnames.", sheetName));
		}

		if (!columnNames.containsAll(this.columnNames)) {
			throw new IllegalStateException(String.format("Sheet %s is missing columnnames.\nExpected columns: %s.\nSheet columns: %s.", sheetName,
					String.join(", ", this.columnNames), String.join(", ", columnNames)));
		}
	}

	public Map<Integer, String> getRelevantColumnIndexNameMapping(Map<Integer, String> columnIndexNameMapping) {
		assertValidColumns(columnIndexNameMapping.values());

		return columnIndexNameMapping.entrySet().stream().filter(x -> columnNames.contains(x.getValue())).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

	}

}
