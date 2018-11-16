package amc.mb.rsassociations.domain;

import javax.validation.constraints.NotNull;

import amc.mb.rsassociations.enums.RSFunction;
import amc.mb.rsassociations.enums.RSFunctionCouple;

public class RSEmployeeCouple extends ImportRow {

	private final RSFunctionCouple rsFunctionCouple;

	@NotNull
	private final RSEmployee firstRSEmployee;

	@NotNull
	private final RSEmployee secondRSEmployee;

	public RSEmployeeCouple(Long rowNumber, @NotNull RSFunctionCouple rsFunctionCouple, @NotNull RSEmployee firstRSEmployee, @NotNull RSEmployee secondRSEmployee) {
		super(rowNumber);

		this.rsFunctionCouple = rsFunctionCouple;

		checkFunction(firstRSEmployee, rsFunctionCouple.getFirstRSFunction());
		this.firstRSEmployee = firstRSEmployee;

		checkFunction(secondRSEmployee, rsFunctionCouple.getSecondRSFunction());
		this.secondRSEmployee = secondRSEmployee;
	}

	private static void checkFunction(RSEmployee rsEmployee, RSFunction rsFunction) {
		if (!rsEmployee.getRsFunctions().contains(rsFunction)) {
			throw new IllegalArgumentException(String.format("Employee %s does not have the required function (%s).", rsEmployee.getFullName(), rsFunction.name()));
		}
	}

	public RSFunctionCouple getRsFunctionCouple() {
		return rsFunctionCouple;
	}

	public RSEmployee getFirstRSEmployee() {
		return firstRSEmployee;
	}

	public RSEmployee getSecondRSEmployee() {
		return secondRSEmployee;
	}

}
