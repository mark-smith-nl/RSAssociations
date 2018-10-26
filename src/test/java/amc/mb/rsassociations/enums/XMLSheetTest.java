package amc.mb.rsassociations.enums;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class XMLSheetTest {

	@Test(expected = IllegalStateException.class)
	public void assertValidColumns_noColumns() {
		XMLSheet sheet = XMLSheet.CONTACT_GEGEVENS;

		sheet.assertValidColumns(null);
	}

	@Test(expected = IllegalStateException.class)
	public void assertValidColumns_duplicateColumns() {
		XMLSheet sheet = XMLSheet.CONTACT_GEGEVENS;

		List<String> columnNames = Arrays.asList("Column one", "Column two", "Column one");
		sheet.assertValidColumns(columnNames);
	}

	@Test(expected = IllegalStateException.class)
	public void assertValidColumns_missingColumns() {
		XMLSheet sheet = XMLSheet.CONTACT_GEGEVENS;

		List<String> columnNames = Arrays.asList("Column one", "Column two", "Overview", "Naam", "Functie", "Tel. nummer", "E-mail");
		sheet.assertValidColumns(columnNames);
	}

	@Test()
	public void assertValidColumns() {
		XMLSheet sheet = XMLSheet.CONTACT_GEGEVENS;

		List<String> columnNames = Arrays.asList("Column one", "Column two", "Overview", "Naam", "Functie", "Tel. nummer", "E-mail", "E-mail 2");
		sheet.assertValidColumns(columnNames);
	}

}
