package amc.mb.rsassociations.persistence;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import amc.mb.rsassociations.domain.RSEmployee;
import amc.mb.rsassociations.domain.RSEmployeeCouple;
import amc.mb.rsassociations.enums.RSFunction;

public interface RSEmployeeMapper {

	void insertRSEmployee(@NotNull RSEmployee rsEmployee);

	void insertRSFunctionForEmployee(String tableName, Long persoonId);

	void insertRSEmployeeCouple(@NotNull @Valid RSEmployeeCouple rsFunctionCouple);

	List<RSEmployee> getAllRSEmployees(String functionEmployeeLinks, boolean withRoles);

	RSEmployee getRSEmployeeByPersoonId(String functionEmployeeLinks, @NotNull Long persoonId);

	Set<RSEmployee> getRSEmployeesWithRole(String functionEmployeeLinks, @NotNull RSFunction rsFunction);

	/* List<RSEmployeeCouple> getRSEmployeeCouples(String tableName); */

	void deleteAll();

}
