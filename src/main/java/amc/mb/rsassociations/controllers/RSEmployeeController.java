package amc.mb.rsassociations.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import amc.mb.rsassociations.services.ImportSpreadSheetService;
import amc.mb.rsassociations.services.RSEmployeeService;

@Controller
@RequestMapping("/rsEmployees")
public class RSEmployeeController {

	private final static Logger LOGGER = LoggerFactory.getLogger(RSEmployeeController.class);

	private final RSEmployeeService rsEmployeeService;

	private final ImportSpreadSheetService importSpreadSheetService;

	public RSEmployeeController(RSEmployeeService rsEmployeeService, ImportSpreadSheetService importSpreadSheetService) {
		this.rsEmployeeService = rsEmployeeService;
		this.importSpreadSheetService = importSpreadSheetService;
	}

	@GetMapping
	public String getRsEmployees(@RequestParam(required = false, value = "undefined") String menuItem, ModelMap map) {
		map.addAttribute("rsEmployees", rsEmployeeService.getAllRSEmployees(true));
		map.addAttribute("menuItem", menuItem);
		System.out.println(menuItem);
		return "RsEmployees";
	}

	@GetMapping("rsFunctionCouple/{rsFunctionCouple}")
	public String getRsEmployeesForFunctionCouple(@RequestParam String menuItem, @PathVariable String rsFunctionCouple) {
		System.out.println(rsFunctionCouple);

		return "redirect:/rsEmployees";
	}

}
