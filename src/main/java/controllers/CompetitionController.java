
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CompetitionService;
import domain.Competition;

@Controller
@RequestMapping("/competition")
public class CompetitionController extends AbstractController {

	//Services
	//=======================================================

	@Autowired
	private CompetitionService	competitionService;


	// Listing
	// ====================================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listCompetitions() {
		ModelAndView result;
		Collection<Competition> competitions;

		competitions = this.competitionService.findAll();

		result = new ModelAndView("competition/list");
		result.addObject("competitions", competitions);
		result.addObject("requestURI", "competition/list.do");

		return result;
	}

	

	// Ancillary Methods
	// ===============================================================================
}
