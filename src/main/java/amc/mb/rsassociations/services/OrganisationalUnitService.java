package amc.mb.rsassociations.services;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import amc.mb.rsassociations.domain.Department;
import amc.mb.rsassociations.domain.Division;
import amc.mb.rsassociations.domain.SubDepartment;
import amc.mb.rsassociations.persistence.OrganisationalUnitMapper;

@Transactional
@Service
@Validated
public class OrganisationalUnitService {

	private final OrganisationalUnitMapper organisationalUnitMapper;

	public OrganisationalUnitService(OrganisationalUnitMapper organisationalUnitMapper) {
		this.organisationalUnitMapper = organisationalUnitMapper;
	}

	public void deleteAll() {
		organisationalUnitMapper.deleteAll();
	}

	public void saveOrganisationalUnits(@NotNull @NotEmpty List<Division> divisions, @NotNull @NotEmpty @Valid List<Department> departments,
			@NotNull @Valid List<SubDepartment> subDepartments) {
		divisions.forEach(this::saveDivision);
		departments.forEach(this::saveDepartment);
		subDepartments.forEach(this::saveSubDepartment);

	}

	public void saveDivision(Division division) {
		organisationalUnitMapper.saveDivision(division);
	}

	public void saveDepartment(Department department) {
		organisationalUnitMapper.saveDepartment(department);
	}

	public void saveSubDepartment(SubDepartment subDepartment) {
		organisationalUnitMapper.saveSubDepartment(subDepartment);
	}
}
