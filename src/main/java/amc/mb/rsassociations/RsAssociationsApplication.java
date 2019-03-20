package amc.mb.rsassociations;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import amc.mb.rsassociations.services.ImportSpreadSheetService;
import amc.mb.rsassociations.services.development.TestService;

@SpringBootApplication
public class RsAssociationsApplication {

	public static void main(String[] args)
			throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ConfigurableApplicationContext context = SpringApplication.run(RsAssociationsApplication.class, args);

		ImportSpreadSheetService importSpreadSheetService = context.getBean(ImportSpreadSheetService.class);

		// importSpreadSheetService.importDataFromFile("/home/mark/Bureaublad/rsAssociations/rsAssociations.xlsx");

		// TestService service = (TestService) context.getBean("testService");

		// service.doItWithArray(null);
		// service.doItWithArray(Arrays.asList("Mark", "Tom", null));

		// System.out.println();
	}

	@Bean(TestService.INNER_TEST_SERVICE_BEAN_NAME)
	public TestService innerTestService() {
		return new TestService(null);
	}

}
