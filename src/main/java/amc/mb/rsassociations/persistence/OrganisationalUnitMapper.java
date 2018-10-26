package amc.mb.rsassociations.persistence;

import amc.mb.rsassociations.domain.Department;
import amc.mb.rsassociations.domain.Division;
import amc.mb.rsassociations.domain.SubDepartment;

public interface OrganisationalUnitMapper {

	void saveDivision(Division division);

	void saveDepartment(Department department);

	void saveSubDepartment(SubDepartment subDepartment);

	void deleteAll();

}
