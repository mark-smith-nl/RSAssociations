package amc.mb.rsassociations.persistence;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.annotations.Param;

import amc.mb.rsassociations.domain.HRAdviseurMedewerkerCouple;
import amc.mb.rsassociations.domain.ProjectControllerAdministrateurCouple;
import amc.mb.rsassociations.domain.RSEmployee;
import amc.mb.rsassociations.domain.test.Mariage;
import amc.mb.rsassociations.domain.test.Person;
import amc.mb.rsassociations.enums.RSFunction;

public interface RSEmployeeMapper {

	void insertRSEmployee(@NotNull RSEmployee rsEmployee);

	void insertRSFunctionForEmployee(String tableName, Long rsEmployeeId);

	void insertProjectControllerAdministrateurCouple(@NotNull @Valid ProjectControllerAdministrateurCouple projectControllerAdministrateurCouple);

	void insertHRAdviseurMedewerkerCouple(@NotNull @Valid HRAdviseurMedewerkerCouple adviseurMedewerkerCouple);

	List<RSEmployee> getAllRSEmployees(String functionEmployeeLinks, boolean withRoles);

	RSEmployee getRSEmployeeById(String functionEmployeeLinks, @NotNull Long rsEmployeeId);

	RSEmployee getRSEmployeeByFullName(String functionEmployeeLinks, @NotNull String fullName);

	Set<RSEmployee> getRSEmployeesWithRole(String functionEmployeeLinks, @NotNull RSFunction rsFunction);

	List<Map<String, Object>> getRSEmployeeIdsAndFullNameWithRole(@Param("functionTable") String functionTable);

	List<HRAdviseurMedewerkerCouple> getHRAdviseurMedewerkerCouples();

	void deleteAll();

	void savePerson(Person person);

	void saveMariage(Person husband, Person wife);

	List<Mariage> getMariages();
}
