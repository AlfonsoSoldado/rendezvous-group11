package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.Announcement;
import domain.User;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private UserService userService;

	// Constructors ---------------------------------------------------------

	public UserController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<User> user;

		user = userService.findAll();

		result = new ModelAndView("user/list");
		result.addObject("user", user);
		result.addObject("requestURI", "user/list.do");

		return result;
	}
	
	@RequestMapping(value = "/listCreator", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int rendezvousId) {
		ModelAndView result;
		User user;

		user = userService.findCreator(rendezvousId);

		result = new ModelAndView("user/listCreator");
		result.addObject("user", user);
		result.addObject("requestURI", "user/listCreator.do");

		return result;
	}

}
