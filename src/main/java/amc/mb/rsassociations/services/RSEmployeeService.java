package amc.mb.rsassociations.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import amc.mb.rsassociations.domain.HRAdviseurMedewerkerCouple;
import amc.mb.rsassociations.domain.ProjectControllerAdministrateurCouple;
import amc.mb.rsassociations.domain.RSEmployee;
import amc.mb.rsassociations.domain.test.Mariage;
import amc.mb.rsassociations.domain.test.Person;
import amc.mb.rsassociations.enums.RSFunction;
import amc.mb.rsassociations.persistence.RSEmployeeMapper;

@Transactional
@Service
@Validated
public class RSEmployeeService {

	private final RSEmployeeMapper rsEmployeeMapper;

	public RSEmployeeService(RSEmployeeMapper rsEmployeeMapper) {
		this.rsEmployeeMapper = rsEmployeeMapper;
	}

	public void saveRSEmployees(@NotNull @Valid Set<RSEmployee> rsEmployees) {
		rsEmployees.forEach(this::saveRSEmployee);
	}

	public void saveRSEmployee(@NotNull @Valid RSEmployee rsEmployee) {
		rsEmployeeMapper.insertRSEmployee(rsEmployee);
		rsEmployee.getRsFunctions().forEach(rsFunction -> {
			rsEmployeeMapper.insertRSFunctionForEmployee(rsFunction.getTableName(), rsEmployee.getRsEmployeeId());
		});
	}

	public List<RSEmployee> getAllRSEmployees(boolean withRoles) {
		return rsEmployeeMapper.getAllRSEmployees(RSFunction.getFunctionEmployeeLinks(), withRoles);
	}

	public RSEmployee getRSEmployeeById(@NotNull Long rsEmployeeId) {
		return rsEmployeeMapper.getRSEmployeeById(RSFunction.getFunctionEmployeeLinks(), rsEmployeeId);
	}

	public RSEmployee getRSEmployeeByFullName(@NotNull @NotEmpty String fullName) {
		return rsEmployeeMapper.getRSEmployeeByFullName(RSFunction.getFunctionEmployeeLinks(), fullName);
	}

	public Set<RSEmployee> getRSEmployeesWithRole(@NotNull RSFunction rsFunction) {
		return rsEmployeeMapper.getRSEmployeesWithRole(RSFunction.getFunctionEmployeeLinks(), rsFunction);
	}

	public List<Map<String, Object>> getRSEmployeeIdsAndFullNameWithRole(@NotNull RSFunction rsFunction) {
		return rsEmployeeMapper.getRSEmployeeIdsAndFullNameWithRole(rsFunction.getTableName());
	}

	public List<HRAdviseurMedewerkerCouple> getHRAdviseurMedewerkerCouples() {
		return rsEmployeeMapper.getHRAdviseurMedewerkerCouples();
	}

	public void saveProjectControllerAdministrateurCouples(@NotNull @Valid Set<ProjectControllerAdministrateurCouple> controllerAdministrateurCouples) {
		controllerAdministrateurCouples.forEach(this::saveProjectControllerAdministrateurCouple);
	}

	public void saveProjectControllerAdministrateurCouple(@NotNull @Valid ProjectControllerAdministrateurCouple controllerAdministrateurCouple) {
		rsEmployeeMapper.insertProjectControllerAdministrateurCouple(controllerAdministrateurCouple);
	}

	public void saveHRAdviseurMedewerkerCouples(@NotNull @Valid Set<HRAdviseurMedewerkerCouple> adviseurMedewerkerCouples) {
		adviseurMedewerkerCouples.forEach(this::saveHRAdviseurMedewerkerCouple);
	}

	public void saveHRAdviseurMedewerkerCouple(@NotNull @Valid HRAdviseurMedewerkerCouple adviseurMedewerkerCouple) {
		rsEmployeeMapper.insertHRAdviseurMedewerkerCouple(adviseurMedewerkerCouple);
	}

	public void deleteAll() {
		rsEmployeeMapper.deleteAll();
	}

	public void savePerson(Person person) {
		rsEmployeeMapper.savePerson(person);
	}

	public void saveMariage(Person husband, Person wife) {
		rsEmployeeMapper.saveMariage(husband, wife);
	}

	public List<Mariage> getMariages() {
		return rsEmployeeMapper.getMariages();
	}

}
