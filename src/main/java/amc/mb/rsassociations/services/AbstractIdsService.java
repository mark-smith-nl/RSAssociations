package amc.mb.rsassociations.services;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import amc.mb.rsassociations.domain.IdsPerson;
import amc.mb.rsassociations.persistence.IdsPersonMapper;

public class AbstractIdsService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected final IdsPersonMapper idsPersonMapper;

	public AbstractIdsService(IdsPersonMapper idsPersonMapper) {
		this.idsPersonMapper = idsPersonMapper;
	}

	public Optional<IdsPerson> getIdsPersonByPersoonId(@NotNull Long persoonId) {
		return Optional.ofNullable(idsPersonMapper.getIdsPersonByPersoonId(persoonId));
	}

	public Optional<IdsPerson> getIdsPersonByEMail(@NotNull @NotEmpty String email) {
		return Optional.ofNullable(idsPersonMapper.getIdsPersonByEMail(email));
	}

}
