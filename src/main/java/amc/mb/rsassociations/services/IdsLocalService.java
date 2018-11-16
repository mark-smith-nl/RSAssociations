package amc.mb.rsassociations.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import amc.mb.rsassociations.domain.IdsPerson;
import amc.mb.rsassociations.persistence.IdsPersonMapper;
import amc.mb.rsassociations.utils.FinalLambdaValue;

@Profile("dev-local")
@Transactional
@Service
@Validated
public class IdsLocalService extends AbstractIdsService {

	private final String idsImportFile;

	public IdsLocalService(IdsPersonMapper idsPersonMapper, @Value("${application-properties.idsImportFile}") String idsImportFile) {
		super(idsPersonMapper);
		this.idsImportFile = idsImportFile;
	}

	// @PostConstruct
	public void importDataFromFile() throws IOException {
		idsPersonMapper.deleteAll();

		Path input = Paths.get(idsImportFile);

		logger.info("Reading ids data from {}.", idsImportFile);
		List<String> lines = Files.readAllLines(input, StandardCharsets.ISO_8859_1);
		lines.remove(0);
		logger.info("Number of records {}.", lines.size());
		Set<IdsPerson> idsPersons = new HashSet<>();
		FinalLambdaValue<Long> rowNumber = new FinalLambdaValue<>(1L);
		lines.forEach(line -> {
			logger.info("Processing line ({}): {}", rowNumber.getValue(), line);
			IdsPerson idsPerson = new IdsPerson(line, rowNumber.getValue());
			if (idsPerson.getAmcgebruikersnaam() != null && !"".equals(idsPerson.getAmcgebruikersnaam().trim())) {
				idsPersons.add(new IdsPerson(line, rowNumber.getValue()));
			}
			rowNumber.setValue(rowNumber.getValue() + 1);
		});

		saveIdsPersons(idsPersons);
	}

	public void saveIdsPersons(@NotNull @Valid Set<IdsPerson> idsPersons) {
		logger.info("Saving {} active (persons with 'amcgebruikersnaam') persons...", idsPersons.size());
		idsPersons.forEach(this::saveIdsPerson);
		logger.info("Saved {} active persons.", idsPersons.size());
	}

	public void saveIdsPerson(@NotNull @Valid IdsPerson idsPerson) {
		// Note: persons without a username are former employees.
		if (idsPerson.getAmcgebruikersnaam() == null || "".equals(idsPerson.getAmcgebruikersnaam().trim())) {
			return;
		}

		idsPersonMapper.insertIdsPerson(idsPerson);
	}

}
