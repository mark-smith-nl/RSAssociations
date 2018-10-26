package amc.mb.rsassociations.services;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import amc.mb.rsassociations.domain.PrincipalInvestigator;

@Transactional
@Service
@Validated
public class PrincipalInvestigatorService {

	public void savePrincipalInvestigators(@NotNull @Valid Set<PrincipalInvestigator> principalInvestigators) {
		// TODO Auto-generated method stub

	}

}
