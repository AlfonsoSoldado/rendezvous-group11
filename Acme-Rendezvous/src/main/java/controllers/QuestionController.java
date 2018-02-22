package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.QuestionService;
import domain.Question;

@Controller
@RequestMapping("/question")
public class QuestionController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private QuestionService questionService;

	// Constructors ---------------------------------------------------------

	public QuestionController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Question> question;

		question = questionService.findAll();

		result = new ModelAndView("question/list");
		result.addObject("question", question);
		result.addObject("requestURI", "question/list.do");

		return result;
	}

}
