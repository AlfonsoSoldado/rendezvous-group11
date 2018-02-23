package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvousService;
import services.UserService;
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("/rendezvous")
public class RendezvousController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private UserService userService;
	
	@Autowired
	private RendezvousService rendezvousService;

	// Constructors ---------------------------------------------------------

	public RendezvousController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Rendezvous> rendezvous;

		rendezvous = rendezvousService.findAll();

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvous", rendezvous);
		result.addObject("requestURI", "rendezvous/list.do");

		return result;
	}
	
	@RequestMapping(value = "/listByUser", method = RequestMethod.GET)
	public ModelAndView listByUser(@RequestParam final int userId) {
		ModelAndView result;
		Collection<Rendezvous> rendezvous;

		User user;
		user = userService.findOne(userId);
		
		rendezvous = user.getRendezvous();

		result = new ModelAndView("rendezvous/listByUser");
		result.addObject("rendezvous", rendezvous);
		result.addObject("requestURI", "rendezvous/listByUser.do");

		return result;
	}
	
	@RequestMapping(value = "/listSimilar", method = RequestMethod.GET)
	public ModelAndView listSimilar(@RequestParam final int rendezvousId) {
		ModelAndView result;
		Collection<Rendezvous> rendezvous;
		Rendezvous rv = rendezvousService.findOne(rendezvousId);

		rendezvous = rv.getSimilar();

		result = new ModelAndView("rendezvous/listSimilar");
		result.addObject("rendezvous", rendezvous);
		result.addObject("requestURI", "rendezvous/listSimilar.do");

		return result;
	}

}
