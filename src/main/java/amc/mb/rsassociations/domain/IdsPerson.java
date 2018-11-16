package amc.mb.rsassociations.domain;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import org.thymeleaf.util.StringUtils;

public class IdsPerson extends ImportRow {

	private final Long persoonId;

	private String amcgebruikersnaam;

	private String amcemail;

	private String voorkeurnaam;

	private String tussenvoegsel;

	private String voorletters;

	private String voornaam;

	private String roepnaam;

	private LocalDate geboortedatum;

	private String geslacht;

	/** Private constructor used by MyBatis. */
	public IdsPerson(Long persoonId, Long rowNumber) {
		super(rowNumber);
		this.persoonId = persoonId;
	}

	/** Constructor used by constructing entries from a file.
	 * Example: 168152~msmith~m.smith@amc.uva.nl~Smith~~M.~Mark~~1965-07-28~M
	*/
	public IdsPerson(String line, long row) {
		super(row);
		String[] field = line.split("~");
		if (field.length != 10) {
			throw new IllegalArgumentException();
		}

		int i = 0;
		this.persoonId = Long.valueOf(field[i++]);
		this.setAmcgebruikersnaam(field[i++]);
		this.setAmcemail(field[i++]);
		this.setVoorkeurnaam(field[i++]);
		this.setTussenvoegsel(field[i++]);
		this.setVoorletters(field[i++]);
		this.setVoornaam(field[i++]);
		this.setRoepnaam(field[i++]);
		this.setGeboortedatum(LocalDate.parse(field[i++]));
		this.setGeslacht(field[i++]);
	}

	public Long getPersoonId() {
		return persoonId;
	}

	public String getAmcgebruikersnaam() {
		return amcgebruikersnaam;
	}

	public void setAmcgebruikersnaam(String amcgebruikersnaam) {
		this.amcgebruikersnaam = amcgebruikersnaam;
	}

	public String getAmcemail() {
		return amcemail;
	}

	public void setAmcemail(String amcemail) {
		this.amcemail = amcemail;
	}

	public String getVoorkeurnaam() {
		return voorkeurnaam;
	}

	public void setVoorkeurnaam(String voorkeurnaam) {
		this.voorkeurnaam = voorkeurnaam;
	}

	public String getTussenvoegsel() {
		return tussenvoegsel;
	}

	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}

	public String getVoorletters() {
		return voorletters;
	}

	public void setVoorletters(String voorletters) {
		this.voorletters = voorletters;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getRoepnaam() {
		return roepnaam;
	}

	public void setRoepnaam(String roepnaam) {
		this.roepnaam = roepnaam;
	}

	public LocalDate getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(LocalDate geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	public String getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(String geslacht) {
		this.geslacht = geslacht;
	}

	public String getFullName() {
		return voorkeurnaam + ", " + voorletters;
	}

	public Set<String> getFirstNames() {
		Set<String> firstNames = new TreeSet<>();

		if (!StringUtils.isEmpty(roepnaam)) {
			firstNames.add(roepnaam);
		}

		if (!StringUtils.isEmpty(voornaam)) {
			firstNames.add(voornaam);
		}

		return firstNames;
	}

	@Override
	public String toString() {
		return "IdsPerson [persoonId=" + persoonId + ", amcgebruikersnaam=" + amcgebruikersnaam + ", amcemail=" + amcemail + ", voorkeurnaam=" + voorkeurnaam + ", tussenvoegsel="
				+ tussenvoegsel + ", voorletters=" + voorletters + ", voornaam=" + voornaam + ", roepnaam=" + roepnaam + ", geboortedatum=" + geboortedatum + ", geslacht="
				+ geslacht + "]";
	}

}
