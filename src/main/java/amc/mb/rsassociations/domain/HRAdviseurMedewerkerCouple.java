package amc.mb.rsassociations.domain;

import javax.validation.constraints.NotNull;

import amc.mb.rsassociations.enums.RSFunction;

public class HRAdviseurMedewerkerCouple extends ExcelRow {

	@NotNull
	private final RSEmployee adviseur;

	@NotNull
	private final RSEmployee medewerker;

	public HRAdviseurMedewerkerCouple(Long rowNumber, @NotNull RSEmployee adviseur, @NotNull RSEmployee medewerker) {
		super(rowNumber);

		checkFunction(adviseur, RSFunction.HR_ADVISEUR);
		this.adviseur = adviseur;

		checkFunction(medewerker, RSFunction.HR_MEDEWERKER);
		this.medewerker = medewerker;
	}

	private static void checkFunction(RSEmployee rsEmployee, RSFunction rsFunction) {
		if (!rsEmployee.getRsFunctions().contains(rsFunction)) {
			throw new IllegalArgumentException(String.format("Employee %s does not have the required function (%s).", rsEmployee.getFullName(), rsFunction.name()));
		}
	}

	public RSEmployee getAdviseur() {
		return adviseur;
	}

	public RSEmployee getMedewerker() {
		return medewerker;
	}

}
