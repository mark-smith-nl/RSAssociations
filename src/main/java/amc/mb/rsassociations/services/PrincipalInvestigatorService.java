package amc.mb.rsassociations.services;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import amc.mb.rsassociations.domain.PrincipalInvestigator;
import amc.mb.rsassociations.persistence.PrincipalInvestigatorMapper;

@Transactional
@Service
@Validated
public class PrincipalInvestigatorService {

	private final PrincipalInvestigatorMapper principalInvestigatorMapper;

	public PrincipalInvestigatorService(PrincipalInvestigatorMapper principalInvestigatorMapper) {
		super();
		this.principalInvestigatorMapper = principalInvestigatorMapper;
	}

	public void savePrincipalInvestigators(@NotNull @Valid Set<PrincipalInvestigator> principalInvestigators) {
		principalInvestigators.forEach(this::savePrincipalInvestigator);

	}

	public void savePrincipalInvestigator(@NotNull @Valid PrincipalInvestigator principalInvestigator) {
		principalInvestigatorMapper.insertPrincipalInvestigator(principalInvestigator);
	}

	public List<PrincipalInvestigator> getAllPrincipalInvestigators() {
		return principalInvestigatorMapper.getAllPrincipalInvestigators();
	}

	public void deleteAll() {
		principalInvestigatorMapper.deleteAll();
	}
}
