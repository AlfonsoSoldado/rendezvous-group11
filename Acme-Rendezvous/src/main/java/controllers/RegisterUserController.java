package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;

@Controller
@RequestMapping("/user")
public class RegisterUserController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private UserService userService;


	// Constructors ---------------------------------------------------------

	public RegisterUserController() {
		super();
	}
	
	// Registering ----------------------------------------------------------
	
	@RequestMapping(value = "/register_User", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView res;
		User user;
		
		user = this.userService.create();
		res = this.createEditModelAndView(user);
		
		return res;
	}
	
	@RequestMapping(value = "/register_User", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final User user,
			final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(user, "actor.params.error");
		} else {
			try {
				this.userService.save(user);
				res = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(user, "actor.commit.error");
			}
		}
		return res;
	}
	
	// Ancillary methods --------------------------------------------------
	
		protected ModelAndView createEditModelAndView(final User user) {
			ModelAndView result;

			result = this.createEditModelAndView(user, null);

			return result;
		}
		
		protected ModelAndView createEditModelAndView(final User user,
				final String message) {
			ModelAndView result;

			result = new ModelAndView("user/register_User");
			result.addObject("user", user);
			result.addObject("message", message);
			
			return result;
		}
}
