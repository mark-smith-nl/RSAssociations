package amc.mb.rsassociations.persistence;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import amc.mb.rsassociations.domain.IdsPerson;

public interface IdsPersonMapper {

	IdsPerson getIdsPersonByPersoonId(@NotNull Long persoonId);

	IdsPerson getIdsPersonByEMail(@NotNull @NotEmpty String email);

	void insertIdsPerson(IdsPerson idsPerson);

	void deleteAll();

}
