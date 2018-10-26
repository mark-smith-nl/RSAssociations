package amc.mb.rsassociations.domain;

import javax.validation.constraints.NotNull;

import amc.mb.rsassociations.enums.RSFunction;

public class ProjectControllerAdministrateurCouple extends ExcelRow {

	@NotNull
	private final RSEmployee controller;

	@NotNull
	private final RSEmployee administrateur;

	public ProjectControllerAdministrateurCouple(Long rowNumber, @NotNull RSEmployee controller, @NotNull RSEmployee administrateur) {
		super(rowNumber);

		checkFunction(controller, RSFunction.PROJECTCONTROLLER);
		this.controller = controller;

		checkFunction(administrateur, RSFunction.PROJECTADMINISTRATEUR);
		this.administrateur = administrateur;
	}

	private static void checkFunction(RSEmployee rsEmployee, RSFunction rsFunction) {
		if (!rsEmployee.getRsFunctions().contains(rsFunction)) {
			throw new IllegalArgumentException(String.format("Employee %s does not have the required function (%s).", rsEmployee.getFullName(), rsFunction.name()));
		}
	}

	public RSEmployee getController() {
		return controller;
	}

	public RSEmployee getAdministrateur() {
		return administrateur;
	}

}
