package amc.mb.rsassociations.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@GetMapping({ "", "/all" })
	public String getRsEmployeeView(ModelMap map) {
		map.addAttribute("rsEmployees", rsEmployeeService.getAllRSEmployees(true));

		return "RsEmployees";
	}
}
