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
