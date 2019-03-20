package amc.mb.rsassociations.services;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import amc.mb.rsassociations.domain.PrincipalInvestigator;
import amc.mb.rsassociations.persistence.PrincipalInvestigatorMapper;
import amc.mb.rsassociations.services.development.TestService;

@Transactional
@Service
@Validated
public class PrincipalInvestigatorService {

	private final PrincipalInvestigatorMapper principalInvestigatorMapper;

	private final TestService testService;

	public PrincipalInvestigatorService(PrincipalInvestigatorMapper principalInvestigatorMapper, @Qualifier("testService") TestService testService) {
		super();
		this.principalInvestigatorMapper = principalInvestigatorMapper;
		this.testService = testService;
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
