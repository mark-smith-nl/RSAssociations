package amc.mb.rsassociations.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import amc.mb.rsassociations.persistence.IdsPersonMapper;

@Profile("!dev-local")
@Transactional
@Service
@Validated
public class IdsService extends AbstractIdsService {

	public IdsService(IdsPersonMapper idsPersonMapper) {
		super(idsPersonMapper);
	}

}
