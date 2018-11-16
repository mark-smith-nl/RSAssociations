package amc.mb.rsassociations.services;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import amc.mb.rsassociations.domain.RSEmployee;
import amc.mb.rsassociations.domain.RSEmployeeCouple;
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
			rsEmployeeMapper.insertRSFunctionForEmployee(rsFunction.getTableName(), rsEmployee.getPersoonId());
		});
	}

	public List<RSEmployee> getAllRSEmployees(boolean withRoles) {
		return rsEmployeeMapper.getAllRSEmployees(RSFunction.getFunctionEmployeeLinks(), withRoles);
	}

	public RSEmployee getRSEmployeeByPersoonId(@NotNull Long persoonId) {
		return rsEmployeeMapper.getRSEmployeeByPersoonId(RSFunction.getFunctionEmployeeLinks(), persoonId);
	}

	public Set<RSEmployee> getRSEmployeesWithRole(@NotNull RSFunction rsFunction) {
		return rsEmployeeMapper.getRSEmployeesWithRole(RSFunction.getFunctionEmployeeLinks(), rsFunction);
	}

	public void saveRSFunctionCouples(@NotNull @Valid Set<RSEmployeeCouple> rsFunctionCouples) {
		rsFunctionCouples.forEach(this::saveRSFunctionCouple);
	}

	public void saveRSFunctionCouple(@NotNull @Valid RSEmployeeCouple rsFunctionCouple) {
		rsEmployeeMapper.insertRSEmployeeCouple(rsFunctionCouple);
	}

	/*
	 * public List<RSEmployeeCouple> getRSEmployeeCouples(@NotNull RSFunctionCouple rsFunctionCouple) { return
	 * rsEmployeeMapper.getRSEmployeeCouples(rsFunctionCouple.getTableName()); }
	 */

	public void deleteAll() {
		rsEmployeeMapper.deleteAll();
	}

}
