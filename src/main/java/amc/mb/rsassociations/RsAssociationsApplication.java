package amc.mb.rsassociations;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import amc.mb.rsassociations.services.AbstractIdsService;
import amc.mb.rsassociations.services.ImportSpreadSheetService;

@SpringBootApplication
public class RsAssociationsApplication {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = SpringApplication.run(RsAssociationsApplication.class, args);

		ImportSpreadSheetService importSpreadSheetService = context.getBean(ImportSpreadSheetService.class);

		importSpreadSheetService.importDataFromFile("/home/mark/Bureaublad/rsAssociations/rsAssociations.xlsx");

		// RSEmployeeService rsEmployeeService = context.getBean(RSEmployeeService.class);

		AbstractIdsService idsService = context.getBean(AbstractIdsService.class);

	}

}
