package amc.mb.rsassociations.persistence;

import java.util.List;

import javax.validation.constraints.NotNull;

import amc.mb.rsassociations.domain.PrincipalInvestigator;

public interface PrincipalInvestigatorMapper {

	void insertPrincipalInvestigator(@NotNull PrincipalInvestigator principalInvestigator);

	List<PrincipalInvestigator> getAllPrincipalInvestigators();

	void deleteAll();

}
