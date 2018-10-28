package amc.mb.rsassociations.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import amc.mb.rsassociations.services.PrincipalInvestigatorService;

@Controller
@RequestMapping("/principalInvestigators")
public class PricipalInvestigatorController {

	private final static Logger LOGGER = LoggerFactory.getLogger(PricipalInvestigatorController.class);

	private final PrincipalInvestigatorService principalInvestigatorService;

	public PricipalInvestigatorController(PrincipalInvestigatorService principalInvestigatorService) {
		this.principalInvestigatorService = principalInvestigatorService;
	}

	@GetMapping
	public String getPricipalInvestigators(@RequestParam(required = false, value = "undefined") String menuItem, ModelMap map) {
		map.addAttribute("principalInvestigators", principalInvestigatorService.getAllPrincipalInvestigators());
		map.addAttribute("menuItem", menuItem);

		return "PrincipalInvestigators";
	}

}
