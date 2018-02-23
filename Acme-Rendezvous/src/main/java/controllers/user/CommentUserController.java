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

import services.CommentService;
import services.RendezvousService;
import controllers.AbstractController;
import domain.Comment;
import domain.Rendezvous;

@Controller
@RequestMapping("/comment/user")
public class CommentUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CommentService commentService;

	@Autowired
	private RendezvousService rendezvousService;

	// Constructors ---------------------------------------------------------

	public CommentUserController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Comment> comment;

		comment = commentService.findAll();

		result = new ModelAndView("comment/list");
		result.addObject("comment", comment);
		result.addObject("requestURI", "comment/list.do");

		return result;
	}
	
	@RequestMapping(value = "/listReplies", method = RequestMethod.GET)
	public ModelAndView listReplies(@RequestParam final int commentId) {
		ModelAndView result;
		Collection<Comment> comment;
		
		Comment c = commentService.findOne(commentId);

		comment = c.getReplies();

		result = new ModelAndView("comment/list");
		result.addObject("comment", comment);
		result.addObject("requestURI", "comment/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int rendezvousId) {
		ModelAndView res;
		Comment comment;
		Collection<Comment> comments = new ArrayList<Comment>();
		Rendezvous rendezvous;

		comment = this.commentService.create();
		rendezvous = rendezvousService.findOne(rendezvousId);
		
		comments.addAll(rendezvous.getComment());
		comments.add(comment);
		rendezvous.setComment(comments);
		
		res = this.createEditModelAndView(comment);

		return res;
	}
	
	@RequestMapping(value = "/createReply", method = RequestMethod.GET)
	public ModelAndView createReply(@RequestParam final int commentId) {
		ModelAndView res;
		
		Comment result;
		
		Comment comment;
		Collection<Comment> replies = new ArrayList<Comment>();

		comment = commentService.findOne(commentId);
		
		result = commentService.create();
		
		replies.addAll(comment.getReplies());
		replies.add(result);
		comment.setReplies(replies);
		
		res = this.createEditModelAndView(result);

		return res;
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int commentId) {
		ModelAndView result;
		Comment comment;

		comment = this.commentService.findOne(commentId);
		result = this.createEditModelAndView(comment);

		return result;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Comment comment,
			final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(comment,
					"rendezvous.params.error");
		else
			try {
				this.commentService.save(comment);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				res = this.createEditModelAndView(comment,
						"rendezvous.commit.error");
			}
		return res;
	}

	// Deleting --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Comment comment,
			final BindingResult binding) {
		ModelAndView res;
		try {
			this.commentService.delete(comment);
			res = new ModelAndView("redirect:../../");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(comment, "stage.commit.error");
		}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Comment comment) {
		ModelAndView result;

		result = this.createEditModelAndView(comment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment comment,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("comment/edit");
		result.addObject("comment", comment);
		result.addObject("message", message);
		result.addObject("requestUri", "comment/user/edit.do");

		return result;
	}
}
