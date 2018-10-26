package amc.mb.rsassociations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import amc.mb.rsassociations.services.ImportSpreadSheetService;
import amc.mb.rsassociations.services.RSEmployeeService;

@SpringBootApplication
public class RsAssociationsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RsAssociationsApplication.class, args);

		ImportSpreadSheetService importSpreadSheetService = context.getBean(ImportSpreadSheetService.class);

		// importSpreadSheetService.importDataFromFile("/home/mark/Bureaublad/test.xlsx");

		RSEmployeeService rsEmployeeService = context.getBean(RSEmployeeService.class);

		/*
		 * RSEmployee erne = rsEmployeeService.getRSEmployeeByFullName("Erne van Proosdij"); System.out.println(erne); System.out.println();
		 * System.out.println(rsEmployeeService.getRSEmployeeById(erne.getRsEmployeeId())); System.out.println();
		 * rsEmployeeService.getAllRSEmployees(true).forEach(System.out::println); System.out.println();
		 * System.out.println(rsEmployeeService.getRSEmployeeByFullName("Barbara Schreuder")); System.out.println();
		 */
		// System.out.println(rsEmployeeService.getRSEmployeeById(753L));
		// System.out.println();
		// rsEmployeeService.getRSEmployeesWithRole(RSFunction.JURIST_KLINISCH).forEach(System.out::println);
		// System.out.println();
		// erne = rsEmployeeService.test("Erne van Proosdij");
		// System.out.println("===> " + erne);
		// List<HRAdviseurMedewerkerMapping> hrAdviseurMedewerkers = rsEmployeeService.getHRAdviseurMedewerkers();
		// System.out.println(hrAdviseurMedewerkers.size());
		// List<Map<String, Object>> rsEmployeeIdsAndFullNameWithRole = rsEmployeeService.getRSEmployeeIdsAndFullNameWithRole(RSFunction.PROJECTCONTROLLER);

		/*
		 * Person husband = new Person("Sprengers", "Paul"); husband.setBelongings(new HashSet<>(Arrays.asList("Table", "Chair", "Bed"))); rsEmployeeService.savePerson(husband);
		 * Person wife = new Person("Belg", "Veerle"); wife.setBelongings(new HashSet<>(Arrays.asList("House", "Car"))); rsEmployeeService.savePerson(wife); Mariage mariage = new
		 * Mariage(husband, wife); rsEmployeeService.saveMariage(husband, wife);
		 */
		/*
		 * Person husband = new Person("Smith", "Mark"); husband.setBelongings(new HashSet<>(Arrays.asList("Dog", "Goldfish", "Turtle", "Chicken", "Lizzard")));
		 * rsEmployeeService.savePerson(husband); Person wife = new Person("Ringenaldus", "Petra"); wife.setBelongings(new HashSet<>(Arrays.asList("Cat", "Parrot")));
		 * rsEmployeeService.savePerson(wife); Mariage mariage = new Mariage(husband, wife); rsEmployeeService.saveMariage(husband, wife);
		 */

		// List<Mariage> mariages = rsEmployeeService.getMariages();
		// System.out.println(mariages.size());
	}
}
