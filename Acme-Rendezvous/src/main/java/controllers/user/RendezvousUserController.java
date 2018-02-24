
package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvousService;
import services.UserService;
import controllers.AbstractController;
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("/rendezvous/user")
public class RendezvousUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private UserService			userService;


	// Constructors ---------------------------------------------------------

	public RendezvousUserController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final String message) {
		ModelAndView result;
		Collection<Rendezvous> rendezvous;

		rendezvous = this.rendezvousService.findAll();

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvous", rendezvous);
		result.addObject("requestURI", "rendezvous/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Rendezvous rendezvous;

		rendezvous = this.rendezvousService.create();
		res = this.createEditModelAndView(rendezvous);

		return res;
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int rendezvousId) {
		ModelAndView result;
		Rendezvous rendezvous;

		rendezvous = this.rendezvousService.findOne(rendezvousId);
		result = this.createEditModelAndView(rendezvous);

		final User own = this.userService.findCreator(rendezvousId);
		final User principal = this.userService.findByPrincipal();

		if (own.equals(principal))
			result = this.createEditModelAndView(rendezvous);
		else
			result = this.list("rendezvous.commit.error");

		return result;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Rendezvous rendezvous, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(rendezvous, "rendezvous.params.error");
		else
			try {
				final Rendezvous saved = this.rendezvousService.save(rendezvous);
				if (rendezvous.getId() == 0) {
					final User user = this.userService.findByPrincipal();
					user.getRendezvous().add(saved);
					this.userService.save(user);
				}
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.println(oops.getCause());
				System.out.println(oops.getMessage());
				System.out.println(oops.getStackTrace());
				res = this.createEditModelAndView(rendezvous, "rendezvous.commit.error");
			}
		return res;
	}

	// Deleting --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Rendezvous rendezvous, final BindingResult binding) {
		ModelAndView res;
		try {
			if (rendezvous.getId() != 0) {
				final User user = this.userService.findByPrincipal();
				user.getRendezvous().remove(rendezvous);
				this.userService.save(user);
				this.rendezvousService.delete(rendezvous);
			}
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			res = this.createEditModelAndView(rendezvous, "rendezvous.commit.error");
		}
		return res;
	}
	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Rendezvous rendezvous) {
		ModelAndView result;

		result = this.createEditModelAndView(rendezvous, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Rendezvous rendezvous, final String message) {
		ModelAndView result;
		final Collection<Boolean> finalModes = new ArrayList<>();

		finalModes.add(false);
		finalModes.add(true);

		result = new ModelAndView("rendezvous/edit");
		result.addObject("finalModes", finalModes);
		result.addObject("rendezvous", rendezvous);
		result.addObject("message", message);
		result.addObject("requestUri", "rendezvous/edit.do");

		return result;
	}
}
