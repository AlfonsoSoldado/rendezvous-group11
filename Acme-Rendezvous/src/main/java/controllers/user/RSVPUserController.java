
package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RSVPService;
import services.RendezvousService;
import services.UserService;
import controllers.AbstractController;
import domain.RSVP;
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("/RSVP/user")
public class RSVPUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private UserService			userService;

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private RSVPService			rsvpService;


	// Constructors ---------------------------------------------------------

	public RSVPUserController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		RSVP rsvp;

		rsvp = this.rsvpService.create();

		result = this.createModelAndView(rsvp);

		return result;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final RSVP r, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createModelAndView(r);
		else
			try {
				final RSVP saved = this.rsvpService.save(r);
				final User u = this.userService.findByPrincipal();

				//Assert.isTrue(this.userService.is18(u));

				final Collection<RSVP> rsvps = u.getRsvp();
				rsvps.add(saved);
				u.setRsvp(rsvps);
				this.userService.save(u);
				final Rendezvous rendezvous = saved.getRendezvous();
				final Collection<User> att = rendezvous.getAttendant();
				att.add(u);
				rendezvous.setAttendant(att);

				this.rendezvousService.save(rendezvous);

				res = new ModelAndView("redirect:../../rendezvous/list.do");
			} catch (final Throwable oops) {
				res = this.createModelAndView(r, "commit.error");
			}

		return res;
	}
	// Deleting --------------------------------------------------------------

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createModelAndView(final RSVP rsvp) {
		ModelAndView result;

		result = this.createModelAndView(rsvp, null);

		return result;
	}

	protected ModelAndView createModelAndView(final RSVP rsvp, final String message) {
		ModelAndView result;

		final boolean f = Boolean.FALSE;
		final boolean t = Boolean.TRUE;

		final Collection<Boolean> confirmed = new ArrayList<>();
		confirmed.add(f);
		confirmed.add(t);

		final User user = this.userService.findByPrincipal();
		final Collection<Rendezvous> rendezvouses = this.rendezvousService.findAll();
		final Collection<Rendezvous> rendezvousesSol = this.rendezvousService.findByAttendantId(user.getId());

		rendezvouses.removeAll(rendezvousesSol);

		result = new ModelAndView("RSVP/user/create");
		result.addObject("RSVP", rsvp);
		result.addObject("confirmed", confirmed);
		result.addObject("message", message);
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestUri", "RSVP/create.do");

		return result;
	}
}
